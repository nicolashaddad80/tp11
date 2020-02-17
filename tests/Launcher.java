package fr.cnam.tp11.tests;


import fr.cnam.tp11.TextColor;
import fr.cnam.tp11.Tp11DebugOnOFF;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Launcher {

    /*Internal class to manage Execution statistics */

    private Class<?> aClass = null;
    private Method setUp = null;
    private Method tearDown = null;
    private ArrayList<ExecElement> testMethodsExecution = new ArrayList<>();

    public Launcher(String aClassName) {
        try {
            this.loadClass(aClassName);
            this.loadSetupTearDown();
            this.getTestMethods();
        } catch (ClassNotFoundException e) {
            this.aClass = null;
            System.out.println("Aucune Class n'est presente dans le fichier " + aClassName + "\n Veuillez essayer avec un autre afichier");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Launcher mytestLauncher = new Launcher("fr.cnam.tp11.tests." + args[0]);
        mytestLauncher.startTests();
        System.out.println(mytestLauncher.getStatistics());
    }

    /* 0.Load  test class */
    private void loadClass(String fileName) throws ClassNotFoundException {
        this.aClass = Class.forName(fileName);
    }

    /*Load setUp() and tearDown() methods.*/
    private void loadSetupTearDown() {
        //loading Setup method
        try {
            this.setUp = aClass.getMethod("setUp");
        } catch (NoSuchMethodException e) {
            this.setUp = null;
        }
        /*loading tearDown method */
        try {
            this.tearDown = aClass.getMethod("tearDown");
        } catch (NoSuchMethodException e) {
            this.tearDown = null;
        }

    }

    /* Loading public methods */

    private void getTestMethods() {
        //Getting all Methods from the class
        Method[] methods = aClass.getMethods();
        String testRegex = "^test.*";

        /*constructing a list of Methods names*/
        for (Method method : methods)
            if (Pattern.matches(testRegex, method.getName()))
                this.testMethodsExecution.add(new ExecElement(method));

    }

    public void startTests() {

        if (this.aClass != null) {
            if (Tp11DebugOnOFF.DEBUG_ON)
                System.out.println("Starting tests");
            Object myObjectUnderTest;
            try {
                /*Instantiating th test object inorder to invoke its test methods*/
                myObjectUnderTest = this.aClass.newInstance();


                for (ExecElement execElement : this.testMethodsExecution) {

                    if (this.setUp != null)
                        this.setUp.invoke(myObjectUnderTest);
                    try {
                        execElement.getMethod().invoke(myObjectUnderTest);
                    } catch (Failure e) {
                        execElement.setFailure(e);
                        if (Tp11DebugOnOFF.DEBUG_ON)
                            System.out.println(execElement.getMethod().getName() + " : " + e.getCause());
                    } catch (Exception e) {
                        execElement.setFailure(e);
                        if (Tp11DebugOnOFF.DEBUG_ON)
                            System.out.println(execElement.getMethod().getName() + " : " + e.getCause());
                    }

                    if (this.tearDown != null)
                        this.tearDown.invoke(myObjectUnderTest);
                }

            } catch (InstantiationException e) {
                System.out.println("Issue during Instantiation");
                if (Tp11DebugOnOFF.DEBUG_ON) e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("The Class is not public");
                if (Tp11DebugOnOFF.DEBUG_ON) e.printStackTrace();
            } catch (InvocationTargetException e) {
                if (Tp11DebugOnOFF.DEBUG_ON) e.printStackTrace();
            } finally {

            }

        }
    }

    private String getStatistics() {
        /*Test Results*/
        /*1.Calculating Statistics*/
        int nbTotalTests = this.testMethodsExecution.size();
        int nbPassTests = 0;
        int nbFailTests = 0;
        for (ExecElement execElement : this.testMethodsExecution) {
            if (execElement.getExecStatus())
                nbPassTests++;
            else
                nbFailTests++;
        }

        StringBuilder testsResults = new StringBuilder();

        /*2.Displaying statistics Overview*/
        testsResults.append("================================================\n");
        testsResults.append("=                 Statistics                   =\n");
        testsResults.append("================================================\n");
        testsResults.append("Number of Tests : " + TextColor.BLUE.set + nbTotalTests + TextColor.DEFAULT.set + "\n");
        testsResults.append("Number of PASSED Tests: " + TextColor.GREEN.set + nbPassTests + TextColor.DEFAULT.set + "/" + TextColor.BLUE.set + nbTotalTests + TextColor.DEFAULT.set + "\n");
        testsResults.append("Number of Failed Tests: " + TextColor.RED.set + +nbFailTests + TextColor.DEFAULT.set + "/" + TextColor.BLUE.set + nbTotalTests + TextColor.DEFAULT.set + "\n");

        /*displaying failure cause foe each Failed test*/
        testsResults.append("================================================\n");
        testsResults.append(TextColor.RED.set);
        testsResults.append("                  Failure Causes                \n");

        for (ExecElement execElement : this.testMethodsExecution) {
            if (!execElement.getExecStatus())
                testsResults.append(TextColor.BLUE.set + execElement.getMethod().getName() + " : " + TextColor.RED.set + execElement.getCause() + "\n");
        }
        testsResults.append("================================================\n");
        testsResults.append(TextColor.DEFAULT.set + "\n");
        return testsResults.toString();
    }

    private static final class ExecElement {
        private Method method;
        private Exception failure = null;

        public ExecElement(Method method) {
            this.method = method;
        }

        public Method getMethod() {
            return this.method;
        }

        public Throwable getCause() {
            return this.failure.getCause();
        }

        public void setFailure(Exception failure) {
            this.failure = failure;
        }

        public boolean getExecStatus() {
            return this.failure == null;
        }
    }
}

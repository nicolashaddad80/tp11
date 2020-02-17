package fr.cnam.tp11.tests;



import fr.cnam.tp11.Tp11DebugOnOFF;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Launcher {
    /*
   //TODO===========================
    TODO complete this class

    3.Create ExecElement internal class
    . Clearte ArrayList of ExcElements
    Change loading of testMethods to use ExecElements Array List
    4. Display statisc
    Display allSatatus.
    5
    */
    //TODO===========================
    private Class<?> aClass = null;
    private Method setUp = null;
    private Method tearDown = null;
    private ArrayList<Method> testMethods = new ArrayList<>();

    public Launcher(String aClassName) {

        if (this.loadClass(aClassName)) {
            this.loadSetupTearDown();
            this.getTestMethods();
        }
    }


    /* 0.Load  test class */
    private boolean loadClass(String fileName) {
        //To Clean Remove Bolean return and just check if the class != NULL
        Boolean loadSuccess = false;
        //loading class
        try {
            this.aClass = Class.forName(fileName);
            loadSuccess = true;
        } catch (ClassNotFoundException e) {
            System.out.println("Aucune Class n'est presente dans le fichier " + fileName + "\n Veuillez essayer avec un autre afichier");
        }
        return loadSuccess;
    }

    /*1. Load setUp() and tearDown() methods.*/
    private void loadSetupTearDown() {
        //loading Setup method
        try {
            this.setUp = aClass.getMethod("setUp");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //loading tearDown method
        try {
            this.tearDown = aClass.getMethod("tearDown");
        } catch (NoSuchMethodException e) {
            //Todo Ignore but don't do it when executing tests.
            e.printStackTrace();
        }

    }

    /* Loading public methods */

    private void getTestMethods() {
        //Getting all Methods from the class
        Method[] methods = aClass.getMethods();
        String testRegex = "^test.*";

        //constructing a list of Methods names
        for (Method method : methods)
            if (Pattern.matches(testRegex, method.getName()))
                this.testMethods.add(method);

    }

    public void startTests() {

        if (this.aClass != null) {
            if (Tp11DebugOnOFF.DEBUG_ON)
                System.out.println("Starting tests");
            Object myObjectUnderTest;
            try {
                //Instantiating th test object inorder to invoke its test methods
                myObjectUnderTest = this.aClass.newInstance();


                for (Method testMethod : this.testMethods) {

                    if (this.setUp != null)
                        this.setUp.invoke(myObjectUnderTest);
                    //TODO Update Statistics
                    try {
                        testMethod.invoke(myObjectUnderTest);
                    } catch (Failure e) {
                        System.out.println(testMethod.getName() + " :Functional failure : ");
                    } catch (Exception e) {
                        System.out.println(testMethod.getName() + " :Runtime failure : " + e.getCause());
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
                e.printStackTrace();
            } finally {
                //TODO Display statistics
            }

        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new Launcher("fr.cnam.tp11.tests." + args[0]).startTests();
    }

}

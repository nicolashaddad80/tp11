package fr.cnam.tp11.tests;

import fr.cnam.cour11.DebugOnOFF;
import fr.cnam.tp11.Money;

public class MoneyTest {

    private Money m1;
    private Money m2;

    public void setUp() {
        if (DebugOnOFF.DEBUG_ON) System.out.println("Starting setUp");
        this.m1 = new Money(5, "Euros");
        this.m2 = new Money(7, "Euros");
        if (DebugOnOFF.DEBUG_ON) System.out.println("end of setUp");
    }

    public void testAdd() {
        if (DebugOnOFF.DEBUG_ON) System.out.println("Starting testAdd");
        if (DebugOnOFF.DEBUG_ON) System.out.println(Money.haveSameCurrency(m1,m2));
            m1.add(m2);
        Assert.assertTrue(12 == m1.getAmount());

        if (DebugOnOFF.DEBUG_ON) System.out.println("End of testAdd");
    }

    public void testSub() {
        if (DebugOnOFF.DEBUG_ON) System.out.println("Starting testSub");
        if (DebugOnOFF.DEBUG_ON) System.out.println(Money.haveSameCurrency(m1,m2));
        m1.sub(m2);
        Assert.assertTrue(-2== m1.getAmount());
        if (DebugOnOFF.DEBUG_ON) System.out.println("End of testSub");
    }

    public void tearDown() {
        if (DebugOnOFF.DEBUG_ON) System.out.println("Starting tearDown");
        if (DebugOnOFF.DEBUG_ON) System.out.println("End of tearDown");
    }

    public void otherToTestLancher(){
        //Should not be launched

        assert(false);
    }

    //
}

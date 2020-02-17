package fr.cnam.tp11.tests;

import fr.cnam.tp11.money.Money;
import fr.cnam.tp11.utils.Assert;
import fr.cnam.tp11.utils.Tp11DebugOnOFF;

public class MoneyTest {

    private Money m1;
    private Money m2;
    private Money m3;

    public void setUp() {
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("Starting setUp");
        this.m1 = new Money(5, "Euros");
        this.m2 = new Money(7, "Euros");
        this.m3 = new Money(10, "Dollars");
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("end of setUp");
    }

    public void testAdd() {
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("Starting testAdd");
        m1.add(m2);
        Assert.assertTrue(12 == m1.getAmount());

        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("End of testAdd");
    }

    public void testSub() {
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("Starting testSub");
        m1.sub(m2);
        Assert.assertTrue(-2 == m1.getAmount());
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("End of testSub");
    }

    public void tearDown() {
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("Starting tearDown");
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("End of tearDown");
    }

    public void otherToTestLancher() {
        //Should not be launched
        assert (false);
    }

    public void testAddDollarsToEuros() {
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("Starting testAdd");
        m1.add(m3);
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("End of testAdd");
    }

    public void testSubEurosToDollars() {
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("Starting testSub");
        m3.sub(m2);
        if (Tp11DebugOnOFF.DEBUG_ON) System.out.println("End of testSub");
    }
}

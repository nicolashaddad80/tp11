package fr.cnam.tp11.tests;

import fr.cnam.tp11.Money;

public class MoneyTest {
  
    private Money m1;
    private Money m2;

    public void setUp() {
        this.m1 = new Money(5, "Euros");
        this.m2 = new Money(7, "Euros");
    }

    public void testAdd() {
        m1.add(m2);
        Assert.assertTrue(12 == m1.getAmount());
    }

    public void testSub() {
        m1.sub(m2);
        Assert.assertTrue(-2== m1.getAmount());
    }

    public void tearDown() {

    }
}

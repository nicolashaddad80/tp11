package fr.cnam.tp11.tests;

import fr.cnam.tp11.Money;

public class MoneyTest {

    public static void main(String[] Args) {
        Money Money1 = new Money(5, "Euro");
        Money Money2 = new Money(10, "Euro");
        Money Money3 = new Money(20, "Dollars");

        Money1.add(Money2);
        Money1.add(Money3);
    }
}

package fr.cnam.tp11.money;

public class Money {

    private double amount = 0;
    private String currency = "";

    public Money(double a_Amount, String a_Currency) {
        this.amount = a_Amount;
        this.currency = a_Currency;
    }

    private static boolean haveSameCurrency(Money a_Money1, Money a_Money2) {
        return a_Money1.getCurrency().equals(a_Money2.getCurrency());
    }

    public void add(Money a_Money) {

        if (Money.haveSameCurrency(a_Money, this)) {
            this.amount += a_Money.getAmount();
        } else {
            throw new InvalidCurrencyException();
        }

    }

    public void sub(Money a_Money) {

        if (Money.haveSameCurrency(a_Money, this)) {
            this.amount -= a_Money.getAmount();
        } else {
            throw new InvalidCurrencyException();
        }
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

}

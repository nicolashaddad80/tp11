package fr.cnam.tp11.money;

public class InvalidCurrencyException extends IllegalArgumentException {

    public InvalidCurrencyException() {
        super("Can not add a Money which has different Currency");
    }

}

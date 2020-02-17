package fr.cnam.tp11.utils;

public final class Assert {

    /*Put constructor to private inorder to avoid invoking it*/
    private Assert() {
    }

    public static final void assertTrue(Boolean status) {
        if (!status)
            throw new Failure("Functional failure");
    }
}

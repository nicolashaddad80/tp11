package fr.cnam.tp11.tests;

import fr.cnam.tp11.Launcher;
import fr.cnam.tp11.LauncherImpl;

public class LauncherTestMain {

    public static void main(String[] args) {
        Launcher mytestLauncher = new LauncherImpl("fr.cnam.tp11.tests." + args[0]);
        mytestLauncher.startTests();
        System.out.println(mytestLauncher.getStatistics());
    }
}

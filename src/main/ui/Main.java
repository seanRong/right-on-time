package ui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // make window object
        GUI gui = new GUI();
        gui.init(); // init all our things!

        // set window object size
        gui.setSize(800, 450);
        gui.setTitle("Title");
        gui.setVisible(true);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

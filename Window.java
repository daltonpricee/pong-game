package com.main;
import javax.swing.*;
/**
 * *Main class with JFrame for pong game.
 * @author Dalton Price
 * @version 6/10/20
 */

public class Window {
    /**
     * *Constructor for Window Object.
     * @param title the title for frame.
     * @param game the game object.
     */
    public Window(String title, Game game) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}

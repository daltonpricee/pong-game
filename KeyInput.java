package com.main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyInput class to allow users to move paddles.
 */
public class KeyInput extends KeyAdapter {

    //instance variables
    private Paddle p1;
    private boolean up1 = false;
    private boolean down1 = false;

    private Paddle p2;
    private boolean up2 = false;
    private boolean down2 = false;

    /**
     * Constructor for KeyInput.
     * @param pd1 paddle 1.
     * @param pd2 paddle 2.
     */
    public KeyInput(Paddle pd1, Paddle pd2) {
        p1 = pd1;
        p2 = pd2;
    }

    /**
     * keyPressed() perform action based off which key is pressed.
     * @param e the KeyEvent object.
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //left side key actions up and down.
        if (key == KeyEvent.VK_UP) {
            p2.switchDirection(-1);
            up2 = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            p2.switchDirection(1);
            down2 = true;
        }
        //right side key actions up and down.
        if (key == KeyEvent.VK_W) {
            p1.switchDirection(-1);
            up1 = true;
        }
        if (key == KeyEvent.VK_S) {
            p1.switchDirection(1);
            down1 = true;
        }
    }

    /**
     * keyReleased() check if key is released. Prevents game lag.
     * @param e KeyEvent object.
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            up2 = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            down2 = false;
        }
        if (key == KeyEvent.VK_W) {
            up1 = false;
        }
        if (key == KeyEvent.VK_S) {
            down1 = false;
        }
        if (!up1 && !down1){
            p1.stop();
        }
        if (!up2 && !down2){
            p2.stop();
        }
    }
}

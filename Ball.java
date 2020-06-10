package com.main;
import java.awt.*;

/**
 * Ball class for ball object.
 */
public class Ball {

    //instance variables
    public static final int SIZE = 16;
    private int x, y;
    private int xVel, yVel; //either 1 or -1
    private int speed = 5;

    /**
     * Constructor for Ball class.
     */
    public Ball() {
        reset();
    }

    /**
     * reset() reset the position of objects.
     */
    private void reset() {
        //initial position
        x = Game.WIDTH / 2 - SIZE / 2;
        y = Game.HEIGHT / 2 - SIZE / 2;

        //initial velo
        xVel = Game.sign(Math.random() * 2.0 - 1);
        yVel = Game.sign(Math.random() * 2.0 - 1);
    }

    /**
     * changeYDir() change ball course based off collision.
     */
    public void changeYDir() {
        yVel *= -1;
    }

    /**
     * changeXDir() change ball course based off collision.
     */
    public void changeXDir() {
        xVel *= -1;
    }

    /**
     * draw() showe graphics.
     * @param g the graphic object.
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, SIZE, SIZE);
    }

    /**
     * update() update paddle position when moved by user.
     * @param p1 paddle 1.
     * @param p2 paddle 2.
     */
    public void update(Paddle p1, Paddle p2) {
        //update movement of paddles
        x += xVel * speed;
        y += yVel * speed;

        //collisions
        if (y + SIZE >= Game.HEIGHT || y <= 0) {
            changeYDir();
        }

        //with walls
        if (x + SIZE >= Game.WIDTH) {
            p1.addPoint();
            reset();
        }

        if (x <= 0) {
            p2.addPoint();
            reset();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}



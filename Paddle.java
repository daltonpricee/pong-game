package com.main;
import java.awt.*;

/**
 * Paddle class to create and use paddles.
 */
public class Paddle {
    //instance variables
    private int x, y;
    private int vel = 0;
    private int speed = 10;
    private int width = 22;
    private int height = 85;
    private int score = 0;
    private Color color;
    private boolean left;

    /**
     * Constructor for paddle class.
     * @param c the color object for paddle color.
     * @param left boolean to determine which paddle.
     */
    public Paddle(Color c, boolean left) {
        color = c;
        this.left = left;

        if (left) {
            x = 0;
        }
        else {
            x = Game.WIDTH - width;
            y = Game.HEIGHT / 2 - height / 2;
        }
    }

    /**
     * addPoint() add point to score.
     */
    public void addPoint() {
        score++;
    }

    /**
     * draw() show the graphics.
     * @param g the graphics object.
     */
    public void draw(Graphics g) {
        //draw paddle
        g.setColor(color);
        g.fillRect(x, y, width, height);

        //draw score
        int sx;
        String scoreText = Integer.toString(score);
        Font font = new Font("Roboto", Font.PLAIN, 50);

        int strWidth = g.getFontMetrics(font).stringWidth(scoreText) + 1;
        int padding = 25;

        if(left) {
            sx = Game.WIDTH / 2 - padding - strWidth;
        }
        else {
            sx = Game.WIDTH / 2 + padding;
        }

        g.setFont(font);
        g.drawString(scoreText, sx, 50);

    }

    /**
     * update() update the position of the ball.
     * @param b the ball object.
     */
    public void update(Ball b) {
        //update position only if possible
        y = Game.ensureRange(y += vel, 0, Game.HEIGHT - height);

        int ballx = b.getX();
        int bally = b.getY();

        //collisions with the ball
        if (left) {

            if (ballx <= width && bally + Ball.SIZE >= y && bally <= y+ height) {
                b.changeXDir();
            }
            else {
                if (ballx + Ball.SIZE >= Game.WIDTH && bally + bally + Ball.SIZE >= y && bally <= y + height) {
                    b.changeXDir();
                }
            }

        }
    }

    /**
     * switchDirection() switch direction on collisions.
     * @param direction the direction.
     */
    public void switchDirection(int direction) {
        vel = speed * direction;
    }

    /**
     * stop() stop the ball from moving.
     */
    public void stop() {
        vel = 0;
    }
}

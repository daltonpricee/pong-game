package com.main;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Game class to handle game objects and gameplay.
 */
public class Game extends Canvas implements Runnable {
    //instance variables
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH * 9/16;
    public boolean running = false;
    private Thread gameThread;

    //game objects
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;

    /**
     * Constructor for Game class to set up game.
     */
    public Game() {
        canvasSetup();
        initialize();
        new Window("Pong game", this);
        this.addKeyListener(new KeyInput(paddle1, paddle2));
        this.setFocusable(true);
    }

    /**
     * initialize() to create ball and paddle objects.
     */
    private void initialize() {
        //initial ball
        ball = new Ball();

        //intiialize paddle
        paddle1 = new Paddle(Color.green, true);
        paddle2 = new Paddle(Color.red, false);
    }

    /**
     * canvasSetup() sets up layout and canvas restrictions.
     */
    private void canvasSetup() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    /**
     * run() overrides inherited run() method of Runnable.
     */
    public void run() {
        this.requestFocus();

        //game timer
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) draw();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    /**
     * draw() to show created objects and graphics.
     */
    private void draw() {
        //initalize drawing tools
        BufferStrategy buffer = this.getBufferStrategy();

        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = buffer.getDrawGraphics();

        //draw background
        drawBackground(g);

        //draw paddles and score
        paddle1.draw(g);
        paddle2.draw(g);

        //draw ball
        ball.draw(g);

        //dispose  ACTUALLY draw put on screen
        g.dispose();
        buffer.show();
    }

    /**
     * drawBackground() creates the background color and border for game.
     * @param g the graphics object.
     */
    private void drawBackground(Graphics g) {
        //black background
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);

        //dotted line
        g.setColor(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
    }

    /**
     * sign() assists with angles and gameplay.
     * @param d, int of either -1, 0 or 1 based off game.
     * @return 1, integer based of angle and gameplay.
     */
    public static int sign(double d) {

        if (d <= 0) {
            return -1;
        }
        return 1;
    }

    /**
     * update() updates the ball and paddle position when moved by user.
     */
    private void update() {
        //update ball
        ball.update(paddle1, paddle2);

        //update paddles
        paddle1.update(ball);
        paddle2.update(ball);
    }

    /**
     * start() starts the game.
     */
    public void start() {

        gameThread = new Thread(this);
        gameThread.start();
        running = true;

    }

    /**
     * stop() stops the game.
     */
    public void stop() {
        try {
            gameThread.join();
            running = false;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ensureRange() makes sure the game objects are in range of window.
     * @param val the value to check.
     * @param min the minimun value val can be.
     * @param max the maximum value val can be.
     * @return int, the game restraints.
     */
    public static int ensureRange(int val, int min, int max) {
        return Math.min(Math.max(val, min), max);
    }

    /**
     * Main method to start and play game.
     * @param args null.
     */
    public static void main(String[] args) {
        new Game();
    }
}

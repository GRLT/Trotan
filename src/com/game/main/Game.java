package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.nio.file.FileSystem;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = -1L;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    public static BufferedImage sprite_sheet;


    public Game() throws IOException {
        handler = new Handler();

        new Window(WIDTH, HEIGHT, "Trotan", this);
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("C:\\Code\\Projects\\Trotan\\assets\\tileSet.png");

        handler.addObject(new Player(0,0,ID.Player));

    }

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 10;
    public static void main(String[] args) throws IOException {
        new Game();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // GAME LOOP
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
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
    }




}

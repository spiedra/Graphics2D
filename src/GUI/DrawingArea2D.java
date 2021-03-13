/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.ClassThread;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author spiedra
 */
public class DrawingArea2D extends JPanel implements Runnable {

    private ClassThread classThread;
    private Thread thread;
    private Graphics2D graphics2d;
    private BufferedImage bufferedImage;
    private int framesPerSecond;
    private long time;
    private long waiting;

    public DrawingArea2D() {
        super();
        this.setSize(800, 600);
        this.framesPerSecond = 60;
        this.time = 1000 / this.framesPerSecond;
    }

    private void init() {
        this.bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        this.graphics2d = (Graphics2D) this.bufferedImage.getGraphics();
        this.classThread = new ClassThread(0, 0);
        this.classThread.start();
    }

    private void drawBackground() {
        this.graphics2d.setColor(Color.WHITE);
        this.graphics2d.fillRect(0, 0, 800, 600);
    }

    private void drawRectangle() {
        this.classThread.drawRectangle(this.graphics2d);
    }

    private void drawToScreen() {
        this.getGraphics().drawImage(this.bufferedImage, 800, 600, null);
        this.getGraphics().dispose();
    }

    private void calculateRefresh() {
        long start;
        long elapsed;
        start = System.nanoTime();
        this.drawBackground();
        this.drawRectangle();
        this.drawToScreen();
        elapsed = System.nanoTime() - start;
        this.waiting = this.time - elapsed / 1000000;
        if (waiting < 0) {
            this.waiting = 5;
        }
    }

    private void sleepThread() {
        try {
            Thread.sleep(this.waiting);
        } catch (InterruptedException ex) {
            Logger.getLogger(DrawingArea2D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (this.thread == null) {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    @Override
    public void run() {

        while (true) {
            this.calculateRefresh();
            this.sleepThread();
        }
    }
}

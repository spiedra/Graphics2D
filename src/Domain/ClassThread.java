/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author spiedra
 */
public class ClassThread extends Thread {

    private int positionX;
    private int positionY;
    private static final int SIZE = 40;

    public ClassThread(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(this.positionX, this.positionY, SIZE, SIZE);
    }

    private void sleepThread() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
    }

    private void moveRectangle() {
        this.positionX += 1;
        if (this.positionX > 800) {
            this.positionX = 0;
        }
    }

    private void animate() {
        while (true) {
            this.moveRectangle();
            this.sleepThread();
        }
    }
    
    @Override
    public void run(){
        this.animate();
    }
}

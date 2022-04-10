package com.example.snake;

import java.awt.*;
import java.util.Random;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2022/4/10 16:29
 */
public class Rice {
    private int x, y;
    private boolean living = true;

    public static int WIDTH = 20;
    public static int HEIGHT = 20;

    TankFrame frame = null;
    Random random = new Random();
    Rectangle rect = new Rectangle();

    public Rice() {
    }

    public Rice(int x, int y, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.frame = frame;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living) {
            frame.rices.remove(this);
            return;
        }

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        boundsCheck();
    }

    public void collideWith(Rice rice) {

        //用一个rect记录位置
        if(this.living&&rice.isLiving()&&this.rect.intersects(rice.rect)){
            rice.die();
            frame.rices.add(new Rice(random.nextInt(frame.getWidth()-10),random.nextInt(frame.getHeight()-10),frame));
        }
    }

    private void boundsCheck() {
        if (x < 2) {
            x = 2;
        }
        if (y < 30) {
            y = 30;
        }
        if (x > TankFrame.GAME_WIDTH - Snake.WIDTH -2) {
            x = TankFrame.GAME_WIDTH - Snake.WIDTH -2;
        }
        if (y > TankFrame.GAME_HEIGHT - Snake.HEIGHT -2) {
            y = TankFrame.GAME_HEIGHT - Snake.HEIGHT -2;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void die() {
        this.living = false;
    }

    public boolean isLiving() {
        return living;
    }

}


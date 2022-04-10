package com.example.snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2022/1/4 20:39
 */
public class Snake {
    private int x, y;
    private int lastX,lastY;
    private ArrayList<int[]> point;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 15;
    private boolean moving = false;
    private boolean living = true;

    public static int WIDTH = 20;
    public static int HEIGHT = 20;
    public int count = 1;

    TankFrame frame = null;
    Random random = new Random();
    Rectangle rect = new Rectangle();

    public Snake() {
    }

    public Snake(int x, int y, Dir dir, TankFrame frame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.frame = frame;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        point = new ArrayList<>();
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,WIDTH,HEIGHT);
        for(int i = 1;i<count;i++){
            g.fillRect(point.get(point.size()-i)[0],point.get(point.size()-i)[1],WIDTH, HEIGHT);
            if(point.get(point.size()-i)[0]==x&&point.get(point.size()-i)[1]==y){
                this.die();
            }
        }
        g.setColor(c);

        move();
    }

    public void move() {
        if (!living) return;
        if (!moving) return;
        lastX = x;
        lastY = y;
        point.add(new int[]{lastX,lastY});
        if(point.size()>count){
            point.remove(0);
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    public void eat(Rice rice) {

        //用一个rect记录位置
        if(this.living&&rice.isLiving()&&this.rect.intersects(rice.rect)){
            rice.die();
            this.count+=1;
            if(frame.rices.size()<=5){
                frame.rices.add(new Rice(random.nextInt(frame.getWidth()-10),random.nextInt(frame.getHeight()-10),frame));
            }
        }
    }

    private void boundsCheck() {
        if (x < 2) {
            this.die();
        }
        if (y < 30) {
            this.die();
        }
        if (x > TankFrame.GAME_WIDTH - Snake.WIDTH -2) {
            this.die();
        }
        if (y > TankFrame.GAME_HEIGHT - Snake.HEIGHT -2) {
            this.die();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void die() {
        this.living = false;
    }

    public boolean isLiving() {
        return living;
    }

}

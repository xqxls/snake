package com.example.snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2022/1/4 19:05
 */
public class TankFrame extends Frame {

    Snake mySnake = new Snake(GAME_WIDTH/2, GAME_HEIGHT/2, Dir.UP,this);
    List<Rice> rices=new ArrayList<>();

    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;


    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("snake game");
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new MyKeyListener());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        if(this.mySnake.isLiving()==false){
            Font font = new Font("微软雅黑",0,100);
            g.setFont(font);
            g.drawString("GAME OVER", GAME_WIDTH/4, GAME_HEIGHT/2);

        }
        else{
            g.drawString("rices:" + rices.size(), 10, 80);
        }

        g.setColor(c);

        mySnake.paint(g);

        for (int i = 0; i <rices.size() ; i++) {
            rices.get(i).paint(g);
        }

        for (int i = 0; i <rices.size() ; i++) {
            for (int j = i+1; j <rices.size() ; j++) {
                rices.get(i).collideWith(rices.get(j));
            }
        }
        for (int i = 0; i <rices.size() ; i++) {
            mySnake.eat(rices.get(i));
        }

    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    bR = false;
                    bU = false;
                    bD = false;
                    setSnakeDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bL = false;
                    bR = true;
                    bU = false;
                    bD = false;
                    setSnakeDir();
                    break;
                case KeyEvent.VK_UP:
                    bL = false;
                    bR = false;
                    bU = true;
                    bD = false;
                    setSnakeDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bL = false;
                    bR = false;
                    bU = false;
                    bD = true;
                    setSnakeDir();
                    break;
                default:
                    break;
            }
//            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    setSnakeDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    setSnakeDir();
                    break;
                case KeyEvent.VK_UP:
                    setSnakeDir();
                    break;
                case KeyEvent.VK_DOWN:
                    setSnakeDir();
                    break;
                case KeyEvent.VK_CONTROL:
                    break;
                default:
                    break;
            }
//            repaint();
        }

        private void setSnakeDir() {

            mySnake.setMoving(true);
            if (bL)
                mySnake.setDir(Dir.LEFT);
            if (bU)
                mySnake.setDir(Dir.UP);
            if (bR)
                mySnake.setDir(Dir.RIGHT);
            if (bD)
                mySnake.setDir(Dir.DOWN);

        }
    }


}

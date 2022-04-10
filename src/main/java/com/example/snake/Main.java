package com.example.snake;

import java.util.Random;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2022/1/4 18:53
 */
public class Main {
    public static void main(String[] args) throws InterruptedException{
        TankFrame frame=new TankFrame();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            frame.rices.add(new Rice(random.nextInt(frame.getWidth()-10),random.nextInt(frame.getHeight()-10),frame));
        }

        frame.mySnake.setMoving(true);

        while(true){
            Thread.sleep(50);
            frame.repaint();
        }
    }
}

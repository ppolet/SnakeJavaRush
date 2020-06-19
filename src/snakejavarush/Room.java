//// https://javarush.ru/quests/lectures/questmultithreading.level02.lecture18

package snakejavarush;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Room {

    private int width, height;
    private Snake snake;
    private Mouse mouse;

    public static Room game;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
    
    public Room(int width, int height, Snake snake){
        this.width = width;
        this.height = height;
        this.snake = snake;
        
    }
    
    public void createMouse(){
        int x = (int)(Math.random()*width);
        int y = (int)(Math.random()*height);
        mouse = new Mouse(x, y);
    }
    
    public void eatMouse(){
        createMouse();
    }
    
    public void sleepG(){
        int millisSleep = 500;
        int millisSleepMin = 200;
        int snakeSize = snake.sections.size();
        
        if (snakeSize > 15) millisSleep = millisSleepMin;
        else {
            millisSleep = millisSleep - (millisSleep - millisSleepMin)*snakeSize/15;
        }
        
        try {
            Thread.sleep(millisSleep);
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException: " + ex.getMessage());
        }
    }
    
    public void run(){
        game.print();
        //game.sleepG();
    }
    
    public void print(){
        int[][] screen = new int[width][height];
        screen[snake.getX()][snake.getY()] = 2; // голова змеи, [0,0]

        if (snake.sections.size() > 1){
            for(int i=1; i<snake.sections.size(); i++){
                screen[snake.sections.get(i).getX()][snake.sections.get(i).getY()] = 1; // тело змеи
            }
        }

        screen[game.mouse.getX()][game.mouse.getY()] = 3; // мышь
        
        String [] symb = {".", "x", "X", "+"};
        for(int i=0; i<width; i++){
            for(int j = 0; j<height; j++){
                System.out.print(symb[screen[i][j]]);
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Snake snake = new Snake(11, 10);
        game = new Room(20, 20, snake);
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }
    
}

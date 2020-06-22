//// https://javarush.ru/quests/lectures/questmultithreading.level02.lecture18

package snakejavarush;

import java.awt.event.KeyEvent;

public class Room {

    private int width, height;
    private Snake snake;
    public final int mouseCount = 2;  //Number of mice
    private final Mouse[] mouse = new Mouse[mouseCount];

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

    public Mouse getMouse(int num) {
        return mouse[num];
    }

    public void setMouse(int num, Mouse mouse) {
        this.mouse[num] = mouse;
    }
    
    public Room(int width, int height, Snake snake){
        this.width = width;
        this.height = height;
        this.snake = snake;
        
    }
    
    public void createMouse(int num){
        int x = (int)(Math.random()*width);
        int y = (int)(Math.random()*height);
        game.setMouse(num, new Mouse(x, y));
        //mouse[num] = new Mouse(x, y);
        //setMouse(mouse);
    }
    
    public void eatMouse(){
        createMouse(0);
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
        //наблюдатель за клавиатурой с JavaRush
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();
        
        while(game.snake.isIsAlive()){
            game.print();
            game.sleepG();
            
            //были ли нажатия на клавиатуре
            if (keyboardObserver.hasKeyEvents()){
                KeyEvent ev = keyboardObserver.getEventFromTop();
                
                if (ev.getKeyChar() == 'q' || ev.getKeyChar() == 'Q'){
                    System.out.println("-= EXIT FROM GAME =-");
                    return;
                }
                
                switch (ev.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        snake.setDirection(SnakeDirection.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.setDirection(SnakeDirection.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        snake.setDirection(SnakeDirection.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.setDirection(SnakeDirection.DOWN);
                        break;
                    default:
                        break;
                }
                    
            }
            game.snake.move();
        }
    }
    
    public void print(){
        int[][] screen = new int[width][height];
        
        screen[snake.getX()][snake.getY()] = 2; // голова змеи, [0,0]

        if (snake.sections.size() > 1){
            for(int i=1; i<snake.sections.size(); i++){
                screen[snake.sections.get(i).getX()][snake.sections.get(i).getY()] = 1; // тело змеи
            }
        }

        for (int i = 0; i<game.mouseCount; i++) screen[game.mouse[i].getX()][game.mouse[i].getY()] = 3; // мышь
        
        //отображение игрового поля
        String [] symb = {".", "x", "X", "+"};
        for(int i=0; i<width; i++){
            for(int j = 0; j<height; j++){
                System.out.print(symb[screen[j][i]]);
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println();
    }
    
    public static void main(String[] args) {
        Snake snake = new Snake(10, 10);
        game = new Room(20, 20, snake);
        game.snake.setDirection(SnakeDirection.DOWN);
        for (int i = 0; i<game.mouseCount; i++) game.createMouse(i);
        game.run();
    }
    
}

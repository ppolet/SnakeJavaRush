//// https://javarush.ru/quests/lectures/questmultithreading.level02.lecture18

package snakejavarush;

public class Room {

    private int width, height;
    private Snake snake;
    private Mouse mouse;

    static Room game;
    
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
        Mouse mouse = new Mouse(x, y);
    }
    
    public void eatMouse(){
        createMouse();
    }
    
    public void sleep() throws InterruptedException{
        int millisSleep = 500;
        int millisSleepMin = 200;
        int snakeSize = snake.sections.size();
        
        if (snakeSize > 15) millisSleep = millisSleepMin;
        else {
            millisSleep = millisSleep - (millisSleep - millisSleepMin)*snakeSize/15;
        }
        
        Thread.sleep(millisSleep);
    }
    
    public void run(){
        
    }
    
    public void print(){
        
    }
    
    public static void main(String[] args) {
        Snake snake = new Snake(10, 10);
        Room room = new Room(20, 20, snake);
        snake.direction = SnakeDirection.DOWN;
        
        room.createMouse();
        room.run();
    }
    
}

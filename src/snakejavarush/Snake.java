package snakejavarush;

import java.util.ArrayList;

public class Snake {
    ArrayList<SnakeSection> sections;
    private boolean isAlive;
    SnakeDirection direction;
    
    public Snake(int x, int y){
        sections = new ArrayList<>();
        this.isAlive = true;
        sections.add(new SnakeSection(x, y));
    }
    
    public ArrayList<SnakeSection> getSections(){
        return sections;
    }

    public boolean isIsAlive() {
        return isAlive;
    }
    
    public SnakeDirection getDirection(){
        return direction;
    }
    
    public void setDirection(SnakeDirection direction){
        this.direction = direction;
    }
    
    public int getX(){
        return sections.get(0).getX();
    }
    
    public int getY(){
        return sections.get(0).getY();
    }
    
    public void move(){
        //координаты головы
        int x = getX();
        int y = getY();
        
        //новые координаты головы
        int newX = x;
        int newY = y;
        switch (direction){
            case UP: 
                newY = y - 1;
                break;
            case DOWN: 
                newY = y + 1;
                break;
            case LEFT:
                newX = x - 1;
                break;
            case RIGHT:
                newX = x + 1;
                break;
        }
        
        if (!checkBody(newX, newY)){
            System.out.println("SNAKE DEAD (eat self)!");
            return;
        }
        
        if (!checkBorders(newX, newY)){
            System.out.println("SNAKE DEAD (crash ro border)!");
            return;
        }

        //Проверка, если змея встретила мышь, то съесть ее, иначе просто двигаться
        if (isAlive){
            boolean flagEatMouse = false;
            sections.add(0, new SnakeSection(newX, newY));
            for (int i = 0; i<Room.game.mouseCount; i++){
                if (newX == Room.game.getMouse(i).getX() && newY == Room.game.getMouse(i).getY()){
                    flagEatMouse = true;
                    Room.game.createMouse(i);
                    break;
                    //Room.game.eatMouse();
                }
            }
            if (!flagEatMouse) sections.remove(sections.size()-1); // удаляем последний сегмент хвоста
        }
    }
    
    //Проверка, если змея уперлась в себя
    private boolean checkBody(int newX, int newY){
        for (SnakeSection section : sections) {
            if (newX == section.getX() && newY == section.getY()){
                isAlive = false;
                break;
            }
        }
        return isAlive;
    }
    
    //Проверка, что змея уперлась в стену
    private boolean checkBorders(int newX, int newY){
        if (newX<0 || newX >= Room.game.getWidth() || newY <0 || newY >= Room.game.getHeight()){
            isAlive = false;
        }
        return isAlive;
    }
}

package snakejavarush;

import java.util.ArrayList;

public class Snake {
    ArrayList<SnakeSection> sections;
    boolean isAlive;
    SnakeDirection direction;
    
    public Snake(int x, int y){
        sections = new ArrayList<SnakeSection>();
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
        
    }
}

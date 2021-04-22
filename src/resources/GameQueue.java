package resources;

/*
  helper method for the game picker view to show games
  last updated 04/22/2021
  Author(s) Ian Holder,
 */

import java.util.*;

public class GameQueue<Game> extends AbstractQueue<Game> {
    private LinkedList<Game> elements;

    //constructor
    public GameQueue(){
        this.elements = new LinkedList<>();
    }

    @Override
    public boolean offer(Game _game) {
        //guards invalid inputs
        if(_game == null) {
            return false;
        }
        this.elements.add(_game);
        return true;
    }

    //take the top element out
    @Override
    public Game poll() {
        Iterator<Game> iter = this.elements.iterator();
        Game t = iter.next();
        if(t != null){
            iter.remove();
            return t;
        }
        return null;
    }

    //look at the top element
    @Override
    public Game peek() {
        return this.elements.getFirst();
    }

    //================= GETTERS ===============
    @Override
    public Iterator<Game> iterator() {
        return this.elements.iterator();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }
}

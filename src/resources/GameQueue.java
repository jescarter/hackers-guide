package src.resources;
/**
 * A utility class for the game picker view to show games.
 * last updated 04/06/2021
 * Author(s) Ian Holder,
 */

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class GameQueue<Game> extends AbstractQueue<Game> {
    private LinkedList<Game> elements;

    public GameQueue(){
        this.elements = new LinkedList<Game>();
    }

    @Override
    public Iterator<Game> iterator() {
        return elements.iterator();
    }

    @Override
    public boolean offer(Game _game) {
        if(_game == null) {
            return false;
        }
        elements.add(_game);
        return true;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Game poll() {
        Iterator<Game> iter = elements.iterator();
        Game t = iter.next();
        if(t != null){
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public Game peek() {
        return elements.getFirst();
    }
}

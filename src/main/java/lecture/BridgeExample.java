package lecture;

import java.util.ArrayList;
import java.util.List;

public class BridgeExample {

    abstract class MapSite {
        protected Renderer renderer = new SwingRenderer();
        abstract void draw();
    }

    interface Renderer {
        void draw(String text);
    }

    class ConsoleRenderer implements Renderer {
        @Override
        public void draw(String text) {
            System.out.println("This is console output: "+text);
        }
    }

    class SwingRenderer implements Renderer {
        @Override
        public void draw(String text) {
            System.out.println("This is graphical output: "+text);
        }
    }

    abstract class Composite extends MapSite {
        protected List<MapSite> elements = new ArrayList<>();

        public void add(MapSite element) {
            elements.add(element);
        }

        public void remove(MapSite element) {
            elements.remove(element);
        }

        public MapSite getChild(int x) {
            return elements.get(x);
        }

        @Override
        public void draw() {
            elements.forEach(e -> e.draw());
        }
    }

    class Room extends Composite {

        @Override
        public void draw() {
            renderer.draw("This is a room");
            super.draw();
        }
    }

    class Door extends MapSite {
        @Override
        public void draw() {
            renderer.draw("Door");
        }
    }
    class Wall extends MapSite {
        @Override
        public void draw() {
            renderer.draw("Wall");
        }
    }
    class Maze extends Composite {
        @Override
        public void draw() {
            renderer.draw("Maze");
            super.draw();
        }
    }

    public void demo() {
        Maze maze = new Maze();

        Room room1 = new Room();
        Room room2 = new Room();

        room1.add(new Wall());
        room1.add(new Wall());
        room1.add(new Wall());
        room1.add(new Wall());

        room2.add(new Wall());
        room2.add(new Wall());
        room2.add(new Wall());
        room2.add(new Wall());

        room1.add(new Door());
        room2.add(new Door());

        maze.add(room1);
        maze.add(room2);

        maze.draw();
    }

    public static void main(String[] args) {
        new BridgeExample().demo();
    }
}

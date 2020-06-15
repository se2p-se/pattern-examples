package lecture;


import java.util.ArrayList;
import java.util.List;

public class CompositeExample {

    interface MapSite {
        void draw();
    }

    abstract class Composite implements MapSite {
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
            System.out.println("This is a room: ");
            super.draw();
        }
    }

    class Door implements MapSite {
        @Override
        public void draw() {
            System.out.println("Door");
        }
    }
    class Wall implements MapSite {
        @Override
        public void draw() {
            System.out.println("Wall");
        }
    }
    class Maze extends Composite {
        @Override
        public void draw() {
            System.out.println("Maze:");
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
        new CompositeExample().demo();
    }

}

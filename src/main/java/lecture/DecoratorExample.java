package lecture;

import java.util.ArrayList;
import java.util.List;

public class DecoratorExample {


    interface MapSite {
        void draw();
    }

    abstract class Composite implements MapSite {
        protected List<MapSite> elements = new ArrayList<>();

        public void add(MapSite x) {
            elements.add(x);
        }

        public void remove(MapSite x) {
            elements.remove(x);
        }

        public MapSite getChild(int x) {
            return elements.get(x);
        }

        @Override
        public void draw() {
            elements.forEach(e -> e.draw());
        }
    }

    class Maze extends Composite {
        @Override
        public void draw() {
            System.out.println("Maze consisting of: ");
            elements.forEach(x -> x.draw());
        }
    }
    class Room extends Composite {
        @Override
        public void draw() {
            System.out.println("Room consisting of:");
            elements.forEach(x -> x.draw());
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

    class Decorator implements MapSite {

        protected MapSite wrappedElement;

        public Decorator(MapSite site) {
            wrappedElement = site;
        }

        @Override
        public void draw() {
            wrappedElement.draw();
        }
    }

    class BombMapSite extends Decorator {

        public BombMapSite(MapSite site) {
            super(site);
        }

        @Override
        public void draw() {
            System.out.println("*************");
            System.out.print("Bomb: ");
            super.draw();
            System.out.println("*************");
        }
    }

    class MagicMapSite extends Decorator {

        public MagicMapSite(MapSite site) {
            super(site);
        }

        @Override
        public void draw() {
            System.out.println("*************");
            System.out.print("Magic: ");
            super.draw();
            System.out.println("*************");
        }
    }

    public void demo() {
        Maze maze = new Maze();
        Room room1 = new Room();
        Room room2 = new Room();
        room1.add(new Wall());
        room1.add(new Wall());
        room1.add(new Wall());
        room1.add(new BombMapSite(new Wall()));
        room2.add(new Wall());
        room2.add(new Wall());
        room2.add(new Wall());
        room2.add(new Wall());
        room1.add(new MagicMapSite(new Door()));
        room2.add(new Door());

        maze.add(room1);
        maze.add(room2);

        maze.draw();
    }

    public static void main(String[] args) {
        new DecoratorExample().demo();
    }
}

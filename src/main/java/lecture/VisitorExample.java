package lecture;

import java.util.ArrayList;
import java.util.List;

public class VisitorExample {

    interface MapSite {
        void draw();
        void accept(Visitor v);
    }

    interface Visitor {
        void visitMaze(Maze m);
        void visitRoom(Room r);
        void visitWall(Wall w);
        void visitDoor(Door d);
    }

    class DrawVisitor implements Visitor {
        @Override
        public void visitMaze(Maze m) {
            System.out.println("Maze:");
        }

        @Override
        public void visitRoom(Room r) {
            System.out.println("Room:");
        }

        @Override
        public void visitWall(Wall w) {
            System.out.println("Wall");
        }

        @Override
        public void visitDoor(Door d) {
            System.out.println("Door");
        }
    }

    class CountVisitor implements Visitor {

        private int numWalls = 0;
        private int numDoors = 0;
        private int numRooms = 0;
        private int numMazes = 0;

        @Override
        public void visitMaze(Maze m) {
            numMazes++;
        }

        @Override
        public void visitRoom(Room r) {
            numRooms++;
        }

        @Override
        public void visitWall(Wall w) {
            numWalls++;
        }

        @Override
        public void visitDoor(Door d) {
            numDoors++;
        }

        public void printStats() {
            System.out.println("Mazes: "+numMazes);
            System.out.println("Rooms: "+numRooms);
            System.out.println("Walls: "+numWalls);
            System.out.println("Doors: "+numDoors);
        }
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

        @Override
        public void accept(Visitor v) {
            elements.forEach(e -> e.accept(v));
        }
    }

    class Room extends Composite {

        @Override
        public void draw() {
            System.out.println("This is a room: ");
            super.draw();
        }

        @Override
        public void accept(Visitor v) {
            v.visitRoom(this);
            super.accept(v);
        }
    }

    class Door implements MapSite {
        @Override
        public void draw() {
            System.out.println("Door");
        }

        @Override
        public void accept(Visitor v) {
            v.visitDoor(this);
        }
    }

    class Wall implements MapSite {
        @Override
        public void draw() {
            System.out.println("Wall");
        }

        @Override
        public void accept(Visitor v) {
            v.visitWall(this);
        }
    }

    class Maze extends Composite {
        @Override
        public void draw() {
            System.out.println("Maze:");
            super.draw();
        }

        @Override
        public void accept(Visitor v) {
            v.visitMaze(this);
            super.accept(v);
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

        // maze.draw();
        DrawVisitor visitor = new DrawVisitor();
        maze.accept(visitor);

        CountVisitor cv = new CountVisitor();
        maze.accept(cv);
        cv.printStats();
    }

    public static void main(String[] args) {
        new VisitorExample().demo();
    }
}

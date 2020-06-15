package lecture;

import java.util.ArrayList;
import java.util.List;

public class PrototypeExample {

    interface Prototype<T extends Prototype> {
        T clone();
    }

    class Room implements Prototype<Room> {
        private String name;
        public Room(String name) {
            this.name = name;
        }

        @Override
        public Room clone() {
            return new Room(name);
        }

        @Override
        public String toString() {
            return "Room: "+name;
        }

    }
    class Door implements Prototype<Door> {
        private String name;
        public Door(String name) {
            this.name = name;
        }

        @Override
        public Door clone() {
            return new Door(name);
        }

        @Override
        public String toString() {
            return "Door: "+name;
        }

    }

    class Maze implements Prototype<Maze> {
        private List<Room> rooms = new ArrayList<>();
        private List<Door> doors = new ArrayList<>();

        public void addRoom(Room room) {
            rooms.add(room);
        }
        public void addDoor(Door door) {
            doors.add(door);
        }

        @Override
        public Maze clone() {
            Maze copy = new Maze();
            doors.forEach(d -> copy.addDoor(d.clone()));
            rooms.forEach(r -> copy.addRoom(r.clone()));
            return copy;
        }

        @Override
        public String toString() {
            return "Maze{" +
                    "rooms=" + rooms +
                    ", doors=" + doors +
                    '}';
        }

    }

    public Maze createMaze() {
        Maze maze = new Maze();
        Room room = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Door door = new Door("Door");
        maze.addRoom(room);
        maze.addRoom(room2);
        maze.addDoor(door);
        return maze;
    }

    public void demo() {
        Maze maze = createMaze();
        Maze maze2 = maze.clone();
        maze2.addRoom(new Room("Added Room"));

        System.out.println(maze);
        System.out.println(maze2);
    }

    public static void main(String[] args) {
        new PrototypeExample().demo();
    }
}

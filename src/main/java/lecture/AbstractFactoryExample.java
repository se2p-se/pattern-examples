package lecture;

public class AbstractFactoryExample {

    interface MapSite {}

    class Room implements MapSite {}
    class Door implements MapSite {}
    class Wall implements MapSite {}

    class Maze {
        public void addRoom(Room room) {
            System.out.println("Adding room of type "+room.getClass());
        }

        public void addDoor(Door door) {
            System.out.println("Adding door of type "+door.getClass());
        }
    }

    class MagicRoom extends Room {}
    class BombedRoom extends Room {}
    class EnchantedDoor extends Door {}

    interface AbstractFactory {
        Maze createMaze();
        Room createRoom();
        Door createDoor();
    }

    class RegularMazeFactory implements AbstractFactory {
        @Override
        public Maze createMaze() {
            return new Maze();
        }

        @Override
        public Room createRoom() {
            return new Room();
        }

        @Override
        public Door createDoor() {
            return new Door();
        }
    }

    class EnchantedMazeFactory implements AbstractFactory {
        @Override
        public Maze createMaze() {
            return new Maze();
        }

        @Override
        public Room createRoom() {
            return new MagicRoom();
        }

        @Override
        public Door createDoor() {
            return new EnchantedDoor();
        }
    }

    class BombedMazeFactory implements AbstractFactory {
        @Override
        public Maze createMaze() {
            return new Maze();
        }

        @Override
        public Room createRoom() {
            return new BombedRoom();
        }

        @Override
        public Door createDoor() {
            return new Door();
        }
    }

    class MazeCreator {
        public Maze createMaze(AbstractFactory factory) {
            Maze maze = factory.createMaze();
            Room room = factory.createRoom();
            Room room2 = factory.createRoom();
            Door door = factory.createDoor();
            maze.addRoom(room);
            maze.addRoom(room2);
            maze.addDoor(door);
            return maze;
        }
    }


    public void demo() {
        MazeCreator creator = new MazeCreator();
        creator.createMaze(new BombedMazeFactory());
    }

    public static void main(String[] args) {
        new AbstractFactoryExample().demo();
    }

}

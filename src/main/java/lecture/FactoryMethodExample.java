package lecture;

public class FactoryMethodExample {
    interface MapSite {}

    class Room implements MapSite {}
    class Door implements MapSite {}
    class Wall implements MapSite {}

    class Maze {
        public void addRoom(Room room) {
            System.out.println("Adding room of type "+room.getClass());
        }

        public void addDoor(Door door) {
            System.out.println("Adding door to maze");
        }
    }

    abstract class MazeCreator {
        protected abstract Room createRoom();

        public Maze createMaze() {
            Maze maze = new Maze();
            Room room = createRoom();
            Room room2 = createRoom();
            Door door = new Door();
            maze.addRoom(room);
            maze.addRoom(room2);
            maze.addDoor(door);
            return maze;
        }
    }

    class RegularMazeCreator extends MazeCreator {
        @Override
        protected Room createRoom() {
            return new Room();
        }
    }

    class MagicRoom extends Room {}

    class MagicMazeCreator extends MazeCreator {
        @Override
        protected Room createRoom() {
            return new MagicRoom();
        }
    }

    public void demo() {
        MazeCreator creator = new MagicMazeCreator();
        creator.createMaze();
    }

    public static void main(String[] args) {
        new FactoryMethodExample().demo();
    }
}

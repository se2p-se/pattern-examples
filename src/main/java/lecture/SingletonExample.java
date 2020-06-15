package lecture;

public class SingletonExample {
    static interface MapSite {}

    static class Room implements MapSite {}
    static class Door implements MapSite {}
    static class Wall implements MapSite {}

    static class Maze {
        public void addRoom(Room room) {
            System.out.println("Adding room of type "+room.getClass());
        }

        public void addDoor(Door door) {
            System.out.println("Adding door to maze");
        }
    }

    static class EnchantedRoom extends Room {}
    static class BombRoom extends Room {}
    static class EnchantedDoor extends Door {}

    interface AbstractFactory {
        Maze createMaze();
        Room createRoom();
        Door createDoor();
    }

    static class EnchantedMazeFactory implements AbstractFactory {

        private static EnchantedMazeFactory instance = null;

        private EnchantedMazeFactory() {}

        public static EnchantedMazeFactory getInstance() {
            if(instance == null) {
                instance = new EnchantedMazeFactory();
            }
            return instance;
        }

        @Override
        public Room createRoom() {
            return new EnchantedRoom();
        }

        @Override
        public Door createDoor() {
            return new EnchantedDoor();
        }

        @Override
        public Maze createMaze() {
            return new Maze();
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
        EnchantedMazeFactory factory = EnchantedMazeFactory.getInstance();
        new MazeCreator().createMaze(factory);
    }

    public static void main(String[] args) {
        new SingletonExample().demo();
    }
}

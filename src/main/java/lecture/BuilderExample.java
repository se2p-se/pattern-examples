package lecture;


public class BuilderExample {
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

    interface Builder {
        Builder addRoom();
        Builder addDoor();
        Maze getProduct();
    }

    class EnchantedBuilder implements Builder {

        private Maze maze = new Maze();

        @Override
        public Builder addRoom() {
            maze.addRoom(new MagicRoom());
            return this;
        }

        @Override
        public Builder addDoor() {
            maze.addDoor(new EnchantedDoor());
            return this;
        }

        @Override
        public Maze getProduct() {
            return maze;
        }
    }

    class BombedBuilder implements Builder {

        private Maze maze = new Maze();

        @Override
        public Builder addRoom() {
            maze.addRoom(new BombedRoom());
            return this;
        }

        @Override
        public Builder addDoor() {
            maze.addDoor(new Door());
            return this;
        }

        @Override
        public Maze getProduct() {
            return maze;
        }
    }

    public void demo() {
        Builder builder = new BombedBuilder();
        Maze maze = builder.addRoom().addRoom().addRoom().addDoor().addDoor().getProduct();
    }

    public static void main(String[] args) {
        new BuilderExample().demo();
    }
}

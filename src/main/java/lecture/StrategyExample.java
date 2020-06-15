package lecture;

import java.util.ArrayList;
import java.util.List;

public class StrategyExample {

    interface MapSite {
        void draw();
    }

    class MazeExplorer {
        private MapSite treasure;
        private Strategy strategy;

        public MazeExplorer(Strategy strategy, MapSite treasure) {
            this.strategy = strategy;
            this.treasure = treasure;
        }

        public void explore(Maze maze) {
            strategy.findTreasureRoom(maze, treasure);
        }
    }

    interface Strategy {
        void findTreasureRoom(Maze maze, MapSite treasure);
    }

    class StrategyA implements Strategy {
        @Override
        public void findTreasureRoom(Maze maze, MapSite treasure) {
            for (int i = 0; i < maze.getSize(); i++) {
                MapSite ms = maze.getChild(i);
                if(ms == treasure) {
                    System.out.println("Found treasure!");
                } else {
                    System.out.println("Meh");
                }
            }
        }
    }

    class StrategyB implements Strategy {
        @Override
        public void findTreasureRoom(Maze maze, MapSite treasure) {
            for(int i = maze.getSize(); i > 0; i--) {
                MapSite ms = maze.getChild(i-1);
                if(ms == treasure) {
                    System.out.println("Found treasure!");
                } else {
                    System.out.println("Meh");
                }
            }
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

        public int getSize() {
            return elements.size();
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

        MazeExplorer explorer = new MazeExplorer(new StrategyB(), room2);
        explorer.explore(maze);

        // maze.draw();
    }

    public static void main(String[] args) {
        new StrategyExample().demo();
    }
}

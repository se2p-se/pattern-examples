package lecture;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdapterExample {


    abstract class MapSite {
        protected Renderer renderer = new AdapterRenderer();
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

    class LegacyOutputDriver {
        public void doTheDrawingOfTheString(String text) {
            System.out.println("Legacy printing of: "+text);
        }
    }

    class AdapterRenderer implements Renderer {

        private LegacyOutputDriver adaptee = new LegacyOutputDriver();

        @Override
        public void draw(String text) {
            adaptee.doTheDrawingOfTheString(text);
        }
    }


    class SwingRenderer implements Renderer {

        private SwingFacade facade = new SwingFacade();

        @Override
        public void draw(String text) {
            facade.drawString(text);
        }
    }

    class SwingFacade {
        public void drawString(String text) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(102, 205, 170));
            panel.setSize(new Dimension(600, 400));
            panel.setLayout(null);

            JLabel label = new JLabel(text);
            label.setBounds(0, 0, 600, 400);
            label.setFont(new Font("Arial", Font.BOLD, 64));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);

            UIManager.put("OptionPane.minimumSize",new Dimension(600, 480));
            JOptionPane.showMessageDialog(null, panel, "You are looking at a labyrinth", JOptionPane.PLAIN_MESSAGE);
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
        new AdapterExample().demo();
    }
}

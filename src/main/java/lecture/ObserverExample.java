package lecture;

import java.util.ArrayList;
import java.util.List;

public class ObserverExample {

    interface Observer {
        void update(Subject s);
    }

    interface Subject {
        void attach(Observer o);
        void detach(Observer o);
        void notifyObservers();
        String getName();
    }

    class Alien implements Subject {
        private String name;
        private List<Observer> observers = new ArrayList<>();

        public Alien(String name) {
            this.name = name;
        }

        @Override
        public void attach(Observer o) {
            observers.add(o);
        }

        @Override
        public void detach(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for(Observer o : observers) {
                o.update(this);
            }
        }

        @Override
        public String getName() {
            return name;
        }

        public void changeName(String newName) {
            this.name = newName;
            notifyObservers();
        }
    }

    class Scientist implements Observer {

        private String name;

        public Scientist(String name) {
            this.name = name;
        }

        @Override
        public void update(Subject s) {
            String newName = s.getName();
            System.out.println(name+": I have been informed about a name change: "+newName);
        }
    }

    public void demo() {
        Scientist observer1 = new Scientist("Pam");
        Scientist observer2 = new Scientist("Joe");

        Alien subject1 = new Alien("ET");
        Alien subject2 = new Alien("Spock");

        subject1.attach(observer1);
        subject2.attach(observer1);

        subject2.attach(observer2);

        subject1.changeName("Yoda");
        subject2.changeName("ALF");
    }

    public static void main(String[] args) {
        new ObserverExample().demo();
    }
}

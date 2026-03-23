abstract class Room {
    String type;
    int beds;
    double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println(type + " Room - Beds: " + beds + ", Price: $" + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single", 1, 50.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double", 2, 90.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite", 3, 150.0);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        SingleRoom single = new SingleRoom();
        DoubleRoom doubleRoom = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("=== Hotel Room Details ===");
        single.displayDetails();
        System.out.println("Available: " + singleAvailability);

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailability);

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailability);
    }
}
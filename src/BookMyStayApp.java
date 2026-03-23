import java.util.HashMap;
import java.util.Map;

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

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        }
    }

    public void displayInventory() {
        System.out.println("=== Current Room Inventory ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        SingleRoom single = new SingleRoom();
        DoubleRoom doubleRoom = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(single.type, 5);
        inventory.addRoomType(doubleRoom.type, 3);
        inventory.addRoomType(suite.type, 2);

        single.displayDetails();
        doubleRoom.displayDetails();
        suite.displayDetails();

        inventory.displayInventory();
    }
}
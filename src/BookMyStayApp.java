import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    String assignedRoomID;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType +
                (assignedRoomID != null ? ", Assigned Room ID: " + assignedRoomID : "");
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

    public boolean allocateRoom(String roomType) {
        int available = getAvailability(roomType);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    public void displayInventory() {
        System.out.println("=== Current Inventory ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms Available: " + entry.getValue());
        }
    }
}

class BookingService {
    private Queue<Reservation> bookingQueue;
    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRoomIDs;

    public BookingService(Queue<Reservation> bookingQueue, RoomInventory inventory) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        allocatedRoomIDs = new HashMap<>();
    }

    private String generateRoomID(String roomType) {
        Set<String> ids = allocatedRoomIDs.getOrDefault(roomType, new HashSet<>());
        int id = 1;
        String roomID;
        do {
            roomID = roomType.substring(0, 2).toUpperCase() + id;
            id++;
        } while (ids.contains(roomID));
        ids.add(roomID);
        allocatedRoomIDs.put(roomType, ids);
        return roomID;
    }

    public void processBookings() {
        System.out.println("=== Processing Booking Requests ===");
        while (!bookingQueue.isEmpty()) {
            Reservation res = bookingQueue.poll();
            if (inventory.allocateRoom(res.roomType)) {
                res.assignedRoomID = generateRoomID(res.roomType);
                System.out.println("Booking Confirmed: " + res);
            } else {
                System.out.println("Booking Failed (No Availability): " + res);
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Reservation> bookingQueue = new LinkedList<>();
        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 2);
        inventory.addRoomType("Suite", 1);

        System.out.print("Enter number of booking requests: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter guest name: ");
            String guest = sc.nextLine();
            System.out.print("Enter requested room type: ");
            String room = sc.nextLine();
            bookingQueue.add(new Reservation(guest, room));
        }

        BookingService bookingService = new BookingService(bookingQueue, inventory);
        bookingService.processBookings();

        inventory.displayInventory();
        sc.close();
    }
}
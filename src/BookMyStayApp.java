import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    String assignedRoomID;
    double basePrice;

    public Reservation(String guestName, String roomType, String assignedRoomID, double basePrice) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.assignedRoomID = assignedRoomID;
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType +
                ", Assigned Room ID: " + assignedRoomID +
                ", Base Price: $" + basePrice;
    }
}

class BookingHistory {
    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation res) {
        history.add(res);
    }

    public void displayAllReservations() {
        System.out.println("=== Booking History ===");
        if (history.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Reservation res : history) {
            System.out.println(res);
        }
    }

    public void displaySummary() {
        System.out.println("=== Booking Summary Report ===");
        Map<String, Integer> roomCounts = new HashMap<>();
        double totalRevenue = 0;
        for (Reservation res : history) {
            roomCounts.put(res.roomType, roomCounts.getOrDefault(res.roomType, 0) + 1);
            totalRevenue += res.basePrice;
        }
        for (String roomType : roomCounts.keySet()) {
            System.out.println(roomType + " Rooms Booked: " + roomCounts.get(roomType));
        }
        System.out.println("Total Revenue: $" + totalRevenue);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingHistory bookingHistory = new BookingHistory();

        System.out.print("Enter number of confirmed reservations to add to history: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Reservation " + (i + 1));
            System.out.print("Guest Name: ");
            String guest = sc.nextLine();
            System.out.print("Room Type: ");
            String roomType = sc.nextLine();
            System.out.print("Assigned Room ID: ");
            String roomID = sc.nextLine();
            System.out.print("Base Price: ");
            double price = sc.nextDouble();
            sc.nextLine(); // consume newline

            Reservation res = new Reservation(guest, roomType, roomID, price);
            bookingHistory.addReservation(res);
        }

        System.out.println("\n--- Display All Reservations ---");
        bookingHistory.displayAllReservations();

        System.out.println("\n--- Display Summary Report ---");
        bookingHistory.displaySummary();

        sc.close();
    }
}
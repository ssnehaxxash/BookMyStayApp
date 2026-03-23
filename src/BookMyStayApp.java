import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    String assignedRoomID;
    double basePrice;

    public Reservation(String guestName, String roomType, double basePrice) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType +
                (assignedRoomID != null ? ", Assigned Room ID: " + assignedRoomID : "") +
                ", Base Price: $" + basePrice;
    }
}

class AddOnService {
    String name;
    double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addServiceToReservation(String reservationID, AddOnService service) {
        reservationServices.computeIfAbsent(reservationID, k -> new ArrayList<>()).add(service);
    }

    public void displayServices(String reservationID) {
        List<AddOnService> services = reservationServices.get(reservationID);
        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services for Reservation ID: " + reservationID);
        } else {
            System.out.println("Add-on Services for Reservation ID: " + reservationID);
            double total = 0;
            for (AddOnService s : services) {
                System.out.println("- " + s);
                total += s.price;
            }
            System.out.println("Total Add-On Cost: $" + total);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Reservation> confirmedReservations = new HashMap<>();
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        System.out.print("Enter number of confirmed reservations: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter guest name: ");
            String guest = sc.nextLine();
            System.out.print("Enter room type: ");
            String roomType = sc.nextLine();
            System.out.print("Enter assigned room ID: ");
            String roomID = sc.nextLine();
            System.out.print("Enter base price for the room: ");
            double price = sc.nextDouble();
            sc.nextLine(); // consume newline

            Reservation res = new Reservation(guest, roomType, price);
            res.assignedRoomID = roomID;
            confirmedReservations.put(roomID, res);
        }

        System.out.println("\nAvailable Add-On Services:");
        System.out.println("1. Breakfast ($10)");
        System.out.println("2. Airport Pickup ($25)");
        System.out.println("3. Spa ($40)");

        for (Reservation res : confirmedReservations.values()) {
            System.out.println("\nEnter add-on selections for " + res.guestName + " (room " + res.assignedRoomID + ")");
            System.out.println("Enter service numbers separated by comma (e.g., 1,3) or 0 for none:");
            String input = sc.nextLine();
            String[] choices = input.split(",");
            for (String choice : choices) {
                switch (choice.trim()) {
                    case "1": serviceManager.addServiceToReservation(res.assignedRoomID, new AddOnService("Breakfast", 10)); break;
                    case "2": serviceManager.addServiceToReservation(res.assignedRoomID, new AddOnService("Airport Pickup", 25)); break;
                    case "3": serviceManager.addServiceToReservation(res.assignedRoomID, new AddOnService("Spa", 40)); break;
                    default: break;
                }
            }
        }

        System.out.println("\n=== Reservations and Add-On Services ===");
        for (String resID : confirmedReservations.keySet()) {
            System.out.println(confirmedReservations.get(resID));
            serviceManager.displayServices(resID);
        }

        sc.close();
    }
}
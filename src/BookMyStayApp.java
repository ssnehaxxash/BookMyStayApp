import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Requested Room: " + roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
    }

    public void displayQueue() {
        System.out.println("=== Current Booking Requests ===");
        for (Reservation r : requestQueue) {
            System.out.println(r);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        System.out.print("Enter number of booking requests: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter guest name: ");
            String guest = sc.nextLine();
            System.out.print("Enter requested room type: ");
            String room = sc.nextLine();
            bookingQueue.addRequest(new Reservation(guest, room));
        }

        bookingQueue.displayQueue();
        sc.close();
    }
}
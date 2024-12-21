import java.util.Scanner;

public class NUAwaitlist {
    private static MinHeap hp;

    /*
     * Main takes input from the user and performs the consequent actions
     */
    public static void main(String args[]) {
        hp = new MinHeap();
        boolean flightReady = false;
        do {
            requests();
            int choice = getRequests();
            switch (choice) {
                case 1:
                    reqUpgrade(hp);
                    break;
                case 2:
                    cancelReq(hp);
                    break;
                case 3:
                    getkPriority();
                    break;
                case 4:
                    flightReady = true;
                    break;
            }
        } while (!flightReady);

    }

    // First prompt. Asks users to choose a Request option.
    private static void requests() {
        System.out.println("-------------------------");
        System.out.println("Request Options:");
        System.out.println("\t 1: Request Upgrade");
        System.out.println("\t 2: Cancel Request");
        System.out.println("\t 3: Print k priority passengers");
        System.out.println("\t 4: Flight Ready. Quit.");
    }

    // Prompts user for an input from 1 to 4
    // Ensures that input is valid, else throw an exception
    private static int getRequests() {
        System.out.println("-------------------------");
        System.out.print("\nInput a number 1-4:");
        Scanner reader = new Scanner(System.in);
        int op;
        boolean valid = false;
        do {
            // ensure that input is an integer
            if (!reader.hasNextInt()) {
                throw new IllegalArgumentException("Invalid input: input must be an integer!");
            }
            op = reader.nextInt();

            // ensure that option is in range
            if (op < 1 || op > 4) {
                throw new IllegalArgumentException("Invalid range: request a number from 1 to 4");
            }
            else {
                valid = true;
            }
        } while (!valid);
        System.out.println("Valid input. You choose option " + op);
        System.out.println("-------------------------");
        return op;
    }

    /*
     * Insert frequent flyer into priority queue (represented as a min-heap).
     * Time is O(log n) because insertHeap takes O(log n).
     * insertHeap puts the Node into the last element of the array and heapify.
     */
    public static void reqUpgrade(MinHeap hp) {
        // TODO Auto-generated method stub
        System.out.println("You have requested an upgrade to your flight.");
        String ffname = getFFinfo();
        ffStatus();
        int status = getRequests();
        int time = hp.numberOfStatus(status);
        hp.insertHeap(hp.putKey(status, time), hp.putValue(ffname));
        System.out.println(ffname + ", we have successfully inputed you into the priority queue.");
        System.out.println("\t Please remember your key!!");
        System.out.println("\t Your key is " + status + ", " + time);
        System.out.println("\n");
    }

    // Prompts user for their name
    // Ensure that name is valid, else throw an exception
    private static String getFFinfo() {
        System.out.print("\nEnter your name: ");
        Scanner reader = new Scanner(System.in);
        String name;
        boolean valid = false;
        do {
            if (!reader.hasNext()) {
                throw new IllegalArgumentException("Invalid name.");
            }
            name = reader.nextLine();
            if (name.equals("")) {
                throw new IllegalArgumentException("Invalid name.");
            }
            else {
                valid = true;
            }
        } while (!valid);
        return name;
    }

    // Lists the possible frequent flyer status
    private static void ffStatus() {
        System.out.println("\nfrequent flyer status:");
        System.out.println("\t 1: Super");
        System.out.println("\t 2: Platinum");
        System.out.println("\t 3: Gold");
        System.out.println("\t 4: Silver");
        System.out.print("What is your frequent flyer status?");
    }

    /*
     * cancelReq removes frequent flyer from queue.
     * Time is O(nlog n) because searching takes at best O(n) and remove takes O(log n).
     */
    public static void cancelReq(MinHeap hp) {
        System.out.println("You have requested to cancel your upgrade.");
        String ffname = getFFinfo();
        hp.deleteNode(ffname);
        System.out.println(ffname + ", we have successfully cancelled your upgrade request.");
    }

    /*
     * Prints k highest priority frequent flyers
     * Time = O(k log n) because it takes O(log n) for removeMin() and
     * we call removeMin() k times.
     */
    private static void getkPriority() {
        System.out.print("\nHow many people do you want to print?");
        Scanner reader = new Scanner(System.in);
        boolean valid = false;
        int k;
        do {
            if (!reader.hasNextInt()) {
                throw new IllegalArgumentException("Not an integer. Enter an integer.");
            }
            k = reader.nextInt();
            if (k > hp.getSize()) {
                valid = false;
            }
            valid = true;
        } while (!valid);
        hp.findkPriority(k);
    }


}


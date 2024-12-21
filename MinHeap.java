public class MinHeap {

    private class Node {
        private Key key;
        private Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private class Key {
        private int status;
        private int time; // tie-breaker, time in wait-list

        public Key(int status, int time) {
            this.status = status;
            this.time = time;
        }
    }

    private class Value {
        private String name;

        public Value(String name) {
            this.name = name;
        }
    }

    // Fields
    private int size;
    private Node[] A;
    private int maxSize = 100;

    /**
     * Initializes an empty minimun heap.
     */
    public MinHeap() {
        A = new Node[maxSize];
    }

    public Node putNode(Key k, Value v) {
        Node n = new Node(k, v);
        return n;
    }

    public Key putKey(int status, int time) {
        Key k = new Key(status, time);
        return k;
    }

    public Value putValue(String name) {
        Value v = new Value(name);
        return v;
    }

    public int getSize() {
        return size;
    }

    /*
     * Resize if the maxSize >= size. Called in insertHeap.
     */
    private void resize(int maxSize) {
        Node[] newA = new Node[maxSize];
        for (int i = 1; i <= maxSize; i++) {
            newA[i] = A[i];
        }
        A = newA;
    }

    /*
     * Insert a node with key k (status, time) and value v (name) into min-Heap A
     *
     */
    public void insertHeap(Key k, Value v) {
        Node person = new Node(k, v);
        size += 1;
        if (size >= maxSize) {
            maxSize = 2 * maxSize;
            resize(maxSize);
        }
        A[size] = person;
        int i = size;
        // while child has a parent and the parent's status is greater than child
        // (min-Heap)
        // swap parent and child node
        while (((i / 2) > 0) && (A[i / 2].key.status > A[i].key.status)) {
            Node temp = A[i / 2];
            A[i / 2] = A[i];
            A[i] = temp;
            i = i / 2;
        }

        // tie-breaker is time in waiting list
        // while parent and child have same status, swap if parent has a greater time
        // (min-Heap)
        while ((i / 2 > 0) && (A[i / 2].key.status == A[i].key.status) && (A[i / 2].key.time
                > A[i].key.time)) {
            Node temp2 = A[i / 2];
            A[i / 2] = A[i];
            A[i] = temp2;
            i = i / 2;
        }

        System.out.println("**for display purposes** Priority Queue is ");
        for (int j = 1; j <= size; j++) {
            System.out.println("\t (" + A[j].key.status + ", " + A[j].key.time + ")");
        }
    }

    /*
     * Search for a node (k,v) in Heap A and return the index of node
     */
    private int searchNode(String name) {
        int i;
        for (i = 1; i <= size; i++) {
            if (A[i].value.name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Delete a node (k,v) from min-Heap and restore heap
     */
    public void deleteNode(String name) {
        int person = searchNode(name);
        A[person] = A[size];
        A[size] = null;
        size = size - 1;
        heapify(person);

        System.out.println("**for display purposes** Priority Queue is ");
        for (int j = 1; j <= size; j++) {
            System.out.println("\t (" + A[j].key.status + ", " + A[j].key.time + ")");
        }
    }

    /*
     * Maintain min-Heap priority: smaller status is above larger status in the heap
     * Super = 1 Platinum = 2 Gold = 3 Silver = 4 if there is a tie between status,
     * swap on time (smaller = waiting longer in the list)
     *
     */
    private void heapify(int i) {
        while (i < size) {
            if ((2 * i + 1) <= size) { // this node has two internal children
                int child1 = 2 * i;
                int child2 = 2 * i + 1;
                if ((A[i].key.status < A[child1].key.status) && (A[i].key.status
                        < A[child2].key.status)) {
                    return; // parent's status is less than 2 children

                }
                else {
                    // find smaller of two children and swap with i
                    int j = smallerOfNodes(child1, child2);
                    Node tmp = A[j];
                    A[j] = A[i];
                    A[i] = tmp;
                    i = j;
                }
            }
            else { // this node has 0 or 1 internal child
                if (2 * i <= size) { // only one child
                    if (A[i].key.status < A[2 * i].key.status) {
                        return;
                    }
                    else {
                        int j = smallerOfNodes(i, 2 * i);
                        Node tmp = A[j];
                        A[j] = A[i];
                        A[i] = tmp;
                        i = j;
                    }
                }
                // no children
                return;
            }
        }
    }

    /*
     * Return smaller of the two nodes based on status and time
     */
    private int smallerOfNodes(int child1, int child2) {
        if (A[child1].key.status < A[child2].key.status) {
            return child1;
        }
        else if (A[child1].key.status == A[child2].key.status) {
            if (A[child1].key.time <= A[child2].key.time) {
                return child1;
            }
            else {
                return child2;
            }
        }
        else {
            return child2;
        }
    }

    /*
     * Remove the root of the Heap and heapify to maintain heap priority
     */
    public Node removeMin() {
        Node tmp = A[1];
        A[1] = A[size];
        size = size - 1;
        heapify(1);
        return tmp;
    }

    /*
     * Prints k highest priority frequent flyers
     * Time = O(k log n) because it takes O(log n) for removeMin() and
     * we call removeMin() k times.
     */
    public void findkPriority(int k) {
        for (int i = 1; i <= k; i++) {
            Node kq = removeMin();
            System.out.println("Name is " + kq.value.name + " and priority was "
                                       + kq.key.status + ", " + kq.key.time);
        }
    }


    private int nSuper; // number of Super, Platinum, Gold, Silver
    private int nPlat;
    private int nGold;
    private int nSilver;

    public int numberOfStatus(int status) {
        int time;
        if (status == 1) {
            nSuper += 1;
            time = nSuper;
        }
        else if (status == 2) {
            nPlat += 1;
            time = nPlat;
        }
        else if (status == 3) {
            nGold += 1;
            time = nGold;
        }
        else {
            nSilver += 1;
            time = nSilver;
        }
        return time;
    }

    public static void main(String[] args) {

    }
}

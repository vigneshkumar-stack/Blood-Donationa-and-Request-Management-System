import java.util.*;

// =====================================================
//   BLOOD DONATION MANAGEMENT SYSTEM — DSA Basics
// =====================================================

public class Main2 {

    // ── Donor (our core data object) ──────────────────
    static class Donor {
        String name, blood, city;

        Donor(String name, String blood, String city) {
            this.name = name;
            this.blood = blood;
            this.city  = city;
        }

        public String toString() {
            return name + " | " + blood + " | " + city;
        }
    }

    // ─────────────────────────────────────────────────
    //  1. LINKED LIST — store donors as a chain of nodes
    // ─────────────────────────────────────────────────
    static class LinkedList {
        static class Node {
            Donor data;
            Node  next;
            Node(Donor d) { this.data = d; }
        }

        Node head;

        // Add donor to end
        void add(Donor d) {
            Node newNode = new Node(d);
            if (head == null) { head = newNode; return; }
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newNode;
        }

        // Print all donors
        void display() {
            Node cur = head;
            int i = 1;
            while (cur != null) {
                System.out.println("  " + i++ + ". " + cur.data);
                cur = cur.next;
            }
        }

        // Convert to array (needed for sorting/searching)
        Donor[] toArray() {
            java.util.List<Donor> list = new java.util.ArrayList<>();
            Node cur = head;
            while (cur != null) { list.add(cur.data); cur = cur.next; }
            return list.toArray(new Donor[0]);
        }
    }

    // ─────────────────────────────────────────────────
    //  2. STACK — track last actions (undo feature)
    // ─────────────────────────────────────────────────
    static class Stack {
        String[] data = new String[10];
        int top = -1;

        void push(String action) {
            if (top < data.length - 1) data[++top] = action;
        }

        String pop() {
            return (top >= 0) ? data[top--] : "Stack is empty";
        }

        void display() {
            System.out.println("  Actions (top to bottom):");
            for (int i = top; i >= 0; i--)
                System.out.println("  [" + i + "] " + data[i]);
        }
    }

    // ─────────────────────────────────────────────────
    //  3. QUEUE — process blood requests in order
    // ─────────────────────────────────────────────────
    static class Queue {
        String[] data = new String[10];
        int front = 0, rear = 0;

        void enqueue(String request) {
            if (rear < data.length) data[rear++] = request;
        }

        String dequeue() {
            return (front < rear) ? data[front++] : "Queue is empty";
        }

        void display() {
            System.out.println("  Pending requests (front to rear):");
            for (int i = front; i < rear; i++)
                System.out.println("  [" + i + "] " + data[i]);
        }
    }

    // ─────────────────────────────────────────────────
    //  4. HASHING — quickly find donors by blood group
    // ─────────────────────────────────────────────────
    static class HashTable {
        HashMap<String, java.util.List<String>> map = new HashMap<>();

        void put(String blood, String donorName) {
            map.putIfAbsent(blood, new java.util.ArrayList<>());
            map.get(blood).add(donorName);
        }

        void search(String blood) {
            java.util.List<String> found = map.getOrDefault(blood, null);
            if (found == null)
                System.out.println("  No donors found for " + blood);
            else
                System.out.println("  Donors with " + blood + ": " + found);
        }

        void display() {
            System.out.println("  Blood Group -> Donors:");
            for (Map.Entry<String, java.util.List<String>> e : map.entrySet())
                System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
    }

    // ─────────────────────────────────────────────────
    //  5. SORTING — Bubble Sort (by name, A to Z)
    // ─────────────────────────────────────────────────
    static void bubbleSort(Donor[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].name.compareTo(arr[j + 1].name) > 0) {
                    Donor tmp = arr[j]; arr[j] = arr[j+1]; arr[j+1] = tmp;
                }
    }

    // ─────────────────────────────────────────────────
    //  6. SEARCHING — Linear & Binary Search
    // ─────────────────────────────────────────────────
    static int linearSearch(Donor[] arr, String blood) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i].blood.equals(blood)) return i;
        return -1;
    }

    // Array must be sorted by name before calling this
    static int binarySearch(Donor[] arr, String name) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = arr[mid].name.compareTo(name);
            if (cmp == 0) return mid;
            else if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    // ─────────────────────────────────────────────────
    //  HELPER — print section headers
    // ─────────────────────────────────────────────────
    static void heading(String title) {
        System.out.println("\n+------------------------------------------+");
        System.out.printf( "|  %-40s|%n", title);
        System.out.println("+------------------------------------------+");
    }

    // ─────────────────────────────────────────────────
    //  MAIN
    // ─────────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("+------------------------------------------+");
        System.out.println("|   BLOOD DONATION SYSTEM  -  DSA Basics   |");
        System.out.println("+------------------------------------------+");

        // Donor data
        Donor d1 = new Donor("Arjun Mehta",  "O+",  "Hyderabad");
        Donor d2 = new Donor("Priya Sharma", "A+",  "Mumbai");
        Donor d3 = new Donor("Rahul Verma",  "B-",  "Delhi");
        Donor d4 = new Donor("Sneha Reddy",  "AB+", "Chennai");
        Donor d5 = new Donor("Karan Patel",  "O-",  "Bangalore");

        // ── 1. LINKED LIST ───────────────────────────
        heading("1. LINKED LIST - Donor Registry");
        LinkedList list = new LinkedList();
        list.add(d1); list.add(d2); list.add(d3);
        list.add(d4); list.add(d5);
        System.out.println("  All donors (linked node by node):");
        list.display();

        // ── 2. STACK ─────────────────────────────────
        heading("2. STACK - Action Log (Undo)");
        Stack stack = new Stack();
        stack.push("Registered: Arjun Mehta");
        stack.push("Request submitted: O+ at AIIMS");
        stack.push("Marked donated: Priya Sharma");
        stack.display();
        System.out.println("\n  Undo last action -> " + stack.pop());
        System.out.println("  After undo:");
        stack.display();

        // ── 3. QUEUE ─────────────────────────────────
        heading("3. QUEUE - Blood Request Processing");
        Queue queue = new Queue();
        queue.enqueue("Amit Roy   | O+  | AIIMS Delhi");
        queue.enqueue("Sunita Rao | A+  | Apollo Hyd");
        queue.enqueue("Kavya Nair | AB+ | Manipal Blr");
        queue.display();
        System.out.println("\n  Processing first request -> " + queue.dequeue());
        System.out.println("  Remaining:");
        queue.display();

        // ── 4. HASHING ───────────────────────────────
        heading("4. HASHING - Find Donor by Blood Group");
        HashTable ht = new HashTable();
        Donor[] all = list.toArray();
        for (Donor d : all) ht.put(d.blood, d.name);
        ht.display();
        System.out.println();
        ht.search("O+");
        ht.search("AB+");
        ht.search("B+");   // not in list

        // ── 5. SORTING ───────────────────────────────
        heading("5. BUBBLE SORT - Donors by Name A to Z");
        Donor[] sorted = list.toArray();
        System.out.println("  Before sorting:");
        for (Donor d : sorted) System.out.println("  - " + d);
        bubbleSort(sorted);
        System.out.println("\n  After Bubble Sort:");
        for (Donor d : sorted) System.out.println("  - " + d);

        // ── 6. SEARCHING ─────────────────────────────
        heading("6. SEARCHING - Linear and Binary Search");

        System.out.println("  Linear Search for blood group 'B-':");
        int li = linearSearch(all, "B-");
        if (li >= 0) System.out.println("  Found -> " + all[li]);
        else         System.out.println("  Not found");

        System.out.println("\n  Binary Search for donor 'Rahul Verma':");
        int bi = binarySearch(sorted, "Rahul Verma");
        if (bi >= 0) System.out.println("  Found -> " + sorted[bi]);
        else         System.out.println("  Not found");

        // ── SUMMARY ──────────────────────────────────
        heading("DSA Topics Used - Quick Summary");
        System.out.println("  Linked List   -> Chain of donor nodes, easy insert");
        System.out.println("  Stack         -> LIFO, tracks actions for undo");
        System.out.println("  Queue         -> FIFO, processes requests in order");
        System.out.println("  Hash Table    -> O(1) lookup by blood group");
        System.out.println("  Bubble Sort   -> Compares neighbors, sorts by name");
        System.out.println("  Linear Search -> Scans each element one by one");
        System.out.println("  Binary Search -> Halves search space each step");

        System.out.println("\n  Done!");
    }
}

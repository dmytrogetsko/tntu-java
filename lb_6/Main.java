import java.util.*;

public class Main {
    public static void main(String[] args) {
        Mammal mammal1 = new Mammal("Lion", 10);
        Mammal mammal2 = new Mammal("Elephant", 25);
        Mammal mammal3 = new Mammal("Tiger", 15);
        Mammal mammal4 = new Mammal("Lion", 10); // Duplicate
        Mammal mammal5 = new Mammal("Elephant", 25); // Duplicate

        // 1. ArrayList
        List<Mammal> mammalsList = new ArrayList<>(Arrays.asList(mammal1, mammal2, mammal3, mammal4, mammal5));
        System.out.println("Unordered List:");
        mammalsList.forEach(System.out::println);

        // 2. HashSet
        Set<Mammal> mammalsSet = new HashSet<>(mammalsList);
        System.out.println("\nUnique Elements in HashSet:");
        mammalsSet.forEach(System.out::println);

        // 3. Comparable, Collections.sort()
        Collections.sort(mammalsList);
        System.out.println("\nSorted List by Age:");
        mammalsList.forEach(System.out::println);

        // 4. TreeSet
        Set<Mammal> mammalsTreeSet = new TreeSet<>(mammalsList);
        System.out.println("\nUnique Ordered Elements in TreeSet:");
        mammalsTreeSet.forEach(System.out::println);

        // 5. Map
        Map<String, Mammal> mammalMap = new TreeMap<>();
        mammalMap.put("Lion", mammal1);
        mammalMap.put("Elephant", mammal2);
        mammalMap.put("Tiger", mammal3);
        mammalMap.put("DuplicateLion", mammal4);
        mammalMap.put("DuplicateElephant", mammal5);

        System.out.println("\nMammal Map:");
        mammalMap.forEach((key, value) -> System.out.println(key + ": " + value));

        // LinkedList
        LinkedList<Mammal> mammalLinkedList = new LinkedList<>(mammalsList);
        System.out.println("\nLinkedList:");
        mammalLinkedList.forEach(System.out::println);

        // ArrayList
        ArrayList<Mammal> mammalArrayList = new ArrayList<>(mammalsList);
        System.out.println("\nArrayList:");
        mammalArrayList.forEach(System.out::println);

        // Queue
        Queue<Mammal> mammalQueue = new LinkedList<>(mammalsList);
        System.out.println("\nQueue:");
        while (!mammalQueue.isEmpty()) {
            System.out.println(mammalQueue.poll());
        }

        // PriorityQueue
        PriorityQueue<Mammal> mammalPriorityQueue = new PriorityQueue<>(mammalsList);
        System.out.println("\nPriorityQueue:");
        while (!mammalPriorityQueue.isEmpty()) {
            System.out.println(mammalPriorityQueue.poll());
        }
    }
}

class Mammal implements Comparable<Mammal> {
    private String name;
    private int age;
    private Owner owner;

    public Mammal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    class Body {
        void show() {
            System.out.println("Showing body parts of " + name);
        }
    }

    public void showBodyParts() {
        Body body = new Body();
        body.show();
    }

    static class Heart {
        void pump() {
            System.out.println("Heart is pumping blood.");
        }
    }

    public void showLocalClass() {
        class LocalClass {
            void display() {
                System.out.println("Local class in action for " + name);
            }
        }

        LocalClass local = new LocalClass();
        local.display();
    }

    @Override
    public String toString() {
        return "Mammal{name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mammal mammal = (Mammal) o;
        return age == mammal.age && name.equals(mammal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Mammal o) {
        return Integer.compare(this.age, o.age);
    }
}

class Owner {
    private String name;

    public Owner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{name='" + name + "'}";
    }
}

class Habitat {
    private String name;
    private List<Mammal> mammals;

    public Habitat(String name) {
        this.name = name;
        this.mammals = new ArrayList<>();
    }

    public void addMammal(Mammal mammal) {
        mammals.add(mammal);
    }

    @Override
    public String toString() {
        return "Habitat{name='" + name + "', mammals=" + mammals + "}";
    }
}

class Zoo {
    private String name;
    private List<Mammal> mammals;

    public Zoo(String name) {
        this.name = name;
        this.mammals = new ArrayList<>();
    }

    public void addMammal(Mammal mammal) {
        mammals.add(mammal);
    }

    @Override
    public String toString() {
        return "Zoo{name='" + name + "', mammals=" + mammals + "}";
    }
}

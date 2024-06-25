public class Main {
    public static void main(String[] args)
    {
        Mammal mammal = new Mammal("Lion", 10);
        Owner owner = new Owner("John Doe");
        Habitat habitat = new Habitat("Savannah");

        // Association
        mammal.setOwner(owner);

        // Aggregation
        habitat.addMammal(mammal);

        // Composition
        Zoo zoo = new Zoo("Ukrainian Zoo");
        zoo.addMammal(new Mammal("Elephant", 25));
        zoo.addMammal(new Mammal("Tiger", 15));

        // Inner class usage
        mammal.showBodyParts();

        Mammal.Heart heart = new Mammal.Heart();
        heart.pump();

        mammal.showLocalClass();

        System.out.println(mammal);
        System.out.println(owner);
        System.out.println(habitat);
        System.out.println(zoo);
    }
}

class Mammal {
    private String name;
    private int age;
    private Owner owner;

    public Mammal(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    class Body {
        void show()
        {
            System.out.println("Showing body parts of " + name);
        }
    }

    public void showBodyParts()
    {
        Body body = new Body();
        body.show();
    }

    static class Heart {
        void pump()
        {
            System.out.println("Heart is pumping blood.");
        }
    }

    public void showLocalClass()
    {
        class LocalClass {
            void display()
            {
                System.out.println("Local class in action for " + name);
            }
        }

        LocalClass local = new LocalClass();
        local.display();
    }

    @Override
    public String toString()
    {
        return "Mammal{name='" + name + "', age=" + age + "}";
    }
}

class Owner {
    private String name;

    public Owner(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Owner{name='" + name + "'}";
    }
}

class Habitat {
    private String name;
    private List<Mammal> mammals;

    public Habitat(String name)
    {
        this.name = name;
        this.mammals = new ArrayList<>();
    }

    public void addMammal(Mammal mammal)
    {
        mammals.add(mammal);
    }

    @Override
    public String toString()
    {
        return "Habitat{name='" + name + "', mammals=" + mammals + "}";
    }
}

class Zoo {
    private String name;
    private List<Mammal> mammals;

    public Zoo(String name)
    {
        this.name = name;
        this.mammals = new ArrayList<>();
    }

    public void addMammal(Mammal mammal)
    {
        mammals.add(mammal);
    }

    @Override
    public String toString()
    {
        return "Zoo{name='" + name + "', mammals=" + mammals + "}";
    }
}
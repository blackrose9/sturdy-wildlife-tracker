import java.util.ArrayList;

public class Animal {
    private int id;
    private String type;
    private String name;
    private String age;
    private String health;
    //.....
    private static ArrayList<Animal> instances = new ArrayList<Animal>();

    public Animal(String mType, String mName, String mAge, String mHealth) {
        this.type = mType;
        this.name = mName;
        this.age = mAge;
        this.health = mHealth;
        //.....
        instances.add(this);
    }

    public static ArrayList<Animal> getAll(){
        return instances;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getHealth() { return health; }

    public void setHealth(String health) { this.health = health; }
}

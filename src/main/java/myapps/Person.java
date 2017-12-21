package myapps;

public class Person {
    private String name;
    private int age;
    private String location;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Location: " + this.location + " Age: " + this.age;
    }
}

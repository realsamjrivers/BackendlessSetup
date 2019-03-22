package examples.pltw.backendlesssetup;

public class Person
{

    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Person() {
        name = null;
        surname = null;
    }
}

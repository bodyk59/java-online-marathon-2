import java.util.Arrays;
import java.util.Comparator;

/*
 * Create classes with name PersonComparator, EmployeeComparator, DeveloperComparator
 * that implement the Comparator<Type> generic interface.
 * In the Utility class create public static method named sortPeople(...) that takes an array
 * of Person type and derivative from it types, and comparator as input, and returns the value of void type.
 * Also, as second argument the method sortPeople(...) can takes generic comparator for elements of Object type.
 * The sortPeople(...) method should sorted records by ascending.
 * At first by name, then by age, then by salary, and then by Level (JUNIOR < MIDDLE < SENIOR).
 */


/**
 * @author Bogdan Kurchak
 */
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

class Employee extends Person {
    private double salary;

    public Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + " Salary: " + salary;
    }
}

class Developer extends Employee {
    private Level level;

    public Developer(String name, int age, double salary, Level level) {
        super(name, age, salary);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return super.toString() + " Level: " + level;
    }
}

enum Level {
    JUNIOR, MIDDLE, SENIOR
}

class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        if (person1.getName().equals(person2.getName())) {
            return Integer.compare(person1.getAge(), person2.getAge());
        }
        return person1.getName().compareTo(person2.getName());
    }
}

class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee employee1, Employee employee2) {
        int result = new PersonComparator().compare(employee1, employee2);
        return result == 0 ? Double.compare(employee1.getSalary(), employee2.getSalary()) : result;
    }
}

class DeveloperComparator implements Comparator<Developer> {
    @Override
    public int compare(Developer developer1, Developer developer2) {
        int result = new EmployeeComparator().compare(developer1, developer2);
        return result == 0 ? developer1.getLevel().compareTo(developer2.getLevel()) : result;
    }
}

class Utility {
    public static <T extends Person>void sortPeople(T[] people, Comparator<? super T> comparator) {
        Arrays.sort(people, comparator);
    }
}

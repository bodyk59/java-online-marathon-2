import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * Create next types: Person (field String name), Student (fields String studyPlace, int studyYears)
 * and Worker (fields String workPosition, int experienceYears).
 * Classes Student and Worker are derived from class Person. All classes have getters to return fields.
 * Create a maxDuration() method of the MyUtils class to return a list of Students with maximum duration of study
 * and Workers with maximum experience.
 * For example, for a given list
 * [Person [name=Ivan],
 * Student [name=Petro, studyPlace=University, studyYears=3],
 * Worker [name=Andriy, workPosition=Developer, experienceYears=12],
 * Student [name=Stepan, studyPlace=College, studyYears=4],
 * Worker [name=Ira, workPosition=Manager, experienceYears=8],
 * Student [name=Ihor, studyPlace=University, studyYears=4]]
 * you should get
 * [Worker [name=Andriy, workPosition=Developer, experienceYears=12],
 * Student [name=Stepan, studyPlace=College, studyYears=4],
 * Student [name=Ihor, studyPlace=University, studyYears=4]]
 */

/**
 * @author Bogdan Kurchak
 */
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person [name=" + name +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

class Student extends Person {
    private String studyPlace;
    private int studyYears;

    public Student(String name, String studyPlace, int studyYears) {
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public int getStudyYears() {
        return studyYears;
    }

    @Override
    public String toString() {
        return "Student [name=" + super.getName() +
                ", studyPlace=" + studyPlace +
                ", studyYears=" + studyYears +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (studyYears != student.studyYears) return false;
        return Objects.equals(studyPlace, student.studyPlace);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (studyPlace != null ? studyPlace.hashCode() : 0);
        result = 31 * result + studyYears;
        return result;
    }
}

class Worker extends Person {
    private String workPosition;
    private int experienceYears;

    public Worker(String name, String workPosition, int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public String toString() {
        return "Worker [name" + super.getName() +
                ", workPosition=" + workPosition +
                ", experienceYears=" + experienceYears +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Worker worker = (Worker) o;

        if (experienceYears != worker.experienceYears) return false;
        return Objects.equals(workPosition, worker.workPosition);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (workPosition != null ? workPosition.hashCode() : 0);
        result = 31 * result + experienceYears;
        return result;
    }
}

class MyUtils {
    public List<Person> maxDuration(List<Person> persons) {
        if (persons == null || persons.isEmpty())
            return new ArrayList<>();
        
        int maxStudyYear = persons.stream()
                .filter(Objects::nonNull)
                .filter(Student.class::isInstance)
                .mapToInt(person -> ((Student) person).getStudyYears())
                .max()
                .orElse(0);

        int maxExperienceYear = persons.stream()
                .filter(Objects::nonNull)
                .filter(Worker.class::isInstance)
                .mapToInt(person -> ((Worker) person).getExperienceYears())
                .max()
                .orElse(0);

        Predicate<Person> maxValues = person ->
                person instanceof Worker && ((Worker) person).getExperienceYears() == maxExperienceYear ||
                        person instanceof Student && ((Student) person).getStudyYears() == maxStudyYear;

        return persons.stream()
                .filter(maxValues)
                .distinct()
                .collect(Collectors.toList());
    }
}

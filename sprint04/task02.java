import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 *
 * Create the commonStudents() method of the MyUtils class to return a HashSet of common elements of two student lists.
 * For example, for a given
 * list1 [Students [id=1, name=Ivan], Students [id=2, name=Petro], Students [id=3, name=Stepan]]
 * and
 * list2 [Students [id=1, name=Ivan], Students [id=3, name=Stepan], Students [id=4, name=Andriy]]
 * you should get
 * [Students [id=3, name=Stepan], Students [id=1, name=Ivan]].
 */

/**
 * @author Bogdan Kurchak
 */
class MyUtils {
    public static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return  "[id: " + id +
                    ", name: " + name + "]";
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null || this.getClass() != object.getClass()) {
                return false;
            }

            Student student = (Student) object;

            return id == student.id && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .filter(student -> list1.contains(student) && list2.contains(student))
                .collect(Collectors.toSet());
    }
}

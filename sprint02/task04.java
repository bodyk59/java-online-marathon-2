import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * Create classes Employee (fields String name, int experience and BigDecimal basePayment)
 * and Manager (field double coefficient) with methods which
 * return the general working experience and payment for work done.
 * A getter methods of class Employee return value of all fields: getName(), getExperience() and getPayment().
 * Classes Manager is derived from class Employee
 * and override getPayment() method to return multiplication of a coefficient and base payment.
 * Create a largestEmployees() method of the MyUtils class to return a List of unique employees with
 * maximal working experience and payment without duplicate objects.
 * The original list must be unchanged.
 * For example, for a given list
 * [Employee [name=Ivan, experience=10, basePayment=3000.00],
 * Manager [name=Petro, experience=9, basePayment=3000.00, coefficient=1.5],
 * Employee [name=Stepan, experience=8, basePayment=4000.00],
 * Employee [name=Andriy, experience=7, basePayment=3500.00],
 * Employee [name=Ihor, experience=5, basePayment=4500.00],
 * Manager [name=Vasyl, experience=8, basePayment=2000.00, coefficient=2.0]]
 * you should get
 * [Employee [name=Ivan, experience=10, basePayment=3000.00],
 * Manager [name=Petro, experience=9, basePayment=3000.00, coefficient=1.5],
 * Employee [name=Ihor, experience=5, basePayment=4500.00]].
 */

/**
 * @author Bogdan Kurchak
 */
class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getExperience() != employee.getExperience()) return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        return Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getExperience();
        result = 31 * result + (basePayment != null ? basePayment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee [name=" + getName()
                + ", experience=" + getExperience()
                + ", basePayment=" + getPayment()
                + "]";
    }
}

class Manager extends Employee {
    private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        return super.getPayment().multiply(BigDecimal.valueOf(getCoefficient()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;

        Manager manager = (Manager) o;

        return Double.compare(manager.getCoefficient(), getCoefficient()) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getCoefficient());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Manager [name=" + super.getName()
                + ", experience=" + super.getExperience()
                + ", basePayment=" + super.getPayment()
                + ", coefficient=" + getCoefficient()
                + "]";
    }
}

class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        if (workers == null || workers.isEmpty())
            return new ArrayList<>();

        int maxEmployeeExperience = workers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Employee::getExperience)
                .max()
                .orElse(0);

        BigDecimal maxPayment = workers.stream()
                .filter(Objects::nonNull)
                .map(Employee::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::max);

        Predicate<Employee> maxValue = employee -> employee.getExperience() == maxEmployeeExperience ||
                employee.getPayment().compareTo(maxPayment) == 0;

        return workers.stream()
                .filter(Objects::nonNull)
                .filter(maxValue)
                .distinct()
                .collect(Collectors.toList());
    }
}

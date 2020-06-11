/*
 * 1. Create an instances of Employee class named 'emp1' and 'emp2'.
 * 2. Set not null values for 'fullName' and 'salary' properties.
 * 3. Create array of Employee type with name 'employees' and add two objects created before.
 * 4. Create variable with name 'employeesInfo' of String type.
 * 5. Using a loop, iterrate across array and write to variable named 'employeesInfo' info about each employee in next fommat:
 */

/**
 * @author Bogdan Kurchak
 */
Employee emp1 = new Employee();
emp1.fullName = "John Doe";
emp1.salary = 10000;

Employee emp2 = new Employee();
emp2.fullName = "John Doe";
emp2.salary = 20000;

Employee[] employees = {emp1, emp2};

String employeesInfo = java.util.Arrays.stream(employees)
        .map(employee -> String.format("{fullName: \"%s\", salary: %.1f}", employee.fullName, employee.salary))
        .collect(java.util.stream.Collectors.joining(", ", "[", "]"));

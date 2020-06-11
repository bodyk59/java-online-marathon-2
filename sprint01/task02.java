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

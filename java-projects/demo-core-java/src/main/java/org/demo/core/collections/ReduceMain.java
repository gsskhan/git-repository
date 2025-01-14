package org.demo.core.collections;

import java.util.List;
import java.util.stream.Collectors;

public class ReduceMain {

    record Employee(int id, String name, int age, Double salary, String role) {}

    public static void main(String[] args) {
        // List of strings
        var listOne = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        // List of Employees
        var listTwo = List.of(
            new Employee(1, "John", 30, 50000.0, "Developer"),
            new Employee(2, "Jane", 25, 60000.0, "Designer"),
            new Employee(3, "Bob", 35, 70000.0, "Manager"),
            new Employee(4, "Alice", 28, 55000.0, "Developer"),
            new Employee(5, "Charlie", 32, 65000.0, "Designer"),
            new Employee(6, "David", 27, 75000.0, "Manager"),
            new Employee(7, "Eve", 31, 58000.0, "Developer"),
            new Employee(8, "Frank", 29, 68000.0, "Designer"),
            new Employee(9, "Grace", 33, 78000.0, "Manager"),
            new Employee(10, "Hank", 26, 52000.0, "Developer"),
            new Employee(11, "Ivy", 55, null, "CEO")
        );

        // Concatenate strings
        String resultConcatedString = listOne.stream()
            .reduce("", (partialResult, element) -> partialResult + element);
        System.out.println("Result concatenated string: " + resultConcatedString);

        // Concatenated strings with total length
        int resultConcatenatingLength = listOne.stream()
            .reduce(0, (partialResult, element) -> partialResult + element.length(), Integer::sum);
        System.out.println("Result concatenated string length: " + resultConcatenatingLength);

        // Total employees by role
        var resultTotalEmployeesByRole = listTwo.stream()
            .collect(Collectors.groupingBy(
                    Employee::role, Collectors.counting()));
        System.out.println("Result total employees by role: " + resultTotalEmployeesByRole);

        // Total salaries by role
        var resultTotalSalariesByRole = listTwo.stream()
            .collect(Collectors.groupingBy(
                    Employee::role,
                    // Collectors.summingDouble(Employee::salary) // Doesn't work, as it can't handle null
                    // Collectors.summingDouble(e -> e.salary() == null ? 0 : e.salary()) // Works, but i want to use reduce
                    Collectors.reducing(0.0, e -> e.salary() == null ? 0 : e.salary(), Double::sum)
            ));
        System.out.println("Result total salaries by role: " + resultTotalSalariesByRole);
    }
}
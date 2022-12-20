package Prob7b;

@FunctionalInterface
public interface SalaryComparator<Employee> {
    int compare(Employee e, int salary);
}

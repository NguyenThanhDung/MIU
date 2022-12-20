package Prob7b;

public class LambdaLibrary implements SalaryComparator<Employee> {
    @Override
    public int compare(Employee e, int salary) {
        return e.getSalary() - salary;
    }
}

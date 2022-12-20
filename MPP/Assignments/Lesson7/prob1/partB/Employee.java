package lesson7.labs.prob1.partB;

import java.util.Objects;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + salary + ")";
    }


    public boolean equals(Employee e) {
        return e.name.equals(name) && e.salary == salary;
    }

//	public int hashCode() {
//		return Objects.hash(name, salary);
//	}

//	public int hashCode() {
//		int result = 17; //seed
//		int hashName = name.hashCode();
//		result += 31 * result + hashName;
//		result += 31 * result + salary;
//		return result;
//	}
}

package lesson10.labs.prob3;

public class Employee implements Comparable {
    String firstName, lastName;


    int salary;
    public Employee(String f, String l, int s) {
        this.firstName = f;
        this.lastName = l;
        this.salary = s;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append("first name: ");
        sb.append(firstName);
        sb.append(" last name: ");
        sb.append(lastName);
        sb.append(" salary: ");
        sb.append("" + salary+">");
        return sb.toString();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) return  1;
        if (this.getClass() != o.getClass()) return 1;

        int compareValue = this.firstName.compareTo(((Employee) o).firstName);
        if (compareValue != 0) return compareValue;

        compareValue = this.lastName.compareTo(((Employee) o).lastName);
        if (compareValue != 0) return compareValue;

        return this.salary - ((Employee) o).salary;
    }
}

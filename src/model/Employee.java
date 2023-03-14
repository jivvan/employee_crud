package model;

public class Employee {
    private int employeeID, level;
    private double salary;
    private String name, address;
    public Employee(int id,String name, String address, double salary, int level){
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.level = level;
        this.employeeID = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setLevel(int level){
        this.level = level;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public int getEmployeeID(){
        return this.employeeID;
    }

    @Override
    public String toString() {
        return String.format("|%-3d|%-20s|%-15s|%-10d|%-15.3f|%n",employeeID,name,address,level,salary);
    }

    public String[] toCSV() {
        String[] res = {Integer.toString(employeeID), name, address, Integer.toString(level), Double.toString(salary)};
        return res;
    }
}

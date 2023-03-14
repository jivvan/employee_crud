package store;

import model.Employee;

import java.util.ArrayList;

public interface EmployeeStore {
    void insertEmployee(String name, String address, int level, double salary);
    ArrayList<Employee> getEmployees();
    boolean deleteEmployee(int id);
    void updateEmployee(int id, String name, String address, int level, double salary);
}

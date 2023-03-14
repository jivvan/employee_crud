package model;

import store.EmployeeStore;

import java.util.Scanner;

public class EmployeeManager {
    EmployeeStore employeeStore;
    private Scanner scanner = new Scanner(System.in);

    public EmployeeManager(EmployeeStore employeeStore) {
        this.employeeStore = employeeStore;
    }

    public void manageEmployees() {
        System.out.println("Welcome to employee management software:");
        Scanner scanner = new Scanner(System.in);
        whileloop:
        while (true) {
            System.out.println("Enter operation to perform");
            System.out.println("1. Create employee\t2. Display employees\t3. Update employee\t4. Delete employee");
            int opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    createEmployeeHandler();
                    break;
                case 2:
                    displayEmployeesHandler();
                    break;
                case 3:
                    updateEmployeeHandler();
                    break;
                case 4:
                    deleteEmployeeHandler();
                    break;
                default:
                    break whileloop;
            }
        }
    }

    private void createEmployeeHandler() {
        System.out.println("Enter name of employee:");
        String name = scanner.nextLine();
        System.out.println("Enter address of employee:");
        String address = scanner.nextLine();
        System.out.println("Enter salary of employee:");
        double salary = scanner.nextDouble();
        System.out.println("Enter level of employee:");
        int level = scanner.nextInt();
        scanner.nextLine();
        this.employeeStore.insertEmployee(name, address, level, salary);
    }

    private void displayEmployeesHandler() {
        System.out.println("Employees in this list are:");
        System.out.format("+-------------------------------------------------------------------+%n");
        System.out.format("|%-3s|%-20s|%-15s|%-10s|%-15s|\n", "ID", "Name", "Address", "Level", "Salary");
        System.out.format("+-------------------------------------------------------------------+%n");
        this.employeeStore.getEmployees().forEach(employee -> {
            System.out.format("%s", employee.toString());
        });
        System.out.println("+-------------------------------------------------------------------+");
    }

    private void updateEmployeeHandler() {
        System.out.println("Enter id of employee to edit:");
        int idToEdit = this.scanner.nextInt();
        this.scanner.nextLine();
        System.out.println("Enter field you want to edit:");
        System.out.println("1. Name\t2. Address\t3. Salary\t4. Level");
        int opt = scanner.nextInt();
        scanner.nextLine();
        String name="", address="";
        int level=0;
        double salary=0;
        switch (opt) {
            case 1:
                System.out.println("Enter updated name:");
                name = scanner.nextLine();
                break;
            case 2:
                System.out.println("Enter updated address:");
                address = scanner.nextLine();
                break;
            case 3:
                System.out.println("Enter updated salary:");
                salary = scanner.nextDouble();
                scanner.nextLine();
                break;
            case 4:
                System.out.println("Enter updated level:");
                level = scanner.nextInt();
                scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice");
        }
        this.employeeStore.updateEmployee(idToEdit, name, address, level, salary);
    }

    void deleteEmployeeHandler() {
        System.out.println("Enter id of employee to edit:");
        int idToDelete = this.scanner.nextInt();
        this.scanner.nextLine();
        boolean deleted = this.employeeStore.deleteEmployee(idToDelete);
        if (deleted) {
            System.out.println("Successfully deleted employee of id " + idToDelete);
        } else {
            System.out.println("Invalid id! Cannot delete.");
        }
    }
}

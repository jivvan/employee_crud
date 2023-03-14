import model.EmployeeManager;
import store.DBRepo;

public class Main {
    public static void main(String[] args) {
//        store.FileRepo repo = new store.FileRepo("./employees.csv");
        DBRepo repo = new DBRepo();
        EmployeeManager employeeManager = new EmployeeManager(repo);
        employeeManager.manageEmployees();
    }
}
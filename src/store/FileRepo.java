package store;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import model.Employee;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepo implements EmployeeStore {

    File dataFile;

    FileRepo(String filepath) {
        try {
            dataFile = new File(filepath);
            if (dataFile.createNewFile()) {
                System.out.println("Created file for saving employee info!");
            }
        } catch (IOException exception) {
            System.out.println("Error occurred while opening file: " + filepath);
            System.exit(1);
        }
    }

    public void writeEmployees(ArrayList<Employee> employees) {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            List<String[]> res = new ArrayList<>();
            for (var employee : employees) {
                res.add(employee.toCSV());
            }
            csvWriter.writeAll(res);
            csvWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertEmployee(String name, String address, int level, double salary){
        var currEmployees = getEmployees();
        int id = 1;
        if(currEmployees.size()>0){
            id = currEmployees.get(currEmployees.size()-1).getEmployeeID()+1;
        }
        currEmployees.add(currEmployees.size(), new Employee(id, name, address, salary, level));
        this.writeEmployees(currEmployees);
    }

    @Override
    public void updateEmployee(int id, String name, String address, int level, double salary){
        var currEmployees = getEmployees();
        for(int i=0;i<currEmployees.size();i++){
            if(currEmployees.get(i).getEmployeeID()==id){
                if(name !="")currEmployees.get(i).setName(name);
                else if(address!="")currEmployees.get(i).setAddress(address);
                else if(level!=0)currEmployees.get(i).setLevel(level);
                else if(salary!=0)currEmployees.get(i).setSalary(salary);
            }
        }
        this.writeEmployees(currEmployees);
    }

    @Override
    public boolean deleteEmployee(int id){
        var currEmployees = getEmployees();
        int indexToDelete = -1;
        for (int i = 0; i < currEmployees.size(); i++) {
            if (currEmployees.get(i).getEmployeeID() == id) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete >= 0) {
            currEmployees.remove(indexToDelete);
            this.writeEmployees(currEmployees);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> res = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(dataFile);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                int id = 0, level = 0;
                String name = "", address = "";
                double salary = 0;
                for (int i = 0; i < nextRecord.length; i++) {
                    String record = nextRecord[i];
                    switch (i) {
                        case 0:
                            id = Integer.parseInt(record);
                            break;
                        case 1:
                            name = record;
                            break;
                        case 2:
                            address = record;
                            break;
                        case 3:
                            level = Integer.parseInt(record);
                            break;
                        case 4:
                            salary = Double.parseDouble(record);
                            break;
                    }
                }
                var emp = new Employee(id, name, address, salary, level);
                res.add(emp);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}

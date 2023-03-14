package store;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class DBRepo implements EmployeeStore {

    Connection conn;

    public DBRepo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://root:password@localhost:3306/employee_database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void insertEmployee(String name, String address, int level, double salary) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO employee (name, address, level, salary) VALUES (?, ?, ?, ?)");
            st.setString(1, name);
            st.setString(2, address);
            st.setInt(3, level);
            st.setDouble(4, salary);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> res = new ArrayList<>();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employee;");
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                int level = rs.getInt("level");
                int id = rs.getInt("id");
                double salary = rs.getDouble("salary");
                res.add(new Employee(id, name, address, salary, level));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return res;
    }

    @Override
    public boolean deleteEmployee(int id) {
        try {
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM employee WHERE id=?");
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return true;
    }

    @Override
    public void updateEmployee(int id, String name, String address, int level, double salary) {
        try {
            if (name != "") {
                PreparedStatement st = this.conn.prepareStatement("UPDATE employee SET name=? WHERE id=?;");
                st.setString(1, name);
                st.setInt(2, id);
                st.execute();
            }else if (address != "") {
                PreparedStatement st = this.conn.prepareStatement("UPDATE employee SET address=? WHERE id=?;");
                st.setString(1, address);
                st.setInt(2, id);
                st.execute();
            }else if (level != 0) {
                PreparedStatement st = this.conn.prepareStatement("UPDATE employee SET level=? WHERE id=?;");
                st.setInt(1, level);
                st.setInt(2, id);
                st.execute();
            }else if (salary != 0) {
                PreparedStatement st = this.conn.prepareStatement("UPDATE employee SET salary=? WHERE id=?;");
                st.setDouble(1, salary);
                st.setInt(2, id);
                st.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

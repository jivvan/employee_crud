# Employee CRUD
A employee management system CRUD application

## Project Structure
```bash
employee_crud
└───src
    │   Main.java
    │
    ├───model
    │       Employee.java
    │       EmployeeManager.java
    │
    └───store
            DBRepo.java
            EmployeeStore.java
            FileRepo.java
```

- Main.java contains main method, which initializes a store and instantiates the EmployeeManager
- EmployeeStore is an interface. DBRepo and FileRepo implement the interface.

So, there are two ways to store employees, one way is to store them in MySQL database and another is to store them in a plain csv file. 

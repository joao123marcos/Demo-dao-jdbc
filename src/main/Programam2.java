package main;

import java.util.List;
import java.util.Scanner;

import Model.Class.Department;
import Model.Dao.DaoFactory;
import Model.Dao.DepartmentDao;

public class Programam2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        Department dep = new Department(9, "D3");
        departmentDao.insert(dep);

        System.out.println();
        System.out.println("----- Teste 1 Department: Insert ---------");
        System.out.println("Department inserted with sucess");

        System.out.println();
        System.out.println("----- Teste 2 Department: Update ---------");
        dep.setId(9);
        dep.setNameDepartment("Joaozim");
        departmentDao.update(dep);
        System.out.println("Department updated with sucess");

        System.out.println();
        System.out.println("----- Teste 3 Department: deleteById ---------");
        System.out.print("Enter id department for delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        departmentDao.deleteById(id);

        System.out.println();
        System.out.println("Department deleted with sucess");

        System.out.println();
        System.out.println("----- Teste 4 Department: FindById ---------");
        System.out.print("Enter id department: ");
        id = sc.nextInt();
        sc.nextLine();
        dep = departmentDao.findById(id);
        if (dep != null) {
            System.out.println("Department found!");
        }else{
            System.out.println("Department not found!");
        }
        
        System.out.println();
        System.out.println("----- Teste 5 Department: FindAll ---------");
        List<Department> list = departmentDao.findAll();
        for (Department d : list) {
            System.out.println(d);
        }
        
        sc.close();
    }
}

package main;

import Model.Class.Department;
import Model.Dao.DaoFactory;
import Model.Dao.DepartmentDao;

public class Programam2 {
    public static void main(String[] args) {

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
    }
}

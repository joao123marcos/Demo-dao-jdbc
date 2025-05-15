package main;

import java.util.List;

import Model.Class.Department;
import Model.Class.Seller;
import Model.Dao.DaoFactory;
import Model.Dao.SellerDao;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(5);

        System.out.println();
        System.out.println("---- Teste 1 Seller: FindById ------");
        System.out.println("Dados do vendedor! ");
        System.out.println(seller);

        System.out.println();
        System.out.println("---- Teste 2 Seller: FindByDepartment ------");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartmentId(department);
        for (Seller s : list) {
            System.out.println(s);
        }

    }
}

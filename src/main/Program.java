package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import Model.Class.Department;
import Model.Class.Seller;
import Model.Dao.DaoFactory;
import Model.Dao.SellerDao;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
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

        System.out.println();
        System.out.println("---- Teste 3 Seller: FindAll ------");
        list = sellerDao.findAll();
        for (Seller s : list) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println("---- Teste 4 Seller: Inser ------");
        LocalDate data = LocalDate.parse("15/05/2010", fmt); 
        Seller seller2 = new Seller(null, "Joao Pedro", 
           "JoaoPedroa@gmail.com", data, 4500.00, 
           department);
        
        sellerDao.insert(seller2);
        System.out.println("Seller inserted, your id is: "+seller2.getIdSeller());

        System.out.println();
        System.out.println("---- Teste 5 Seller: Update ------");
        seller2 = sellerDao.findById(1);
        seller2.setNameSeller("Maria das coves");
        sellerDao.update(seller2);
        System.out.println("Updated Seller sucess");
        
        System.out.println();
        System.out.println("---- Teste 6 Seller: DeleteById ------");
        sellerDao.deleteById(1);
        System.out.println("Seller deleted with sucess!");

    }
}

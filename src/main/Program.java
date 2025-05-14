package main;

import Model.Class.Seller;
import Model.Dao.DaoFactory;
import Model.Dao.SellerDao;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.println();
        System.out.println("Dados do vendedor! ");
        System.out.println(seller);

    }
}

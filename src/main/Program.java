package main;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import Model.Class.Department;
import Model.Class.Seller;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        LocalDate dataAtual = LocalDate.now();
        
        Department obj = new Department(1, "Books");

        Seller seller = new Seller(1, "Joao",
        "joao@gmail.com", dataAtual, 2000.0, obj);

        System.out.println();
        System.out.println(obj);
        System.out.println();
        System.out.println(seller);
    }
}

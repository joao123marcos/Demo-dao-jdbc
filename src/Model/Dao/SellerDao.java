package Model.Dao;

import java.util.List;
import Model.Class.Seller;

public interface SellerDao {
    void insert(Seller objSeller);
    void update(Seller objSeller);
    void delet(Seller objSeller);
    Seller findById(Integer idSeller);
    List<Seller> findAll();
}

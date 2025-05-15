package Model.Dao;

import java.util.List;

import Model.Class.Department;
import Model.Class.Seller;

public interface SellerDao {
    void insert(Seller objSeller);
    void update(Seller objSeller);
    void deleteById(Integer id);
    Seller findById(Integer idSeller);
    List<Seller> findAll();
    List<Seller> findByDepartmentId(Department department);
}

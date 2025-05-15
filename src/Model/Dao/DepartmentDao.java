package Model.Dao;

import java.util.List;
import Model.Class.Department;

public interface DepartmentDao {
    
    void insert(Department objDpartment);
    void update(Department objDpartment);
    void deleteById(Integer id);
    Department findById(Integer idDepartment);
    List<Department> findAll();
}

package Model.Dao.ImplementsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Model.Class.Department;
import Model.Dao.DepartmentDao;
import db.DB;
import db.DbException;

public class DepartmentDaoJDBC  implements DepartmentDao{

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Department objDpartment) {
       PreparedStatement ps = null;
       try {
          ps = connection.prepareStatement(
            "insert into department (Name) "+
            "values (?) ");
          ps.setString(1, objDpartment.getNameDepartment());
          ps.executeUpdate();  
       } catch (SQLException e) {
         throw new DbException(e.getMessage());
       }finally{
         DB.closeStatement(ps);
       }
    }

    @Override
    public void update(Department objDpartment) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(
                "update department a "+
                "set a.Name = ? "+
                "where a.Id = ?");
            ps.setString(1, objDpartment.getNameDepartment());
            ps.setInt(2, objDpartment.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delet'");
    }

    @Override
    public Department findById(Integer idDepartment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Department> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}

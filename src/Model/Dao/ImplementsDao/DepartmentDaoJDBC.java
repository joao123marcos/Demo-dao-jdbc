package Model.Dao.ImplementsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(
                "delete from department a "+
                "where a.id = ?");
            ps.setInt(1, Id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public Department findById(Integer idDepartment) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(
                "select * from department a where a.id = ?");

            ps.setInt(1, idDepartment);
            rs = ps.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
            }else{
                return null;
            }            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Department> findAll() {
       ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(
                "select * from department");

            rs = ps.executeQuery();
            List<Department> list = new ArrayList<>();
            while (rs.next()) {
                Department dep = instantiateDepartment(rs);
                list.add(dep);
            }
            return list;       
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        } 
    }
    
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setNameDepartment(rs.getString("Name"));
        return dep;
    }
}

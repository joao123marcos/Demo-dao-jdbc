package Model.Dao.ImplementsDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Class.Department;
import Model.Class.Seller;
import Model.Dao.SellerDao;
import db.DB;
import db.DbException;

public class SellerDaoJDBC implements SellerDao{

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller objSeller) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(
            "INSERT INTO seller "+ 
            "(Name, Email, BirthDate, BaseSalary, DepartmentId) "+
            "VALUES "+
            "(?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, objSeller.getNameSeller());
            ps.setString(2, objSeller.getEmailSeller());
            //Converti o meu localDate do objeto para date, que é oq o JDBC usa
            ps.setDate(3, Date.valueOf(objSeller.getBirthDate()));
            ps.setDouble(4, objSeller.getSalarySeller());
            ps.setInt(5, objSeller.getDepartment().getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected >0 ) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int idSeller = rs.getInt(1);
                    objSeller.setIdSeller(idSeller);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Error insert Seller ");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Seller objSeller) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(
                "UPDATE seller "+
                "SET Name = ?, Email = ?, BirthDate = ?, "+
                "BaseSalary = ?, DepartmentId = ? "+
                "WHERE Id = ?");

            ps.setString(1, objSeller.getNameSeller());
            ps.setString(2, objSeller.getEmailSeller());
            ps.setDate(3, Date.valueOf(objSeller.getBirthDate()));
            ps.setDouble(4, objSeller.getSalarySeller());
            ps.setInt(5, objSeller.getDepartment().getId());
            ps.setInt(6, objSeller.getIdSeller());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM seller WHERE Id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(ps);
        }
    }

    @Override
    public Seller findById(Integer idSeller) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
             ps = connection.prepareStatement(
              "SELECT seller.*, department.Name as DepName "+
              "FROM seller INNER JOIN department "+
              "ON seller.DepartmentId = department.Id "+
              " WHERE seller.Id = ?");

            ps.setInt(1, idSeller);
            rs = ps.executeQuery();
            /*Como o rs é um formato de tabela e aponta para posição 0 eu preciso
              verificar se o rs encontou algum vendedor lá no banco*/
            if (rs.next()) { //verifico oq tem dentro do rs
                Department dep = instantiateDepartment(rs); 
               
                Seller seller = instantiateSeller(rs, dep);

                return seller;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }       
    }

    @Override
    public List<Seller> findAll() {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
             ps = connection.prepareStatement(
                "SELECT seller.*,department.Name as DepName "+
                "FROM seller INNER JOIN department "+
                "ON seller.DepartmentId = department.Id "+
                "ORDER BY Name ");

            rs = ps.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                } 
               
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
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
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNameDepartment(rs.getString("DepName"));
        return dep;

    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws 
     SQLException {

        /*Com isso eu tenho um departamento instanciado recuperando 
        dados do meu banco*/
        Seller seller = new Seller();
        seller.setIdSeller(rs.getInt("Id"));
        seller.setNameSeller(rs.getString("Name"));
        seller.setEmailSeller(rs.getString("Email"));
        seller.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        seller.setSalarySeller(rs.getDouble("BaseSalary"));
        seller.setDepartment(dep);
        return seller;

    }

    @Override
    public List<Seller> findByDepartmentId(Department department) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
             ps = connection.prepareStatement(
                "SELECT seller.*,department.Name as DepName "+
                "FROM seller INNER JOIN department "+
                "ON seller.DepartmentId = department.Id "+
                "WHERE DepartmentId = ? "+
                "ORDER BY Name ");

            ps.setInt(1, department.getId());
            rs = ps.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                } 
               
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }
    
}

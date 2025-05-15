package Model.Dao.ImplementsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Seller objSeller) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delet(Seller objSeller) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delet'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNameDepartment("Name");
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
    
}

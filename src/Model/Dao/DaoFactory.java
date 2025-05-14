package Model.Dao;

import Model.Dao.ImplementsDao.SellerDaoJDBC;
import db.DB;

/*A fabria dao ela chama quem sabe fazer os métodos */
public class DaoFactory {
    
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}

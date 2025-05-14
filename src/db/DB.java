package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB{

    private static Connection conexao = null;
    
    /*Metodo que gera uma conexão com o banco de dados */
    public static Connection getConnection(){
        if (conexao == null) {
            try {
                Properties props = carregandoConfigDB();
                String urlDB = props.getProperty("dburl");
                conexao = DriverManager.getConnection(urlDB, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conexao;
    }

    public static void closeConnectionDB(){
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
               throw new DbException(e.getMessage());
            }
        }
    }

    /*Este método acesso o arquivo db.properties localizado na pasta do projeto
     * e retorna na variável props o conteúdo do arquivo.*/
    private static Properties carregandoConfigDB(){
        try (FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;

        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }

    }

    public static void closeStatement(Statement st){
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    
}

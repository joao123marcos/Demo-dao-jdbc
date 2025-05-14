package db;

public class DbIntefrityException extends RuntimeException {
 
    private static final long serialVersionUID = 1L;

    public DbIntefrityException(String msg){
        super(msg);
    }
    
}
package by.teplouhova.chemist;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO <T extends Entity>  {

    protected ProxyConnection connection;
    public abstract T findById(long id);
    public abstract void create(T entity);
    public abstract void update(T entity);

    void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    public void close(Statement st)  {
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package by.teplouhova.chemist;

import by.teplouhova.chemist.dao.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

@WebListener
public class ChemistContextListener implements ServletContextListener {

    private final static Logger LOGGER= LogManager.getLogger();

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        ConnectionPool.getInstance().closePoolConnections();
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver("com.mysql.cj.jdbc.Driver"));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Connection driver is not deregistered ");
            new RuntimeException( "Connection driver is not deregistered",e);
        }
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            ConnectionPool.getInstance();
            //Locale locale=Locale.getDefault();


        } catch (SQLException e) {
            LOGGER.log(Level.FATAL,"Connection driver is not registered ");
            new RuntimeException( "Connection driver is not registered",e);
        }

    }
}

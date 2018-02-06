package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class ChemistContextListener implements ServletContextListener {

    private final static Logger LOGGER= LogManager.getLogger();

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        ConnectionPool.getInstance().closePoolConnections();
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver("com.impl.jdbc.Driver"));
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL,"Connection driver is not unregistered ");

        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            ConnectionPool.getInstance();


        } catch (SQLException e) {
            LOGGER.log(Level.FATAL,"Connection driver is not registered ");
            new RuntimeException( "Connection driver is not registered",e);
        }
    }
}

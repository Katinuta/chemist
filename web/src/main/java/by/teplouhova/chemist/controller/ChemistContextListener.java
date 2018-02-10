package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.manager.MessageManager;
import by.teplouhova.chemist.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The listener interface for receiving chemistContext events.
 * The class that is interested in processing a chemistContext
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addChemistContextListener<code> method. When
 * the chemistContext event occurs, that object's appropriate
 * method is invoked.
 *
 */
@WebListener
public class ChemistContextListener implements ServletContextListener {

    /** The Constant LOGGER. */
    private final static Logger LOGGER= LogManager.getLogger();

    /**
     * Context destroyed.
     *
     * @param arg0 the arg 0
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

        ConnectionPool.getInstance().closePoolConnections();
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver("com.impl.cj.jdbc.Driver"));

        } catch (SQLException e) {
            LOGGER.log(Level.FATAL,"Connection driver is not unregistered ");

        }
    }

    /**
     * Context initialized.
     *
     * @param arg0 the arg 0
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            ConnectionPool.getInstance();
            MessageManager.EN.getBundle();
        } catch (SQLException  e) {
            LOGGER.fatal("Connection driver is not registered ");
            new RuntimeException( "Connection driver is not registered",e);
        }
    }
}

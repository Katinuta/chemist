package test.by.teplouhova.chemist.pool;

import by.teplouhova.chemist.pool.ConnectionPool;
import by.teplouhova.chemist.pool.ProxyConnection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class ConnectionPoolTest {
    private ConnectionPool connectionPool;
    private int poolSize;

    @BeforeClass
    public void setUp() {
        connectionPool = ConnectionPool.getInstance();
        poolSize = connectionPool.size();
    }

    @Test
    public void connectionPoolInitialized() throws Exception {
        assertNotNull(connectionPool);
    }

    @Test
    public void getConnectionTest() throws Exception {
        ProxyConnection connection = connectionPool.getConnection();
        assertNotNull(connectionPool);
        connection.close();
    }

    @Test
    public void releaseConnectionTest() throws Exception {
        ProxyConnection connection = connectionPool.getConnection();
        connection.close();
        int actual = connectionPool.size();
        assertEquals(poolSize, actual);
    }

    @AfterClass
    public void after() {
        connectionPool.closePoolConnections();
    }
}

package test.by.teplouhova.chemist.property;

import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import static org.testng.Assert.assertFalse;

public class PropertyTest {
    private static final String DB_CONFIG = "database.properties";
    private static final String LANGUAGE_WRONG = "Messages_en_EN.properties";
    private static final String LANGUAGE = "MessagesBundle_en_EN.properties";

    @Test
    public void databasePropTest() throws Exception{
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(DB_CONFIG);
        properties.load(is);
        assertFalse(properties.isEmpty());
    }



    @Test
    public void languagePropTest() throws Exception{
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(LANGUAGE);
        properties.load(is);
        assertFalse(properties.isEmpty());
    }

    @Test(expectedExceptions = MissingResourceException.class)
    public void languagePropTestException() throws Exception{
        ResourceBundle bundle=ResourceBundle.getBundle(LANGUAGE_WRONG);
    }
}

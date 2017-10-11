package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static String environment;

    private Properties properties = new Properties();
    private static Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    public PropertyReader() {
        loadProperties();
    }

    private void loadProperties() {

        try {
            InputStream inputStream = new FileInputStream("configuration/config.properties");
            properties.load(inputStream);

        } catch(IOException e) {

            logger.warn(e.getMessage());
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}

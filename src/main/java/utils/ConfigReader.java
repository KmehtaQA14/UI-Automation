package utils;


import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("testconfig.properties");
            if (input == null) {
                throw new RuntimeException("Configuration file 'testconfig.properties' not found in classpath.");
            }
            properties = new Properties();
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DataLoader {

    private DataLoader() {
    }

    private static Properties properties = null;

    public static String getWebDriverVersion() {
        return getProperty("ChromeDriverVersion");
    }

    public static String getLandingURL() {
        return getProperty("landingURL");
    }

    public static boolean runLocally() {
        return getProperty("runLocally").equals("true");
    }

    private static String getProperty(String property) {
        if (properties == null) {
            InputStream inputStream = DataLoader.class.getResourceAsStream("test.properties");
            properties = new Properties();

            try {
                properties.load(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return properties.getProperty(property);
    }

}

package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DataLoader {

    private DataLoader() {
    }

    private static Properties properties = null;

    public static String getEmail() {
        return getProperty("email");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getName() {
        return getProperty("name");
    }

    public static String getLastName() {
        return getProperty("lastName");
    }

    public static String getCompany() {
        return getProperty("company");
    }

    public static String getloginURL() {
        return getProperty("loginURL");
    }

    public static String getAccessToken() {
        return getProperty("accessToken");
    }

    public static String getSettingsLeadsStatusURL() {
        return getProperty("settingsLeadsStatusURL");
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

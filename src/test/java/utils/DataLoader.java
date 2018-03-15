package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DataLoader {

  private static Properties properties = null;

  private DataLoader() {
  }

  public static String getWebDriverVersion() {
    return getProperty("ChromeDriverVersion");
  }

  public static String getLandingURL() {
    return getProperty("landingURL");
  }

  public static boolean isLocalRun() {
    return getProperty("localRun").equals("true");
  }

  public static String getStoriesToRun() {
    return getProperty("stories");
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

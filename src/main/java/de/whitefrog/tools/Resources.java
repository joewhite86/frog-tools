package de.whitefrog.tools;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class Resources {
  static final Logger logger = LoggerFactory.getLogger(Resources.class);
  public static URL getPropertyUrl(String filename) throws ConfigurationException {
    return Resources.getProperties(filename).getURL();
  }
  public static PropertiesConfiguration getProperties(String filename) throws ConfigurationException {
    String env = System.getProperty("semantic.env");

    if("dev".equals(env)) {
      int index = filename.indexOf(".properties");
      String devFile = filename.substring(0, index) + ".dev.properties";
      try {
        return new PropertiesConfiguration(devFile);
      }
      catch(ConfigurationException e) {
        logger.debug("no dev properties for file \"{}\"", filename);
      }
    }

    return new PropertiesConfiguration(filename);
  }
}

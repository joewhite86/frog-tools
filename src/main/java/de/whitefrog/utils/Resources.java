package de.whitefrog.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class Resources {
  static final Logger logger = LoggerFactory.getLogger(Resources.class);

  private Resources() {}

  public static URL getPropertyUrl(String filename) throws ConfigurationException {
    return Resources.getProperties(filename).getURL();
  }

  public static PropertiesConfiguration getProperties(String filename) throws ConfigurationException {
    String env = System.getProperty("semantic.env");

    if("dev".equals(env)) {
      String extension = FilenameUtils.getExtension(filename);
      if(extension != null && !extension.isEmpty()) {
        int index = filename.indexOf(extension);
        String devFile = filename.substring(0, index) + "dev." + extension;
        try {
          return new PropertiesConfiguration(devFile);
        } catch (ConfigurationException e) {
          logger.debug("no dev properties for file \"{}\"", filename);
        }
      }
    }

    return new PropertiesConfiguration(filename);
  }
}

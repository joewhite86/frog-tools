package de.whitefrog.utils;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringUtils extends org.apache.commons.lang.StringUtils {
  public static String shuffle(String string) {
    List<String> letters = Arrays.asList(string.split(""));
    Collections.shuffle(letters);
    StringWriter shuffled = new StringWriter();
    for (String letter : letters) {
      shuffled.append(letter);
    }
    return shuffled.toString();
  }
}

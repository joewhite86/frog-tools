package de.whitefrog.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by jochen on 14.04.14.
 */
public class TimeUtils {
  public static String formatInterval(long i) {
    final long days = TimeUnit.MILLISECONDS.toDays(i);
    final long hr = TimeUnit.MILLISECONDS.toHours(i);
    final long min = TimeUnit.MILLISECONDS.toMinutes(i - TimeUnit.HOURS.toMillis(hr));
    final long sec = TimeUnit.MILLISECONDS.toSeconds(i - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
    final long ms = TimeUnit.MILLISECONDS.toMillis(i - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));

    if(days > 0) {
      return String.format("%d days %d hours", days, hr);
    }
    else if(hr > 0) {
      return String.format("%d hours %d minutes", hr, min);
    }
    else if(min > 0) {
      return String.format("%d minutes %d seconds", min, sec);
    }
    else if(sec > 0) {
      return String.format("%d seconds %d ms", sec, ms);
    }
    else {
      return String.format("%dms", ms);
    }
  }
}

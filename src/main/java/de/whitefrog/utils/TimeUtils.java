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
    if((hr|min|sec) == 0) {
      return String.format("%dms", ms);
    }
    else if(days > 0) {
      return String.format("%ddays %02d:%02d:%02d", days, hr, min, sec);
    }
    else {
      return String.format("%02d:%02d:%02d", hr, min, sec);
    }
  }
}

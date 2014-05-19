package de.whitefrog.utils;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
  public static String formatInterval(long i) {
    final long days = TimeUnit.MILLISECONDS.toDays(i);
    final long hr = TimeUnit.MILLISECONDS.toHours(i - TimeUnit.DAYS.toMillis(days));
    final long min = TimeUnit.MILLISECONDS.toMinutes(i - TimeUnit.DAYS.toMillis(days) - TimeUnit.HOURS.toMillis(hr));
    final long sec = TimeUnit.MILLISECONDS.toSeconds(i - TimeUnit.DAYS.toMillis(days) - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
    final long ms = i - TimeUnit.DAYS.toMillis(days) - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec);

    if(days > 0) {
      return MessageFormat.format("{0} {0,choice,0#days|1#day|1<days} {1} {1,choice,0#hours|1#hour|1<hours}", days, hr);
    }
    else if(hr > 0) {
      return MessageFormat.format("{0} {0,choice,0#hours|1#hour|1<hours} {1} {1,choice,0#minutes|1#minute|1<minutes}", hr, min);
    }
    else if(min > 0) {
      return MessageFormat.format("{0} {0,choice,0#minutes|1#minute|1<minutes} {1} {1,choice,0#seconds|1#second|1<seconds}", min, sec);
    }
    else if(sec > 0) {
      return MessageFormat.format("{0} {0,choice,0#seconds|1#second|1<seconds} {1}ms", days, hr);
    }
    else {
      return MessageFormat.format("{0}ms", ms);
    }
  }
}

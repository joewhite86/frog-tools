package de.whitefrog.utils;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
  public static String formatInterval(long i) {
    return formatInterval(i, TimeUnit.MILLISECONDS);
  }
  public static String formatInterval(long i, TimeUnit timeUnit) {
    if(!timeUnit.equals(TimeUnit.NANOSECONDS)) i = timeUnit.toNanos(i);
    final long days = timeUnit.toDays(i);
    final long hr = timeUnit.toHours(i - TimeUnit.DAYS.toNanos(days));
    final long min = timeUnit.toMinutes(i - TimeUnit.DAYS.toNanos(days) - TimeUnit.HOURS.toNanos(hr));
    final long sec = timeUnit.toSeconds(i - TimeUnit.DAYS.toNanos(days) - TimeUnit.HOURS.toNanos(hr) - TimeUnit.MINUTES.toNanos(min));
    final long ms = timeUnit.toMillis(i - TimeUnit.DAYS.toNanos(days) - TimeUnit.HOURS.toNanos(hr) - TimeUnit.MINUTES.toNanos(min) - TimeUnit.SECONDS.toNanos(sec));
    final long ns = i - TimeUnit.DAYS.toNanos(days) - TimeUnit.HOURS.toNanos(hr) - TimeUnit.MINUTES.toNanos(min) - TimeUnit.SECONDS.toNanos(sec) - TimeUnit.MILLISECONDS.toNanos(ms);

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
      return MessageFormat.format("{0} {0,choice,0#seconds|1#second|1<seconds} {1}ms", sec, ms);
    }
    else if(ms > 10) {
      return MessageFormat.format("{0}ms", ms);
    }
    else if(ms > 0) {
      return MessageFormat.format("{0}.{1}ms", ms, ns);
    }
    else {
      return MessageFormat.format("{0}ns", ns);
    }
  }
}

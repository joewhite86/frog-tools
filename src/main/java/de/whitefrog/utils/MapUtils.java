package de.whitefrog.utils;

import java.util.*;

public class MapUtils {
  private MapUtils() {}

  public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey( Map<K, V> map ) {
    List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );

    Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
      public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
        return (o1.getKey()).compareTo( o2.getKey() );
      }
    });

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
      result.put( entry.getKey(), entry.getValue() );
    }

    return result;
  }

  public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ) {
    List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );

    Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
      public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
        return (o1.getValue()).compareTo( o2.getValue() );
      }
    });

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
      result.put( entry.getKey(), entry.getValue() );
    }

    return result;
  }
}

package de.whitefrog.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils extends org.apache.commons.collections.ListUtils {
  // chops a list into non-view sublists of length L
  public static <T> List<List<T>> split(List<T> list, final int L) {
    List<List<T>> parts = new ArrayList<>();
    final int N = list.size();
    for(int i = 0; i < N; i += L) {
      parts.add(new ArrayList<>(
          list.subList(i, Math.min(N, i + L)))
      );
    }
    return parts;
  }
}

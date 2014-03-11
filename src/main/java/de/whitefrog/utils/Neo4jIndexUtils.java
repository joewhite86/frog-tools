package de.whitefrog.utils;

import de.whitefrog.exception.QueryParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.index.lucene.QueryContext;

import java.util.*;

public class Neo4jIndexUtils {
  private static QueryContext buildQuery(String field, String value, boolean allowWildcard) {
    String query = QueryParser.escape(value).replace(" ", "\\ ");
    if(value.contains("*")) query = query.replace("\\*", "*");
    return new QueryContext(field + ":" + query);
  }
  private static QueryContext buildQuery(String field, String value, boolean allowWildcard, Map<String, String> sorting) {
    QueryContext context = buildQuery(field, value, allowWildcard);

    for(String key: sorting.keySet()) {
      context.sort(key, sorting.get(key));
    }

    return context;
  }
  public static <T extends PropertyContainer> T querySingle(Index<T> index, String field, String query) {
    if(query == null) throw new NullPointerException();
    try(IndexHits<T> results = index.query(buildQuery(field, query, false))) {
      if(results.hasNext()) {
        return results.getSingle();
      }
      else return null;
    }
    catch(NoSuchElementException e) {
      throw new IllegalStateException(String.format("found more than one element with %s \"%s\"", field, query));
    }
    catch(NullPointerException e) {
      throw new QueryParseException(query, e);
    }
  }

  public static <T extends PropertyContainer> Set<T> query(Index<T> index, String field, String query) {
    return query(index, field, query, new HashMap<String, String>());
  }

  public static <T extends PropertyContainer> Set<T> query(Index<T> index, String field, String query, Map<String, String> sorting) {
    if(query == null) throw new NullPointerException();
    Set<T> nodes = new HashSet<>();
    try(IndexHits<T> results = index.query(buildQuery(field, query, true, sorting))) {
      for(T node: results) {
        nodes.add(node);
      }
      return nodes;
    }
    catch(NullPointerException e) {
      throw new QueryParseException(query, e);
    }
  }
}

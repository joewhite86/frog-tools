package de.whitefrog.utils;

import de.whitefrog.exception.QueryParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import java.util.NoSuchElementException;

public class Neo4jIndexUtils {
  public static <T extends PropertyContainer> T querySingle(Index<T> index, String field, String query) {
    if(query == null) throw new NullPointerException();
    try(IndexHits<T> results = index.query(field + ":\"" + QueryParser.escape(query) + "\"")) {
      if(results.hasNext()) {
        return results.getSingle();
      }
      else return null;
    }
    catch(NoSuchElementException e) {
      throw new IllegalStateException(String.format("found more than one element with %s \"%s\"", field, query));
    }
    catch(NullPointerException e) {
      throw new QueryParseException(query);
    }
  }

  public static <T extends PropertyContainer> T query(Index<T> index, String field, String query) {
    if(query == null) throw new NullPointerException();
    try(IndexHits<T> results = index.query(field + ":\"" + QueryParser.escape(query) + "\"")) {
      if(results.hasNext()) {
        return results.getSingle();
      }
      else return null;
    }
    catch(NoSuchElementException e) {
      throw new IllegalStateException(String.format("found more than one element with %s \"%s\"", field, query));
    }
    catch(NullPointerException e) {
      throw new QueryParseException(query);
    }
  }
}

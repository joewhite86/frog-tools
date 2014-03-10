package de.whitefrog.exception;

public class QueryParseException extends RuntimeException {
  String query;
  public QueryParseException(String query, Throwable cause) {
    super("Could not parse \"" + query + "\"", cause);
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}

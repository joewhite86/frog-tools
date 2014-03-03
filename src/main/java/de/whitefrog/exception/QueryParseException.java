package de.whitefrog.exception;

public class QueryParseException extends RuntimeException {
  String query;
  public QueryParseException(String query) {
    super("Could not parse \"" + query + "\"");
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}

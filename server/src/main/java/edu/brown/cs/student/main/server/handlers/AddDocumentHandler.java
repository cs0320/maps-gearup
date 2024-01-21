package edu.brown.cs.student.main.server.handlers;

import edu.brown.cs.student.main.server.storage.StorageInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddDocumentHandler implements Route {

  public StorageInterface storageHandler;
  public AddDocumentHandler(StorageInterface storageHandler) {
    this.storageHandler = storageHandler;
  }
  /**
   * Invoked when a request is made on this route's corresponding path e.g. '/hello'
   *
   * @param request  The request object providing information about the HTTP request
   * @param response The response object providing functionality for modifying the response
   * @return The content to be set in the response
   */
  @Override
  public Object handle(Request request, Response response) {
    String uid = request.queryParams("uid");
    // in your final Sprint implementation, you may need more than just a unique id.
    return this.storageHandler.addDocument(uid);
  }
}

package edu.brown.cs.student.main.server;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class AddInfoHandler implements Route {
public FirebaseInterface fireInterface;
  public AddInfoHandler(FirebaseInterface firebaseInterface){
    this.fireInterface = firebaseInterface;
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
    String nameOfPin = request.queryParams("nameOfPin");
    String description = request.queryParams("description");
    String uid = request.queryParams("uid");
    return fireInterface.addDocument(uid,description,nameOfPin);
  }
}

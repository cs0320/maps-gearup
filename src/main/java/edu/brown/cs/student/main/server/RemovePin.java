package edu.brown.cs.student.main.server;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import spark.Request;
import spark.Response;
import spark.Route;
public class RemovePin implements Route {
  /**
   * Invoked when a request is made on this route's corresponding path e.g. '/hello'
   *
   * @param request  The request object providing information about the HTTP request
   * @param response The response object providing functionality for modifying the response
   * @return The content to be set in the response
   */
  @Override
  public Object handle(Request request, Response response) {
    String nameOfPinToRemove = request.queryParams("nameOfPinToRemove");
    String uid = request.queryParams("uid");

    try {
      Firestore db = FirestoreClient.getFirestore();
      CollectionReference collectionRef = db.collection("users");
      DocumentReference userRef = collectionRef.document(uid);

      // Check if the user exists
      if (userRef.get().get().exists()) {
        // User exists, remove the specified field
        userRef.update(nameOfPinToRemove, FieldValue.delete()).get();
        return "Success";
      } else {
        // User doesn't exist, handle accordingly (return an error or do nothing)
        return "User not found";
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "error";
    }
  }
}

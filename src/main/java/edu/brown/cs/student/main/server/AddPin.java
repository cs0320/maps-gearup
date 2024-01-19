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

public class AddPin implements Route {
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
    double lat = Double.parseDouble(request.queryParams("lat"));
    double lon = Double.parseDouble(request.queryParams("lon"));
    String description = request.queryParams("description");
    String uid = request.queryParams("uid");

    try {
      Firestore db = FirestoreClient.getFirestore();
      CollectionReference collectionRef = db.collection("users");
      DocumentReference userRef = collectionRef.document(uid);

      // Check if the user exists
      if (!userRef.get().get().exists()) {
        // User doesn't exist, create a new one with the first pin
        Map<String, Object> newUser = new HashMap<>();
        Map<String, Object> pinData = new HashMap<>();
        pinData.put("Lat", lat);
        pinData.put("Lon", lon);
        pinData.put("description", description);
        newUser.put(nameOfPin, pinData);
        userRef.set(newUser).get();
      } else {
        // User exists, update the document with the new pin
        Map<String, Object> pinData = new HashMap<>();
        pinData.put("Lat", lat);
        pinData.put("Lon", lon);
        pinData.put("description", description);
        userRef.update(nameOfPin, pinData).get();
      }
      return "Success";
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "error";
    }
  }
}

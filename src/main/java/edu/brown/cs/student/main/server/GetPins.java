package edu.brown.cs.student.main.server;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

public class GetPins implements Route {
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

    try {
      Firestore db = FirestoreClient.getFirestore();
      CollectionReference collectionRef = db.collection("users");
      DocumentReference userRef = collectionRef.document(uid);
      response.type("application/json");
      if (userRef.get().get().exists()) {
        Map<String, Object> userDoc = userRef.get().get().getData();
        if (userDoc != null) {
          Moshi moshi = new Moshi.Builder().build();
          JsonAdapter<Map<String, Object>> adapter = moshi.adapter(
              Types.newParameterizedType(Map.class, String.class, Object.class));
          return adapter.toJson(userDoc);
        }
      }
        return "[]";
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "[]";
    }
  }
}

package edu.brown.cs.student.main.server;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import java.util.HashMap;
import java.util.Map;

public class FirebaseUtilities implements FirebaseInterface {

  @Override
  public String addDocument(String uid, String description, String nameOfObject) {
    try {
      Firestore db = FirestoreClient.getFirestore();
      CollectionReference collectionRef = db.collection("users");
      DocumentReference userRef = collectionRef.document(uid);

      if (!userRef.get().get().exists()) {
        Map<String, Object> newUser = new HashMap<>();
        userRef.set(newUser).get();
      }
      Map<String, Object> pinData = new HashMap<>();
      pinData.put("description", description);
      userRef.update(nameOfObject, pinData).get();
      return "Success";
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "error";
    }
  }
}

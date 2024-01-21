package edu.brown.cs.student.main.server.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FirebaseUtilities implements StorageInterface {


  public FirebaseUtilities() {
    try {
      // Load the service account JSON file as a FileInputStream
      // TODO:
      //  Create /resources/ folder and add your admin SDK from Firebase.
      FileInputStream serviceAccount =
          new FileInputStream(
"src/main/resources/<YOUR_SECRETS>.json");
      FirebaseOptions options =
          new FirebaseOptions.Builder()
              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .build();

      FirebaseApp.initializeApp(options);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public String addDocument(String uid) {
    // adds a document with title 'uid' to your collection.
    try {
      // https://firebase.google.com/docs/firestore/quickstart
      // TODO: use the guide above to implement this handler

      // 1: Get a ref to the collection that you created

      Firestore db = FirestoreClient.getFirestore();
      CollectionReference collectionRef = db.collection("things");
      DocumentReference thingRef = collectionRef.document(uid);

      // 2: Make the data payload to add to your collection

      Map<String, Object> data = new HashMap<>();
      data.put("field1", "value1");
      data.put("field2", 42);

      // 3: Write data to the collection ref

      thingRef.set(data);

      return "Success";
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "error";
    }
  }
}
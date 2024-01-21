package edu.brown.cs.student.main.server.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

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

      // 2: Make the data payload to add to your collection

      // 3: Write data to the collection ref

      return "Success";
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "error";
    }
  }
}

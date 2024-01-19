package edu.brown.cs.student.main.server;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

/***
 * Creates our firebase app.
 */
public class FirebaseInitialize {
  /***
   * This method is used to initialize our firebase app.
   */
  public static void initialize() {
    try {
      // Load the service account JSON file as a FileInputStream
      FileInputStream serviceAccount =
          new FileInputStream(
              "src/main/resources/meikdatabase-firebase-adminsdk-5r9bn-be1c95c791.json");

      //TODO
/*Change this to something like "link to your credentials", also change the resources folder to
* be private, so it doesn't get into github when students put their keys*/

      FirebaseOptions options =
          new FirebaseOptions.Builder()
              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .build();

      FirebaseApp.initializeApp(options);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

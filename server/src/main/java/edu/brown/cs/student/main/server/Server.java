package edu.brown.cs.student.main.server;
import static spark.Spark.before;

import edu.brown.cs.student.main.server.handlers.AddDocumentHandler;
import edu.brown.cs.student.main.server.storage.FirebaseUtilities;
import edu.brown.cs.student.main.server.storage.StorageInterface;
import spark.Spark;

/**
 * Top Level class for our project, utilizes spark to create and maintain our server.
 */
public class Server {

  public static void setUpServer(){
    int port = 3232;
    Spark.port(port);

    before((request, response) -> {
      response.header("Access-Control-Allow-Origin", "*");
      response.header("Access-Control-Allow-Methods", "*");
    });

    StorageInterface firebaseUtils = new FirebaseUtilities();

    Spark.get("addDocument", new AddDocumentHandler(firebaseUtils));
    Spark.notFound(
        (request, response) -> {
          response.status(404); // Not Found
          System.out.println("ERROR");
          return "404 Not Found - The requested endpoint does not exist.";
        });
    Spark.init();
    Spark.awaitInitialization();

    System.out.println("Server started at http://localhost:" + port);
  }

  /**
   * Runs Server.
   *
   * @param args none
   */
  public static void main(String[] args) {
     setUpServer();
  }
}

package edu.brown.cs.student.main.server;
import spark.Spark;

/**
 * Top Level class for our project, utilizes spark to create and maintain our server.
 */
public class Server {


/**Setting up the server this way and not putting everything to
 * run on main so they can use dependency injection for possible mocking**/
  public static void setUpServer(FirebaseInterface firebaseInterface){
    int port = 3232;
    Spark.port(port);

    /**Just added these to check if firebase was working**/
    Spark.get("addPin", new AddInfoHandler(firebaseInterface));
    /**These, along with the classes can be deleted**/
    Spark.notFound(
        (request, response) -> {
          response.status(404); // Not Found
          System.out.println("ERROR");
          return "404 Not Found - The requested endpoint does not exist.";
        });
    Spark.init();
    Spark.awaitInitialization();

    // Notice this link alone leads to a 404... Why is that?
    System.out.println("Server started at http://localhost:" + port);
  }

  /**
   * Runs Server.
   *
   * @param args none
   */
  public static void main(String[] args) {
    try {
      FirebaseInitialize.initialize();
    } catch (Exception e) {
      System.err.println("Could not connect to database: " + e.getMessage());
    }
     setUpServer(new FirebaseUtilities());
    //Example query: http://localhost:3232/myPins?uid=325UsopQ2MdWbewYZDVs6mKKTi62
  }
}

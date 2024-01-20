package edu.brown.cs.student.main.server;

public class MockingUtilities implements FirebaseInterface{

  @Override
  public String addDocument(String uid, String description, String nameOfObject) {
    return "Success!";
  }
}

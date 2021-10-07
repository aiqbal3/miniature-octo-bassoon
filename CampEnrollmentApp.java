//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 Camp Badger
// Files: Camper, CampTreeNode, CamperBST, CampManager,
// CampEnrollmentApp
// Course: 300, Fall, 2019
//
// Author: Adeel Iqbal
// Email: aiqbal3@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CampEnrollmentApp {

  public static void main(String[] args) throws IOException {
    CampManager campMang = new CampManager();
    // campMang.printStatistics();


    // System.out.println("IOException, Error reading file");
    try {
      List<String> fileLines = Files.readAllLines(Paths.get("sim.txt"));
      for (int i = 0; i < fileLines.size(); i++) {
        String[] camperData = fileLines.get(i).split(" ");
        if (fileLines.get(i).charAt(0) == 'S') {
          System.out.println("--- Camp Statistics ---");
          campMang.printStatistics();
          System.out.println("-----------------------");
        } else if (fileLines.get(i).startsWith("E")) {
          try {
            campMang.enrollCamper(
                new Camper(camperData[2], camperData[1], Integer.parseInt(camperData[3])));
            System.out
                .println("Enrollment of " + camperData[2] + " " + camperData[1] + " successful!");
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        } else if (fileLines.get(i).startsWith("R")) {
          try {
            
            campMang.unenrollCamper(new Camper(camperData[2], camperData[1], 10));
            System.out
                .println("Unenrollment of " + camperData[2] + " " + camperData[1] + " Successful!");
          } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
          }
        } else if (fileLines.get(i).startsWith("T")) {
          System.out.println("--- " + camperData[1] + " Traversal ---");
          Iterator<Camper> iter = campMang.traverse(camperData[1]);
          while (iter.hasNext()) {
            System.out.println(iter.next().toString());
          }

          System.out.println("--------------------------");
        }
      }
    } catch (IOException e) {
      throw new IOException("IOException in main");
    }
  }

}

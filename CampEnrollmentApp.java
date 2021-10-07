//
// Title: P09 Camp Badger
// Files: Camper, CampTreeNode, CamperBST, CampManager,
// CampEnrollmentApp
//
// Author: Adeel Iqbal
// Email: aiqbal3@wisc.edu


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

import java.util.Iterator;
import java.util.NoSuchElementException;
// Title:           P09 Camp Badger
// Files:           Camper, CampTreeNode, CamperBST, CampManager, 
//                  CampEnrollmentApp

// Author:          Adeel Iqbal
// Email:           aiqbal3@wisc.edu



public class CampManager {
  private CamperBST campers;
  private final static String[] CABIN_NAMES =
      new String[] {"Otter Overpass", "Wolverine Woodland", "Badger Bunkhouse"};

  /**
   * Constructor for the CampManager by initializing the campers field
   */
  public CampManager() {
    campers = new CamperBST();
  }

  /**
   * Prints statistics based on the current "state" of the camp. The statistics to be printed is the
   * total number of campers.
   */
  public void printStatistics() {
    System.out.println("Number of Campers: " + campers.size());
  }
  
  /**
   * "Enrolls" a camper by determining their cabin and adding them to the tree.
   * 
   * @param newCamper - the camper to be enrolled into the camp
   */
  public void enrollCamper(Camper newCamper) {
    int ageOfNewCamper = newCamper.getAge(); // gets the age of newCamper
    // if age of camper is between 8 and 9, camper is assigned to first cabin,
    // which is Otter Overpass
    if (ageOfNewCamper <= 9 && ageOfNewCamper >= 8) {
      newCamper.assignCabin(CABIN_NAMES[0]);
      campers.insert(newCamper);
    }
    // if age of camper is between 12 and 10, camper is assigned to second cabin,
    // which is Wolverine Woodland
    else if (ageOfNewCamper <= 12 && ageOfNewCamper >= 10) {
      newCamper.assignCabin(CABIN_NAMES[1]);
      campers.insert(newCamper);
    }
    // if age of camper is between 14 and 13, camper is assigned to third cabin,
    // which is Badger Bunkhouse
    else if (ageOfNewCamper <= 14 && ageOfNewCamper >= 13) {
      newCamper.assignCabin(CABIN_NAMES[2]);
      campers.insert(newCamper);
    } else { // if the camper is too old or too young, person can't be enrolled
      System.out.println("This person is either too old or too young to be in " + "Camp Badger.");
    }
  }
  /**
   * "Unenrolls" a camper by removing them from the tree.
   * @param delCamper - the camper to be unenrolled from the camp
   * @throws NoSuchElementException - if CamperBST.delete throws the exception
   */
  public void unenrollCamper (Camper delCamper) throws NoSuchElementException{    
    try {
      campers.delete(delCamper);
    }catch(NoSuchElementException exception) {
      throw exception;
    }    
  }  
  public Iterator<Camper> traverse(String order){
    return campers.traverse(order);
  }
}

import java.util.Iterator;
import java.util.NoSuchElementException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P09 Camp Badger
// Files:           Camper, CampTreeNode, CamperBST, CampManager, 
//                  CampEnrollmentApp
// Course:          300, Fall, 2019
//
// Author:          Adeel Iqbal
// Email:           aiqbal3@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


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

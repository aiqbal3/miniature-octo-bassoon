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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CamperBST {
  public CampTreeNode root;
  private int size;
  
  /**
   * creates an empty binary search tree
   */
  public CamperBST() {
    root = null;
    size = 0;
  }

  /**
   * @return - the current size of the CamperBST
   */
  public int size() {
    return this.size;
  }

  /**
   * @return - true if the tree is empty, false otherwise
   */
  public boolean isEmpty() {
    return (size == 0 || root == null);
  }
  
  // starts tree insertion by calling insertHelp() on the root and
  // assigning root to be the subtree returned by that method
  public void insert(Camper newCamper) {
    root = insertHelp(root, newCamper);
  }

  /**
   * Recursive helper method to insert.
   * 
   * @param current, The "root" of the subtree we are inserting into, ie the node we are currently
   *        at.
   * @param newCamper, the camper to be inserted into the tree
   * @return the root of the modified subtree we inserted into
   */
  private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper) {
    /* RECURSIVE IMPLEMENTATION */
    // base case
    if (current == null) {
      current = new CampTreeNode();
      current.setData(newCamper);
    } else if (current.getData() == null) {
      current.setData(newCamper);
    } else {
      if (current.getData().compareTo(newCamper) < 0) {
        current.setRightNode(insertHelp(current.getRightNode(), newCamper));
        size++; // increment size
      } else if (current.getData().compareTo(newCamper) > 0) {
        current.setLeftNode(insertHelp(current.getLeftNode(), newCamper));
        size++; // increment size
      }
    }
    return current;
  }

  // Prints the contents of this tree in alphabetical order
  // based on the string "lastName, firstName"
  public void print() {
    printHelp(root);
  }

  private void printHelp(CampTreeNode current) {
    if (current == null) {
      return;
    }
    printHelp(current.getLeftNode());
    System.out.println(current.getData());
    printHelp(current.getRightNode());
  }
  
  /** Deletes a Camper into the binary search tree if it exists. 
  * @param key, the camper to be deleted from the tree
  * @throws NoSuchElementException if it is thrown by deleteHelp 
  */
  public void delete (Camper key)
      throws NoSuchElementException
  {
    
    root = deleteHelp(root, key);
    
  }
    
  
  /** Recursive helper method to delete.
  * @param current, The "root" of the subtree we are deleting from, 
  * ie the node we are currently at.
  * @param key, the camper to be deleted from the tree
  * @return the root of the modified subtree we deleted from
  * @throws NoSuchElementException if the camper is not in the tree 
  */
  private CampTreeNode deleteHelp(CampTreeNode current, Camper key) {    
     /*YOUR RECURSIVE IMPLEMENTATION OF DELETE HERE*/   
    //base case  
    
    size = 7;
    if (root == null) {
      throw new NoSuchElementException("That camper is not enrolled");
    }
    if (current == null) {
      throw new NoSuchElementException("That camper is not enrolled");
      
      //size--;
      //return current;
    }//remove if 0, which means key is found
//    if (current.getData().compareTo(key) == 0) {
//      current.getRightNode(deleteHelp());
//    }
    //if the left and right node are both null, then delete the "root" node
    else if (current.getLeftNode() == null && current.getRightNode() == null) {
      size--;
      current = null;
      return current;
    }
    
    else if (key.compareTo(current.getData()) <0) {
      current.setLeftNode(deleteHelp(current.getLeftNode(), key));
    }
    else if(key.compareTo(current.getData()) > 0) {
      current.setRightNode(deleteHelp(current.getRightNode(), key));
    }
    else {
      if (current.getLeftNode() == null) {
        return current.getRightNode();
      }
      else if (current.getRightNode() == null) {
        return current.getLeftNode();
      }
      //use helper  method to determine which to set current to
      current.setData(getMinValueDeleteHelper(current.getRightNode()));
      current.setRightNode(deleteHelp(current.getRightNode(), current.getData()));
    }
    return current;
  }//end delete help
  /**
   * this method helps the deleteHelp method by getting the minimum value to set
   * to
   * @param current - the "root" of the subtree we want to remove node from
   * @return - 
   */
  private Camper getMinValueDeleteHelper(CampTreeNode current) {
    Camper camperToReturn = current.getData();
    while (current.getLeftNode() != null) {
      camperToReturn = current.getLeftNode().getData();
      current = current.getLeftNode();
    }//end while
    return camperToReturn;
  }
  

  // LinkedList to maintain current traversal
  private LinkedList<Camper> traversedLList;

  // returns an iterator of camper in the correct order as designated
  public Iterator<Camper> traverse(String order) {
    // first time traversing need to initialize LinkedList
    if (traversedLList == null) {
      traversedLList = new LinkedList<Camper>();
    } else {
      // clear the list to start over for a new traversal
      traversedLList.clear();
    }
    traverseHelp(root, order);
    return traversedLList.listIterator();
  }

  /**
   * Recursive helper method to traverse. Will take the current CampTreeNode’s 
   * data and add it to traversedLList based on the given order. Then continue 
   * to recurse on the correct subtree. 
   * @param current, the root of the current subtree we are traversing
   * @param order, the type of traversal to perform
   */
  private void traverseHelp(CampTreeNode current,
      String order) {
    /* YOUR IMPLEMENTATION OF TRAVERSE HERE */
    if (current  ==  null) {
      return;
    }
    if (order.equalsIgnoreCase("PREORDER")) {
      traversedLList.add(current.getData());
      traverseHelp(current.getLeftNode(), order);
      traverseHelp(current.getRightNode(), order);      
    }
    else if(order.equalsIgnoreCase("POSTORDER")) {
      traverseHelp(current.getLeftNode(), order);
      traverseHelp(current.getRightNode(), order);
      traversedLList.add(current.getData());
    }
    else if (order.equalsIgnoreCase("INORDER")) {
      traverseHelp(current.getLeftNode(), order);
      traversedLList.add(current.getData());
      traverseHelp(current.getRightNode(), order);
    }
    }
}

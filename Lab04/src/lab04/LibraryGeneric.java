package lab04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Comparator;

/**
 * Class representation of a library (a collection of library books).
 * 
 */

public class LibraryGeneric<Type> {

  private ArrayList<LibraryBookGeneric<Type>> library;

  public LibraryGeneric() {
    library = new ArrayList<LibraryBookGeneric<Type>>();

  }
  
  /**
   * Add the specified book to the library, assume no duplicates.
   * 
   * @param isbn --
   *          ISBN of the book to be added
   * @param author --
   *          author of the book to be added
   * @param title --
   *          title of the book to be added
   */

  public void add(long isbn, String author, String title) {
 
    library.add(new LibraryBookGeneric<Type>(isbn, author, title));
    
  }

  /**
   * Add the list of library books to the library, assume no duplicates.
   * 
   * @param list --
   *          list of library books to be added
   */

  public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {

    library.addAll(list);
  }

  /**
   * Add books specified by the input file. One book per line with ISBN, author,
   * and title separated by tabs.
   * 
   * If file does not exist or format is violated, do nothing.
   * 
   * @param filename
   */

  public void addAll(String filename) {

    ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

    try {

      Scanner fileIn = new Scanner(new File(filename));

      int lineNum = 1;

      while (fileIn.hasNextLine()) {

        String line = fileIn.nextLine();

        Scanner lineIn = new Scanner(line);

        lineIn.useDelimiter("\\t");

        if (!lineIn.hasNextLong())

          throw new ParseException("ISBN", lineNum);

        long isbn = lineIn.nextLong();

        if (!lineIn.hasNext())

          throw new ParseException("Author", lineNum);

        String author = lineIn.next();

        if (!lineIn.hasNext())

          throw new ParseException("Title", lineNum);

        String title = lineIn.next();

        toBeAdded.add(new LibraryBookGeneric(isbn, author, title));

        lineNum++;
      }

    } catch (FileNotFoundException e) {

      System.err.println(e.getMessage() + " Nothing added to the library.");

      return;

    } catch (ParseException e) {

      System.err.println(e.getLocalizedMessage()

          + " formatted incorrectly at line " + e.getErrorOffset()

          + ". Nothing added to the library.");
      return;
    }
    library.addAll(toBeAdded);
  }

  /**
   * Returns the holder of the library book with the specified ISBN.
   * If no book with the specified ISBN is in the library, returns null.
   * 
   * @param isbn --
   *          ISBN of the book to be looked up
   */
  //OK
  public Type lookup(long isbn) {

        for (int i=0; i<library.size(); i++){

            if(isbn==library.get(i).getIsbn()){

                return library.get(i).getHolder();
            }         
        }
    return null;

  }

  /**
   * Sets the holder and due date of the library book with the specified ISBN.
   * If no book with the specified ISBN is in the library, returns false.
   * If the book with the specified ISBN is already checked out, returns false.
   * Otherwise, returns true.
   * 
   * @param isbn --
   *          ISBN of the library book to be checked out
   * @param holder --
   *          new holder of the library book
   * @param month --
   *          month of the new due date of the library book
   * @param day --
   *          day of the new due date of the library book
   * @param year --
   *          year of the new due date of the library book
   */
//OK
  public boolean checkout(long isbn, Type holder, int month, int day, int year) {
	     for(int i=0; i<library.size(); i++){	     
	         if(isbn==library.get(i).getIsbn()){        
	             if(library.get(i).getHolder() != null){
	                 return false; 
	             }
	             else{
	                GregorianCalendar dueDate= new GregorianCalendar (year, month, day);
	                library.get(i).checkOut(holder, dueDate);       
	                return true;
	             }
	         }
	     }
	    return false;
	  }

  /**
   * Returns the list of library books checked out to the specified holder.
   * 
   * If the specified holder has no books checked out, returns an empty list.
   * 
   * @param holder --
   *          holder whose checked out books are returned
   */
  //OK
  public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {

	     ArrayList<LibraryBookGeneric<Type>> bookList = new ArrayList<LibraryBookGeneric<Type>>();

	  for (int i=0; i<library.size(); i++){
		   if(library.get(i).getHolder() != null){
	          if((library.get(i).getHolder()).equals(holder)){
	              bookList.add(library.get(i));
	          }
		   }
	      }

	      return bookList;
	  }  

  /**
   * Unsets the holder and due date of the library book.
   * If no book with the specified ISBN is in the library, returns false.
   * If the book with the specified ISBN is already checked in, returns false.
   * Otherwise, returns true.
   * 
   * @param isbn -
   *          ISBN of the library book to be checked in
   */
  //OK
  public boolean checkin(long isbn) {
	  boolean returned = false;
	  for (int i=0; i<library.size(); i++){
		  if(isbn == library.get(i).getIsbn()){
			  if(library.get(i).getHolder() == null){
	                 returned= false; 
	             }else{
	          	 library.get(i).checkIn();
	             returned = true;
	             }
		  }
      }
		  return returned;
  }

  /**
   * Unsets the holder and due date for all library books checked out by the
   * specified holder.
   * If no books with the specified holder are in the library, returns false;
   * Otherwise, returns true.
   * 
   * @param holder --
   *          holder of the library books to be checked in
   */
  //OK

  public boolean checkin(Type holder) {
   boolean returned = false;
   int counter= 0; 
   for (int i=0; i<library.size(); i++){
        if((holder).equals(library.get(i).getHolder())){
            library.get(i).checkIn();
            returned=true;
             counter++;              
        }      
        if (counter>0){
         returned= true;
       }else{
         returned= false;
     }
   } 
  return returned;
  }
  
  /**
   * Returns the list of library books, sorted by ISBN (smallest ISBN first).
   */
  public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
    ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
    libraryCopy.addAll(library);

    OrderByIsbn comparator = new OrderByIsbn();

    sort(libraryCopy, comparator);

    return libraryCopy;
  }
  
  /**
   * Performs a SELECTION SORT on the input ArrayList. 
   *    1. Find the smallest item in the list. 
   *    2. Swap the smallest item with the first item in the list. 
   *    3. Now let the list be the remaining unsorted portion 
   *       (second item to Nth item) and repeat steps 1, 2, and 3.
   */
  private static <ListType> void sort(ArrayList<ListType> list,
      Comparator<ListType> c) {
    for (int i = 0; i < list.size() - 1; i++) {
      int j, minIndex;
      for (j = i + 1, minIndex = i; j < list.size(); j++)
        if (c.compare(list.get(j), list.get(minIndex)) < 0)
          minIndex = j;
      ListType temp = list.get(i);
      list.set(i, list.get(minIndex));
      list.set(minIndex, temp);
    }
  }

  /**
   * Comparator that defines an ordering among library books using the ISBN. DONE
   */
  protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {
   public int compare(LibraryBookGeneric<Type> lhsIsbn, LibraryBookGeneric<Type> rhsIsbn){
    if (lhsIsbn.getIsbn()== rhsIsbn.getIsbn()){
      return 0;
    }
    if (lhsIsbn.getIsbn()<rhsIsbn.getIsbn()){
      return -1;
    }
    if (lhsIsbn.getIsbn()>rhsIsbn.getIsbn()){
      return 1;
    }
     return-1;
    }
  }
  /**
   * Returns the list of library books, sorted by author. DONE?
   */
  public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
   ArrayList<LibraryBookGeneric<Type>> authorList = new ArrayList<LibraryBookGeneric<Type>>();
   authorList.addAll(library);
 
   OrderByAuthor comparator = new OrderByAuthor();
 
    sort(authorList, comparator);

   return authorList;
  }
 
 /**
  * Returns the list of library books whose due date is older than the input
  * date. The list is sorted by date (oldest first).
  *
  * If no library books are overdue, returns an empty list.
  */

  public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day,int year) {
   ArrayList<LibraryBookGeneric<Type>> libraryOverdue = new ArrayList<LibraryBookGeneric<Type>>();  
   GregorianCalendar currentDate= new GregorianCalendar (year, month, day);
    for (int i=0; i<library.size(); i++){
        if(library.get(i).getDueDate()!= null 
          && library.get(i).getDueDate().compareTo(currentDate)<0){
          libraryOverdue.add(library.get(i));  
        }
    } 
    OrderByDueDate comparator = new OrderByDueDate(); 
    sort(libraryOverdue, comparator);
    return libraryOverdue;
   }
   /**
   * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
   * DONE?
   */

  protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {
public int compare(LibraryBookGeneric<Type> book1 , LibraryBookGeneric<Type> book2){
	
  if (book1.getAuthor().equals(book2.getAuthor())){
        if(book1.getTitle().equals(book2.getTitle())){
          return 0;
        }
        if ((book1.getTitle()).compareTo(book2.getTitle())<0){
         return -1;
        }
        if ((book1.getTitle()).compareTo(book2.getTitle())>0){
         return 1;
        }
  }
  if ((book1.getAuthor()).compareTo(book2.getAuthor())<0){
    return -1;
  }

  if ((book1.getAuthor()).compareTo(book2.getAuthor())>0){
   return 1;
  }
  return-1;
 } 
}
  
/**
* Comparator that defines an ordering among library books using the due date.
*/
 protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {
   public int compare(LibraryBookGeneric<Type> book1 , LibraryBookGeneric<Type> book2){

   if (book1.getDueDate().equals(book2.getDueDate())){
       if(book1.getDueDate().equals(book2.getDueDate())){
        return 0;
       }
       if ((book1.getDueDate()).compareTo(book2.getDueDate())<0){
        return -1;
       }
       if ((book1.getDueDate()).compareTo(book2.getDueDate())>0){
        return 1;
       }
  }
   if ((book1.getDueDate()).compareTo(book2.getDueDate())<0){
      return -1;
   }
   if ((book1.getDueDate()).compareTo(book2.getDueDate())>0){
     return 1;
   }
  return-1;
  }
 }
}
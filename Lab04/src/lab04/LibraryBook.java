package lab04;
import java.util.GregorianCalendar;

public class LibraryBook extends Book{
   
    String holder;
    GregorianCalendar dueDate;
   
    public LibraryBook (long isbn, String author, String title){
        super(isbn, author, title);
        //TODO auto-generated constructor stub
    }
   
    public String getHolder(){
        return this.holder;
        //similar to what we have in book to return author
    }
   
    public GregorianCalendar getDueDate(){
        return this.dueDate;
    }
   
    public void checkIn(){
        holder= null;
        dueDate=null;
    }
   
    public void checkOut(String holder, GregorianCalendar dueDate){
        this.holder = new String(holder);
        this.dueDate = new GregorianCalendar (dueDate.get(GregorianCalendar.YEAR), 
        		dueDate.get(GregorianCalendar.MONTH),
        		dueDate.get(GregorianCalendar.DATE));
       
        // create a new String
        // new GregorianCalendar obj
        //year, month, date
        // dueDate.get(GregorianCalendar.YEAR)
        //same format for the month and date
    }
}
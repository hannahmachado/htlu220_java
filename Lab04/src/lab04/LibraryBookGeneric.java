package lab04;
import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{
   
    Type holder;
    GregorianCalendar dueDate;
   
    public LibraryBookGeneric (long isbn, String author, String title){
        super(isbn, author, title);
        //TODO auto-generated constructor stub
    }
   
    public Type getHolder(){
        return this.holder;
        //similar to what we have in book to return author
    }
   
    public GregorianCalendar getDueDate(){
        return this.dueDate;
    }
   
    public void checkIn(){
       this.holder= null;
       this.dueDate=null;
    }
   
    public void checkOut(Type holder, GregorianCalendar dueDate){
        this.holder = holder;
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
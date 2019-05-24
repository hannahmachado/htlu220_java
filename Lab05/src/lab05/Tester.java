package lab05;

public class Tester {
	public static void main(String[] args) {
      BinarySearchSet testerList = new BinarySearchSet(); 
      System.out.println(testerList.size());
	  System.out.println(testerList.isEmpty());
	  
	testerList.binary_add(77);
    testerList.binary_add(2);
    //testerList.binary_add(30);
    //////testerList.binary_add(14);
    //////testerList.binary_add(23);
    testerList.binary_add(99);
    //testerList.binary_add(57);
    testerList.binary_add(15);
    
    System.out.println("contains 99: " + testerList.contains(99));
    System.out.println("remove 99: " + testerList.remove(99));
    System.out.println("contains 30: " + testerList.contains(30));
    System.out.println("contains 99: " + testerList.contains(99));
    System.out.println("remove 01: " + testerList.remove(01));
    System.out.println("contains 3: " + testerList.contains(3));
	  
	  
   }



}

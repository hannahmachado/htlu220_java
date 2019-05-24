package lab01;

public class CoinFlipExperiment {
  static public int coinFlipExperiment (){
          int Winnings=0;
           for (int a=0; a<100; a++){
             double flip = Math.random();
              if (flip < 0.505) {
                   Winnings++;
                  }
                  else {
                   Winnings--;
                } 
          }
            return Winnings;
    }
    public static void main(String[] args) {
      int amount= coinFlipExperiment ();
       System.out.println("Win/loss amount: " + amount);   
    }
}
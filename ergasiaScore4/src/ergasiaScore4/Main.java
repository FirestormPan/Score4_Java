package ergasiaScore4;

import java.util.Scanner;
import java.util.Random;
public class Main {
	
	public static void main(String[] args) {
				
		Player player1 = new Player();
		Player player2 = new Player();
		Player[] players= {player1,player2};
		

		Scanner in = new Scanner(System.in);
		System.out.println( player1.getName() + "please choose your symbol ( 'x' or 'o' ) " );
		char c = in.next(".").charAt(0);
		if(c == 'x' || c == 'X') { //an epile3ei to x tou anath8etai  to x ,an pathsei otidhpote allo, to o
			player1.setSymbol('x');
			player2.setSymbol('o');
		}else {
			player1.setSymbol('o');
			player2.setSymbol('x');
		}
		
		/*_*/	       /* = */

		Tablo tabladur = new Tablo();
		
		Random rand = new Random(); 
		if(rand.nextGaussian() > 0.5) { //οποιος ειναι πρωτος στο array θα παιξει πρωτος. 50% πιθανοτητα να αλλαξουν σειρα απο την αρχικοποιημενη
			players[0]=player2;
			players[1]=player1;
		}
		
		/*.*/
		
		//let the games begin!
		int roundCounter=0;
		boolean	weHaveAwinner = false;
		while(!weHaveAwinner ) {
		roundCounter++;		
		weHaveAwinner = tabladur.makeMoove(players[roundCounter%2]);//το roundCounter%2 παιρνει τιμες 0 και 1, και κανει την κινηση του ο αντιστοιχος παικτης (απο το players array) 
	
		if(weHaveAwinner) {
			System.out.println("~GAME OVER~ game ended after " + roundCounter +" rounds, and the winner is " + players[roundCounter%2].getName() );
		}
		else if(tabladur.isDraw()) {
			System.out.println("~GAME OVER~ game ended after " + roundCounter +" rounds on a Draw" );
			break;
		}
			

			

		
		}

		
		
		
	}

	
	
		
}
	
	





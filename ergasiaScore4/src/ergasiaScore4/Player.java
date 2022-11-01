package ergasiaScore4;

import java.util.Scanner;

public class Player {

	private String name; 
	private char symbol;

	public Player(){ //constructoras
		Scanner in = new Scanner(System.in);
		System.out.println("please enter the player's name (minimum 3 characters): ");
		name = in.nextLine();
		while(name.length() < 3) {
			System.out.println("try entering again. Name has to be minimum 3 characters: ");
			name = in.nextLine();
		}
			
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public String getName(){
		return name;
	}
	
	public char getSymbol(){
		return symbol;
	}
	
}
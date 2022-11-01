package ergasiaScore4;

import java.util.Scanner;

public class Tablo {
	
	int numberOfRows = getNumberOfRowsOrCollumns("Gramwn");
	int numberOfCollumns =getNumberOfRowsOrCollumns("Sthlwn");

	char[][] Tabaltur = new char[numberOfRows][numberOfCollumns];
	
	public Tablo() {	
		
		for(int i=0; i<numberOfRows; i++) {
			for(int j=0; j<numberOfCollumns; j++)
				Tabaltur[i][j]='-';
		}
		 printTable();
	}

	
	public int getNumberOfRowsOrCollumns(String collsOrRows) { 
		//pairnei gia parametro String me timh "Sthlwn" h "Gramwn" gia na to typwsei sthn protroph
		int numberOfRowsOrCollumns;
		do{
			System.out.println("dwse ton ari8mo twn " + collsOrRows + " (4-14): ");
			Scanner in = new Scanner(System.in);
			 numberOfRowsOrCollumns =in.nextInt();
		}while( numberOfRowsOrCollumns<4 || numberOfRowsOrCollumns>15);
		return numberOfRowsOrCollumns;
	}
	
	public boolean makeMoove(Player turnPlayer) {
		//get the collumn
		  Scanner in = new Scanner(System.in);
		  int CollumnNumber; //η στηλη στην οποια θα εισαχθει το συμβολο
		   do{ 
			  System.out.println("hey " + turnPlayer.getName() + ", its your turn! choose an unfilled collumn column(1 to "+ numberOfCollumns +")");
			  CollumnNumber = in.nextInt() - 1; 
		  }while(CollumnNumber>=numberOfCollumns || CollumnNumber<0  || Tabaltur[0][CollumnNumber] != '-'); //ελεγχος να μην βγω εξω απο τον πινακα και αν ειναι γεματη η στηλη
		  //
		  int i=1;
			for(i=1; i<numberOfRows; i++) {
				if(Tabaltur[i][CollumnNumber] != '-'  &&   Tabaltur[i-1][CollumnNumber] == '-' ) 
				break;
			} 
	
			Tabaltur[i-1][CollumnNumber]=turnPlayer.getSymbol();
			printTable();
			return checkForWinners(i-1,CollumnNumber,turnPlayer.getSymbol());
	}
	
	public boolean isDraw() { //ελεγχει ολες της κορυφες των στηλων εαν υπαρχει καποια με - 
		int j=0;
		while( j<numberOfCollumns && Tabaltur[0][j] !='-' )
			j++;
		
		return ( j == numberOfCollumns  );
	}
	
	public boolean checkForWinners(int insertedRow ,int insertedCollumn, char symbol ) { 
		//θα ελεγξω μονο γυρω απο το σημειο οπου εισηχθη το τελευταιο συμβολο για συνδιασμους που επιβαιβεωνουν νικητη
		//τις μεθοδους που καλουνται εδω τις εκανα private καθως εχουν νοημα μονο οταν καλουνται απο αυτην την συναρτηση
		return ( checkKa8eta(insertedRow,insertedCollumn,symbol) 
				|| checkOrizondia(insertedRow,insertedCollumn,symbol) 
				|| ckeckDiagwnia_panwProsKatw(insertedRow,insertedCollumn,symbol)
				||  ckeckDiagwnia_katwProsPanw(insertedRow,insertedCollumn,symbol)
			);

	}
	
	
	private boolean checkKa8eta(int insertedRow ,int insertedCollumn, char symbol) {
		int chainlinks=1; //ο αριθμος τον συνεχομενων συμβολων ,σε τετοια σειρα ωστε να οδηγουν σε νικη
		int currentRow=insertedRow;
		/*
	ελεγχω αν οι παρακατω θεσεις(απο αυτην που εισηχθη το συμβολο) εχουν το συμβολο του παικτη εως οτου συναντησω διαφορετικο συμβολο ή εχω chainlink=4 ή βγω εξω απο τα ορια του πινακα
		*/
		while(currentRow+1 < numberOfRows  && chainlinks<4) {
			currentRow++;
			if(Tabaltur[currentRow][insertedCollumn] == symbol) {
				chainlinks++;
			}else break;
		}
		
		return(chainlinks==4);
	}
	
	private boolean checkOrizondia(int insertedRow ,int insertedCollumn, char symbol) {
		//ιδια λογικη με το καθετο ,αλλα εδω ελεγχω και αριστερα και δεξια του σημειου οπου εισηχθη το συμβολο
		int chainlinks=1;
		int currentColl=insertedCollumn;
		while(currentColl>0 && chainlinks<4) {
			currentColl--;
			if(Tabaltur[insertedRow][currentColl] == symbol) {
				chainlinks++;
			}else break;
		}
		currentColl=insertedCollumn;
		while(currentColl+1 < numberOfCollumns  && chainlinks<4) {
			currentColl++;
			if(Tabaltur[insertedRow][currentColl] == symbol) {
				chainlinks++;
			}else break;
		}
		return(chainlinks==4);
		
	}
	
	private boolean ckeckDiagwnia_panwProsKatw( int insertedRow ,int insertedCollumn, char symbol ) {

		int chainlinks=1;
		int currentRow=insertedRow;
		int currentCollumn=insertedCollumn;
		
		while(currentRow>0  &&   currentCollumn>0    &&   chainlinks<4) { //bhma panw
			currentRow--;
			currentCollumn--;
			if(Tabaltur[currentRow][currentCollumn] == symbol) {
				chainlinks++;
			}else break;
			
		}
		currentRow=insertedRow;
		currentCollumn=insertedCollumn;
		while(currentRow+1<numberOfRows   &&  currentCollumn+1<numberOfCollumns    &&   chainlinks<4) {
			currentRow++;
			currentCollumn++;
			if(Tabaltur[currentRow][currentCollumn] == symbol) {
				chainlinks++;
			}else break;		
		}
				
		return(chainlinks==4);
	}
	
	private boolean ckeckDiagwnia_katwProsPanw( int insertedRow ,int insertedCollumn, char symbol ) {
		//ιδια λογικη με τα παραπανω αλλα εδω ελεγχω διαγωνια του σημειου εισαγωγης. συμπληροματικα η απο κατω μεθοδος ->
		int chainlinks=1;
		int currentRow=insertedRow;
		int currentCollumn=insertedCollumn;
		while(currentRow+1 < numberOfRows   &&  currentCollumn>0    && chainlinks<4) {
			currentRow++;
			currentCollumn--;
			if(Tabaltur[currentRow][currentCollumn] == symbol) {
				chainlinks++;
			}else break;
			
		}

		currentRow=insertedRow;
		currentCollumn=insertedCollumn;
		while(currentRow>0   &&  currentCollumn+1<numberOfCollumns    && chainlinks<4) {
			currentRow--;
			currentCollumn++;
			if(Tabaltur[currentRow][currentCollumn] == symbol) {
				chainlinks++;
			}else break;
			  
		}
				
		return(chainlinks==4);
	}
	
	
	
  	public void printTable() {
		System.out.println("");
		for(int i=0; i<numberOfRows; i++) {
			System.out.print("|");
			for(int j=0;j<numberOfCollumns; j++){
				System.out.print(" " + this.Tabaltur[i][j] + " ");	
			}
			System.out.println("|");	
		}
		
		for(int i=-1; i<numberOfCollumns;i++) //η διαχωριστικη γραμμη θελω να εχει μηκος(σε χαρακτηρες) τοσο οσο και η καθε γραμμη του σκορ4  
			System.out.print("---");
		
		System.out.println("");
		
		for(int i=1; i<numberOfCollumns+1; i++)
			System.out.print("  " + i);
		
		System.out.println();
	}

	}

package PJ4;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. One Pair: one pair of the same card
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,10,25,50,1000};
    private static final String[] goodHandTypes={ 
	  "One Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /** default constructor, set balance = startingBalance */
    public VideoPoker()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public VideoPoker(int balance)
    {
	this.playerBalance= balance;
        oneDeck = new Decks(1, false);
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		if (i == 4){
		System.out.println(goodHandTypes[i]+"\t\t|\t"+multipliers[i]);
		}
		else
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
    {// implement this method!
    	 List<Integer> sortedHand = new ArrayList<>();
         for (int i = 0; i <= 4; i++) {
             sortedHand.add(playerHand.get(i).getRank());
         }//end for
         Collections.sort(sortedHand);

         if (isRoyalFlush(sortedHand)) {
             System.out.println(goodHandTypes[8] + "!");
             playerBalance += (playerBet * multipliers[8]);
         } else if (isStraightFlush(sortedHand)) {
             System.out.println(goodHandTypes[7] + "!");
             playerBalance += (playerBet * multipliers[7]);
         } else if (isFourOfaKind(sortedHand)) {
             System.out.println(goodHandTypes[6] + "!");
             playerBalance += (playerBet * multipliers[6]);
         } else if (isFullhouse(sortedHand)) {
             System.out.println(goodHandTypes[5] + "!");
             playerBalance += (playerBet * multipliers[5]);
         } else if (isFlush()) {
             System.out.println(goodHandTypes[4] + "!");
             playerBalance += (playerBet * multipliers[4]);
         } else if (isStraight(sortedHand)) {
             System.out.println(goodHandTypes[3] + "!");
             playerBalance += (playerBet * multipliers[3]);
         } else if (isThreeOfaKind(sortedHand)) {
             System.out.println(goodHandTypes[2] + "!");
             playerBalance += (playerBet * multipliers[2]);
         } else if (isTwoPairs(sortedHand)) {
             System.out.println(goodHandTypes[1] + "!");
             playerBalance += (playerBet * multipliers[1]);
         } else if (isOnePair()) {
             System.out.println(goodHandTypes[0] + "!");
             playerBalance += (playerBet * multipliers[0]);
         } else {
             System.out.println("Sorry, you lost!");
         }
    	
    }//End CheckHands()

    /*************************************************
     *   add new private methods here ....
     *
     *************************************************/
    private boolean isOnePair() {
    	//initialize variables
    	int ace = 0;
    	int two = 0;
    	int three = 0;
    	int four = 0;
    	int five = 0;
    	int six = 0;
    	int seven = 0;
    	int eight = 0;
    	int nine = 0;
    	int ten = 0;
        int jack = 0;    
        int queen = 0;
        int king = 0;


        for (int i = 0; i < playerHand.size(); i++) {
            switch (playerHand.get(i).getRank()) {
                case 1:
                    ace++;
                    break;
                case 2:
                	two++;
                	break;
                case 3:
                	three++;
                	break;
                case 4:
                	four++;
                	break;
                case 5:
                	five++;
                	break;
                case 6:
                	six++;
                	break;
                case 7:
                	seven++;
                	break;
                case 8:
                	eight++;
                	break;
                case 9:
                	nine++;
                	break;
                case 10:
                	ten++;
                	break;
                case 11:
                    jack++;
                    break;
                case 12:
                    queen++;
                    break;
                case 13:
                    king++;
                    break;
                default:
                    break;
            }//end switch
        }//end for
        return (ace == 2 || two == 2 || three == 2 || four == 2 || five == 2 || six == 2 || seven == 2 ||
        		 eight == 2 || nine == 2 || ten == 2 || jack == 2 || queen == 2 || king == 2);
    }//end isOnePair()

    private boolean isTwoPairs(List<Integer> twoPair) {
        int count = 0;
        for (int i = 0; i < twoPair.size() - 1; i++) {
            if (twoPair.get(i) == twoPair.get(i + 1)) {
                count++;
                i += 2;
            }//end if
        }//end for
        return count == 2;
    }//end isTwoPair()

    private boolean isThreeOfaKind(List<Integer> threeOfaKind) {
        int count = 0;
        for (int i = 0; i < playerHand.size() - 1; i++) {
            if (threeOfaKind.get(i) != threeOfaKind.get(i + 1)) {
                break;
            } else {
                count++;
            }//end if-else
        }//end for
        return count == 2;
    }//end isThreeOfaKind()

    private boolean isStraight(List<Integer> straight) {
        int count = 0;
        for (int i = 0; i < straight.size() - 1; i++) {
            if (straight.get(i) + 1 == straight.get(i + 1)) {
                count++;
            }//end if
        }//end for 
        return count == 4;
    }//end isStraight()

    private boolean isFlush() {
        int count = 0;
        for (int i = 0; i < playerHand.size() - 1; i++) {
            if (playerHand.get(i).getSuit() == playerHand.get(i + 1).getSuit()) {
                count++;
            }//end if
        }//end for 
        return count == 4;
    }//end isFlush()

    private boolean isFullhouse(List<Integer> fullHouse) {
        int count = 0;
        for (int i = 3; i < fullHouse.size() - 1; i++) {
            if (isThreeOfaKind(fullHouse) && fullHouse.get(i) == fullHouse.get(i + 1)) {
                count++;
            }//end if 
        }//end for 
        return count == 1;
    }//end isFullHouse()

    private boolean isFourOfaKind(List<Integer> fourOfaKind) {
        int count = 0;
        for (int i = 0; i < playerHand.size() - 1; i++) {
            if (fourOfaKind.get(i) != fourOfaKind.get(i + 1)) {
                break;
            } else {
                count++;
            }//end if-else
        }//end for 
        return count == 3;
    }//end isFourOfaKind()

    private boolean isStraightFlush(List<Integer> straightFlush) {
        return isStraight(straightFlush) && isFlush();
    }//end isStraightFlush()

    private boolean isRoyalFlush(List<Integer> royalFlush) {
        int count = 0;
        if (royalFlush.get(0) == 1) {
            count++;
            for (int i = 1; i < playerHand.size() - 1; i++) {
                if (royalFlush.get(i) + 1 == royalFlush.get(i + 1)) {
                    count++;
                }//end if 
            }//end for
        }//end if
        return count == 4;
    }//end isRoyalFlush()

  
	private void replaceHand() {	
    	List<Card> newCards = new ArrayList<>();
    	Card newCard;
        Scanner userInput = new Scanner(System.in);
        int index;
        boolean loopControl = true;
        
        //grabbing more cards for possible replacements 
        try {
        	oneDeck.reset();
        	oneDeck.shuffle();
			newCards = oneDeck.deal(numberOfCards);
		} catch (PlayingCardException e) {
			e.getMessage();
		}	
        
         //replacing cards at specified locations      
       
        	
        	do {
        		System.out.print("Enter positions of cards to replace (e.g. 1 4 5 ): ");
        		String replaceCards = userInput.nextLine();
        	if(replaceCards.contains("1")){
        		index = 0;
        		playerHand.remove(index);
        		newCard = newCards.remove(0);
        		playerHand.add(index, newCard);
        		loopControl = false;
        	}
        	if(replaceCards.contains("2")){
        		index = 1;
        		playerHand.remove(index);
        		newCard = newCards.remove(0);
        		playerHand.add(index, newCard);
        		loopControl = false;
        	}
        	if(replaceCards.contains("3")){
        		index = 2;
        		playerHand.remove(index);
        		newCard = newCards.remove(0);
        		playerHand.add(index, newCard);
        		loopControl = false;
        	}
        	if(replaceCards.contains("4")){
        		index = 3;
        		playerHand.remove(index);
        		newCard = newCards.remove(0);
        		playerHand.add(index, newCard);
        		loopControl = false;
        	}
        	if(replaceCards.contains("5")){
        		index = 4;
        		playerHand.remove(index);
        		newCard = newCards.remove(0);
        		playerHand.add(index, newCard);
        		loopControl = false;
        	}
        	if(replaceCards.contains("12345")){
        		playerHand.addAll(newCards);
        		loopControl = false;
        	}
        	if(replaceCards.isEmpty()){
        		loopControl = false;
        	}
        	}while (loopControl);//end while 
        
    }//end replaceHand();
    
    /*************************************************
     *   end of new private methods
     *
     *************************************************/

    public void play() 
    {// implement this method!
    /** The main algorithm for single player poker game 
     *
     * Steps:
     * 		showPayoutTable()
     *
     * 		++	
     * 		show balance, get bet 
     *		verify bet value, update balance
     *		reset deck, shuffle deck, 
     *		deal cards and display cards
     *		ask for positions of cards to replace 
     *          get positions in one input line
     *		update cards
     *		check hands, display proper messages
     *		update balance if there is a payout
     *		if balance = O:
     *			end of program 
     *		else
     *			ask if the player wants to play a new game
     *			if the answer is "no" : end of program
     *			else : showPayoutTable() if user wants to see it
     *			goto ++
     */
    		showPayoutTable();
    		playerBalance = 1000;
    		playerHand = new ArrayList<>();
    		boolean continueToPlay = true;
    		boolean isBetValid = false;
    		Scanner inputBet = new Scanner(System.in);	
    		Scanner inputReply = new Scanner(System.in);
    		
    		//game loop
    		while(continueToPlay){
    		
    		System.out.println("-----------------------------------");
    		System.out.println("Balance: $" + playerBalance);
    		System.out.print("Enter bet: ");
    		
    		//is bet valid loop
    		while(!isBetValid){
    			playerBet = inputBet.nextInt();
    			if (playerBet <= playerBalance){
    				playerBalance -= playerBet;
    				isBetValid = true;
    			} else {
    				System.out.println("\tBet is too large. Try again.");
    			}
    		}
    		//reset loop condition
    		isBetValid = false;
    		
    		//rest and shuffle deck
    		oneDeck.reset();
    		oneDeck.shuffle();
					
    				//dealing hand
    				try {
    					playerHand = oneDeck.deal(numberOfCards);	
    					System.out.println("Hand: " + playerHand);	
        				replaceHand();
        				System.out.println("Hand: " + playerHand);
    					checkHands();
    				} catch (PlayingCardException e) {
    					System.out.println("PlayingCardException: "+e.getMessage());
    				}//end try-catch
    				
    		//updateBalance		
    		if(playerBalance == 0){		
    			System.out.println("\nNo Funds, \n\tGAME OVER.");
    			continueToPlay = false;
    			
    		}else{
    		
    		String reply;
    		boolean loop = true;
    		//making sure that input is valid 
    		do{
    			//print updated balance and ask to play again
        		System.out.print("\n\nYour balance: $" + playerBalance);
        		System.out.println(", one more game (y or n)?" );
    			reply = inputReply.nextLine();	
    			
    			if (reply.contains("y") || reply.contains("Y")
    				|| reply.contains("n") || reply.contains("N")){
    				loop = false;
    			}//end if 
    		}while(loop);
    		
    		//user continues to play
    		if (reply.contains("y") || reply.contains("Y")){
    			
    			//making sure that input is valid 
    			do{
    				//ask to see payout table
        			System.out.println("Want to see payout table (y or n) ");
    				reply = inputReply.nextLine();
    				if (reply.contains("y") || reply.contains("Y")
    	    				|| reply.contains("n") || reply.contains("N")){
    					loop = true;
    				}//end if 
    			}while(!loop);
    			if (reply.contains("y") || reply.contains("Y")){
    				showPayoutTable();
    			}//end if
    			
    		}//end if
    		else {
				System.out.println("Bye!");
				continueToPlay = false;
			}//end else
    		}//end else-if				
    		}//end while 
    }//end play()

    /*************************************************
     *   Do not modify methods below
    /*************************************************

    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 

    public void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(3,1));
		playerHand.add(new Card(3,10));
		playerHand.add(new Card(3,12));
		playerHand.add(new Card(3,11));
		playerHand.add(new Card(3,13));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(3,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(1,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(3,5));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(4,8));
		playerHand.add(new Card(1,8));
		playerHand.add(new Card(4,12));
		playerHand.add(new Card(2,8));
		playerHand.add(new Card(3,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(4,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(2,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(2,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set One Pair
		playerHand.set(0, new Card(2,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set One Pair
		playerHand.set(2, new Card(4,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set no Pair
		playerHand.set(2, new Card(4,6));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) 
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands();
    }
}

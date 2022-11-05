/**
 * This file contains starter code for the Nim game.  We copied
 * over method names from the TwoPlayerGame interface and provided
 * just enough implementation so that the compiler can compile them
 * without producing any errors.  These implementations are not
 * correct, so you should replace them with real implementations.
 *
 * We also added an empty constructor, which you should implement.
 * 
 * A good place to start is by writing a main method...
 */

//import java class for randomness
import java.util.Random;

//import java scanner class for input
import java.util.Scanner;

public class Nim implements TwoPlayerGame {
    
    //an array to hold the board state
    private int[] board;

    //an int to hold the current player number
    private int currentPlayer;

    //int to hold the number of piles
    private int numberOfPiles;

    /**
     * Initializes a new Nim board game.
     *
     * @param numPiles The number of matchstick piles.
     * @param minMatches The smallest number of matches per pile.
     * @param maxMatches The largest number of matches per pile.
     */
    public Nim(int numPiles, int minMatches, int maxMatches) {
      // initialize the board state using the constructor arguments
      board = new int[numPiles];
    
      //initialize random generator
      Random r = new Random();

      //iterate through the board int array and assign each pile a random integer of matches between minMatches and maxMatches inclusive  
      for(int i = 0; i < numPiles; i++) {
        board[i] = r.nextInt(maxMatches-minMatches+1) + minMatches;
      }

      //set class variable numberOfPiles equal to user variable numPiles
      numberOfPiles = numPiles;

      //set the currentPlayer to 0
      currentPlayer = 0;
    }
  
    /**
     * Returns the current value for a given resource.
     *
     * @param resource Describes the game element.
     * @returns The current value of the resource.
     */  
    public int getResource(int resource) {
        //return the number of matches in that pile
        return board[resource];
    }
    
    /**
     * Sets the game state. Should not check whether the given
     * parameters are valid. isValidMove should be called before
     * calling this method to ensure that a move is legal.
     *
     * @param resource Which resource to alter (e.g., position, gamepiece, pile of matches, etc.)
     * @param updatedValue The updated value of the resource (e.g., how many matches remain, the updated position of a piece, etc.)
     */
    public void setResource(int resource, int updatedValue) {
        board[resource] = updatedValue;
    }
    
    /**
     * Returns the number representing the current player.
     * 
     * @returns The current player.
     */
    public int getPlayer() {
        return currentPlayer;
    }
    
    /**
     * Changes the current player to the given player.
     * 
     * @param player A player number.
     */
    public void setPlayer(int player) {
        currentPlayer = player;
    }
    
    /**
     * Returns true if the specification of a move describes a legal move
     * given all the rules of the game. Note: this does not check whether the
     * move is *good* move, only whether it is legal.
     *
     * @param resource Which resource to alter (e.g., position, gamepiece, pile of matches, etc.)
     * @param updatedValue The updated value of the resource (e.g., how many matches remain, the updated position of a piece, etc.)
     * @return True iff the move is valid.
     */
    public boolean isValidMove(int resource, int updatedValue) {
        //check whether the pile number is legal
        if(resource < 0 || resource > numberOfPiles) {
            return false;
        }
        //check whether the number of matchsticks will be less than 0
        if(updatedValue < 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns true if the game is over.
     * @returns True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        //return false if there are still matches in the piles
        for(int i = 0; i < board.length; i++) {
            if(board[i] != 0) {
                return false;
            }
        }
        //otherwise, return true
        return true;
    }
    
    /**
     * Displays the board on screen.
     */
    public void displayBoard() {
        //display the board

        //first print which player is playing adding one to it so player 1 and 2 instead of 0 and 1
        System.out.println("Player: " + (getPlayer() + 1));

        //then iterate through the board and print each pile number plus 1 along with a number of |'s according to how many matches are in the pile
        for(int i = 0; i < board.length; i++) {
            //print pile number
            System.out.print("[" + (i+1) + "]");
            //print the matchsticks
            for(int j = 0; j < board[i]; j++) {
                System.out.print(" |");
            }
            //print newline between each pile
            System.out.println();
        }
    }

    /**
     * The entry point to the program.  It currently ignores
     * the contents of the args array.
     * @param args
     */
    public static void main(String[] args) {
        //initialize the Nim game
        Nim n = new Nim(5,2,8);

        //create the input scanner
        Scanner input = new Scanner(System.in);

        //game loop
        while(!n.isGameOver()) {
            //print the board
            n.displayBoard();
            
            //ask the player for which pile to choose, subtract 1 to convert to indexing value
            System.out.print("Which pile [1-" + n.numberOfPiles + "]? ");
            int pile = input.nextInt() - 1;


            //ask the player for how many maches to remove
            System.out.print("How many matches [1-" + n.getResource(pile) + "]? ");
            int numberOfMatches = input.nextInt();

            //make sure the input isn't <= 0
            //check that it is a valid move
            if(n.isValidMove(pile, n.getResource(pile) - numberOfMatches) && numberOfMatches > 0) {
                //if it is a valid move, change the game state
                n.setResource(pile, n.getResource(pile) - numberOfMatches);
            }
            else{
                //otherwise, say it is an error and make the other player win
                System.err.println("Error, not a valid move.");

                //change the player
                if(n.getPlayer() == 1){
                    n.setPlayer(0);
                }
                else{
                    n.setPlayer(1);
                }
                
                //now the other player wins
                System.out.println("Player " + (n.getPlayer() + 1) + " wins!");

                //and exit the program
                System.exit(44);
            }

            //change the player
            if(n.getPlayer() == 1){
                n.setPlayer(0);
            }
            else{
                n.setPlayer(1);
            }
        }

        //game is over so print the player other than the current player wins!
        System.out.println("Player " + (n.getPlayer() + 1) + " wins!");
    }
  }
  
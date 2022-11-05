/* Use the TwoPlayerGame interface to implement the
 * "Silver Dollar Game". You may add as many additional
 * methods as you need.
 */

//import the vector class we will use
import structure5.Vector;

//import java class for randomness
import java.util.Random;

//import java scanner class for input
import java.util.Scanner;


public class CoinStrip implements TwoPlayerGame {
  //setup instance variables for class
  //hold the board state - a 1 is a coin and a 0 is no coin
  private Vector<Integer> board;
  //hold the current player
  private int currentPlayer;
  //hold the number of coins the game has
  private int numberOfCoins;

  public CoinStrip() {
    //instantiate the vector
    board = new Vector<Integer>();

    //the current player is 1
    currentPlayer = 1;
    
    //generate the gameboard
    //if the game is over immediately, generate a new board
    do {
      generateBoard();
    } while (isGameOver());

  }
  
  //method to generate a board
  public void generateBoard() {
    //initialize random generator
    Random r = new Random();
    
    //start the game with a random number of coins between 3 and 5 inclusive
    numberOfCoins = r.nextInt(3) + 3;

    //make a coinCount to help poulating the board
    int coinCount = 0;

    while (coinCount < numberOfCoins) {
      if (r.nextBoolean()) {
        board.add(1);
        coinCount += 1;
      } else {
        board.add(0);
      }
    }
  }
  /**
   * Returns the current value for a given resource.
   *
   * @param resource Describes the game element.
   * @returns The current value of the resource.
   */
  public int getResource(int resource) {
    return this.board.get(resource);
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
    this.board.set(resource, updatedValue);
  }
  
  /**
   * Returns the number representing the current player.
   *
   * @returns The current player.
   */
  public int getPlayer() {
    return this.currentPlayer;
  }
  
  /**
   * Changes the current player to the given player.
   *
   * @param player A player number.
   */
  public void setPlayer(int player) {
    this.currentPlayer = player;
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
    //resource is the coinChosen, updatedValue is the numSpacesLeft

    //make sure the index is within the bounds
    if (resource < 0 || resource >= board.size()) {
      return false;
    }
    
    //check if the chosen spot is not a coin or if not moved right
    if (board.get(resource) == 0 || (resource - updatedValue) >= resource) {
      return false;
    }

    //check if there exists a coin between the chosen coin and where it is going or if will land on coin
    for (int i = resource - updatedValue; i < resource; i++) {
      if (board.get(i) == 1) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Returns true if the game is over.
   * @returns True if the game is over, false otherwise.
   */
  public boolean isGameOver() {
    for (int i = 0; i < numberOfCoins; i++) {
      if (getResource(i) == 0) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Displays the board on screen.
   */
  public void displayBoard() {
    //first print which player is playing
    System.out.println("Player: " + getPlayer());

    //print out the board
    for (int i = 0; i < board.size(); i++) {
      if (board.get(i) == 1) {
        System.out.print("[O] ");
      } else {
        System.out.print("[ ] ");
      }
    }
    System.out.println();

    //print numbers underneath the board to show the player options
    for (int i = 0; i < board.size(); i++) {
      System.out.print(i + "   ");
    }
    System.out.println();
  }

  public void changePlayer() {
    //change the player
    if (getPlayer() == 1) {
      setPlayer(2);
    } else {
      setPlayer(1);
    }

  }

  //method for taking a turn
  //The main method of your CoinStrip class should set up the board and then prompt each of two players to make their moves. A move will be of the form:
  //<cn> <ns> where <cn> specifies the coin to be moved and <ns> specifies the number of spaces that coin should be moved.
  public void takeATurn(Scanner input, CoinStrip c) {
      //prompt the user
      System.out.println("Enter the number corresponding to the coin you would like to move and how many spaces to move it");
      System.out.println("For example, 4 2 would move the coin at position 4 two spaces to the left.");
      
      int coinChosen;
      int numSpacesLeft;
      try {
        //get response
        coinChosen = input.nextInt();
        //get response
        numSpacesLeft = input.nextInt();
      //catch an input mismatch error and try again
      } catch (java.util.InputMismatchException e) {
        System.out.println("Input error, try again.");
        input.next();
        takeATurn(input, c);
        return;
      }

      //check if the move is valid and if it is, do it
      if (c.isValidMove(coinChosen, numSpacesLeft)) {
        c.setResource(coinChosen, 0);
        c.setResource(coinChosen - numSpacesLeft, 1);
      //otherwise ask again
      } else {
        System.out.println("Not legal move.  Try again");
        takeATurn(input, c);
      }
  }

  public static void main(String[] args) {
    //make the game board
    CoinStrip c = new CoinStrip();

    //create the input scanner
    Scanner input = new Scanner(System.in);

    //while the game isn't over, do the gameloop
    while (!c.isGameOver()) {
      //display the board
      c.displayBoard();
      
      //then currentPlayer takes a turn
      c.takeATurn(input, c);
      
      //then change the player
      c.changePlayer();
    }
    //if the game is over
    c.changePlayer();
    //say the player who moved last wins
    System.out.println("Player " + c.getPlayer() + " wins!");
  }
}
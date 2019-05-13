import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameController implements PlayerListener {
  private Board board;
  private Dice d1 = new Dice();
  private Dice d2 = new Dice();

  private Player p1;
  private Player p2;
  private Player currentPlayer;
  private Player winner = null;
  private List<Sprite> players = new ArrayList<>();

  private List<GameSpriteListener> spriteListeners = new ArrayList<>();
  private List<GameControlListener> controlListeners = new ArrayList<>();

  private static GameController instance;
  public static GameController getInstance(){
    if(instance == null){
      instance = new GameController();
    }
    return instance;
  }

  private GameController() {
    board = new Board(80);

    p1 = new Player("P1", 0, board, this);
    p2 = new Player("P2", 1, board, this);
    currentPlayer = p1;
    
    players.add(p1);
    players.add(p2);    
  }

  private void rollDice() {
    d1.roll();
    d2.roll();
  }

  public void takeTurn() {
    if(winner != null)	{	  
      JFrame jEnd = new JFrame(); //Can't play when someplayer won
      JOptionPane.showMessageDialog(jEnd,"END");	  
      return;
	}	
    rollDice();
    currentPlayer.walk(d1.getFace() + d2.getFace());
  }

  private void changeTurn() {
    if (currentPlayer == p1)
      currentPlayer = p2;
    else
      currentPlayer = p1;
  }
  //settings black block
  private void blackBlock(){
	  takeTurn();
	  changeTurn();
  }
  
  //settings pink block
  private void pinkBlock(){
	  changeTurn();
  }
  //settings new game
  public void newGame(){
	  
	players.remove(p1);
    players.remove(p2);
	
	board = new Board(80);
    p1 = new Player("P1", 0, board, this);
    p2 = new Player("P2", 1, board, this);
    currentPlayer = p1;
    players.add(p1);
    players.add(p2);   
  }

  @Override
  public void walking(Player owner){
    notifyGamePlayEnabled(false);
    for(GameSpriteListener listener : spriteListeners){
      listener.spriteUpdated();
    }
  }

  @Override
  public void walkCompleted(Player onwer) {        
    changeTurn();
    notifyGamePlayEnabled(true);
  }

  @Override
  public void hasWon(Player owner) {
    if (winner == null) { // first winner only
      winner = owner;
    }
  }

  public Player getWinner() {
    return winner;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public List<Sprite> getSprites(){
    return players;
  }

  public void addSpriteListener(GameSpriteListener listener){
    spriteListeners.add(listener);
  }

  public void addControlListener(GameControlListener listener){
    controlListeners.add(listener);
  }

  public void notifyGamePlayEnabled(boolean enabled){
    for(GameControlListener listener : controlListeners){
      listener.changeGamePlayEnabled(enabled);
    }
  }

}
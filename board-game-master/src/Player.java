import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Player extends Sprite {
  private static Color[] COLORS = {Color.BLUE, Color.MAGENTA };
  private String name;
  private int index;
  private Board board;
  private PlayerListener listener;
  private int currentPos = 0;
  private GameController gc; //settings block

  public Player(String name, int index, Board board, PlayerListener listener) {
    this.name = name;
    this.index = index;
    this.board = board;
    this.listener = listener;
	
  }

  public void walk(int steps) {		
    int next_position = Math.min(currentPos + steps, board.getFinishPos());   
		//JOptionPane.showMessageDialog(null,"Roll = "+steps,"RollScore",1);
		System.out.println("Roll = "+ steps);			
		new WalkerThread(next_position).start();
		System.out.println(this + " walks from " + currentPos + " to " + next_position);	
	
	  //set pink&black block
	 if(next_position==10 || next_position==31 || next_position== 50 || next_position== 71){
		 new WalkerThread(next_position).start(); 	  		 
		 JFrame jPinkBlock = new JFrame(); 
		 JOptionPane.showMessageDialog(jPinkBlock,"Dice Again");	 
		 System.out.println("Dice Again");				  
		
	  }	  
	 else if(next_position==11 || next_position==30 || next_position== 51 || next_position== 70){		
		 JFrame jBlackBlock = new JFrame(); 
		 JOptionPane.showMessageDialog(jBlackBlock,"Return to Start!");			
		 System.out.println("Return to Start!");	
		 currentPos = 0;		 
	  }	  	 
	  
	    	
  }
  public void doWalking(){	 
    listener.walking(this);
  }

  
  

  public void doWalkCompleted(){
    Integer tunnelOutPos = board.getTunnelOutPos(currentPos);
    if(tunnelOutPos != null){
      System.out.println(this + " enter tunnel from " + currentPos + " to " + tunnelOutPos.intValue());
      currentPos = tunnelOutPos.intValue();
      listener.walking(this);
    }
    listener.walkCompleted(this);
    if (currentPos >= board.getFinishPos()) {      
      this.listener.hasWon(this);
    }   
  }

  @Override
  public void draw(Graphics2D g2d){
    if(currentPos > 0){
      g2d.setColor(COLORS[index]);
      Point ref = board.getRefLocationForPos(currentPos);
      g2d.fillOval(ref.x + 10 + (index * 40), ref.y + 30, 20, 20);
    }
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getName();
  }  

  private class WalkerThread extends Thread {
    private int toPos;
    public WalkerThread(int toPos){
      this.toPos = toPos;
    } 
    public void run(){
      while(currentPos < toPos){
        currentPos ++;
        doWalking();
        try{ Thread.sleep(300); } catch(Exception e){}        
      }
      doWalkCompleted();
    }
  }
}
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;



public class ControlPanel extends JPanel implements GameControlListener{
  private GameController gc;
  private JButton playButton,newGameButton;
  private JLabel playerLabel;
  private JLabel aboutLabel,aboutLabel1;

  public ControlPanel(){
    gc = GameController.getInstance();
    gc.addControlListener(this);

    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(new ActionListener(){    
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(1);
      }
    });

    playButton = new JButton("Play");
    playButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        gc.takeTurn();
      }
    });
	//newgame
	newGameButton = new JButton("RESTARTGAME");
    newGameButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        gc.newGame();		
      }
    });
	

    playerLabel = new JLabel("", SwingConstants.CENTER);
	aboutLabel = new JLabel("PINK = Free 1 Dice!!", SwingConstants.CENTER); //description 
	aboutLabel.setForeground(Color.pink);
	aboutLabel1 = new JLabel("BLACK = Return to Start!!", SwingConstants.CENTER); //description
	aboutLabel1.setForeground(Color.black);
    playerLabel.setFont(playerLabel.getFont().deriveFont(24.0f));
	

    updatePlayerLabel();
    setLayout(new GridLayout(0, 1));
    add(playerLabel);
    add(playButton);    
	
	add(aboutLabel);
	add(aboutLabel1);
	add(newGameButton);
	add(exitButton);
  }

  public void changeGamePlayEnabled(boolean enabled){
    playButton.setEnabled(enabled);
    updatePlayerLabel();
  }

  private void updatePlayerLabel(){
    playerLabel.setText(gc.getCurrentPlayer().toString());
  }
}
public class Dice {
	public int dice;  
	
	public Dice(){
		dice = 1;
	}

	public void rollDice(){
		dice = (int)(Math.random()*6 + 1);
	}
 
	public int getDice(){
		return dice;
	}
}//v2

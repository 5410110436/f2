public class Game{
	public int winScore;
	public int sumScore;
	

	public Game(int win){
		winScore = win;
	}
	public void walk(String name,int num){
		sumScore += num;
		if(sumScore >= winScore){
			printWinner(name);
		}
	}

	public void printWinner(String name){
		System.out.println( name + " You are winner");
	}	
	
}//v2

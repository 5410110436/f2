public class CounterTest{
	
	public static void main(String args[]){		
		Counter c = new Counter();	
		
		for(int i=0; i<5; i++){	
			c.inc();
			System.out.print(c.getVal() + ",");
		}			
	}
}

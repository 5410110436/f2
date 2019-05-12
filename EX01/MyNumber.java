public class MyNumber {
    public static void main (String args[]){      
        int input = 10;
        Counter c = new Counter();     
        ///test01
        System.out.println("my number input = " +  input );        

        for(int i = 0 ; i <10 ; i++){
            System.out.println("my number C inc = " + c.inc());
        }
        
        System.out.println("my number C reset = " +  c.reset());
        //test02 
    }  
}
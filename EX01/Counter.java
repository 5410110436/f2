import javax.swing.JOptionPane;

public class Counter {
       
    public int myNumber;
        
    public Counter() {    
        myNumber = 0;           
    }

    public int inc(){     
        return myNumber++;
    }
       
    public int reset(){
        myNumber = 0;
        return myNumber;       
    }    
}
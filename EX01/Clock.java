public class Clock{
	private int hr;
	private int min;
	private int state;

	public Clock(int hour,int minute){
		hr = hour;
		min = minute;
		state = 0;
	}
	// set state v2
	public void pressUp(){
		if(state == 1){
			hr++;
		}
		else if(state == 2){
			min++;
		}
	}
	public void pressDown(){
		if(state == 1){
			hr--;
		}
		else if(state == 2){
			min--;
		}
	}
	public void pressCenter(){
		while(1){
			if(state == 0){
				state = 1;
			}
			else{
				state = 0;
				printTime();
			}
		}
		else{
			switch(state){
				case 1:
					state = 2; break;
				case 2:
					state = 1; break; 
			}
		}
	}
	public void printTime(){
		System.out.println(hr + ":" + min);
	}
}
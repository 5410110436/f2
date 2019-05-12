public class ClockDisplay{
	private NumberDisplay hour;
	private NumberDisplay minute;
	private String time;

	public ClockDisplay(){
		hour = new NumberDisplay(24);
		minute = new NumberDisplay(60);
		setTimeString();
	}

	private void setTimeString(){
		time = hour.getDisplay() + ":" + minute.getDisplay();
	}

	public void setTime(int hour, int minute){
		hour.setVal(hour);
		minute.setVal(minute);
		setTimeString();
	}

	public String getTime(){
		return time;
	}

	
}
package lift;

import java.math.BigDecimal;

public class Eleavtor implements Print {
	private int pos;
	private String state;
	private double etime = 0;
	private String runState;
	
	public Eleavtor(){
		this.pos = 1;
		this.state = "STILL";
	}
	
	public void Go(Queue queue, int time,  int pos){
		if(pos > 0 && pos < 11){
			Request ask = new Request(time, pos, "ER", "=", "=");
			queue.add(ask);
		}else{
			System.out.println("INVALID" + "[" + "ER, " + pos + ", "  + time + "]");
		}
	}
	
	public void run(Request ask){
		
	}
	
	/*public void run(Request ask){
		if(ask.getTo() == this.pos){
			this.state = "STILL";
			if(etime <= ask.getTime()){
				etime = ask.getTime() + 1;
			}else{
				etime = etime + 1;
			}
			this.pos = ask.getTo();
			ask.seteTime(etime);
			System.out.println("("+this.pos+","+this.state+","+etime+")");
		}else if(ask.getTo() > this.pos){
			this.state = "UP";
			if(etime <= ask.getTime()){
				etime = ask.getTime() + (ask.getTo() - this.pos) * 0.5;
			}else{
				etime = etime + (ask.getTo() - this.pos) * 0.5;
			}
			this.pos = ask.getTo();
			ask.seteTime(etime);
			System.out.println("("+this.pos+","+this.state+","+etime+")");
			etime = etime + 1;
			ask.seteTime(etime);
		}else if(ask.getTo() < this.pos){
			this.state = "DOWN";
			if(etime <= ask.getTime()){
				etime = ask.getTime() + (this.pos - ask.getTo()) * 0.5;
			}else{
				etime = etime + (this.pos - ask.getTo()) * 0.5;
			}
			this.pos = ask.getTo();
			ask.seteTime(etime);
			System.out.println("("+this.pos+","+this.state+","+etime+")");
			etime = etime + 1;
			ask.seteTime(etime);
		}
	}*/
	
	public int getPos(){
		return this.pos;
	}
	
	public String getState(){
		return this.state;
	}
	
	public double getEtime(){
		return etime;
	}

	public void print(int pos, String state, double etime, Request ask) {
		int i =(int) (ask.getTime());
		if(ask.getType().equals("ER")){
			System.out.println("["+ ask.getType()+ ", " + ask.getTo() + ", " + i+ "]" + " / " +"("+pos+","+state+","+etime+")");
		}else{
			System.out.println("["+ ask.getType() + ", " +ask.getTo() + ", " + ask.gettheWay() + ", " +i+ "]" + " / " +"("+pos+","+state+","+etime+")");
		}	
	}
	public void getRunstate(Sche sche){
		this.runState = sche.toString();
	}
}

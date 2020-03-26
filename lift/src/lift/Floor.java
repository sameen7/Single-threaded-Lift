package lift;

public class Floor {
	
	public void Up(Queue queue, int time,  int pos){
		if(pos < 10 && pos > 0){
			Request ask = new Request(time, pos, "FR", "+", "UP");
			queue.add(ask);
		}else{
			System.out.println("INVALID" + "[" + "FR, " + pos + ", " + "UP, " + time + "]");
		}
	}
	
	public void Down(Queue queue, int time,  int pos){
		if(pos > 1 && pos < 11){
			Request ask = new Request(time, pos, "FR", "-", "DOWN");
			queue.add(ask);
		}else{
			System.out.println("INVALID" + "[" + "FR, " + pos + ", " + "DOWN, " + time + "]");
		}
	}

}

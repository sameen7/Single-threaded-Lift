package lift;

public class Sche extends Scheduler implements Print {
	private int  tofloor = 0;
	//private Queue queue;
	//private Queue doqueue;
	private Eleavtor lift;
	private double time = 0;
	private String state = "STILL";
	private  int floor = 1;
	
	public Sche(Queue queue, Eleavtor lift) {
		super(queue);
		this.lift = lift;
	}
	
	public void command(){
		if(this.queue.isEmpty()){
			System.exit(0);
		}else{
			Request ask = this.queue.getAsk();
			if(!pass(ask)){
				this.time += 1;
				this.lift.print(ask.getTo(), this.state, this.time, ask);
				ask.seteTime(this.time);
				this.doqueue.doAdd(ask);
			}else{
				print(floor, state, time, ask);
			}
			while(!this.queue.isEmpty()){
				int flag = 0;
				ask = queue.getdoAsk(0);
				int tofloor;
				if(!pass(ask)){
					tofloor = ask.getTo();
					if(ask.getTime() > this.time){
						this.time = ask.getTime();
					}
					if(tofloor > this.floor){
						this.state = "UP";
						ask.setWay("+");
					}else if(tofloor < this.floor){
						this.state = "DOWN";
						ask.setWay("-");
					}else{
						this.state = "STILL";
					}
					if(this.state.equals("UP")){
						for(int i = this.floor; i <= tofloor; i++){
							for(int k = 0; k < this.queue.size(); k++){
								ask = this.queue.getdoAsk(k);
								if(!pass(ask)){
									if(ask.getTime() < this.time && !ask.getWay().equals("-") && ask.getTo() != this.floor){
										if(tofloor < ask.getTo() && ask.getType().equals("ER")){
											tofloor = ask.getTo();
										}
										if(i == ask.getTo()){
											flag = 1;
											lift.print(i, this.state, time, ask);
											ask.seteTime(this.time + 1);
											this.doqueue.doAdd(ask);
											this.queue.remove(k);
											k--;
										}
									}
								}else{
									print(floor, state, time, ask);
									this.queue.remove(k);
									k--;
								}
							}
							if(flag == 1){
								this.time += 1;
							}
							if(i != tofloor){
								this.time += 0.5;
							}
							flag = 0;
						}
						this.floor = tofloor;
					}else if(this.state.equals("DOWN")){
						for(int i = this.floor; i >= tofloor; i--){
							for(int k = 0; k < this.queue.size(); k++){
								ask = this.queue.getdoAsk(k);
								if(!pass(ask)){
									if(ask.getTime() < this.time && !ask.getWay().equals("+") && ask.getTo() != this.floor){
										if(tofloor > ask.getTo() && ask.getType().equals("ER")){
											tofloor = ask.getTo();
										}
										if(i == ask.getTo()){
											flag = 1;
											lift.print(i, this.state, this.time, ask);
											ask.seteTime(this.time + 1);
											this.doqueue.doAdd(ask);
											this.queue.remove(k);
											k--;
										}
									}
								}else{
									print(floor, state, time, ask);
									this.queue.remove(k);
									k--;
								}
							}
							if(flag == 1){
								this.time += 1;
							}
							if(i != tofloor){
								this.time += 0.5;
							}
							flag = 0;
						}
						this.floor = tofloor;
					}else{
						if(ask.getTime() > this.time){
							this.time = ask.getTime();
						}
						this.time += 1;
						lift.print(tofloor, this.state, this.time, ask);
						ask.seteTime(this.time);
						this.doqueue.doAdd(ask);
						this.queue.remove(0);
					}
				}else{
					print(floor, state, time, ask);
					this.queue.remove(0);
				}
			}
		}
	}

	public void print(int pos, String state, double etime, Request ask) {
		int i =(int) (ask.getTime());
		if(ask.getType().equals("ER")){
			System.out.println("SAME" + "["+ ask.getType()+ ", " + ask.getTo() + ", " + i+ "]" );
		}else{
			System.out.println("SAME" + "["+ ask.getType() + ", " +ask.getTo() + ", " + ask.gettheWay() + ", " +i+ "]");
		}	
	}
	
	public String toString(){
		return this.state + ":" +this.time;
	}
}

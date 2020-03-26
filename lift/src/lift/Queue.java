package lift;

import java.util.ArrayList;

public class Queue implements Print{
	private ArrayList<Request> list;
	
	public Queue(){
		this.list = new ArrayList<Request>();
	}
	
	public void add(Request ask){
		if(this.list.isEmpty()){
			if(ask.getTime() == 0 && ask.getType().equals("FR") && ask.gettheWay().equals("UP") && ask.getTo() == 1){
				this.list.add(ask);
			}else{
				print(0, null, 0, ask);
				//System.exit(0);
			}
		}else{
			if(ask.getTime() > this.list.get(this.list.size() - 1).getTime()){
				this.list.add(ask);
			}else if(ask.getTime() == this.list.get(this.list.size() - 1).getTime()){
				if(ask.getTo() == this.list.get(this.list.size() - 1).getTo()){
					if(!ask.getType().equals(this.list.get(this.list.size() - 1).getType())){
						this.list.add(ask);
					}else if(!ask.getWay().equals(this.list.get(this.list.size() - 1).getWay())){
						this.list.add(ask);
					}else{
						print(0, null, 0, ask);
					}
				}else{
					this.list.add(ask);
				}
			}else{
				print(0, null, 0, ask);
			}
		}
	}
	
	public Request getAsk(){
		Request ask = this.list.get(0);
		this.list.remove(0);
		return ask;
	} 
	
	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	
	public int size(){
		return this.list.size();
	}
	
	public Request getdoAsk(int i){
		return this.list.get(i);
	} 
	
	public void add(int i, Request ask){
		this.list.add(i, ask);
	}
	
	public void remove(int i){
		this.list.remove(i);
	}
	
	public void doAdd(Request ask){
		this.list.add(ask);
	}

	public void print(int pos, String state, double etime, Request ask) {
		int i =(int) (ask.getTime());
		if(ask.getType().equals("ER")){
			System.out.println("INVALID" + "["+ ask.getType()+ ", " + ask.getTo() + ", " + i+ "]" );
		}else{
			System.out.println("INVALID" + "["+ ask.getType() + ", " +ask.getTo() + ", " + ask.gettheWay() + ", " +i+ "]");
		}	
	}
}

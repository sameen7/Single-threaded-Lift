package lift;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lift {
	private Scheduler sch;
	private Queue queue;
	private Eleavtor lift;
	private Sche sche;
	
	public Lift(){
		this.queue = new Queue();
		this.sch = new Scheduler(this.queue);
		this.lift = new Eleavtor();
		this.sche = new Sche(this.queue, this.lift);
	}
	
	public void fAsk(int pos, String way, int time){
		if(pos > 0 && pos < 11 && time >= 0){
			Floor floor = new Floor();
			if(way.equals("+")){
				floor.Up(this.queue, time, pos);
			}else{
				floor.Down(this.queue, time, pos);
			}
		}else{
			if(way.equals("+")){
				System.out.println("INVALID" + "[" + "FR, " + pos + ", " + "UP, " + time + "]");
			}else{
				System.out.println("INVALID:" + "[" + "FR, " + pos + ", " + "DOWN, " + time + "]");
			}
		}
	}
	
	public void eAsk(int pos, int time){
		if(pos > 0 && pos < 11 && time >= 0){
			this.lift.Go(this.queue, time, pos);
		}else{
			System.out.println("INVALID" + "[" + "ER, " + pos + ", "  + time + "]");
		}
	}
	
	public void runlift(){
		/*while(!this.queue.isEmpty()){
			this.sch.command(this.lift);
		}*/
		this.sche.command();
	}
	
	public static void  main(String[] args){
		Lift LIFT = new Lift();
		String input = new String();
		Scanner in = new Scanner(System.in);
		try{
			input = in.nextLine().replace(" ", "");
		}catch(Exception e){
			//System.exit(0);
		};
		while(!input.equals("run")){
			Pattern pat1 = Pattern.compile("\\(FR,(\\+?\\d+),UP,(\\+?\\d+)\\)");
			Pattern pat2 = Pattern.compile("\\(FR,(\\+?\\d+),DOWN,(\\+?\\d+)\\)");
			Pattern pat3 = Pattern.compile("\\(ER,(\\+?\\d+),(\\+?\\d+)\\)");
			Matcher m1 = pat1.matcher(input);
			Matcher m2 = pat2.matcher(input);
			Matcher m3 = pat3.matcher(input);
			if(m1.matches()){
				try{
					int pos = Integer.parseInt(m1.group(1));
					int time = Integer.parseInt(m1.group(2));
					LIFT.fAsk(pos, "+", time);
				}catch(Exception e){
					System.out.println("INVALID" + input.replace("(", "[").replace(")", "]"));
				};
			}else if(m2.matches()){
				try{
					int pos = Integer.parseInt(m2.group(1));
					int time = Integer.parseInt(m2.group(2));
					LIFT.fAsk(pos, "-", time);
				}catch(Exception e){
					System.out.println("INVALID" + input.replace("(", "[").replace(")", "]"));
				};
			}else if(m3.matches()){
				try{
					int pos = Integer.parseInt(m3.group(1));
					int time = Integer.parseInt(m3.group(2));
					LIFT.eAsk(pos, time);
				}catch(Exception e){
					System.out.println("INVALID" + input.replace("(", "[").replace(")", "]"));
				};
			}else{
				System.out.println("INVALID" + "[" + input + "]");
			}
			try{
				input = in.nextLine().replace(" ", "");
			}catch(Exception e){
				//System.exit(0);
			};
		}
		LIFT.runlift();
	}
}

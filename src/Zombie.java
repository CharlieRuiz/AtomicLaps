import java.util.Random;

public class Zombie {
	
	public int x, y, stun;
	public boolean active;
	
	public Zombie(boolean z){
		Random r = new Random();
        int n = r.nextInt(2);
        y = 0;
        
        if(n == 0) {
        	x = r.nextInt(4) + 3;
        }else {
        	x = r.nextInt(4) + 13;
        }
        
        if(z){
        	active = true;
        	stun = 0;
        } else {
        	active = false;
        	stun = 2;
        }
		
	}
	
	public Zombie(int y, int x) {
		this.x = x;
		this.y = y;
		active = false;
		stun = 12;
	}

}

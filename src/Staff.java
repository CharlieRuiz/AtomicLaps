
public class Staff {
	
	public int x, y, lastx, lasty;
	public boolean alive, safe;
	
	public Staff(int i) {
		alive = true;
		safe = false;
		spawnPos(i);
		lastx = x;
		lasty = y;
	}
	
	public void spawnPos(int i) {
		if(i == 0) {
			x = 9;
			y = 1;
			
		} else if(i == 1){
			x = 3;
			y = 3;
			
		} else if(i == 2){
			x = 6;
			y = 3;
			
		} else if(i == 3){
			x = 11;
			y = 3;
			
		} else if(i == 4){
			x = 15;
			y = 3;
			
		} else if(i == 5){
			x = 4;
			y = 5;
			
		} else if(i == 6){
			x = 15;
			y = 6;
			
		} else if(i == 7){
			x = 2;
			y = 7;
			
		} else if(i == 8){
			x = 7;
			y = 7;
			
		} else if(i == 9){
			x = 3;
			y = 8;
			
		} else if(i == 10){
			x = 17;
			y = 8;
			
		} else if(i == 11){
			x = 11;
			y = 9;
			
		} else if(i == 12){
			x = 13;
			y = 12;
			
		} else if(i == 13){
			x = 3;
			y = 13;
			
		} else if(i == 14){
			x = 17;
			y = 13;
			
		} else if(i == 15){
			x = 6;
			y = 14;
			
		} else if(i == 16){
			x = 10;
			y = 15;
			
		} else if(i == 17){
			x = 3;
			y = 17;
			
		} else if(i == 18){
			x = 7;
			y = 17;
			
		} else if(i == 19){
			x = 13;
			y = 17;
			
		}
		
	}
	
	public boolean checkLast(int r, int c) {
		if(c == lastx && r == lasty) {
			return true;
		}
		return false;
	}

}

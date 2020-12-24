import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Timer;

public class MiCanvas extends Canvas implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage flickering;
	private Timer game;
	private int w, h;
	private int size = 20;
	private int offset;
	private int tam;
	private int zomNum = 2;
	private int end = 2;
	private int[][] map = new int[size][size]; //1 = staff, 2 = zombie
	private boolean[][] mapWall = new boolean[size][size];
	private Staff[] staff = new Staff[size];
	private Zombie[] zombie = new Zombie[22];
	private int[] exit = new int[4];
	private Coordenadas[][] grid = new Coordenadas[size][size];
	
	public MiCanvas(int w, int h){
		super();
		this.w = w;
		this.h = h;
		flickering = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		game = new Timer(3000, this);
		game.start();
		
		offset = w / 10;
		tam = (w - offset * 2) / size;
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				map[i][j] = 0;
				grid[i][j] = new Coordenadas();
				grid[i][j].y = (tam * (i)) + offset;
				grid[i][j].x = (tam * (j)) + offset;
				
			}
			
		}
		
		createWalls();
		createExit();
		createStaff();
		createZombie();
        
	}
	
	public void paint(Graphics g){
		Graphics gra = flickering.createGraphics();
		
		gra.setColor(Color.WHITE);
		gra.fillRect(0,0,w,h);
		gra.setColor(Color.BLACK);
		createMap(gra);
		paintWalls(gra);
		gra.setColor(Color.BLUE);
		paintStaff(gra);
		gra.setColor(Color.GREEN);
		paintZombie(gra);
		g.drawImage(flickering, 0, 0, null);
		
	}
	
	public void update(Graphics g){
	     paint(g);
	}
	
	public void createMap(Graphics gra) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				gra.drawRect(grid[i][j].x,grid[i][j].y,tam,tam);
			}
			
		}
		
	}
	
	public void createWalls() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				mapWall[i][j] = false;
				if(i == 0 || i == 19 || j == 0 || j == 19) {
					mapWall[i][j] = true;
				}
			}
		}
		
		//Windows
		mapWall[0][3] = false;
		mapWall[0][4] = false;
		mapWall[0][5] = false;
		mapWall[0][6] = false;
		mapWall[0][13] = false;
		mapWall[0][14] = false;
		mapWall[0][15] = false;
		mapWall[0][16] = false;
		//Exit
		mapWall[15][19] = false;
		mapWall[16][19] = false;
		mapWall[17][19] = false;
		mapWall[18][19] = false;
		//innerWalls
		mapWall[4][2] = true;
		mapWall[4][3] = true;
		mapWall[4][4] = true;
		mapWall[4][5] = true;
		mapWall[4][6] = true;
		mapWall[4][7] = true;
		
		mapWall[4][10] = true;
		mapWall[4][11] = true;
		mapWall[4][12] = true;
		mapWall[4][13] = true;
		mapWall[4][14] = true;
		mapWall[4][15] = true;
		mapWall[4][16] = true;
		
		mapWall[5][13] = true;
		mapWall[6][13] = true;
		mapWall[7][13] = true;
		
		mapWall[10][1] = true;
		mapWall[10][2] = true;
		mapWall[10][3] = true;
		mapWall[10][4] = true;
		mapWall[10][5] = true;
		mapWall[10][6] = true;
		mapWall[10][7] = true;
		mapWall[10][8] = true;
		mapWall[10][12] = true;
		mapWall[10][13] = true;
		mapWall[10][14] = true;
		mapWall[10][15] = true;
		mapWall[10][16] = true;
		mapWall[10][17] = true;
		mapWall[10][18] = true;
		
		mapWall[12][2] = true;
		mapWall[12][3] = true;
		mapWall[12][4] = true;
		mapWall[12][5] = true;
		mapWall[12][6] = true;
		mapWall[12][7] = true;
		
		mapWall[16][2] = true;
		mapWall[16][3] = true;
		mapWall[16][4] = true;
		mapWall[16][5] = true;
		mapWall[16][6] = true;
		mapWall[16][7] = true;
		
		mapWall[12][12] = true;
		mapWall[13][12] = true;
		mapWall[14][12] = true;
		mapWall[15][12] = true;
		
		mapWall[12][16] = true;
		mapWall[13][16] = true;
		mapWall[14][16] = true;
		mapWall[15][16] = true;
		
	}
	
	public void paintWalls(Graphics gra) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(mapWall[i][j]) {
					gra.fillRect(grid[i][j].x,grid[i][j].y,tam,tam);
				}
			}
				
		}
		
	}
	
	public void createStaff() {
		for(int i = 0; i < size; i++) {
			staff[i] = new Staff(i);
			map[staff[i].y][staff[i].x] = 1;
		}
	}
	
	public void paintStaff(Graphics gra){
		for(int i = 0; i < size; i++) {
			if(staff[i].alive && !staff[i].safe) {
				gra.fillRect(grid[staff[i].y][staff[i].x].x,grid[staff[i].y][staff[i].x].y,tam,tam);
			}
		}
	}
	
	public void createZombie() {
		for(int i = 0; i < zomNum; i++) {
			zombie[i] = new Zombie(true);
		}
		if(zombie[0].x == zombie[1].x) {
			if(mapWall[0][zombie[1].x + 1]) {
				zombie[1].x = zombie[1].x-1;
			} else{
				zombie[1].x = zombie[1].x+1;
			}
		}
		map[zombie[0].y][zombie[0].x] = 2;
		map[zombie[1].y][zombie[1].x] = 2;
	}
	
	public void paintZombie(Graphics gra) {
		for(int i = 0; i < zomNum; i++) {
			gra.fillRect(grid[zombie[i].y][zombie[i].x].x,grid[zombie[i].y][zombie[i].x].y,tam,tam);
		}
	}
	
	public void createExit() {
		
		exit[0] = 15;
		exit[1] = 16;
		exit[2] = 17;
		exit[3] = 18;
	}
	
	public void moveStaff() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < 2; j++) {
				if(staff[i].alive && !staff[i].safe) {
					if(!checkExitY(staff[i].y)) {
						if(staff[i].y == 9 && staff[i].x > 11) {
							if(!mapWall[staff[i].y][staff[i].x - 1]) {
								if(map[staff[i].y][staff[i].x-1] == 0) {
									map[staff[i].y][staff[i].x] = 0;
									staff[i].lastx = staff[i].x;
									staff[i].x = staff[i].x - 1;
									map[staff[i].y][staff[i].x] = 1;
								}
							}							
						}
						else if(!mapWall[staff[i].y+1][staff[i].x]) {
							if(map[staff[i].y+1][staff[i].x] == 0) {
								map[staff[i].y][staff[i].x] = 0;
								staff[i].lasty = staff[i].y;
								staff[i].y = staff[i].y + 1;
								map[staff[i].y][staff[i].x] = 1;
							}
						}
						else if(!mapWall[staff[i].y][staff[i].x + 1]) {
							if(map[staff[i].y][staff[i].x+1] == 0) {
								map[staff[i].y][staff[i].x] = 0;
								staff[i].lastx = staff[i].x;
								staff[i].x = staff[i].x + 1;
								map[staff[i].y][staff[i].x] = 1;
							}
						}
						else if(!mapWall[staff[i].y][staff[i].x - 1]) {
							if(map[staff[i].y][staff[i].x-1] == 0) {
								map[staff[i].y][staff[i].x] = 0;
								staff[i].lastx = staff[i].x;
								staff[i].x = staff[i].x - 1;
								map[staff[i].y][staff[i].x] = 1;
							}
						}
					} else if(!checkExitX(staff[i].x)) {
						if(!mapWall[staff[i].y][staff[i].x + 1]) {
							if(map[staff[i].y][staff[i].x+1] == 0) {
								map[staff[i].y][staff[i].x] = 0;
								staff[i].lastx = staff[i].x;
								staff[i].x = staff[i].x + 1;
								map[staff[i].y][staff[i].x] = 1;
							}
						}
						else if(!mapWall[staff[i].y+1][staff[i].x]) {
							if(map[staff[i].y+1][staff[i].x] == 0) {
								map[staff[i].y][staff[i].x] = 0;
								staff[i].lasty = staff[i].y;
								staff[i].y = staff[i].y + 1;
								map[staff[i].y][staff[i].x] = 1;
							}
						}
						else if(!mapWall[staff[i].y][staff[i].x - 1]) {
							if(map[staff[i].y][staff[i].x-1] == 0) {
								map[staff[i].y][staff[i].x] = 0;
								staff[i].lastx = staff[i].x;
								staff[i].x = staff[i].x - 1;
								map[staff[i].y][staff[i].x] = 1;
							}
						}
					} else {
						staff[i].safe = true;
						map[staff[i].y][staff[i].x] = 0;
					}
				}
			}
		}
	}
	
	public void moveZombie() {
		for(int i = 0; i < zomNum; i++) {
			for(int j = 0; j < 4; j++) {
				if(zombie[i].active) {
					Random r = new Random();
					int n = r.nextInt(3);
					if(n == 0) {
						if(!mapWall[zombie[i].y+1][zombie[i].x]) {
							if(map[zombie[i].y+1][zombie[i].x] == 0) {
								map[zombie[i].y][zombie[i].x] = 0;
								zombie[i].y = zombie[i].y + 1;
								map[zombie[i].y][zombie[i].x] = 2;
								if(map[zombie[i].y+1][zombie[i].x] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y+1,zombie[i].x);
									zomNum++;
									map[zombie[i].y+1][zombie[i].x] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y+1 && staff[z].x == zombie[i].x) {
											staff[z].alive = false;
										}
									}
								}
								if(map[zombie[i].y-1][zombie[i].x] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y-1,zombie[i].x);
									zomNum++;
									map[zombie[i].y-1][zombie[i].x] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y-1 && staff[z].x == zombie[i].x) {
											staff[z].alive = false;
										}
									}						
								}
								if(map[zombie[i].y+1][zombie[i].x+1] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y+1,zombie[i].x+1);
									zomNum++;
									map[zombie[i].y+1][zombie[i].x+1] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y+1 && staff[z].x == zombie[i].x+1) {
											staff[z].alive = false;
										}
									}	
								}
								if(map[zombie[i].y-1][zombie[i].x+1] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y-1,zombie[i].x+1);
									zomNum++;
									map[zombie[i].y-1][zombie[i].x+1] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y-1 && staff[z].x == zombie[i].x+1) {
											staff[z].alive = false;
										}
									}
								}
								if(map[zombie[i].y][zombie[i].x+1] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y,zombie[i].x+1);
									zomNum++;
									map[zombie[i].y][zombie[i].x+1] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y && staff[z].x == zombie[i].x+1) {
											staff[z].alive = false;
										}
									}	
								}
								if(map[zombie[i].y][zombie[i].x-1] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y,zombie[i].x-1);
									zomNum++;
									map[zombie[i].y][zombie[i].x-1] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y && staff[z].x == zombie[i].x-1) {
											staff[z].alive = false;
										}
									}	
								}
								if(map[zombie[i].y-1][zombie[i].x-1] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y-1,zombie[i].x-1);
									zomNum++;
									map[zombie[i].y-1][zombie[i].x-1] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y-1 && staff[z].x == zombie[i].x-1) {
											staff[z].alive = false;
										}
									}
								}
								if(map[zombie[i].y+1][zombie[i].x-1] == 1) {
									zombie[zomNum] = new Zombie(zombie[i].y+1,zombie[i].x-1);
									zomNum++;
									map[zombie[i].y+1][zombie[i].x-1] = 2;
									for(int z = 0; z < size; z++) {
										if(staff[z].y == zombie[i].y+1 && staff[z].x == zombie[i].x-1) {
											staff[z].alive = false;
										}
									}
								}
							}
						}
					}
					//move zombie up
//					if(n == 1) {
//						if(zombie[i].y > 0) {
//							if(!mapWall[zombie[i].y-1][zombie[i].x]) {
//								if(map[zombie[i].y-1][zombie[i].x] == 0) {
//									map[zombie[i].y][zombie[i].x] = 0;
//									zombie[i].y = zombie[i].y - 1;
//									map[zombie[i].y][zombie[i].x] = 2;
//								}
//							}
//						}
//					}
					if(n == 1) {
						if(!mapWall[zombie[i].y][zombie[i].x+1]) {
							if(map[zombie[i].y][zombie[i].x+1] == 0) {
								map[zombie[i].y][zombie[i].x] = 0;
								zombie[i].x = zombie[i].x + 1;
								map[zombie[i].y][zombie[i].x] = 2;
							}
						}
					}
					if(n == 2) {
						if(!mapWall[zombie[i].y][zombie[i].x-1]) {
							if(map[zombie[i].y][zombie[i].x-1] == 0) {
								map[zombie[i].y][zombie[i].x] = 0;
								zombie[i].x = zombie[i].x - 1;
								map[zombie[i].y][zombie[i].x] = 2;
							}
						}
					}
					
				} else {
					zombie[i].stun--;
					if(zombie[i].stun == 0){
						zombie[i].active = true;
					}
				}
			}
		}
	}
	
	public boolean checkExitY(int y) {
		for(int i = 0; i < 4; i++) {
			if(y == exit[i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkExitX(int x) {
		if(x == 19) {
			return true;
		}
		return false;
	}
	
	public boolean checkEnd() {
		for(int i = 0; i <size; i++) {
			if(staff[i].alive && !staff[i].safe) {
				return true;
			}
		}
		return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == game){
			if(checkEnd()) {
				moveStaff();
				moveZombie();
				repaint();
			} else {
				end--;
				if(end == 0) {
					System.exit(0);
				}
			}
		}
	}

}

import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args){
		
		//Iniciar el panel del juego
		
		int w = 1000;
		int h = 1000;
		
		MiVentana juego = new MiVentana(w, h);
		juego.setSize(w, h);
		juego.setVisible(true);
		juego.setResizable(false);
		juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}

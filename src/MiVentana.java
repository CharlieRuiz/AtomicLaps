import javax.swing.JFrame;

public class MiVentana extends JFrame{
	
	//Crear el Objeto de la ventana

	private static final long serialVersionUID = 1L;
	private MiCanvas canvas;
	
	public MiVentana(int w, int h){
		super();
		canvas = new MiCanvas(w, h);
		add(canvas);
	}

}

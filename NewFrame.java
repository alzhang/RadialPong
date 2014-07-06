import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
public class NewFrame extends Canvas{
	public NewFrame(){
		
	}
public void paint(Graphics g){
	
}
	public static void main (String[] args){
		Frame mf = new Frame ("Goodbye AWT");
		mf.setBackground(Color.black);
		NewFrame frame1= new NewFrame();
		mf.add( frame1);
		mf.setSize(500,500);
		mf.setBackground(Color.black);
		mf.setVisible(true);
		mf.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						System.exit(0);
					}
				}
				);
	}
}
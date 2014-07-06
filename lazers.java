import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
public class lazers extends Canvas{
	private Thread thread1;
	private int type;
	

	private double chanceLimit=.7;

	private CopyOnWriteArrayList<RadialLaser>LazerList;
	public lazers(){
		LazerList = new CopyOnWriteArrayList<RadialLaser>();


		thread1 = new Thread(new Runnable() { // new thread
			public void run() {
			while (thread1 == Thread.currentThread()) {
				
			repaint();
			try {
			Thread.sleep(500);

			}// ends try

			catch (InterruptedException e) {
			e.printStackTrace();
			}// ends catch

			}// ends while loop
			}// ends run method

			});
		thread1.start();
				}

	
	

		
	public void paint(Graphics g){
		g.drawOval(300,300,100,100);
		double chance =Math.random();
		if(chance>chanceLimit )
			LazerList.add(new RadialLaser(50,350));
		for(RadialLaser l1: LazerList){
			l1.draw(g,LazerList);
		}
		System.out.println(LazerList.size());
	}

	public static void main (String[] args){
		Frame mf = new Frame ("Goodbye AWT");
		mf.add( new lazers());
		mf.setSize(700,700);
		//mf.setBackground(Color.black);
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
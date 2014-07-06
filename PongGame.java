import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class PongGame extends Canvas implements Runnable, KeyListener {
	private static int width = 717, height = 753, radius = 350,
			diameter = radius * 2;
	private int bufferWidth;
	private int bufferHeight;
	private Image bufferImage;
	private Graphics bufferGraphics;
	private Player p1;
	private boolean left = false, right = false;
	private int status=0;
	private Thread thisThread;
	private int score=0;
	private int type=0;
	private double chanceLimit=.93;
	private double chance;
	private CopyOnWriteArrayList<lazer>LazerList;
	private CopyOnWriteArrayList<RadialLaser>RadialLazerList;
	public static void main(String[] args) {
		PongGame game = new PongGame();
		createFrame(game);
		game.init();
	}

	public void init() {
		p1 = new Player();
	}

	public static void createFrame(PongGame g) {
		Frame myFrame = new Frame("untitled");

		myFrame.setSize(width, height); // frame size
		myFrame.setBackground(Color.white);
		myFrame.add(g);
		// Make sure program ends when window is closed
		WindowAdapter d = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}

		};
		myFrame.addWindowListener(d);
		g.addKeyListener(g);
		myFrame.setVisible(true); // see frame
		g.requestFocus();
	}

	public PongGame() {
		thisThread = new Thread(this); // create a thread.0 for an object
		thisThread.start();
		LazerList = new CopyOnWriteArrayList<lazer>();
		RadialLazerList = new CopyOnWriteArrayList<RadialLaser>();
		lazer l = new lazer();
		LazerList.add(l);
	}

	/**
	 * Start the thread
	 */
	public void run() {

		while (Thread.currentThread() == thisThread) { // am I running?
			repaint(); // redraw screen

			try {
				updatePosition(p1);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Double buffered update
	 */
	public void update(Graphics g) {
		paint(g);
	}

	public void updatePosition(Player p) {
		if (left) {
			p.setTheta(p.getTheta() + p.getOmega()*20/100);
		}
		else if (right) {
			p.setTheta(p.getTheta() - p.getOmega()*20/100);
		}

	}

	public void paint(Graphics g) {
		// checks the buffersize with the current panelsize
		// or initialises the image with the first paint
		if (bufferWidth != getSize().width || bufferHeight != getSize().height
				|| bufferImage == null || bufferGraphics == null)
			resetBuffer();

		if (bufferGraphics != null) {
			// this clears the offscreen image, not the onscreen one
			bufferGraphics.clearRect(0, 0, bufferWidth, bufferHeight);

			// calls the paintbuffer method with
			// the offscreen graphics as a param
			updateFrame((Graphics2D) bufferGraphics);

			// we finaly paint the offscreen image onto the onscreen image
			g.drawImage(bufferImage, 0, 0, this);
		}
		
	}

	public void updateFrame(Graphics2D g) {
	
		drawBoard(g);
		if(status==0)
			drawStart(g);
		else
		{
			 chance =Math.random();
			if(chance>chanceLimit )
				LazerList.add(new lazer());
			for(lazer l1: LazerList){
				l1.draw(g,LazerList);
			}
			if(chance>chanceLimit )
				RadialLazerList.add(new RadialLaser(50,700));
			for(RadialLaser l1: RadialLazerList){
				l1.draw(g,RadialLazerList);
			}
			g.setColor(Color.BLACK);
			drawScore(g);
			drawSprite(g);
			score++;
		}
		
	}
	public void drawScore(Graphics2D g)
	{
		
		g.drawString("Score:"+score, 320, 355);
	}
	public void drawBoard(Graphics2D g) {
		g.drawOval(0, 0, diameter, diameter);
		g.drawOval(300, 300, 100, 100);
	}

	public void drawSprite(Graphics2D g) {
		p1.draw(g);
	}
	public void drawStart(Graphics2D g)
	{
		
		g.drawString("Press Space", 315, 355);
	}

	/**
	 * Reinitialize double buffered graphics when canvas changes size
	 */
	private void resetBuffer() {
		// always keep track of the image size
		bufferWidth = getSize().width;
		bufferHeight = getSize().height;

		// clean up the previous image
		if (bufferGraphics != null) {
			bufferGraphics.dispose();
			bufferGraphics = null;
		}
		if (bufferImage != null) {
			bufferImage.flush();
			bufferImage = null;
		}
		System.gc();

		// create the new image with the size of the panel
		bufferImage = createImage(bufferWidth, bufferHeight);
		bufferGraphics = bufferImage.getGraphics();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(status==0)
		{
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				status = 1;
			}
		}
		else if(status==1)
		{
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}

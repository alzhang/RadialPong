import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;


public class RadialLaser {
	private int theta, inR, bD, outR;
	private int stage;
	private int balltime=3;
	private int lasertime=7;
	public RadialLaser(int inR, int outR)
	{
		double gen=Math.random()*360;
		setTheta((int)gen);
		setInR(inR);
		setbD(10);
		setOutR(outR);
	}
	public void setOutR(int r)
	{
		outR=r;
	}
	public void setbD(int x)
	{
		bD=x;
	}
	public void setInR(int r)
	{
		inR=r;
	}
	public void setTheta(int t)
	{
		theta=t;
	}
	public int getStage()
	{
		return stage;
	}
	public void setStage(int s)
	{
		stage=s;
	}
	public void increase()
	{
		stage++;
	}
	public int getX()
	{
		double x= (inR)*Math.cos(Math.toRadians(theta))-bD/2+350;
		return ((int)x);
	}
	public int getY()
	{
		double y= (inR)*Math.sin(Math.toRadians(theta))-bD/2+350;
		return ((int)y);
	}
	public int getStartX()
	{
		double x= (inR)*Math.cos(Math.toRadians(theta))+350;
		return ((int)x);
	}
	public int getStartY()
	{
		double y= (inR)*Math.sin(Math.toRadians(theta))+350;
		return ((int)y);
	}
	public int getMaxX()
	{
		double x= (outR)*Math.cos(Math.toRadians(theta))+350;
		return ((int)x);
	}
	public int getMaxY()
	{
		double y= (outR)*Math.sin(Math.toRadians(theta))+350;
		return ((int)y);
	}
	public void drawBall(Graphics g)
	{
		g.drawOval(getX(), getY(), bD, bD);
	}
	public void draw(Graphics g, CopyOnWriteArrayList<RadialLaser> LazerList) {
		this.increase();
		g.setColor(Color.RED);
		if (getStage() <= balltime*50) {
			drawBall(g);
		}
		else if (getStage() > balltime*50 && getStage() <= lasertime*50) {
			 if ( getStage() <= lasertime*50) {
			//drawBall(g);
			g.drawLine(getStartX(), getStartY(), getMaxX(), getMaxY());
		}
		else
			LazerList.remove(this);

	}

}
}

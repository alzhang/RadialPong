import java.awt.Graphics2D;


public class Player {
	private int radius, sR; 
	private double theta, omega;
	public Player()
	{
		setRad(350);
		setSize(20);
		setTheta(0);
		setOmega(3);
	}
	public Player(int rad, int size, int theta, int omega)
	{
		setRad(rad);
		setSize(size);
		setTheta(theta);
		setOmega(omega);
	}
	public void setRad(int x)
	{
		radius=x;
	}
	public void setSize(int x)
	{
		sR=x;
	}
	public void setTheta(double x)
	{
		theta=x;
	}
	public void setOmega(double x)
	{
		omega=x;
	}
	public int getSize()
	{
		return sR;
	}
	public double getOmega()
	{
		return omega;
	}
	public double getTheta()
	{
		return theta;
	}
	public int getX()
	{
		double x= (radius-sR/2)*Math.cos(Math.toRadians(theta))-sR/2+350;
		return ((int)x);
	}
	public int getY()
	{
		double y= (radius-sR/2)*Math.sin(Math.toRadians(theta))-sR/2+350;
		return ((int)y);
	}
	public void draw(Graphics2D g)
	{
		g.drawOval(getX(), getY(), sR, sR);
	}
	
}

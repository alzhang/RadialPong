import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;

public class lazer{
	private int type;
	private int xpos;
	private  int ypos;
	private  int stage ;
	private int balltime = 3;
	private int lazertime= 7;
	public lazer(){
		
	type = (int)(Math.random()*2);
	if (type==0){
		xpos=0;
		ypos = (int)(Math.random()*700+1);
	}
	else{
		ypos = 0;
		xpos = (int)(Math.random()*700+1);
	}
	}
	
	
	public  int getType() {
		return type;
	}


	public  void setType(int t) {
		type = t;
	}


	public  int getXpos() {
		return xpos;
	}


	public  void setXpos(int x) {
		xpos = x;
	}


	public  int getYpos() {
		return ypos;
	}


	public  void setYpos(int y) {
		ypos = y;
	}


	public  int getStage() {
		return stage;
	}


	public  void setStage(int s) {
		stage = s;
	}


	public  void increase(){
		stage++;
	}
	public void draw(Graphics g,	CopyOnWriteArrayList<lazer> LazerList){
		this.increase();
		g.setColor(Color.RED);
if (this.getStage()<=balltime*50){
	//System.out.println(this.getStage());
	g.fillOval(this.getXpos(),this.getYpos(), 10, 10);
}
else if (this.getStage()>balltime*50 && this.getStage()<=lazertime*50){

	if  (this.getType()==0)
		g.drawLine(this.getXpos(),this.getYpos(), 700, this.getYpos());
	else if (this.getType()==1)
		g.drawLine(this.getXpos(),this.getYpos(), this.getXpos(), 700);
}

else
	LazerList.remove(this);

	}
	}
	

public class lazer2{
	private int type;
	private int xpos;
	private  int ypos;
	private  int stage ;
	public lazer2(){
		
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
	
	
}
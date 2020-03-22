public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;
	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Body p){
		double dis = Math.pow(xxPos - p.xxPos, 2) + Math.pow(yyPos - p.yyPos, 2); 
		return Math.sqrt(dis);
	}
	public double calcForceExertedBy(Body p){
		double distance = this.calcDistance(p);
		return G * mass * p.mass/Math.pow(distance, 2);
	}
	public double calcForceExertedByX(Body p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		return force * (p.xxPos - xxPos) / distance;
	}
	public double calcForceExertedByY(Body p){
		double distance = this.calcDistance(p);
		double force = this.calcForceExertedBy(p);
		return force * (p.yyPos - yyPos) / distance;
	}
	public double calcNetForceExertedByX(Body[] bodies){
		double forcex = 0;
		for (Body p : bodies) {
			if (this.equals(p)) {
				continue;
			}
			else{
				forcex += calcForceExertedByX(p);
			}
		}
		return forcex;
	}
	public double calcNetForceExertedByY(Body[] bodies){
		double forcey = 0;
		for (Body p : bodies) {
			if (this.equals(p)) {
				continue;
			}
			else{
				forcey += calcForceExertedByY(p);
			}
		}
		return forcey;
	}
	public void update(double dt, double fx, double fy){
		double ax = fx/mass;
		double ay = fy/mass;
		xxVel = xxVel + dt * ax;
		yyVel = yyVel + dt * ay;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}
	public void draw(){
		String bodyToDraw =  "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, bodyToDraw);
		StdDraw.show();
	}
}



public class NBody{
	public static double readRadius(String file){
		In in = new In(file);
		int numberofplanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Body[] readBodies(String file){
		In in = new In(file);
		int numberofplanets = in.readInt();
		double radius = in.readDouble();
		Body[] bodies = new Body[numberofplanets];
		for (int i = 0; i < numberofplanets; i = i + 1){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String filename = in.readString();
			bodies[i] = new Body(xxPos, yyPos, xVel, yVel, mass, filename);
		}
		return bodies;
	} 
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] bodies = NBody.readBodies(filename);
		double r = NBody.readRadius(filename);
		/**
		StdDraw.setScale(-r, r);
		StdDraw.picture(-r, r, backimage);
		StdDraw.show();
		for (Body b : bodies) {
			b.draw();
		} */
		StdDraw.enableDoubleBuffering();
		for (double time = 0; time <= T; time = time + dt){
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			int i = 0;
			for (Body b : bodies) {
				double forcex = b.calcNetForceExertedByX(bodies);
				double forcey = b.calcNetForceExertedByY(bodies);
				xForces[i] = forcex;
				yForces[i] = forcey;
				i = i + 1;
			}
			int j = 0;
			for (Body b : bodies) {
				b.update(dt, xForces[j], yForces[j]);
				j = j + 1;
			}
			String backimage = "images/starfield.jpg";
			StdDraw.setScale(-r, r);
			StdDraw.picture(0, 0, backimage);
			StdDraw.show();
			for (Body b : bodies) {
			b.draw();
		}
			StdDraw.show();
			StdDraw.pause(10);
		}
	}
}
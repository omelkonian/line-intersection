package basic_geometric_entities;

import math.geom2d.Point2D;
import math.geom2d.conic.Parabola2D;
import math.geom2d.line.Line2D;

public class Line {
	public int lineId;

	public Double a;
	public Double b;
	public Double c;
	public Double t1;
	public Double t2;

	public Line(int id, Double a, Double b, Double c, Double t1, Double t2) {
		this.lineId = id;
		this.a = a;
		this.b = b;
		this.c = c;
		this.t1 = t1;
		this.t2 = t2;
	}

	public Line(Line l) {
		this.lineId = l.lineId;
		this.a = l.a;
		this.b = l.b;
		this.c = l.c;
		this.t1 = l.t1;
		this.t2 = l.t2;
	}

	public Line(LineInstance l) {
		this.lineId = l.lineId;
		this.a = l.a;
		this.b = l.b;
		this.c = l.c;
		this.t1 = l.t1;
		this.t2 = l.t2;
	}

	public void printLine() {
		System.out.println("Line " + this.lineId + ": " + this.a + " " + this.b
				+ " " + this.c + " " + this.t1 + " " + this.t2);
	}

	public boolean isInRange(Double x) {
		return (x >= t1 && x <= t2);
	}

	public Double getY(Double x) {
		return (this.a * Math.pow(x, 2) + b * x + c);
	}

	public Point getStartPoint() {
		Double x = t1;
		Double y = this.getY(x);
		return (new Point(x, y));
	}

	public Point getEndPoint() {
		Double x = t2;
		Double y = this.getY(x);
		return (new Point(x, y));
	}

	// For graphics.
	public Line2D getLine2D() {
		return new Line2D(this.getStartPoint().x + 150,
				-this.getStartPoint().y + 175, this.getEndPoint().x + 150,
				-this.getEndPoint().y + 175);
	}
	// For graphics.
	public Parabola2D getParabola2D() {
		Double a = this.a ;
		Double b = this.b ;
		Double c = this.c ;
		Point2D vertex1 = new Point2D(((-b / (2 * a))+150), -getY(-b
				/ (2 * a))+175);
		Point2D focus1 = new Point2D((-(b / (2 * a))+150),
				(-((1 - (b * b) + (4 * a * c)) / (4 * a))+175));
		
		return Parabola2D.create(vertex1, focus1);
	}

	@Override
	public String toString() {
		return ("Line " + this.lineId + " : " + "a) " + this.a + "  b) "
				+ this.b + "  c) " + this.c + "  t1) " + this.t1 + "  ) t2) " + this.t2);
	}
}

package basic_geometric_entities;

import java.util.Comparator;

public class LineInstance extends Line implements Comparator<LineInstance> ,Comparable<LineInstance> {

	public Double currentY;
	
	public LineInstance(int id, Double a, Double b, Double c, Double t1, Double t2, Double currentX) {
		super(id, a, b, c, t1, t2);
		this.currentY = this.getY(currentX);
	}
	
	public LineInstance(Line l, Double currentX) {
		super(l);
		this.currentY = this.getY(currentX);
	}
	
	public LineInstance(Line l, boolean immediateY, Double currentY) {
		super(l);
		this.currentY = currentY;
	}
	
	public int compare(LineInstance one, LineInstance two) {
		if (two.currentY < one.currentY)
			return -1;
		if (two.currentY > one.currentY)
			return 1;
		return 0;
	}
	
	@Override
	public int compareTo(LineInstance o) {
		if (this.lineId == o.lineId)
			return 0;
		if (this.currentY < o.currentY)
			return -1;
		if (this.currentY > o.currentY)
			return 1;
		return 0;
	}
	
	@Override
	public Double getY(Double x) {
		return  (this.a*Math.pow(x, 2) + b*x + c);
	}
	
	@Override
	public void printLine() {
		System.out.println("Line " + this.lineId + " : \n   " + "a) " + this.a + "  b) "
				+ this.b + "  c) " + this.c + "  t1) " + this.t1 + "  ) t2) " + this.t2 + " --->  y) " + this.currentY + "\n");
	}
	
	@Override
	public String toString() {
		return ("Line " + this.lineId + " : \n    " + "a) " + this.a + "  b) "
				+ this.b + "  c) " + this.c + "  t1) " + this.t1 + "  ) t2) " + this.t2 + " --->  y) " + this.currentY + "\n");
	}
	
}

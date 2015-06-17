package events;

import java.util.ArrayList;
import java.util.Comparator;

import math.geom2d.Point2D;
import math.geom2d.conic.Parabola2D;
import math.geom2d.line.Line2D;
import basic_geometric_entities.Line;
import basic_geometric_entities.Point;

public class Event implements Comparator<Event> {
	// The x-coordinate of the event.
	public Double x;
	// The type of the event.
	public EventType type;
	// The line associated with this event. // Always contains successor
	public Line associatedLine;
	// The second line associated with this event(in case of intersection). //
	// Always contains predecessor
	public Line associatedLine2;

	
	// GRAPHICS : Shapes to draw during the event. 
	public Line2D sweepLine;
	public Point2D eventPoint;
	public ArrayList<Point2D> pointsToDraw = new ArrayList<Point2D>();
	public ArrayList<Point2D> hotPointsToDraw = new ArrayList<Point2D>();
	public ArrayList<Line2D> linesToDraw = new ArrayList<Line2D>();
	public ArrayList<Parabola2D> parabolasToDraw = new ArrayList<Parabola2D>();
	
	
	public Event() {
	}

	public Event(Event e) {
		this.sweepLine = new Line2D(e.x + 150, 0, e.x + 150, 1175);
		
		this.x = e.x;
		this.type = e.type;
		this.associatedLine = e.associatedLine;
		this.associatedLine2 = e.associatedLine2;
		
		// GRAPHICS
		this.pointsToDraw = e.pointsToDraw;
		this.eventPoint = e.eventPoint;
		this.hotPointsToDraw = e.hotPointsToDraw;
		this.linesToDraw = e.linesToDraw;
	}
	
	public Event(Double double1, EventType type, Line line) {
		this.sweepLine = new Line2D(double1 + 150, 0, double1 + 150, 1175);
		this.x = double1;
		this.type = type;
		this.associatedLine = line;
	}

	public Event(Double double1, EventType type, Line line1, Line line2) {
		this.sweepLine = new Line2D(double1 + 150, 0, double1 + 150, 1175);
		this.x = double1;
		this.type = type;

		// Keep order for convenience when handling events.
		this.associatedLine = (line1.getY(double1) > line2.getY(double1)) ? line2
				: line1;
		this.associatedLine2 = (line1.getY(double1) > line2.getY(double1)) ? line1
				: line2;
	}

	public int compare(Event one, Event two) {
		if (two.x < one.x)
			return 1;
		if (two.x > one.x)
			return -1;
		if (two.associatedLine == one.associatedLine)
			return 0;
		else
			return (one.associatedLine.lineId - two.associatedLine2.lineId); // Differentiate different events with lineIDs.
	}

	// For graphics.
	public Point2D getPoint2D() {
		return new Point2D((this.x + 150), (-this.associatedLine.getY(this.x) + 175));
	}
	public Point getPoint() {
		return new Point(this.x, this.associatedLine.getY(this.x));
	}
	
	public void emptyLists() {
		this.pointsToDraw.clear();
		this.hotPointsToDraw.clear();
		this.linesToDraw.clear();
	}
		
	@Override
	public String toString() {
		if (this.type == EventType.INTERSECTION)
			return ("  " + this.type.toString() + " : \n                 " + this.x
					+ "\n                                	[LineID : "
					+ this.associatedLine.lineId + ", " + this.associatedLine2.lineId + "]");
		else
			return ("  " + this.type.toString() + " : \n                 " + this.x
				+ "\n                                	[LineID : "
				+ this.associatedLine.lineId + "]");
	}
}

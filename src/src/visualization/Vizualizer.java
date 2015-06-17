package visualization;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.conic.Parabola2D;
import math.geom2d.line.Line2D;
import red_black_tree.RedBlackTree;
import basic_geometric_entities.Line;
import basic_geometric_entities.LineInstance;
import basic_geometric_entities.Point;
import eventhandler.Executor;
import events.Event;
import events.EventType;

@SuppressWarnings("serial")
public class Vizualizer extends Applet implements ActionListener {

	public Executor executor;

	public Event shapeHolder;

	public ArrayList<Point> reportedIntersections;
	public Point2D eventPoint;

	public ArrayList<Line2D> permanentLines;
	public ArrayList<Parabola2D> permanentParabolas;
	public Line2D sweepLine;
	public ArrayList<Point2D> points;
	public ArrayList<Point2D> hotPoints;
	public ArrayList<Line2D> lines;
	public ArrayList<Parabola2D> parabolas;

	final static BasicStroke simple = new BasicStroke(0.5f);
	final static BasicStroke small = new BasicStroke(0.5f);
	final static BasicStroke verySmall = new BasicStroke(0.1f);
	final static float dash1[] = { 10.0f };
	final static BasicStroke dashed = new BasicStroke(0.5f,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

	DecimalFormat df = new DecimalFormat("#.###");

	Button createGraph = new Button(" Execute Next Command ");
		
	
	public void init() {
		
		this.executor = new Executor("input.txt");
		this.shapeHolder = new Event(this.executor.plane.events.element());

		reportedIntersections = new ArrayList<Point>();
		hotPoints = new ArrayList<Point2D>();
		points = new ArrayList<Point2D>();
		lines = new ArrayList<Line2D>();
		parabolas = new ArrayList<Parabola2D>();
		permanentLines = new ArrayList<Line2D>();
		permanentParabolas = new ArrayList<Parabola2D>();

		// Draw all lines.
		for (Line l : this.executor.plane.lines) {
			if (l.a == 0)
				this.permanentLines.add(l.getLine2D());
			else
				this.permanentParabolas.add(l.getParabola2D());
		}
		
	}

	public void start() {
		this.setBackground(Color.WHITE);
		this.setSize(new Dimension(1500, 900));

		this.add(createGraph);
		createGraph.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		
		// Empty lists.
		if (this.shapeHolder != null)
			this.shapeHolder.emptyLists();

		hotPoints.clear();
		points.clear();
		lines.clear();
		parabolas.clear();

		// Get shapes to draw after executing command.
		this.shapeHolder = this.executor.executeOneCommand();

		if (this.shapeHolder != null) {
			this.sweepLine = this.shapeHolder.sweepLine;
			this.eventPoint = this.shapeHolder.eventPoint;
		}

		if (this.shapeHolder != null) {
			if (this.shapeHolder.type == EventType.INTERSECTION)
				this.reportedIntersections.add(this.shapeHolder.getPoint());
		}

		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.scale(5, 3.5);

		// draw grid box
		g2.setStroke(simple);
		g2.setColor(Color.BLACK);

		g2.drawRect(125 - 100, 50, 250, 250);
		// draw x and y axis
		g2.setStroke(verySmall);
		g2.drawLine(250 - 100, 50, 250 - 100, 300);
		g2.drawLine(125 - 100, 175, 375 - 100, 175);

		// CENTER' : (150, 175)

		g2.setFont(new Font("Courier", Font.PLAIN, 10));

		// Draw command
		if (this.executor.lastCommand != null) {
			g2.drawString(this.executor.lastCommand.toString(), 15, 15);
		}

		// Draw reported intersections.
		g2.setFont(new Font("Courier", Font.PLAIN, 10));
		g2.drawString("Intersections", 15, 45);
		g2.setFont(new Font("Courier", Font.PLAIN, 7));
		for (int i = 0; i < this.reportedIntersections.size(); i++) {
			Point cur = this.reportedIntersections.get(i);
			g2.drawString(
					i + ") : " + " (" + df.format(cur.x) + " , "
							+ df.format(cur.y) + ")", 15, 80 + i * 20);
		}

		// Draw axis scaling.
		for (int count = 0; count < 50; count++) {
			// x-axis tick marks
			g2.drawLine((250 + count * 10) / 2 - 100, 345 / 2,
					(250 + count * 10) / 2 - 100, 355 / 2);
			// y-axis tick marks
			g2.drawLine(495 / 2 - 100, (100 + count * 10) / 2, 505 / 2 - 100,
					(100 + count * 10) / 2);
		}

		// keep things within grid box
		g2.setClip(125 - 100, 50, 250, 250);

		g2.setColor(Color.BLACK);

		if (this.shapeHolder != null) {
			this.sweepLine = this.shapeHolder.sweepLine;

			for (Point2D p : this.shapeHolder.pointsToDraw) {
				this.points.add(p);
			}
			for (Point2D p : this.shapeHolder.hotPointsToDraw) {
				this.hotPoints.add(p);
			}

			RedBlackTree.getArrayInOrder(this.executor.plane.rootNode.root());
			for (RedBlackTree rb : RedBlackTree.temp) {
				LineInstance toDraw = rb.value;
				if (toDraw.a == 0) {
					this.lines.add(toDraw.getLine2D());
				} else {
					this.parabolas.add(toDraw.getParabola2D());
				}
			}
		}

		// Draw Sweep Line

		g2.setColor(Color.RED);
		g2.setStroke(dashed);

		if (this.shapeHolder != null) {
			if (this.shapeHolder.sweepLine != null)
				this.sweepLine.draw(g2);

			if (this.eventPoint != null) {
				switch (this.shapeHolder.type) {
				case START:
					g2.setColor(Color.GREEN);
					break;
				case END:
					g2.setColor(Color.BLACK);
					break;
				case INTERSECTION:
					g2.setColor(Color.RED);
					g2.setStroke(verySmall);
					break;
				default:
					break;
				}
				this.eventPoint.draw(g2, 0.6);
			}
		}
		
		g2.setStroke(small);

		for (Line2D l : this.permanentLines) {
			g2.setStroke(verySmall);
			g2.setColor(Color.GRAY);

			l.draw(g2);
		}
		for (Line2D l : this.lines) {
			g2.setColor(Color.BLACK);

			l.draw(g2);
		}
		for (Point2D p : this.points) {
			g2.setColor(Color.GREEN);
			p.draw(g2, 0.6);
		}
		for (Point2D p : this.hotPoints) {
			g2.setColor(Color.RED);
			p.draw(g2, 0.6);
		}
		for (Parabola2D par : this.parabolas) {
			g2.setColor(Color.CYAN);

			Box2D box = new Box2D(0, 1000, 0, 1000);
			par.clip(box).draw(g2);
		}
		for (Parabola2D par : this.permanentParabolas) {
			g2.setStroke(verySmall);
			g2.setColor(Color.GRAY);

			Box2D box = new Box2D(0, 1000, 0, 1000);
			par.clip(box).draw(g2);
		}

		this.emptyLists();
	}

	public void emptyLists() {
		this.points.clear();
		this.lines.clear();
		this.parabolas.clear();
	}
	
	public void destroy() {
	}
}

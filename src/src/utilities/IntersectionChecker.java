package utilities;

import math.geom2d.Point2D;
import math.geom2d.conic.Parabola2D;
import math.geom2d.line.LineSegment2D;
import basic_geometric_entities.Line;
import basic_geometric_entities.Point;

public final class IntersectionChecker {
	public static Pair areIntersectingAt(Line l1, Line l2) {

		if (l1.a == 0 & l2.a == 0) { // Straight lines.

			LineSegment2D line1 = new LineSegment2D(new Point2D(
					l1.getStartPoint().x, l1.getStartPoint().y), new Point2D(
					l1.getEndPoint().x, l1.getEndPoint().y));

			LineSegment2D line2 = new LineSegment2D(new Point2D(
					l1.getStartPoint().x, l1.getStartPoint().y), new Point2D(
					l1.getEndPoint().x, l1.getEndPoint().y));

			Point intersectionPoint = new Point(line1.intersection(line2)
					.getX(), line1.intersection(line2).getY());

			if (l1.isInRange(intersectionPoint.x)
					&& l2.isInRange(intersectionPoint.x))
				return new Pair(new Point(line1.intersection(line2).getX(),
						line1.intersection(line2).getY()), null);

			else
				return null;

		} else if (l1.a != 0 && l2.a != 0) { // Quadratic lines.

			Double a = l1.a - l2.a, b = l1.b - l2.b, c = l1.c - l2.c;

			Double discriminant = b - 4 * a * c;

			if (discriminant > 0) { // Two intersections.
				
				Point p1 = new Point(-b + Math.sqrt(discriminant) / (2 * a),
						l1.getY(-b + Math.sqrt(discriminant) / (2 * a)));

				Point p2 = new Point(-b - Math.sqrt(discriminant) / (2 * a),
						l1.getY(-b - Math.sqrt(discriminant) / (2 * a)));

				if (l1.isInRange(p1.x) && l2.isInRange(p1.x))
					return new Pair(p1, p2);
				else
					return null;

			} else if (discriminant == 0) { // One intersection.
				Point p = new Point(-b / (2 * a), l1.getY(-b / (2 * a)));
				if (l1.isInRange(p.x) && l2.isInRange(p.x))
					return new Pair(p, null);
				else
					return null;
			} else
				return null; // No intersection.

		} else { // Both straight and quadratic.
			Line straight = (l1.a == 0) ? l1 : l2;
			Line quadratic = (l1.a == 0) ? l2 : l1;

			LineSegment2D line = new LineSegment2D(new Point2D(
					straight.getStartPoint().x, straight.getStartPoint().y),
					new Point2D(straight.getEndPoint().x, straight
							.getEndPoint().y));

			Point2D vertex = new Point2D(-quadratic.b / (2 * quadratic.a),
					quadratic.getY(-quadratic.b / (2 * quadratic.a)));

			Point2D focus = new Point2D(
					-(quadratic.b / (2 * quadratic.a)),
					(1 - (quadratic.b * quadratic.b) + (4 * quadratic.a * quadratic.c))
							/ (4 * quadratic.a));

			Parabola2D parabola = Parabola2D.create(vertex, focus);
			
			Point p = new Point(parabola.intersections(line).iterator()
					.next().getX(), parabola.intersections(line).iterator()
					.next().getY());
			
			if (l1.isInRange(p.x) && l2.isInRange(p.x))
				return new Pair(p, null);
			else
				return null;
		}

	}
}
	
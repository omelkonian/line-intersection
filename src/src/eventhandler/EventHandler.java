package eventhandler;

import plane.Plane;
import red_black_tree.RedBlackTree;
import utilities.IntersectionChecker;
import utilities.Pair;
import basic_geometric_entities.Line;
import basic_geometric_entities.LineInstance;
import events.Event;
import events.EventType;

public final class EventHandler {

	public static void handleEvent(Plane plane, Event event, boolean printInfo) {

		// GRAPHICS - Add shapes to event.		
		event.eventPoint = event.getPoint2D();

		plane.rootNode = plane.rootNode.refreshKeys(plane.rootNode.root(),
				event.x);

		char eventType = 0;
		Double eventX = event.x;
		int newIntersections = 0;

		LineInstance predecessor = null, successor = null, predecessor2 = null, successor2 = null;

		switch (event.type) {
		case START:

			eventType = 'S';

			// Insert line into the sweep line status.

			plane.rootNode = plane.rootNode.root().add(
					new LineInstance(event.associatedLine, true,
							event.associatedLine.getY(event.x)));

			// Remove past intersection events between predecessor and
			// successor.
			predecessor = plane.rootNode.root().getPredecessorLine(
					plane.rootNode.root(),
					new LineInstance(event.associatedLine, true,
							event.associatedLine.getY(event.x)));

			successor = plane.rootNode.root().getSuccessorLine(
					plane.rootNode.root(),
					new LineInstance(event.associatedLine, true,
							event.associatedLine.getY(event.x)));

			if (predecessor != null && successor != null) {

				for (Event e : plane.events.priorityQueue) {

					if (((e.associatedLine == new Line(predecessor) && e.associatedLine2 == new Line(
							successor)))
							|| (e.associatedLine2 == new Line(predecessor) && e.associatedLine == new Line(
									successor))) {
						plane.events.remove(e);
					}
				}
			}

			// Add future intersection events between the new line and
			// predecessor or successor.

			if (predecessor != null) {

				Pair interPerCur = IntersectionChecker.areIntersectingAt(
						predecessor, new LineInstance(event.associatedLine,
								true, event.associatedLine.getY(event.x)));

				if (interPerCur != null) {

					if (interPerCur.point1.x > event.x) {

						newIntersections++;
						
						Event toAdd = new Event(interPerCur.point1.x,
								EventType.INTERSECTION, predecessor,
								new LineInstance(event.associatedLine, true,
										event.associatedLine.getY(event.x)));
						event.hotPointsToDraw.add(toAdd.getPoint2D());
						plane.events.add(toAdd);

					}

					if (interPerCur.point2 != null) {
						if (interPerCur.point2.x > event.x) {

							newIntersections++;
							
							Event toAdd = new Event(interPerCur.point2.x,
									EventType.INTERSECTION, predecessor,
									new LineInstance(event.associatedLine,
											true,
											event.associatedLine.getY(event.x)));
							event.hotPointsToDraw.add(toAdd.getPoint2D());
							plane.events.add(toAdd);
						}
					}
				}
			}
			if (successor != null) {

				Pair interCurSuc = IntersectionChecker.areIntersectingAt(
						new LineInstance(event.associatedLine, true,
								event.associatedLine.getY(event.x)), successor);

				if (interCurSuc != null) {

					if (interCurSuc.point1.x > event.x) {

						newIntersections++;

						Event toAdd = new Event(interCurSuc.point1.x,
								EventType.INTERSECTION, new LineInstance(
										event.associatedLine, true,
										event.associatedLine.getY(event.x)),
								successor);
						event.hotPointsToDraw.add(toAdd.getPoint2D());
						plane.events.add(toAdd);

					}
					if (interCurSuc.point2 != null) {
						if (interCurSuc.point2.x > event.x) {

							newIntersections++;

							Event toAdd = new Event(interCurSuc.point1.x,
									EventType.INTERSECTION,
									new LineInstance(event.associatedLine,
											true, event.associatedLine
													.getY(event.x)), successor);
							event.hotPointsToDraw.add(toAdd.getPoint2D());
							plane.events.add(toAdd);
						}
					}
				}
			}
			break;
		case END:
			eventType = 'E';

			// Add future intersection events .

			predecessor = plane.rootNode.root().getPredecessorLine(
					plane.rootNode.root(),
					new LineInstance(event.associatedLine, true,
							event.associatedLine.getY(event.x)));

			successor = plane.rootNode.root().getSuccessorLine(
					plane.rootNode.root(),
					new LineInstance(event.associatedLine, true,
							event.associatedLine.getY(event.x)));

			if (predecessor != null && successor != null) {

				Pair intersect = IntersectionChecker.areIntersectingAt(
						predecessor, successor);

				if (intersect != null && intersect.point1.x > event.x) {

					newIntersections++;

					Event toAdd = new Event(intersect.point1.x,
							EventType.INTERSECTION, predecessor, successor);
					event.hotPointsToDraw.add(toAdd.getPoint2D());
					plane.events.add(toAdd);
				}

				if (intersect != null && intersect.point2 != null) {
					if (intersect.point2.x > event.x) {

						newIntersections++;

						Event toAdd = new Event(intersect.point2.x,
								EventType.INTERSECTION, predecessor, successor);
						event.hotPointsToDraw.add(toAdd.getPoint2D());
						plane.events.add(toAdd);
					}
				}
			}

			plane.rootNode = plane.rootNode.removeByID(
					event.associatedLine.lineId, plane.rootNode.root());

			break;

		case INTERSECTION:
			eventType = 'I';

			// Swap lines in the sweep line status.

			RedBlackTree.getOldYL(event.associatedLine, plane.rootNode.root());

			LineInstance li1 = new LineInstance(event.associatedLine, true,
					RedBlackTree.tempOldY);

			predecessor = plane.rootNode.root().getPredecessorLine(
					plane.rootNode.root(), li1);

			successor = plane.rootNode.root().getSuccessorLine(
					plane.rootNode.root(), li1);

			RedBlackTree.getOldYL(event.associatedLine2, plane.rootNode.root());

			LineInstance li2 = new LineInstance(event.associatedLine2, true,
					RedBlackTree.tempOldY);

			predecessor2 = plane.rootNode.root().getPredecessorLine(
					plane.rootNode.root(), li2);
			successor2 = plane.rootNode.root().getSuccessorLine(
					plane.rootNode.root(), li2);

			// Swap the lines with a little offset to one of them.

			plane.rootNode = plane.rootNode.root().remove(li1);

			li1.currentY = li1.getY(event.x) + 0.00001;

			plane.rootNode = plane.rootNode.root().add(li1);

			plane.rootNode = plane.rootNode.root().remove(li2);

			li2.currentY = li2.getY(event.x);

			plane.rootNode = plane.rootNode.root().add(li2);


			// If there is a second intersection associated with these lines,
			// re-insert it for tests to pass.
			for (Event e : plane.events.priorityQueue) {
				if (e.associatedLine == event.associatedLine 
					 && e.associatedLine2 == event.associatedLine2) {
					plane.events.remove(e);
					newIntersections++;

					plane.events.add(e);
					break;
				}
			}

			// Report intersection.

			plane.intersectionNo++;

			// Update dependencies.

			// Between successor and predecessor of first line.
			if (predecessor != null && successor != null) {
				Pair intersect = IntersectionChecker.areIntersectingAt(
						predecessor, successor);

				if (intersect != null && intersect.point1.x > event.x) {

					newIntersections++;

					Event toAdd = new Event(intersect.point1.x,
							EventType.INTERSECTION, predecessor, successor);
					event.hotPointsToDraw.add(toAdd.getPoint2D());
					plane.events.add(toAdd);
				}

				if (intersect != null) {
					if (intersect.point2 != null) {
						if (intersect.point2.x > event.x) {

							newIntersections++;

							plane.events.add(new Event(intersect.point2.x,
									EventType.INTERSECTION, predecessor,
									successor));
						}
					}
				}
			}

			// Between successor and predecessor of second line.
			if (predecessor2 != null && successor2 != null) {
				Pair intersect = IntersectionChecker.areIntersectingAt(
						predecessor2, successor2);

				if (intersect != null) {
					if (intersect != null && intersect.point1.x > event.x) {

						newIntersections++;

						plane.events.add(new Event(intersect.point1.x,
								EventType.INTERSECTION, predecessor2,
								successor2));
					}
					if (intersect.point2 != null) {
						if (intersect.point2.x > event.x) {

							newIntersections++;

							plane.events.add(new Event(intersect.point2.x,
									EventType.INTERSECTION, predecessor2,
									successor2));
						}
					}
				}
			}

			break;

		default:
			System.err.println("ERROR HANDLING EVENT : unknown type");
			break;
		}

		if (printInfo) {
			System.out.printf("event: %1c %6.2f %1d\n", eventType, eventX,
					newIntersections);
		}
	}
}

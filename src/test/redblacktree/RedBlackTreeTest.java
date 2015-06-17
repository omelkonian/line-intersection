package redblacktree;

import org.junit.Test;

import plane.Plane;
import basic_geometric_entities.LineInstance;

public class RedBlackTreeTest {

//	@Test
//	public void initialStoring() {
//		System.out.println("-----------initialStoring");
//		Plane plane = new Plane("input.txt");
//
//		System.out.println(plane.sweepLine.toString());
//		System.out.println("Empty :" + plane.sweepLine.isEmpty());
//		System.out.println("Red :" + plane.sweepLine.isRed);
//		System.out.println("Black :" + plane.sweepLine.isBlack());
//	}
//
	@Test
	public void remove() {
		System.out.println("-----------remove------------");
		Plane plane = new Plane("input.txt");

		if (!plane.sweepLine.isEmpty())
			System.out.println("SWEEP LINE STATUS SHOULD BE EMPTY AT START-UP!!");
		
		LineInstance li1 = new LineInstance(0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
		
		plane.printSweepLine();
		
		plane.rootNode = plane.sweepLine.add(li1);
		
		plane.printSweepLine();
		
		plane.rootNode = plane.rootNode.remove(li1);
		
		plane.printSweepLine();
	}
//
//	@Test
//	public void getSuccessor() {
//		System.out.println("-----------getSuccessor");
//		Plane plane = new Plane("input.txt");
//
//		RedBlackTree line0 = plane.sweepLine.locate(new LineInstance(
//				plane.lines.get(0), -2.5));
//		RedBlackTree line1 = plane.sweepLine.locate(new LineInstance(
//				plane.lines.get(1), -2.5));
//		RedBlackTree line2 = plane.sweepLine.locate(new LineInstance(
//				plane.lines.get(2), -2.5));
//
//		System.out.println("In order successor of (0) is :");
//		line0 = RedBlackTree.getSuccessor(line0, plane.sweepLine.root());
//		if (line0 != null)
//			line0.printNode();
//		System.out.println("In order successor of (1) is :");
//		line1 = RedBlackTree.getSuccessor(line1, plane.sweepLine.root());
//		if (line1 != null)
//			line1.printNode();
//		System.out.println("In order successor of (2) is :");
//		line2 = RedBlackTree.getSuccessor(line2, plane.sweepLine.root());
//		if (line2 != null)
//			line2.printNode();
//	}
//
//	@Test
//	public void getPredecessor() {
//		System.out.println("-----------getPredecessor");
//		Plane plane = new Plane("input.txt");
//
//		RedBlackTree line0 = plane.sweepLine.locate(new LineInstance(
//				plane.lines.get(0), -2.5));
//		RedBlackTree line1 = plane.sweepLine.locate(new LineInstance(
//				plane.lines.get(1), -2.5));
//		RedBlackTree line2 = plane.sweepLine.locate(new LineInstance(
//				plane.lines.get(2), -2.5));
//
//		System.out.println("In order Predecessor of (0) is :");
//		line0 = RedBlackTree.getPredecessor(line0, plane.sweepLine.root());
//		if (line0 != null)
//			line0.printNode();
//		System.out.println("In order Predecessor of (1) is :");
//		line1 = RedBlackTree.getPredecessor(line1, plane.sweepLine.root());
//		if (line1 != null)
//			line1.printNode();
//		System.out.println("In order Predecessor of (2) is :");
//		line2 = RedBlackTree.getPredecessor(line2, plane.sweepLine.root());
//		if (line2 != null)
//			line2.printNode();
//	}
//
//	@Test
//	public void inorderTraversal() {
//		System.out.println("-----------inorderTraversal----------");
//		Plane plane = new Plane("input.txt");
//
//		RedBlackTree.inOrder(plane.sweepLine);
//
//	}
//
//	@Test
//	public void parent() {
//		System.out.println("-----------getSuccessor_Predecessor");
//		Plane plane = new Plane("input.txt");
//
//		System.out.println("Parent of (1) is : ");
//		plane.sweepLine.locate(new LineInstance(plane.lines.get(1), -2.5)).parent
//				.printNode();
//
//		System.out.println("Parent of (0) is : ");
//		plane.sweepLine.locate(new LineInstance(plane.lines.get(0), -2.5)).parent
//				.printNode();
//
//		System.out.println("Parent of (2) is : ");
//		plane.sweepLine.locate(new LineInstance(plane.lines.get(2), -2.5)).parent
//				.printNode();
//	}
//
//	@Test
//	public void root() {
//		System.out.println("-----------Root-----------");
//		Plane plane = new Plane("input.txt");
//		plane.sweepLine.printNode();
//		// if (plane.sweepLine.left.left == null)
//		// System.out.println("GOTCHA!");
//		plane.sweepLine.left.printNode();
//
//		plane.sweepLine.right.printNode();
//
//		plane.sweepLine.left.right.printNode();
//
//		plane.sweepLine.right.left.printNode();
//	}
//
//	@Test
//	public void arrayFromTree() {
//		System.out.println("-----------arrayFromTree-----------");
//		Plane plane = new Plane("input.txt");
//
//		RedBlackTree.temp.clear();
//		RedBlackTree.getArrayInOrder(plane.sweepLine.root());
//		ArrayList<RedBlackTree> array = new ArrayList<RedBlackTree>(
//				RedBlackTree.temp);
//		for (RedBlackTree r : array)
//			r.value.printLine();
//
//	}
}

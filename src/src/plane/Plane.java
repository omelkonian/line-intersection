package plane;
import input.CommandType;
import input.InputSolver;

import java.util.ArrayList;

import red_black_tree.RedBlackTree;
import basic_geometric_entities.Line;
import events.Event;
import events.EventQueue;
import events.EventType;

public class Plane {
	int lineNo;
	public ArrayList<Line> lines;
	public ArrayList<CommandType> commands;
	
	public EventQueue events;
	public RedBlackTree sweepLine;
	public RedBlackTree rootNode;
	
	public int intersectionNo;
	
	public Plane(String inputText) {
		this.intersectionNo = 0;
		
		InputSolver inputData = new InputSolver(inputText);
	
		this.lineNo = inputData.getLineNo();
		// Line equations stored in an array (taken from input file).
		this.lines = new ArrayList<Line>(inputData.getLines()); 
		
		// Commands stored in an array to be handled.
		this.commands = new ArrayList<CommandType>(inputData.getCommands());
		
		// Events stored in Priority Queue.
		this.events = new EventQueue(new Event());
		
		// Enqueue initial events (start/end points).
		for (Line l : this.lines) {
			this.events.add(new Event(l.getStartPoint().x, EventType.START, l));
			this.events.add(new Event(l.getEndPoint().x, EventType.END, l));
		}
		
		
		//Sweep line stored in a Red-Black tree.
		this.sweepLine = new RedBlackTree();
		this.rootNode = this.sweepLine;
		
//		System.out.println("STARTING AT " + startingX);
	
		
//		this.sweepLine = this.sweepLine.add(new LineInstance(this.events.element().associatedLine, startingX));
				
	}
	
	
	public void printInput() {
		this.printLines();
		this.printCommands();
	}
	public void printLines() {
		for (Line line : this.lines) {
			line.printLine();
		}
	}
	public void printCommands() {
		for (CommandType com : this.commands) {
			System.out.println(com.toString());
		}
	}
	
	public void printSweepLine() {
		System.out.println("^^^^^^^^^^^^^^Printing sweep line status^^^^^^^^^^^^^^^");
		RedBlackTree.inOrder(this.rootNode.root());
		System.out.println();
		System.out.println();
	}
	public void printEvents() {
		System.out.println("^^^^^^^^^^^^^^Printing events^^^^^^^^^^^^^^^");
		for (Event e : this.events.priorityQueue)
			System.out.println(e.toString());
		System.out.println();
		System.out.println();
	}
	
	public void printAll() {
		this.printEvents();
		this.printSweepLine();
	}
	
	public void printStatus() {
		int lineNo = this.sweepLine.countTree(rootNode.root());
		System.out.printf("status: %d:", lineNo);
		ArrayList<Integer> lineIDs = new ArrayList<Integer>(RedBlackTree.fillIDs(rootNode.root()));
		for (Integer id : lineIDs) {
			System.out.printf(" %d", id);
		}
		System.out.println();
	}
	
}


import java.util.ArrayList;

import basic_geometric_entities.Line;

public class InputSolver {
	LineConstructor lineConstructor;
	CommandConstructor commandConstructor;
	
	public InputSolver(String inputFile) {
		this.lineConstructor = new LineConstructor(inputFile);
		this.commandConstructor = new CommandConstructor(this.lineConstructor.inputPosition);
	}
	
	public int getLineNo() {
		return this.lineConstructor.lineNo;
	}
	
	public ArrayList<Line> getLines() {
		return this.lineConstructor.lines;
	}
	
	public ArrayList<CommandType> getCommands() {
		return this.commandConstructor.commands;
	}
	public void printInput() {
		System.out.println("----------------INPUT-------------------");
		this.lineConstructor.printInputLines();
		this.commandConstructor.printCommands();
	}
}

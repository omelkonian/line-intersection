

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import basic_geometric_entities.Line;

/**
 *	Extracts the lines given as input in a text file.
 */
public class LineConstructor {
	public int lineNo;
	public ArrayList<Line> lines;
	public Scanner inputPosition;

	public LineConstructor(String inputFile) {
		this.lines = new ArrayList<Line>();
		
		Scanner sc = null;
		try {
			URL url = getClass().getResource(inputFile);
			sc = new Scanner(new FileReader(url.getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int lineCounter = 0;
		this.lineNo = sc.nextInt();
		for (int i = 0; i < this.lineNo; i++) {
			this.lines.add(new Line(lineCounter, sc.nextDouble(), sc
					.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc
					.nextDouble()));
			lineCounter++;
		}
		
		this.inputPosition = sc;
	}
	
	public void printInputLines() {
		System.out.println("Line No : " + this.lineNo);
		System.out.println("Lines:");
		for (Line l : this.lines) {
			System.out.println("    " + l.toString());
		}
	}
}

package visualization;

import eventhandler.Executor;

public class Main {

	Vizualizer viz;

	public Main() {
	}

	public static void main(String[] args) {
		Main main = new Main();

		if (args.length > 0) {
			for (int i = 0; i < args.length; i++)
				System.out.println(args[i]);
			if (args[0].equals("-display")) { // Displaying, not for tests.
				main.viz = new Vizualizer();
				main.viz.init();			
			}		
		}
		else { // Print what the tests anticipate.
			Executor executor = new Executor("input.txt");
			executor.executeCommands();
		}
	}

}

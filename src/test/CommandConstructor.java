

import java.util.ArrayList;
import java.util.Scanner;

public class CommandConstructor {
	ArrayList<CommandType> commands;

	public CommandConstructor(Scanner inputPosition) {
		this.commands = new ArrayList<CommandType>();

		while (inputPosition.hasNextLine()) {
			String nextCommand = inputPosition.nextLine();

			switch (nextCommand) {
			case "step":
				this.commands.add(CommandType.STEP);
				break;
			case "step -p":
				this.commands.add(CommandType.STEP_P);
				break;
			case "status":
				this.commands.add(CommandType.STATUS);
				break;
			case "run":
				this.commands.add(CommandType.RUN);
				break;
			}
		}
	}

	public void printCommands() {
		System.out.println("Commands:");
		for (CommandType c : this.commands) {
			System.out.println("    Command " + this.commands.indexOf(c)
					+ ": " + c.toString());
		}
	}
}

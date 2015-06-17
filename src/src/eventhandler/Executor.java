package eventhandler;

import input.CommandType;
import plane.Plane;
import events.Event;

public class Executor {
	public Plane plane;
	public CommandType lastCommand;
	
	public Executor(String inputFile) {
		this.plane = new Plane(inputFile);
	}
	
	public Event executeOneCommand() {
		Event ret = null;
		if (!plane.commands.isEmpty()) {
			lastCommand = plane.commands.get(0);
			ret = this.handleCommand(lastCommand);
			plane.commands.remove(0);
		}
		
		return ret;
	}
	
	public void executeCommands() {
		for (CommandType c : plane.commands) {
			this.handleCommand(c);
		}
	}
	
	public Event handleCommand(CommandType command) {
		Event ret = null;
		switch (command) {
		case STEP:
			if (plane.events.isEmpty())
				System.out.printf("error: no more events\n");
			else {
				ret = plane.events.remove();
				EventHandler.handleEvent(plane, ret, false);
			}
			break;
		case STEP_P:
			if (plane.events.isEmpty())
				System.out.printf("error: no more events\n");
			else {
				ret = plane.events.remove();
				EventHandler.handleEvent(plane, ret, true);
			}
			break;
		case STATUS:
			plane.printStatus();
			break;
		case RUN:
			while (!plane.events.isEmpty()) {	
				ret = plane.events.remove();
				EventHandler.handleEvent(this.plane, ret, false);
			}
			System.out.printf("summary: %d segments , %d intersections\n", plane.lines.size(), plane.intersectionNo);
			break;
		default:
			break;
		}
		return ret;
	}
	
	
}

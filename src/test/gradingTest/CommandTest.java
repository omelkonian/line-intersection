package gradingTest;

import org.junit.Test;

import eventhandler.Executor;

public class CommandTest {

	@Test
	public void test() {
		Executor executor = new Executor("input.txt");
		executor.plane.printCommands();
		System.out.println();
		System.out.println();
		System.out.println();
		executor.executeCommands();
	}

}

package eventHandling;

import org.junit.Test;

import eventhandler.Executor;

public class ExecutionTest {

	@Test
	public void test() {
		Executor executor = new Executor("input.txt");
		executor.executeCommands();
	}

}

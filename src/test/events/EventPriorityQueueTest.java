package events;

import org.junit.Test;

import plane.Plane;

public class EventPriorityQueueTest {

	@Test
	public void test() {
		Plane plane = new Plane("input.txt");

		while (plane.events.size() > 0)
			System.out.println(plane.events.remove().toString());
	}
}

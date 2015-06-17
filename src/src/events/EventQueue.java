package events;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EventQueue {
	public PriorityQueue<Event> priorityQueue;
	
	public EventQueue(Comparator<Event> comparator) {
		this.priorityQueue = new PriorityQueue<Event>(11, comparator);
	}
	
	public boolean add(Event e) {
		// Check queue for duplicate x-coordinates.
		for (Event eve : this.priorityQueue) {
			if (eve.x.equals(e.x)) {
				return false;
			}
		}
		return (this.priorityQueue.add(e));
	}
	
	public Event remove() {
		return this.priorityQueue.remove();
	}
	public boolean remove(Event e) {
		return this.priorityQueue.remove(e);
	}

	public Event element() {
		return this.priorityQueue.element();
	}
	
	public boolean isEmpty() {
		return this.priorityQueue.isEmpty();
	}
	
	public int size() {
		return this.priorityQueue.size();
	}

}

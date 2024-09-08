import java.util.*;

public class Stack {
	LinkedList stack;
	public Stack () {
		stack = new LinkedList();
	}
	public void push (Object obj) {
		stack.add(0, obj);
	}
	public Object pop () {
		Object returnVal = stack.element();
		stack.remove(0);
		return returnVal;
	}
	public Object peek () {
		return stack.element();
	}
	public boolean isEmpty () {
		return stack.isEmpty();
	}
}

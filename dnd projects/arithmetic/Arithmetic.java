import java.util.Objects;
import java.util.Scanner;

public class Arithmetic {

	//Evaluates a String exp that has an arithmetic expression, written in classic notation
	// ("( 2 + ( ( 3 * 5 ) / ( 11 - 6 ) ) )")
	public static int evaluate(String exp) {
		String stoutString = convertClassicToStout(exp);
		return (evaluateStout(stoutString));
	}
	
	//Returns the result of doing operand1 operation operand2,
	//e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if(operation.equals("-")) {
			return operand1 - operand2;
		} else if (operation.equals("+")) {
			return operand1 + operand2;
		} else if (operation.equals("*")) {
			return operand1 * operand2;
		} else {
			return operand1 / operand2;
		}
	}
	
	//Evaluates a String exp that has an arithmetic expression written in STOUT notation
	public static int evaluateStout(String exp) {
		Stack stoutStack = new Stack ();
		Scanner expression = new Scanner (exp);
		while (expression.hasNext()) {
			if (expression.hasNextInt()) {
				stoutStack.push(expression.nextInt());
			} else {
				int secondOperand = (int) stoutStack.pop();
				int firstOperand = (int) stoutStack.pop();
				int eval = operate(firstOperand, secondOperand, expression.next());
				stoutStack.push(eval);
			}
		}
		return (int) stoutStack.peek();
	}
	// ( 2 + ( ( 3 * 5 ) / ( 11 - 6 )))
	// 2 3 5 * 11 6 - / +
	public static String convertClassicToStout(String exp) {
		StringBuilder stoutString = new StringBuilder ("");
		Stack operators = new Stack ();
		Scanner expression = new Scanner (exp);
		while (expression.hasNext()) {
			String next = "";
			if (!expression.hasNextInt()) {
				next = expression.next();
			} else {
				stoutString.append(expression.nextInt() + " ");
			}
		
			if (!(next.equals("(") || next.equals(")") || next.equals(""))) {
				operators.push(next);
			} else {
				if (next.equals(")") && !operators.isEmpty()) {
					stoutString.append(operators.pop() + " ");
				}
			}
		}
		return stoutString.substring(0, stoutString.length() - 1);
	}
	
	
}

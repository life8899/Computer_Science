package util;

public class InterpreterCalculator
{
	public static InterpreterNumber calculate(InterpreterExpression expression)
	{
		switch (expression.getOperator()) {
			case "+": return expression.getFirstOperand().add(expression.getSecondOperand());
			case "-": return expression.getFirstOperand().subtract(expression.getSecondOperand());
			case "*": return expression.getFirstOperand().multiply(expression.getSecondOperand());
			case "/": return expression.getFirstOperand().divide(expression.getSecondOperand());
			default: return null;
		}
	}
}
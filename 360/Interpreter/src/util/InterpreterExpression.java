package util;

public class InterpreterExpression
{
	private InterpreterNumber firstOperand;
	private String operator;
	private InterpreterNumber secondOperand;

	public InterpreterExpression(String[] expressionComponents) throws IllegalArgumentException
	{
		if (expressionComponents.length != 3) {
			throw new IllegalArgumentException("InterpreterExpression requires 3 expression components");
		}
		this.firstOperand = new InterpreterNumber(expressionComponents[0]);
		this.operator = expressionComponents[1];
		this.secondOperand = new InterpreterNumber(expressionComponents[2]);
	}

	public InterpreterNumber getFirstOperand()
	{
		return this.firstOperand;
	}

	public String getOperator()
	{
		return this.operator;
	}

	public InterpreterNumber getSecondOperand()
	{
		return this.secondOperand;
	}
}

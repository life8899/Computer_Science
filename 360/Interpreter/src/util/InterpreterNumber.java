package util;

public class InterpreterNumber
{
	private Double doubleValue;
	private Integer integerValue;

	public InterpreterNumber(String stringValue) throws NumberFormatException
	{
		try {
			integerValue = Integer.parseInt(stringValue);
		} catch (NumberFormatException e) {
			doubleValue = Double.parseDouble(stringValue);
		}
	}

	public InterpreterNumber(Double doubleValue)
	{
		this.doubleValue = doubleValue;
		this.integerValue = null;
	}

	public InterpreterNumber(Integer integerValue)
	{
		this.integerValue = integerValue;
		this.doubleValue = null;
	}

	public Double getDoubleValue()
	{
		return this.doubleValue;
	}

	public Integer getIntegerValue()
	{
		return this.integerValue;
	}

	public String toString()
	{
		if (doubleValue != null) {
			return String.valueOf(doubleValue);
		} else if (integerValue != null) {
			return String.valueOf(integerValue);
		} else {
			return "Uninitialized InterpreterNumber";
		}
	}

	public InterpreterNumber add(InterpreterNumber operand)
	{
		// x + y = z
		if (this.doubleValue != null) { // x is a double
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.doubleValue + operand.getDoubleValue());
			} else { // y is an integer
				return new InterpreterNumber(this.doubleValue + operand.getIntegerValue());
			}
		} else { // x is an integer
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.integerValue + operand.getDoubleValue());
			} else { // y is an integer
				return new InterpreterNumber(this.integerValue + operand.getIntegerValue());
			}
		}
	}

	public InterpreterNumber subtract(InterpreterNumber operand)
	{
		// x + y = z
		if (this.doubleValue != null) { // x is a double
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.doubleValue - operand.getDoubleValue());
			} else { // y is an integer
				return new InterpreterNumber(this.doubleValue - operand.getIntegerValue());
			}
		} else { // x is an integer
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.integerValue - operand.getDoubleValue());
			} else { // y is an integer
				return new InterpreterNumber(this.integerValue - operand.getIntegerValue());
			}
		}
	}

	public InterpreterNumber multiply(InterpreterNumber operand)
	{
		// x + y = z
		if (this.doubleValue != null) { // x is a double
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.doubleValue * operand.getDoubleValue());
			} else { // y is an integer
				return new InterpreterNumber(this.doubleValue * operand.getIntegerValue());
			}
		} else { // x is an integer
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.integerValue * operand.getDoubleValue());
			} else { // y is an integer
				return new InterpreterNumber(this.integerValue * operand.getIntegerValue());
			}
		}
	}

	public InterpreterNumber divide(InterpreterNumber operand)
	{
		// x + y = z
		if (this.doubleValue != null) { // x is a double
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.doubleValue / operand.getDoubleValue());
			} else { // y is an integer
				return new InterpreterNumber(this.doubleValue / operand.getIntegerValue());
			}
		} else { // x is an integer
			if (operand.getDoubleValue() != null) { // y is a double
				return new InterpreterNumber(this.integerValue / operand.getDoubleValue());
			} else { // y is an integer
				// Cast to double to avoid integer division
				InterpreterNumber result = new InterpreterNumber((double)this.integerValue / operand.getIntegerValue());
				if (result.toString().endsWith(".0")) { // Even division, integer division was preferred
					return new InterpreterNumber(this.integerValue / operand.getIntegerValue());
				} else { // Uneven division, double division was preferred
					return result;
				}
			}
		}
	}
}
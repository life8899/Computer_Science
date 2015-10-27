package main;

import identifier.Identifier;
import identifier.SymbolTable;
import parser.ExpressionParser;
import parser.IdentifierParser;
import parser.NumericParser;
import parser.StringParser;
import util.InterpreterCalculator;
import util.InterpreterNumber;

import java.util.Scanner;

public class Interpreter
{
	public static SymbolTable<InterpreterNumber> numbers = new SymbolTable<>();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        String input;

        System.out.println("Starting Interpreter...");
        while (!quit) {
            System.out.print(">> ");
            input = scanner.nextLine().trim();
			if (StringParser.isQuit(input)) {
				quit = true;
			}
            if (NumericParser.isNumericConstant(input)) {
                System.out.println(StringParser.parsePrintConstant(input));
            }
            if (ExpressionParser.isArithmeticExpression(input)) {
	            System.out.println(InterpreterCalculator.calculate(ExpressionParser.parseArithmeticExpression(input)));
            }
            if (IdentifierParser.isIdentifier(input)) {
	            if (!numbers.contains(input)) {
		            numbers.insert(new Identifier<>(input, new InterpreterNumber(0)));
	            }
            }
            if (IdentifierParser.isConstantAssignment(input)) {
	            Identifier<InterpreterNumber> x = IdentifierParser.parseConstantAssignment(input);
	            if (numbers.contains(x)) {
		            numbers.update(x);
	            } else {
		            numbers.insert(x);
	            }
            }
            if (IdentifierParser.isIdentifierAssignment(input)) {
	            String[] identifierNames = IdentifierParser.parseIdentifierAssignment(input);
	            if (numbers.contains(identifierNames[1])) {
		            Identifier<InterpreterNumber> newIdentifier = new Identifier<>(identifierNames[0], numbers.lookup(identifierNames[1]));
		            if (numbers.contains(newIdentifier)) {
			            numbers.update(newIdentifier);
		            } else {
			            numbers.insert(newIdentifier);
		            }
	            } else {
		            System.out.println("Undefined Identifier " + identifierNames[1]);
	            }
            }
            if (IdentifierParser.isIdentifierConstantExpressionAssignment(input)) {
	            String[] assignmentComponents = IdentifierParser.parseIdentifierConstantExpressionAssignment(input);
	            String firstIdentifierName = assignmentComponents[0];
	            String arithmeticExpression = assignmentComponents[1];
	            Identifier<InterpreterNumber> identifier = new Identifier<>(firstIdentifierName, InterpreterCalculator.calculate(ExpressionParser.parseArithmeticExpression(arithmeticExpression)));
	            if (numbers.contains(firstIdentifierName)) {
		            numbers.update(identifier);
	            } else {
		            numbers.insert(identifier);
	            }
            }
            if (IdentifierParser.isIdentifierExpressionAssignment(input)) {
				try {
					String[] assignmentComponents = IdentifierParser.parseIdentifierExpressionAssignment(input);
					String firstIdentifierName = assignmentComponents[0];
					String arithmeticExpression = assignmentComponents[1];
					Identifier<InterpreterNumber> identifier = new Identifier<>(firstIdentifierName, InterpreterCalculator.calculate(ExpressionParser.parseArithmeticExpression(arithmeticExpression)));
					if (numbers.contains(firstIdentifierName)) {
						numbers.update(identifier);
					} else {
						numbers.insert(identifier);
					}
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
            }
            if (StringParser.isPrintConstant(input)) {
	            System.out.println(StringParser.parsePrintConstant(input));
            }
            if (StringParser.isPrintIdentifier(input)) {
	            String identifierName = StringParser.parsePrintIdentifier(input);
	            if (numbers.contains(identifierName)) {
		            System.out.println(numbers.lookup(identifierName).toString());
	            } else {
		            System.out.println("Undefined Identifier " + identifierName);
	            }
            }
        }
        System.out.println("Quitting...");
    }
}

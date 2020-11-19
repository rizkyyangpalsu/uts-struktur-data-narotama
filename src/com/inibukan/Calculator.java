package com.inibukan;

import java.util.Stack;

public class Calculator {

    protected enum Operator{SUB, ADD, DIVISION, MULTI, MODULO, EXPONENT, NOT_FOUND}
    protected Stack<Double> numberStack = new Stack<Double>();
    protected Stack<Operator> operatorStack = new Stack<Operator>();

    public double main(String arithmetic) {
        String arithmeticReplace = arithmetic.replace(" ", "");

        for (Character character : arithmeticReplace.toCharArray()) {
            try {
                numberStack.push(Double.parseDouble(character.toString()));
            } catch (NumberFormatException exception) {
                Operator operator = checkOperator(character.toString());
                if (operator == Operator.NOT_FOUND) {
                    System.out.println("Operator not found!");
                    System.exit(1);
                }

                checkOrderOperation(operator);
                operatorStack.push(operator);
            }
        }

        checkOrderOperation(Operator.NOT_FOUND);
        if (numberStack.size() == 1 && operatorStack.size() == 0) {
            return numberStack.pop();
        }

        return 0;
    }

    protected void checkOrderOperation(Operator operator) {
        while (numberStack.size() >= 2 && operatorStack.size() >= 1) {
            if (operatorPriority(operator) <= operatorPriority(operatorStack.peek())) {
                double secondNumber = numberStack.pop();
                double firstNumber = numberStack.pop();
                Operator operator1 = operatorStack.pop();

                double result = calculate(firstNumber, secondNumber, operator1);
                numberStack.push(result);
            } else {
                break;
            }
        }
    }

    protected Operator checkOperator(String charOperator) {
        return switch (charOperator) {
            case "+" -> Operator.ADD;
            case "-" -> Operator.SUB;
            case "*" -> Operator.MULTI;
            case "/" -> Operator.DIVISION;
            case "%" -> Operator.MODULO;
            case "^" -> Operator.EXPONENT;
            default -> Operator.NOT_FOUND;
        };
    }

    protected int operatorPriority(Operator operator) {
        return switch (operator) {
            case ADD -> 1;
            case SUB -> 1;
            case MULTI -> 2;
            case DIVISION -> 2;
            case MODULO -> 3;
            case EXPONENT -> 4;
            default -> 0;
        };
    }

    protected double calculate(double first, double second, Operator operator) {
        return switch (operator) {
            case ADD -> first + second;
            case SUB -> first - second;
            case MULTI -> first * second;
            case DIVISION -> first / second;
            case MODULO -> first % second;
            case EXPONENT -> Math.pow(first, second);
            default -> 0;
        };
    }

}

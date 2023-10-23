package pl.graczyk;

import java.util.List;
import java.util.Stack;

import static pl.graczyk.Constants.*;

public class Onp {
    private String expression;
    Stack<Character> onpStack = new Stack();

    public Onp(String expression) {
        this.expression = expression;
    }

    public String calcOnp() {
        String result = "";
        char[] expressionChars = expression.toCharArray();

        for(char expressionChar: expressionChars) {
            if(Character.isDigit(expressionChar)) {
                result += expressionChar;
            } else if (expressionChar == OPEN_BRACKET) {
                onpStack.push(expressionChar);
            } else if(expressionChar == CLOSE_BRACKET) {
                result = emptyTheStack(result);
            }  else if (isOperator(expressionChar)) {
                if(!onpStack.empty()){
                    char lastStackElement = onpStack.peek();
                    if(stackElHasHigherOrSamePriority(expressionChar,lastStackElement)
                            && elIsNotBracket(lastStackElement)) {
                        result += onpStack.pop();
                    }}
                onpStack.push(expressionChar);
            }
        }
        result = emptyTheStack(result);
        return result;
    }

    /**
     * Checks if an element is open or close bracket
     *
     * @return  {@code true} if item is not open/close bracket; {@code false} otherwise.
     */
    private boolean elIsNotBracket(char stackElement) {
        return stackElement != OPEN_BRACKET
                && stackElement != CLOSE_BRACKET;
    }

    /**
     * Push each element of stack.
     * If it is not open/close bracket, add element to result string.
     *
     * @return  modified string.
     */
    private String emptyTheStack(String result) {
        while (!onpStack.isEmpty()) {
            if(onpStack.peek().equals(OPEN_BRACKET) || onpStack.peek().equals(CLOSE_BRACKET)) {
                onpStack.pop();
            } else {
                result += onpStack.pop();
            }
        }
        return result;
    }

    /**
     * Check if stack element has Higher or Same Priority
     *
     * @return  {@code true} if expression char is not * or / and if stack element is not + or -;
     * {@code false} otherwise.
     */
    private boolean stackElHasHigherOrSamePriority(char expressionChar,
                                                   char lastStackElement) {
        return !((expressionChar == MULTIPLY || expressionChar == DIVIDE)
              && (lastStackElement == PLUS || lastStackElement == MINUS));
    }

    /**
     * Check if element is an Operator
     *
     * @return  {@code true} if element is an operator;
     * {@code false} otherwise.
     */
    private boolean isOperator(char expressionChar) {
        List<Character> operators = List.of(MULTIPLY, MINUS, DIVIDE, PLUS);
        return operators.contains(expressionChar);
    }
}

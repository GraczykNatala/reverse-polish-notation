package pl.graczyk;

import java.util.Stack;

import static pl.graczyk.Constants.*;

public class Onp {
    private final String expression;
    Stack<Character> onpStack = new Stack();

    public Onp(String expression) {
        this.expression = expression;
    }

    public String calcOnp() {
        String result = "";
        char[] expressionChars = expression.toCharArray();

        for (char expressionChar : expressionChars) {
            switch(expressionChar) {
                case OPEN_BRACKET
                        -> onpStack.push(expressionChar);
                case CLOSE_BRACKET
                        -> result = emptyTheStack(result);
                case PLUS, MINUS, MULTIPLY, DIVIDE
                        -> {
                    while(!onpStack.empty()) {
                        char lastStackElement = onpStack.peek();
                        if(stackElHasHigherOrSamePriority(expressionChar, lastStackElement)
                                && elIsNotBracket(lastStackElement)) {
                            result += onpStack.pop();
                        }else {
                            break;
                        }}
                    onpStack.push(expressionChar);
                }
                default
                        -> {
                    if(Character.isDigit(expressionChar)) {
                        result += expressionChar;
                    }
                }
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
        StringBuilder resultBuilder = new StringBuilder(result);
        while (!onpStack.isEmpty()) {
            if(onpStack.peek().equals(OPEN_BRACKET) || onpStack.peek().equals(CLOSE_BRACKET)) {
                onpStack.pop();
            } else {
                resultBuilder.append(onpStack.pop());
            }
        }
        result = resultBuilder.toString();
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
}

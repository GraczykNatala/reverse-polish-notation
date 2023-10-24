package pl.graczyk;
import java.util.Stack;
import static pl.graczyk.Constants.*;

public class Onp {
    private final String expression;
     private final Stack<Character> onpStack = new Stack<>();

    public Onp(String expression) {
        this.expression = expression;
    }

    public String calcOnp() {
        String result = "";
        char[] expressionChars = expression.toCharArray();
        int nextCharIndex = 0;
        for (char expressionChar : expressionChars) {
            nextCharIndex+= 1;
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
                            result += onpStack.pop() + " ";
                        }else {
                            break;
                        }}
                    onpStack.push(expressionChar);
                }
                default
                        -> {
                    if(Character.isDigit(expressionChar)) {
                        result += expressionChar;
                        if(nextCharIndex < expressionChars.length && !Character.isDigit(expressionChars[nextCharIndex])) {
                                result += " ";
                        } else if (isLastCharButStackNotEmpty(expressionChars, nextCharIndex)) {
                            result += " ";
                        }
                    }
                }
            }
        }
        result = emptyTheStack(result);
        return result;
    }
    /**
     * Checks if an element is last element, but stack is not empty so there should be a space between this and next char
     *
     * @return  {@code true} if char is last in expression and stack is not empty; {@code false} otherwise.
     */
    private boolean isLastCharButStackNotEmpty(char[] expressionChars, int nextCharIndex) {
        return nextCharIndex == expressionChars.length && !onpStack.isEmpty();
    }

    /**
     * Checks if an element is open or close bracket
     * @return  {@code true} if item is not open/close bracket; {@code false} otherwise.
     */
    private boolean elIsNotBracket(char stackElement) {
        return stackElement != OPEN_BRACKET
                && stackElement != CLOSE_BRACKET;
    }

    /**
     * Push each element of stack.
     * If it is not open/close bracket, add element to result string.
     * @return  modified string.
     */
    private String emptyTheStack(String result) {
        StringBuilder resultBuilder = new StringBuilder(result);
        while (!onpStack.isEmpty()) {
            if(onpStack.peek().equals(OPEN_BRACKET) || onpStack.peek().equals(CLOSE_BRACKET)) {
                onpStack.pop();
            } else {
                resultBuilder.append(onpStack.pop()).append(" ");
            }
        }
        result = resultBuilder.toString();
        return result;
    }

    /**
     * Check if stack element has Higher or Same Priority
     * @return  {@code true} if expression char is not * or / and if stack element is not + or -;
     * {@code false} otherwise.
     */
    private boolean stackElHasHigherOrSamePriority(char expressionChar,
                                                   char lastStackElement) {
        return !((expressionChar == MULTIPLY || expressionChar == DIVIDE)
              && (lastStackElement == PLUS || lastStackElement == MINUS));
    }
}

package calculate;

import calculate.element.Element;

import java.util.LinkedList;
import java.util.List;

import static calculate.ExampleValidation.checkExpression;
import static calculate.element.CalculateElement.calculateElement;
import static calculate.element.ElementCreator.*;
import static calculate.SymbolType.Symbol.*;
import static calculate.SymbolType.getSymbolType;

public class CalculateExample {
    public static double calculateExample(final String example) {

        if (checkExpression(example))
            return calculate(example);

        else
            throw new StringException("Какая-то неведомая ошибка");
    }

    public static Double calculate(final String subExample) {
        List<Double> numberList = new LinkedList<>();
        List<Character> signList = new LinkedList<>();
        Element element = new Element();

        int i = 0;
        signList.add(null);

        while (i < subExample.length()) {
            char symbol = subExample.charAt(i);
            SymbolType.Symbol symbolType = getSymbolType(symbol);

            if (symbolType == DIGIT) {
                element = getExampleNumber(subExample.substring(i));

            } else if (symbolType == LETTER) {
                element = getExampleFunction(subExample.substring(i));

            } else if (symbolType == BRACKET) {
                element = getExampleBracket(subExample.substring(i));

            } else if (symbolType == SIGN) {
                if (i == 0) {
                    numberList.add(0.0);

                }
                signList.add(subExample.charAt(i));
                i++;
                continue;
            }

            numberList.add(element.getNumber());
            i += element.getLength();
        }
        return calculateElement(numberList, signList);
    }

    public static int getClosingBracketIndex(final String subExample) {
        int bracketAmount = 0;
        int lastBracketIndex = 0;
        char currentChar;

        do {
            currentChar = subExample.charAt(lastBracketIndex++);

            if (currentChar == '(') {
                bracketAmount++;

            } else if (currentChar == ')') {
                bracketAmount--;
            }
        } while (!(bracketAmount == 0 && currentChar == ')'));
        lastBracketIndex--;

        return lastBracketIndex;
    }
}
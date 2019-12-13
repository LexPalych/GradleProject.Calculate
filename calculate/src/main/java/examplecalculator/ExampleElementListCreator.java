package examplecalculator;

import examplecalculator.exampleelement.Element;
import examplecalculator.exampleelement.ElementFactorial;
import examplecalculator.exampleelement.ElementNumber;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static examplecalculator.ExampleElementListCreator.ElementListReplacer.replaceElementList;
import static examplecalculator.exampleelement.Element.TypeElement.FACTORIAL;
import static examplecalculator.exampleelement.Element.TypeElement.SIGN;
import static examplecalculator.exampleelement.ElementCalculator.calculateElement;
import static examplecalculator.exampleelement.ElementCreator.createElementFunction;

public class ExampleElementListCreator {
    /**
     * ��������� ������ (��������� �������� �������) �� ��������:
     * �����, ����� �������������� ��������, ��������� � �������, �������
     * �������� ����� ���������, ��� ��������� � ������� � �������, ���������� �������������
     * �������� ���������� �������� � ������ ��� ����������� ������� �������� ������� (���������� �������� �������)
     * @param subExample - ������ (��������� �������� �������)
     * @return - ���������� ������ ��������� �������, ��������� �� �������� �������� � ������ (������-�������) ����� ����
     */
    public static Double createElementList(final String subExample) {
        List<Element> elementList = new LinkedList<>();
        Element element;
        int i = 0;

        while (i < subExample.length()) {
            char currentSymbol = subExample.charAt(i);

            //� ����������� �� ����, ����� ������� ������, ���������� �������, ������� ������ ������� �������
            Function<String, Element> createElementFunction = createElementFunction(currentSymbol);
            element = createElementFunction.apply(subExample.substring(i));
            elementList.add(element);

            //�������� ����������� �� ������ �������, �������� ����� ����� ���������� ������� �������� �������
            i += element.getElement().length();
        }

        return calculateElement(replaceElementList(elementList));
    }

    static class ElementListReplacer {
        /**
         * ���������� "�����" ������ ���������, ������ "!" �� ��� ������ � "-" � ������ ������
         * @param rowElementList - "�����" ������ ���������
         * @return - ��������� ������������ ������ ���������
         */
        static List<Element> replaceElementList(final List<Element> rowElementList) {
            return replaceFactorialElement(replaceFirstElement(rowElementList));
        }

        /**
         * ���������, ���� �� � ������ ������ ������� ���� SIGN (���� ����� "-")
         * ���� ����, �� � ������ ������ ���������� ���� ("0")
         * ����� ��� ���������� �������� "�����-����-�����-...-����-...-�����"
         * @param elementList - "�����" ������ ���������
         * @return - ������������ ������ ���������
         */
        private static List<Element> replaceFirstElement(final List<Element> elementList) {
            if (elementList.get(0).getTypeElement() == SIGN) {
                elementList.add(0, new ElementNumber("0"));
            }

            return elementList;
        }

        /**
         * ���������, ���� �� � "�����" ������ ��������� ���� ���������� "!"
         * ���� ����, �������� ���������� ������� ��������� ���� FACTORIAL
         * ���� "!" ������������ � ������ ���������� �������� � ���������� � "�����" ������ ���������
         * ����� ��� ���������� �������� "�����-����-�����-...-����-...-�����"
         * @param elementList - "�����" ������ ���������
         * @return - ������������ ������ ���������
         */
        private static List<Element> replaceFactorialElement(final List<Element> elementList) {
            int i = 0;

            while (i < elementList.size()) {
                if (elementList.get(i).getTypeElement() == FACTORIAL) {
                    String element = elementList.get(i-1).getElement() + elementList.get(i).getElement();
                    Double value = (Double) elementList.get(i-1).getValue();

                    elementList.set(i-1, new ElementFactorial(element, value));
                    elementList.remove(i);

                } else {
                    i++;
                }
            }

            return elementList;
        }
    }

}
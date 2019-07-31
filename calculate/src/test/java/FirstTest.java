import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {

    @Test
    void first() {
        assertTrue(2 + 2 == 4);
    }

    @Test
    void second() {
        assertTrue(2 + 2 == 5);
    }

    @RepeatedTest(1000)
    void fourth() {
        var failExpression = "20000!";
        assertTrue(new CalculateString(failExpression).calculateString() != 0);



    }

    @Test
    void third() {
        var expression1 = "-1.1+0.1+2+3*8/2-5*e^0+sin(30)-tan(0)-(1.5-0.5*2-1+3!)-(1+(2+((2+3)*2)/2-4)*3)"; // -7
        var expression2 = "1-2*3*1-1*2/2-1*2*4-2"; // -16
        var expression3 = "-1.1+0.1+2+3*8/2-5*e^0+sin(-30)-tan(0)-(1.5-0.5*2-1+3!)-(-1+(-2+((2+3)*2)/2-4)*3)"; // 6
        var expression4 = "11-5*2/4/8*16+4+(7-9/3*4)-3*3"; // -4

        var answer1 = -7;
        var answer2 = -16;
        var answer3 = 6;
        var answer4 = -4;

//        var expressionResult1 = new CalculateString(expression1).calculateString();
//        var expressionResult2 = new CalculateString(expression2).calculateString();
//        var expressionResult3 = new CalculateString(expression3).calculateString();
//        var expressionResult4 = new CalculateString(expression4).calculateString();

//        assertTrue(expressionResult1 == -7);
//        assertTrue(expressionResult2 == -16);
//        assertTrue(expressionResult3 == 6);
//        assertTrue(expressionResult4 == -4);

        var expressions = List.of(expression1, expression2, expression3, expression4);
        var answers = List.of(answer1, answer2, answer3, answer4);

        for (int i = 0; i < expressions.size(); i++) {
            assertTrue(new CalculateString(expressions.get(i)).calculateString() == answers.get(i));
        }
    }

}

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorMethodTesting
{
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////// Function: CheckValid Expression Tests
    @Test
    private void checkValidSimpleAdditionExpressionTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3+5";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidSimpleSubtractionExpressionTest2()
    {
        Calculator calc = new Calculator();
        String sample = "10-5";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidSimpleMultiplicationExpressionTest3()
    {
        Calculator calc = new Calculator();
        String sample = "3*5";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidSimpleDivisionExpressionTest4()
    {
        Calculator calc = new Calculator();
        String sample = "24/3";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidExpressionSpacesIncludedTest5()
    {
        Calculator calc = new Calculator();
        String sample = "  25 +   35 - 22 +3 *        6      ";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidExpressionExponentTest6()
    {
        Calculator calc = new Calculator();
        String sample = "5^5^2";
        assertTrue(calc.isValidExpression(sample));
    }


    /////////////////////////
    //check must fail options
    @Test
    private void checkValidDExpressionDoubleOperatorFailTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3--5";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidDExpressionOnlyDigitsFailTest2()
    {
        Calculator calc = new Calculator();
        String sample = "12345678";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidDExpressionLettersIncludedFailTest3()
    {
        Calculator calc = new Calculator();
        String sample = "12*3/4a-5^";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidDExpressionLettersOnlyFailTest4()
    {
        Calculator calc = new Calculator();
        String sample = "calculator";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidDExpressionNonAlphanumericFailTest5()
    {
        Calculator calc = new Calculator();
        String sample = "1?23;+431-2";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidDExpressionOperatorAtEndFailTest6()
    {
        Calculator calc = new Calculator();
        String sample = "1/3+4*5-";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    private void checkValidDExpressionOperatorAtStartFailTest7()
    {
        Calculator calc = new Calculator();
        String sample = "+1/3+4*5";
        assertFalse(calc.isValidExpression(sample));
    }






    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    /////////////////////////// Function: CheckOperand Tests
    @Test
    private void checkNumberOfOperandsInSimpleExpressionTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3+5+7+9+0";
        ArrayList<Double> operandList = calc.getOperands(sample);
        assertEquals(operandList.size(),5);
    }

    @Test
    private void checkNumberOfOperandsInEmptyExpressionTest2()
    {
        Calculator calc = new Calculator();
        String sample = "";
        ArrayList<Double> operandList = calc.getOperands(sample);
        assertEquals(operandList.size(),0);
    }

    @Test
    private void checkNumberOfOperandsWithMultipleExpressionTest3()
    {
        Calculator calc = new Calculator();
        String sample = "3/5+7+9+0-3*5";
        ArrayList<Double> operandList = calc.getOperands(sample);
        assertEquals(operandList.size(),7);
    }

    @Test
    private void checkOperandsWithMultipleExpressionTest4()
    {
        Calculator calc = new Calculator();
        String sample = "3/5+7+9+0-3*5";
        ArrayList<Double> operandList = calc.getOperands(sample);
        ArrayList<Double> actualList = new ArrayList<>();
        actualList.add(3.0);
        actualList.add(5.0);
        actualList.add(7.0);
        actualList.add(9.0);
        actualList.add(0.0);
        actualList.add(3.0);
        actualList.add(5.0);
        assertEquals(operandList,actualList);
    }



    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    ///////////////////// Function: CheckOperations
    @Test
    private void checkNumberOfOperationsInSimpleExpressionTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3+5+7+9+0";
        ArrayList<Character> operandList = calc.getOperations(sample);
        assertEquals(operandList.size(),4);
    }

    @Test
    private void checkNumberOfOperationsInEmptyExpressionTest2()
    {
        Calculator calc = new Calculator();
        String sample = "";
        ArrayList<Character> operandList = calc.getOperations(sample);
        assertEquals(operandList.size(),0);
    }

    @Test
    private void checkNumberOfOperationsWithMultipleExpressionTest3()
    {
        Calculator calc = new Calculator();
        String sample = "3/5+7+9+0-3*5";
        ArrayList<Character> operandList = calc.getOperations(sample);
        assertEquals(operandList.size(),6);
    }

    @Test
    private void checkOperationsWithMultipleExpressionTest4()
    {
        Calculator calc = new Calculator();
        String sample = "3/5+7+9+0-3*5";
        ArrayList<Character> operandList = calc.getOperations(sample);
        ArrayList<Character> actualList = new ArrayList<>();
        actualList.add('/');
        actualList.add('+');
        actualList.add('+');
        actualList.add('+');
        actualList.add('-');
        actualList.add('*');
        assertEquals(operandList,actualList);
    }



    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    /////////////////////////// Function: Evaluation Tests

    @Test
    private void evaluateExpressionAdditionTest1()
    {
        Calculator calc = new Calculator();
        double operand1 = 1.0;
        double operand2 = 2.0;
        char add = '+';
        assertEquals(calc.evaluateExpression(operand1,operand2,add),3.0);
    }

    @Test
    private void evaluateExpressionAdditionTest2()
    {
        Calculator calc = new Calculator();
        double operand1 = 136.0;
        double operand2 = 27.0;
        char add = '+';
        assertEquals(calc.evaluateExpression(operand1,operand2,add),163.0);
    }


    /////////////////////////////////////////////
    /////////////////////////////////////////////
    //subtraction
    @Test
    private void evaluateExpressionSubtractionTest1()
    {
        Calculator calc = new Calculator();
        double operand1 = 1.0;
        double operand2 = 2.0;
        char subtract = '-';
        assertEquals(calc.evaluateExpression(operand1,operand2,subtract),-1);
    }

    @Test
    private void evaluateExpressionSubtractionTest2()
    {
        Calculator calc = new Calculator();
        double operand1 = 188.0;
        double operand2 = 59.0;
        char subtract = '-';
        assertEquals(calc.evaluateExpression(operand1,operand2,subtract),129);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    //Multiplication
    @Test
    private void evaluateExpressionMultiplicationTest1()
    {
        Calculator calc = new Calculator();
        double operand1 = 1.0;
        double operand2 = 2.0;
        char multiply = '*';
        assertEquals(calc.evaluateExpression(operand1,operand2,multiply),2);
    }

    @Test
    private void evaluateExpressionMultiplicationTest2()
    {
        Calculator calc = new Calculator();
        double operand1 = 10.0;
        double operand2 = 8523.0;
        char multiply = '*';
        assertEquals(calc.evaluateExpression(operand1,operand2,multiply),85230);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    //////////////////////////////////Division
    @Test
    private void evaluateExpressionDivisionTest1()
    {
        Calculator calc = new Calculator();
        double operand1 = 1.0;
        double operand2 = 2.0;
        char divide = '/';
        assertEquals(calc.evaluateExpression(operand1,operand2,divide),0.5);
    }

    @Test
    private void evaluateExpressionDivisionTest2()
    {
        Calculator calc = new Calculator();
        double operand1 = 399.0;
        double operand2 = 3.0;
        char divide = '/';
        assertEquals(calc.evaluateExpression(operand1,operand2,divide),133);
    }

    @Test
    private void evaluateExpressionDivisionTest3()
    {
        Calculator calc = new Calculator();
        double operand1 = 13;
        double operand2 = 0;
        char divide = '/';
        assertEquals(calc.evaluateExpression(operand1,operand2,divide),-1);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    ///////////////////////Function: add to stack
    @Test
    private void addToEmptyStackTest1()
    {
        Calculator calc = new Calculator();
        ArrayList<Double> operands = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        int operandCounter = 0;
        int operationCounter = 0;
        operands.add(1.0);
        operands.add(2.0);
        operations.add('+');
        int numOperandsAdded = calc.addToStack(operands,operations,operandStack,operationStack,operandCounter,operationCounter);
        assertEquals(numOperandsAdded, 2);
    }

    @Test
    private void addNonEmptyStackTest1()
    {
        Calculator calc = new Calculator();
        ArrayList<Double> operands = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        int operandCounter = 2;
        int operationCounter = 1;
        operands.add(6.0);
        operands.add(7.0);
        operands.add(1.0);
        operands.add(2.0);
        operations.add('+');
        operations.add('-');
        operandStack.push(13.0);
        int numOperandsAdded = calc.addToStack(operands,operations,operandStack,operationStack,operandCounter,operationCounter);
        assertEquals(numOperandsAdded, 1);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    ///////////////////////Function: Solve
    @Test
    private void solveSimpleExpressionAdditionTest1()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("3+3");
        assertEquals(result,6);
    }

    @Test
    private void solveSimpleExpressionAdditionTest2()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("531+23153+54235+64875");
        assertEquals(result,142794);
    }

    @Test
    private void solveSimpleExpressionSubtractionTest1()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("12-8");
        assertEquals(result,4);
    }

    @Test
    private void solveSimpleExpressionMultiplicationTest1()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("82*7");
        assertEquals(result,574);
    }

    @Test
    private void solveSimpleExpressionDivisionTest1()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("33/11");
        assertEquals(result,3);
    }

    @Test
    private void solveLongExpressionTest1()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("5*5-3*2+4+28/7+23");
        assertEquals(result,50);
    }

    @Test
    private void solveLongExpressionTest2()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("17+6/3*5-8-3*5");
        assertEquals(result,4);
    }

    @Test
    private void solveLongExpressionTest3()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("13*5-26+58+8-31+26/2+5*8");
        assertEquals(result,127);
    }

    @Test
    private void solveLongExpressionTest4()
    {
        Calculator calc = new Calculator();
        double result = calc.solveExpression("159/3+4*33/6");
        assertEquals(result,75);
    }
}

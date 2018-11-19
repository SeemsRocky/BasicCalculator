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
    public void checkValidSimpleAdditionExpressionTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3+5";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidSimpleSubtractionExpressionTest2()
    {
        Calculator calc = new Calculator();
        String sample = "10-5";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidSimpleMultiplicationExpressionTest3()
    {
        Calculator calc = new Calculator();
        String sample = "3*5";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidSimpleDivisionExpressionTest4()
    {
        Calculator calc = new Calculator();
        String sample = "24/3";
        assertTrue(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidExpressionSpacesIncludedTest5()
    {
        Calculator calc = new Calculator();
        String sample = "  25 +   35 - 22 +3 *        6      ";
        assertTrue(calc.isValidExpression(sample));
    }

    /////////////////////////
    //check must fail options
    @Test
    public void checkValidDExpressionDoubleOperatorFailTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3--5";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidDExpressionOnlyDigitsFailTest2()
    {
        Calculator calc = new Calculator();
        String sample = "12345678";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidDExpressionLettersIncludedFailTest3()
    {
        Calculator calc = new Calculator();
        String sample = "12*3/4a-5";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidDExpressionLettersOnlyFailTest4()
    {
        Calculator calc = new Calculator();
        String sample = "calculator";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidDExpressionNonAlphanumericFailTest5()
    {
        Calculator calc = new Calculator();
        String sample = "1?23;+431-2";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidDExpressionOperatorAtEndFailTest6()
    {
        Calculator calc = new Calculator();
        String sample = "1/3+4*5-";
        assertFalse(calc.isValidExpression(sample));
    }

    @Test
    public void checkValidDExpressionOperatorAtStartFailTest7()
    {
        Calculator calc = new Calculator();
        String sample = "+1/3+4*5";
        assertFalse(calc.isValidExpression(sample));
    }






    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    /////////////////////////// Function: CheckOperand Tests
    @Test
    public void checkNumberOfOperandsInSimpleExpressionTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3+5+7+9+0";
        ArrayList<Integer> operandList = calc.getOperands(sample);
        assertEquals(operandList.size(),5);
    }

    @Test
    public void checkNumberOfOperandsInEmptyExpressionTest2()
    {
        Calculator calc = new Calculator();
        String sample = "";
        ArrayList<Integer> operandList = calc.getOperands(sample);
        assertEquals(operandList.size(),0);
    }

    @Test
    public void checkNumberOfOperandsWithMultipleExpressionTest3()
    {
        Calculator calc = new Calculator();
        String sample = "3/5+7+9+0-3*5";
        ArrayList<Integer> operandList = calc.getOperands(sample);
        assertEquals(operandList.size(),7);
    }

    @Test
    public void checkOperandsWithMultipleExpressionTest4()
    {
        Calculator calc = new Calculator();
        String sample = "3/5+7+9+0-3*5";
        ArrayList<Integer> operandList = calc.getOperands(sample);
        ArrayList<Integer> actualList = new ArrayList<>();
        actualList.add(3);
        actualList.add(5);
        actualList.add(7);
        actualList.add(9);
        actualList.add(0);
        actualList.add(3);
        actualList.add(5);
        assertEquals(operandList,actualList);
    }



    ////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////
    ///////////////////// Function: CheckOperations
    @Test
    public void checkNumberOfOperationsInSimpleExpressionTest1()
    {
        Calculator calc = new Calculator();
        String sample = "3+5+7+9+0";
        ArrayList<Character> operandList = calc.getOperations(sample);
        assertEquals(operandList.size(),4);
    }

    @Test
    public void checkNumberOfOperationsInEmptyExpressionTest2()
    {
        Calculator calc = new Calculator();
        String sample = "";
        ArrayList<Character> operandList = calc.getOperations(sample);
        assertEquals(operandList.size(),0);
    }

    @Test
    public void checkNumberOfOperationsWithMultipleExpressionTest3()
    {
        Calculator calc = new Calculator();
        String sample = "3/5+7+9+0-3*5";
        ArrayList<Character> operandList = calc.getOperations(sample);
        assertEquals(operandList.size(),6);
    }

    @Test
    public void checkOperationsWithMultipleExpressionTest4()
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
    public void evaluateExpressionAdditionTest1()
    {
        Calculator calc = new Calculator();
        int operand1 = 1;
        int operand2 = 2;
        char add = '+';
        assertEquals(calc.evaluateExpression(operand1,operand2,add),3);
    }

    @Test
    public void evaluateExpressionAdditionTest2()
    {
        Calculator calc = new Calculator();
        int operand1 = 136;
        int operand2 = 27;
        char add = '+';
        assertEquals(calc.evaluateExpression(operand1,operand2,add),163);
    }


    /////////////////////////////////////////////
    /////////////////////////////////////////////
    //subtraction
    @Test
    public void evaluateExpressionSubtractionTest1()
    {
        Calculator calc = new Calculator();
        int operand1 = 1;
        int operand2 = 2;
        char subtract = '-';
        assertEquals(calc.evaluateExpression(operand1,operand2,subtract),-1);
    }

    @Test
    public void evaluateExpressionSubtractionTest2()
    {
        Calculator calc = new Calculator();
        int operand1 = 188;
        int operand2 = 59;
        char subtract = '-';
        assertEquals(calc.evaluateExpression(operand1,operand2,subtract),129);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    //Multiplication
    @Test
    public void evaluateExpressionMultiplicationTest1()
    {
        Calculator calc = new Calculator();
        int operand1 = 1;
        int operand2 = 2;
        char multiply = '*';
        assertEquals(calc.evaluateExpression(operand1,operand2,multiply),2);
    }

    @Test
    public void evaluateExpressionMultiplicationTest2()
    {
        Calculator calc = new Calculator();
        int operand1 = 10;
        int operand2 = 8523;
        char multiply = '*';
        assertEquals(calc.evaluateExpression(operand1,operand2,multiply),85230);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    //////////////////////////////////Division
    @Test
    public void evaluateExpressionDivisionTest1()
    {
        Calculator calc = new Calculator();
        int operand1 = 1;
        int operand2 = 2;
        char divide = '/';
        assertEquals(calc.evaluateExpression(operand1,operand2,divide),0);
    }

    @Test
    public void evaluateExpressionDivisionTest2()
    {
        Calculator calc = new Calculator();
        int operand1 = 399;
        int operand2 = 3;
        char divide = '/';
        assertEquals(calc.evaluateExpression(operand1,operand2,divide),133);
    }

    @Test
    public void evaluateExpressionDivisionTest3()
    {
        Calculator calc = new Calculator();
        int operand1 = 13;
        int operand2 = 0;
        char divide = '/';
        assertEquals(calc.evaluateExpression(operand1,operand2,divide),-1);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    ///////////////////////Function: add to stack
    @Test
    public void addToEmptyStackTest1()
    {
        Calculator calc = new Calculator();
        ArrayList<Integer> operands = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        int operandCounter = 0;
        int operationCounter = 0;
        operands.add(1);
        operands.add(2);
        operations.add('+');
        int numOperandsAdded = calc.addToStack(operands,operations,operandStack,operationStack,operandCounter,operationCounter);
        assertEquals(numOperandsAdded, 2);
    }

    @Test
    public void addNonEmptyStackTest1()
    {
        Calculator calc = new Calculator();
        ArrayList<Integer> operands = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        int operandCounter = 2;
        int operationCounter = 1;
        operands.add(6);
        operands.add(7);
        operands.add(1);
        operands.add(2);
        operations.add('+');
        operations.add('-');
        operandStack.push(13);
        int numOperandsAdded = calc.addToStack(operands,operations,operandStack,operationStack,operandCounter,operationCounter);
        assertEquals(numOperandsAdded, 1);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    ///////////////////////Function: Solve
    @Test
    public void solveSimpleExpressionAdditionTest1()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("3+3");
        assertEquals(result,6);
    }

    @Test
    public void solveSimpleExpressionAdditionTest2()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("531+23153+54235+64875");
        assertEquals(result,142794);
    }

    @Test
    public void solveSimpleExpressionSubtractionTest1()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("12-8");
        assertEquals(result,4);
    }

    @Test
    public void solveSimpleExpressionMultiplicationTest1()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("82*7");
        assertEquals(result,574);
    }

    @Test
    public void solveSimpleExpressionDivisionTest1()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("33/11");
        assertEquals(result,3);
    }

    @Test
    public void solveLongExpressionTest1()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("5*5-3*2+4+28/7+23");
        assertEquals(result,50);
    }

    @Test
    public void solveLongExpressionTest2()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("17+6/3*5-8-3*5");
        assertEquals(result,4);
    }

    @Test
    public void solveLongExpressionTest3()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("13*5-26+58+8-31+26/2+5*8");
        assertEquals(result,127);
    }

    @Test
    public void solveLongExpressionTest4()
    {
        Calculator calc = new Calculator();
        int result = calc.solveExpression("159/3+4*33/6");
        assertEquals(result,75);
    }
}

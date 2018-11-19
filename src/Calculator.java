import java.util.*;
/*
    A simple calculator evaluating basic expressions with only positive numbers.
 */
public class Calculator {

    /*
        Returns if char is a valid operator character
     */
    public static boolean isOperation(char c)
    {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    /*
        Checks if expression is valid and doesn't end with an operation
        Use regex to find only digits and operations and must begin and end with a number
     */
    public static boolean isValidExpression(String expression)
    {
        expression = expression.replace(" ", "");
        //need operator count to check that it's not just a number
        int operatorCount = 0;
        for(int i = 0; i < expression.length(); i++)
        {
            char c = expression.charAt(i);
            // check that the operator is surrounded by digits, and to make sure the next char after an operator is another operator
            if(isOperation(c))
            {
                operatorCount+=1;
                // operator at start,end or repeats return false
                if(i == 0 || i == expression.length()-1 || !(Character.isDigit(expression.charAt(i-1)) && Character.isDigit(expression.charAt(i+1))) )
                {
                    return false;
                }
            }
            // if anything in the expression is not an operator or a number, its false
            else if(!Character.isDigit(c))
            {
                return false;
            }
        }
        if(operatorCount == 0)
        {
            return false;
        }
        return true;
    }

    /*
        Returns operands in the expression
     */
    public static ArrayList<Integer> getOperands(String expression)
    {
        ArrayList<Integer> operandArray = new ArrayList<Integer>();
        int firstDigitIndex = 0;
        int lastDigitIndex = 0;
        while(lastDigitIndex<expression.length())
        {
            // find the operation character and obtain substring from first index counter up to position of char
            if(isOperation(expression.charAt(lastDigitIndex)))
            {
                String numberString = expression.substring(firstDigitIndex,lastDigitIndex);
                int number = Integer.parseInt(numberString);
                Integer operand = new Integer(number);
                operandArray.add(operand);
                firstDigitIndex = lastDigitIndex+1;
            }
            // if lastDigitIndex is about to go past length of string, just return end of substring.
            if(lastDigitIndex+1==expression.length())
            {
                String numberString = expression.substring(firstDigitIndex,expression.length());
                int number = Integer.parseInt(numberString);
                Integer operand = new Integer(number);
                operandArray.add(operand);
                firstDigitIndex = lastDigitIndex;

                break; //remember to exit after adding last number
            }
            else
            {
                //if lastDigitIndex+1!= expression.length then we can continue on in the string.
                lastDigitIndex++;
            }


        }
        return operandArray;
    }
    /*
        Returns operations in expression
     */
    public static ArrayList<Character> getOperations(String expression)
    {
        ArrayList<Character> operationArray = new ArrayList<Character>();

        for(int index =0;index<expression.length();index++)
        {
            if(isOperation(expression.charAt(index)))
            {
                Character operation = new Character(expression.charAt(index));
                operationArray.add(operation);
            }

        }
        return operationArray;
    }
    /*
        Do basic math
     */
    public static int evaluateExpression(int operand1, int operand2,char operation)
    {
        switch(operation)
        {
            case '+':
                return operand1+operand2;
            case '-':
                return operand1-operand2;
            case '*':
                return operand1*operand2;
            case '/':
                if(operand2==0)
                {
                    return -1;
                }
                return operand1/operand2;
            default:
                return 0; // figure out what to make the bad value would be
        }

    }

    public static int addToStack(ArrayList<Integer> operands, ArrayList<Character> operations,Stack<Integer> operandStack,Stack<Character> operationStack,
                                  int operandCounter, int operationCounter)
    {
        if(operationStack.isEmpty() && operandStack.size()<=1)
        {
            if(operandStack.isEmpty())
            {
                operandStack.push(operands.get(operandCounter));
                operandStack.push(operands.get(operandCounter+1));

                operationStack.push(operations.get(operationCounter));
                /*System.out.println("operand1: " + operands.get(operandCounter));
                System.out.println("operand2: " +operands.get(operandCounter+1));
                System.out.println("operation1: " +operations.get(operationCounter));*/
                return 2;

            }
            else
            {
                operandStack.push(operands.get(operandCounter));

                operationStack.push(operations.get(operationCounter));
                /*System.out.println("operand1: " + operands.get(operandCounter));
                System.out.println("operation1: " +operations.get(operationCounter));*/
                return 1;
            }

        }
        return 0;
    }

    public static int solveExpression(String expression)
    {
        //delete blank spaces in the expression
        expression = expression.replace(" ", "");

        ArrayList<Integer> operands = getOperands(expression);
        ArrayList<Character> operations = getOperations(expression);

       /* for(int i=0;i<operands.size();i++)
        {
            System.out.print("operand"+i+": " +operands.get(i)+" ");
        }
        for(int i=0;i<operations.size();i++)
        {
            System.out.print("operation"+i+": " +operations.get(i)+" ");
        }
        */

        int total = 0;
        int operandCounter = 0;
        int operationCounter = 0;

        Stack<Integer> operandStack = new Stack<Integer>();
        Stack<Character> operationStack = new Stack<Character>();


        //System.out.println("hi");

        while(operationCounter<=operations.size())
        {
            /*
                starting off where operand and operation stacks are empty.
                Add two operands and one operation
             */
            if(operationStack.isEmpty() && operandStack.size()<=1)
            {
                int operandsAdded = addToStack(operands, operations,operandStack,operationStack, operandCounter, operationCounter);
                if(operandsAdded==2)
                {
                    operandCounter+=2;
                    operationCounter++;
                }
                else
                {
                    operandCounter++;
                    operationCounter++;
                }
                //System.out.println("hi hi operation counter: "+operationCounter + " operandcounter : "+operandCounter);
            }
            else
            {
                //if its just one operation then evaluate and return total
                //System.out.println("hi hi hi");
                if(operationCounter==operations.size())
                {
                    int operand2 = operandStack.pop().intValue();
                    int operand1 = operandStack.pop().intValue();
                    char operation = operationStack.pop().charValue();
                    //System.out.println("first: " + operand1 + " second: "+operand2 + " operation: "+operation);
                    total = evaluateExpression(operand1,operand2,operation);
                    //System.out.println("total: " + total);
                    break;
                }
                //System.out.println("hi hi hi hi");
                char operation1 = operationStack.pop().charValue();
                char operation2 = operations.get(operationCounter).charValue();
                int operand3 = operands.get(operandCounter).intValue();

                //System.out.println("first: " + operation1 + " second: "+operation2 + " operand: "+operand3);

                //if first operation is multiplication/division, do that first and add the next one in
                if(operation1=='*' || operation1=='/')
                {


                    int operand2 = operandStack.pop().intValue();
                    int operand1 = operandStack.pop().intValue();


                    //remember to add operand3 and operation 1 back in
                    operationStack.push(operation1);
                    operandStack.push(operand3);

                    operandStack.push(new Integer(evaluateExpression(operand1,operand2,operation1)));
                    operandStack.push(operands.get(operandCounter));
                    operationStack.push(operations.get(operationCounter));

                }
                else
                {

                    if(operation2=='*' || operation2=='/')
                    {
                        //put back first operation and then evaluate operands 2 and 3
                        operationStack.push(operation1);
                        int operand2 = operandStack.pop().intValue();
                        operandStack.push(new Integer(evaluateExpression(operand2,operand3,operation2)));
                    }
                    else
                    {
                        //if second operation is addition or subtraction, pop first two operands and evaluate and then push back operand3 and operation2
                        int operand2 = operandStack.pop().intValue();
                        int operand1 = operandStack.pop().intValue();
                        operandStack.push(new Integer(evaluateExpression(operand1,operand2,operation1)));
                        operandStack.push(operand3);
                        operationStack.push(operation2);
                    }
                }

                operandCounter++;
                operationCounter++;

            }
        }
        return total;
    }

    public static void  main (String[] args)
    {
        System.out.println("Enter an expression to proceed or type quit to exit.");
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext())
        {
            //System.out.println("Enter an expression to proceed or type quit to exit.");
            String input = scan.next();
            if (input.equalsIgnoreCase("quit"))
            {
                System.out.println("Calculator is turning off");
                break;
            }
            else
            {
                boolean isValid = isValidExpression(input);
                if(isValid)
                {
                    int result = solveExpression(input);
                    System.out.println("The answer is: "+ result);

                }
                else
                {
                    System.out.println("That is not a valid expression! \nPlease enter a valid expression or type quit to exit.");
                }
            }
        }
    }
}

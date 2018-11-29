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
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' ;
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
                if(i==0 || i==expression.length()-1)
                {
                    return false;
                }
                char next = expression.charAt(i+1);
                char prev = expression.charAt(i-1);
                operatorCount+=1;
                // operator at start,end or repeats return false
                if(!((Character.isDigit(prev)||prev=='.') && (Character.isDigit(next) || next=='.')) )
                {
                    return false;
                }
            }
            // if anything in the expression is not an operator or a number, its false
            else if(!(Character.isDigit(c) || c != '.'))
            {
                return false;
            }
        }
        return operatorCount != 0 ? true:false;

    }

    /*
        Returns operands in the expression
     */
    public static ArrayList<Double> getOperands(String expression)
    {
        ArrayList<Double> operandArray = new ArrayList<>();
        int firstDigitIndex = 0;
        int lastDigitIndex = 0;
        while(lastDigitIndex<expression.length())
        {
            // find the operation character and obtain substring from first index counter up to position of char
            if(isOperation(expression.charAt(lastDigitIndex)))
            {
                String numberString = expression.substring(firstDigitIndex,lastDigitIndex);
                double number = Double.parseDouble(numberString);
                Double operand = number;
                operandArray.add(operand);
                firstDigitIndex = lastDigitIndex+1;
            }
            // if lastDigitIndex is about to go past length of string, just return end of substring.
            if(lastDigitIndex+1==expression.length())
            {
                String numberString = expression.substring(firstDigitIndex,expression.length());
                double number = Double.parseDouble(numberString);
                Double operand = number;
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
        ArrayList<Character> operationArray = new ArrayList<>();

        for(int index =0;index<expression.length();index++)
        {
            if(isOperation(expression.charAt(index)))
            {
                Character operation = expression.charAt(index);
                operationArray.add(operation);
            }

        }
        return operationArray;
    }
    /*
        Do basic math
     */
    public static double evaluateExpression(Double operand1, Double operand2,char operation)
    {
        switch(operation)
        {
            case '^':
                return Math.pow(operand1,operand2);
            case '+':
                return operand1+operand2;
            case '-':
                return operand1-operand2;
            case '*':
                return operand1*operand2;
            case '/':
                if(operand2==0)
                {
                    return -1.0;
                }
                return operand1/operand2;
            default:
                return 0.0; // figure out what to make the bad value would be
        }

    }

    public static int addToStack(ArrayList<Double> operands, ArrayList<Character> operations,Stack<Double> operandStack,Stack<Character> operationStack,
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

    /*
        Returns the position of last exp in arraylist, returns -1 if doesnt exist
     */
    public static int isThereChainExponential(int operationCounter,ArrayList<Character> operations)
    {
        int pos = operationCounter;
        while(pos<operations.size())
        {
            if(operations.get(pos).charValue()!='^')
            {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    /*
        Returns an array of double of size 2 which are
        1) Total of chain
        2) The new operandCounter value
     */
    public static double calculateChainExponential(int operandCounter, int lastExpIndex, ArrayList<Double> operands)
    {

        int backwardsOperandPointer = lastExpIndex;
        double base = operands.get(backwardsOperandPointer-1);
        double exp = operands.get(backwardsOperandPointer);
        double total = Math.pow(base,exp);
        //System.out.println("outside loop base:"+base+" exp:" + exp + " total:" +total);
        backwardsOperandPointer-=2;
        operandCounter++;
        while(operandCounter<=lastExpIndex)
        {
            exp = total;
            base = operands.get(backwardsOperandPointer);
            total = Math.pow(base,exp);
            backwardsOperandPointer--;
            operandCounter++;
            //System.out.println("base:"+base+" exp:" + exp + " total:" +total);
        }
        return total;
    }

    /*
        Calculates expression with order of operations in order of ^/*+-
     */
    public static double solveExpression(String expression)
    {
        //delete blank spaces in the expression
        expression = expression.replace(" ", "");

        ArrayList<Double> operands = getOperands(expression);
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

        double total = 0.0;
        int operandCounter = 0;
        int operationCounter = 0;

        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();


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
                    double operand2 = operandStack.pop().doubleValue();
                    double operand1 = operandStack.pop().doubleValue();
                    char operation = operationStack.pop().charValue();
                    //System.out.println("first: " + operand1 + " second: "+operand2 + " operation: "+operation);
                    total = evaluateExpression(operand1,operand2,operation);
                    //System.out.println("total: " + total);
                    break;
                }

                //pop next two operations to see which has priority and 3rd operand in case second operation is higher priority
                char operation1 = operationStack.pop().charValue();
                char operation2 = operations.get(operationCounter).charValue();
                double operand3 = operands.get(operandCounter).doubleValue();

                //System.out.println("first: " + operation1 + " second: "+operation2 + " operand: "+operand3);
                // if both operations are exponential then math.pow operand 2 and 3
                if((operation1=='^' || operation2=='^') && isThereChainExponential(operationCounter,operations)>=operationCounter)
                {
                    double operand2 = operandStack.pop().doubleValue();
                    double operand1 = operandStack.pop().doubleValue();
                    int lastExpIndex = isThereChainExponential(operationCounter,operations);
                    if(operation1=='^' )
                    {
                        if (operation2!='^')
                        {
                            //System.out.println(" first is ^ and second is not ^");
                            operandStack.push(Math.pow(operand1,operand2));
                            operandStack.push(operand3);
                            operationStack.push(operation2);
                        }
                        else
                        {
                            //System.out.println("first and more is ^");
                            //System.out.println(calculateChainExponential(operandCounter,lastExpIndex,operands));
                            operandStack.push(calculateChainExponential(operandCounter,lastExpIndex,operands));

                            operandCounter +=  lastExpIndex-operationCounter-1;
                            operationCounter += lastExpIndex-operationCounter-1;
                        }


                    }
                    // this means operation 1 is not part of the exp chain
                    else if(operation2=='^')
                    {
                        //put back first operand and operation since they are of lower priority
                        operationStack.push(operation1);
                        operandStack.push(operand1);
                        if(lastExpIndex > operandCounter)
                        {
                            //System.out.println("second and more ^");
                            //System.out.println(calculateChainExponential(operandCounter,lastExpIndex,operands));
                            operandStack.push(calculateChainExponential(operandCounter,lastExpIndex,operands));

                            operandCounter +=  lastExpIndex-operationCounter-1;
                            operationCounter += lastExpIndex-operationCounter-1;
                        }
                        else
                        {
                            //System.out.println("only second ^");
                            operandStack.push(Math.pow(operand2,operand3));
                        }
                    }

                }
                //if first operation is multiplication/division/exp, do that first and add the next one in
                else if(operation1=='*' || operation1=='/')
                {
                    double operand2 = operandStack.pop().doubleValue();
                    double operand1 = operandStack.pop().doubleValue();
                    //System.out.println("first: " + operand1 + " second: "+operand2 + " operation: "+operation1);
                    //remember to add operand3 and operation 1 back in
                    operationStack.push(operation1);
                    operandStack.push(operand3);

                    //System.out.println(evaluateExpression(operand1,operand2,operation1));
                    operandStack.push(evaluateExpression(operand1,operand2,operation1));
                    operandStack.push(operands.get(operandCounter));
                    operationStack.push(operations.get(operationCounter));

                }
                else
                {

                    if(operation2=='*' || operation2=='/')
                    {
                        //put back first operation and then evaluate operands 2 and 3
                        operationStack.push(operation1);
                        double operand2 = operandStack.pop().doubleValue();
                        operandStack.push(evaluateExpression(operand2,operand3,operation2));
                        //System.out.println(evaluateExpression(operand2,operand3,operation2));
                    }
                    else
                    {
                        //if second operation is addition or subtraction, pop first two operands and evaluate and then push back operand3 and operation2
                        double operand2 = operandStack.pop().doubleValue();
                        double operand1 = operandStack.pop().doubleValue();
                        //System.out.println("first: " + operand1 + " second: "+operand2 + " operation: "+operation1);
                        //System.out.println(evaluateExpression(operand1,operand2,operation1));
                        operandStack.push(evaluateExpression(operand1,operand2,operation1));
                        operandStack.push(operand3);
                        operationStack.push(operation2);
                    }
                }

                operandCounter++;
                operationCounter++;
                //System.out.println(operandCounter +" " + operationCounter);
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
                    double result = solveExpression(input);
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

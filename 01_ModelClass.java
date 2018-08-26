/*
 * Attach UI to 
 * https://github.com/sylabtechnologies/June_test/blob/master/Codility2/03_polishcalc.cpp
 */

package xcalc;

import java.util.*;

public class ModelClass {
    
    private static final int MY_MAX_INT  = 1048575;
    private static final int MY_ERR_CODE = -1;
    private int myResult;
    private boolean entryMode; 
    private LinkedList<Integer> myStack;
    
    public String myError;
    
    public ModelClass()
    {
        myStack = new LinkedList<Integer>();
        entryMode = false;
    }

    public int getResult()
    {
        return myResult;
    }

    public int execOper(String elem)
    {
        char ch = elem.charAt(0);
        
        // digit display mode
        if ( ch >= '0' && ch <= '9')
        {
            int digit = ch - '0';
            
            if (!entryMode)
            {
                entryMode = true;
                myResult = digit;
            }
            else
            {
                myResult *= 10;
                
                if (myResult > 0)
                    myResult += digit;
                else
                    myResult -= digit;
            }
            
            if (!withinRange(myResult))
            {
                myResult = 0;
                return setError("overflow");
            }
            
            return 0;
        }

        if (elem.equals("+/-"))
        {
            if (entryMode)
            {
                myResult = - myResult;
            }
            
            return 0;
        }

        // if enter just register
        if (elem.equals("Enter"))
        {
            if (entryMode)
            {
                if (!withinRange(myResult)) return setError("overflow");

                myStack.add(myResult);
                entryMode = false;
            }
            return 0;
        }

        // otherwise emulate Enter & process command
        if (entryMode)
            if (execOper("Enter") != 0) return MY_ERR_CODE;
        
        int oper1, oper2;
        switch(elem)
        {

            case "+":
                if (myStack.size() < 2)
                    return setError("stack underflow");

                oper1 = myStack.getLast(); myStack.removeLast();
                oper2 = myStack.getLast(); myStack.removeLast();
                myResult = oper1 + oper2;

                if (!withinRange(myResult)) return setError("overflow");

                myStack.add(myResult);
                return 0;
                
            case "-":
                if (myStack.size() < 2) return setError("stack underflow");

                oper1 = myStack.getLast(); myStack.removeLast();
                oper2 = myStack.getLast(); myStack.removeLast();
                myResult = oper1 - oper2;

                if (!withinRange(myResult)) return setError("overflow");

                myStack.add(myResult);
                return 0;
            
            case "x":
                if (myStack.size() < 2)
                    return setError("stack underflow");

                oper1 = myStack.getLast(); myStack.removeLast();
                oper2 = myStack.getLast(); myStack.removeLast();
                myResult = oper1 * oper2;

                if (!withinRange(myResult)) return setError("overflow");

                myStack.add(myResult);
                return 0;
            
            case "\u00F7":
                if (myStack.size() < 2)
                    return setError("stack underflow");

                oper1 = myStack.getLast(); myStack.removeLast();
                oper2 = myStack.getLast(); myStack.removeLast();
                myResult = oper1/oper2;

                // test not valid?
                if (!withinRange(myResult)) return setError("overflow");

                myStack.add(myResult);
                return 0;
            
            case "DUP":
                if (myStack.size() < 1) return setError("stack underflow");
                myResult = myStack.getLast();

                myStack.add(myResult);
                return 0;
            
            case "POP":
                if (myStack.size() < 1) return setError("stack underflow");

                myStack.removeLast();

                if (myStack.size() != 0)
                    myResult = myStack.getLast();
                else
                    myResult = 0;
                
                return 0;
            
            default:
                return setError("invalid command " + elem);
        }
    }

    private boolean withinRange(int test)
    {
        if (test < - MY_MAX_INT) return false;
            
        if (test > MY_MAX_INT) return false;
        
        return true;
    }
    
    private int setError(String err)
    {
        myError = err;
        return MY_ERR_CODE;
    }
    
}

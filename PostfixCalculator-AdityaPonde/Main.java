import java.util.*;
import java.lang.*;
import java.io.*;

class Main 
{
  public static void main(String[] args) 
  {
    //users calculation 
    String userString = "";
    Scanner scan = new Scanner(System.in);
    //while loop with end case of 0 
    while (!userString.equals("0"))
    {
      System.out.println("\nEnter the the calculations(enter '0' to stop): ");
      userString = scan.nextLine();
      postFix po = new postFix(userString);
      //break the loop if user enters 0
      if (userString.equals("0"))
      {
        System.out.println("\nThank You for using the calculator. Bye!!!");
        break;
      }
      po.calculator();
    }

  }
}
//postfix calculator class
class postFix
{
  private String userInput;
//constructor
  public postFix (String p)
  {
    userInput = p;
  }
//calculator method
  public void calculator ()
  {
    //stack to store every number that the user enters
    Stack <Integer> numberStack = new Stack<Integer>();
    //bottom of the stack
    int firstNum;
    //top of the stack
    int secondNum;
//for loop to check of numbers and if the user enters an operator to solve it
    for (int i = 0; i < userInput.length(); i++)
    {
      char ch = userInput.charAt(i);
      String st = String.valueOf(ch);
      
//weed out the numbers
      if (ch != '+' && ch != '-' && ch != '/' && ch != '*')
      {
        //check for special characters other than operators
        if (!Character.isDigit(ch))
        {
        System.out.println("\nERROR: Invalid character" );
        return;
        }
        //if previous test cases are passed the number is pushed into the stack
        numberStack.push(Integer.valueOf(st));
        

      }
// if theere is an operator it gets solved here
      else if(ch == '+' || ch == '-' || ch == '/' || ch == '*')
      {
        //if there are less than 2 numbers in the stack at this point, there are too few operands
        //method exits
        if (numberStack.size() < 2)
        {
          System.out.println("\nERROR: Too few operands" );
          return;
        }
        //if there are 2 numbers they are popped and operated on
        secondNum = numberStack.pop();
        firstNum = numberStack.pop();
        //result of the operation between firstNum and secondNum
        int res;
        
//if operation is + adds botom and top of stack
        if (st.equals("+"))
        {
          res = secondNum + firstNum;
          numberStack.push(res);
        }
//if operation is - subtracts botom and top of stack
        else if (st.equals("-"))
        {
          res = firstNum - secondNum;
          numberStack.push(res);
//if operation is * multiplies botom and top of stack
        }
        else if (st.equals("*"))
        {
          res = firstNum * secondNum;
          numberStack.push(res);
        }
//if operation is / divides botom and top of stack
        else if (st.equals("/"))
        {   
          //exits if divide by 0
          if (secondNum == 0)
          {
            System.out.println("\nERROR: Divide by 0");
            return;
          }     
          res = firstNum / secondNum;
          numberStack.push(res);

        }
      }
      else 
      {
        
        break;
      }
     

    }
//at this point if theres more than 1 number in the stack there were too many operands 
    if (numberStack.size() > 1)
    {
      System.out.println("\nERROR: Too many operands");
    }
//if everythin checks out code returns the calculations and the user can enter new calclations
    else
    {
      System.out.println("\nThis is the result: " + numberStack );
    }
    
  }
}
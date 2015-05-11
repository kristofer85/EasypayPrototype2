package com.example.notandi.easypayprototype;

import java.util.Stack;


public class Calc {

    public static void main(String[] args) {
        String myString = "2+1*3+2/2";
        int i = calculate(myString);
        System.out.println(i);
    }

    public static Integer calculate(String s) {
        char[] charArray = s.toCharArray();
        String StringToBePushed = "";
        Stack<Integer> values = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();
        Boolean is_it_true = false;
        int finalResult = 0;

        for (char ch : charArray) {
            if(Character.isDigit(ch)) {
                StringToBePushed += ch;
            }
            else if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // pusha t?lunni ? value stack. Og n?llstilli string
                int item = Integer.parseInt(StringToBePushed);
                values.push(item);
                StringToBePushed = "";

                //ef a? seinasti operator sem var pusha? var * e?a / ?? ?arf a? reikna ?t strax og pusha utkomu aftur ? value stack
                if(is_it_true) {
                    is_it_true = false;
                    int pop1 = values.pop();
                    int pop2 = values.pop();
                    char pop3 = operators.pop();
                    values.push(DoCalculations(pop2, pop1, pop3));
                }
                if(ch == '*' || ch == '/') {
                    is_it_true = true;
                }
                //pusha operator ? stack
                operators.push(ch);
            }
        }
        //B?ta vi? StringToBePushed ef hann er ekki
        if(StringToBePushed != "") {
            int item = Integer.parseInt(StringToBePushed);
            values.push(item);
        }
        while(!operators.empty())  {
            int pop1 = values.pop();
            int pop2 = values.pop();
            char pop3 = operators.pop();
            values.push(DoCalculations(pop2, pop1, pop3));
        }
        return values.pop();
    }

    public static int DoCalculations (int num1, int num2, char ch) {
        int returnvalue = 0;
        if(ch == '+') {
            returnvalue = num1 + num2;
        }
        else if(ch == '-') {
            returnvalue = num1 - num2;
        }
        else if(ch == '*') {
            returnvalue = num1 * num2;
        }
        else if(ch == '/') {
            if(num2 == 0) {
                return 0;
            }
            returnvalue = num1 / num2;
        }
        return returnvalue;
    }
}

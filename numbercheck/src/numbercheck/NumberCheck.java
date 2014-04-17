/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numbercheck;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ruman
 */
public class NumberCheck
{

    static Random RANDOM = new Random();
    static boolean[] primeNumbers = null; //contains if index is a prime number

    /**
     * Generates prime number array
     */
    static void generatePrim()
    {
        //Using Sieve of Eratosthenes
        primeNumbers = new boolean[501]; //0 to 500

        primeNumbers[0] = primeNumbers[1] = false;

        //init
        for (int i = 2; i <= 500; i++)
        {
            primeNumbers[i] = true;
        }

        for (int i = 2; i <= (int) (Math.sqrt(500)); i++)
        {
            for (int n = i * i; n <= 500; n += i)
            {
                primeNumbers[n] = false;
            }
        }

        /*for(int i = 0; i < 501;i++)
         {
         if(!primeNumbers[i])
         {
         System.out.println(i + " ist prim.");
         }
         }*/
    }

    /**
     * Check if number is prime number
     *
     * @param num
     */
    static boolean isPrime(int num)
    {
        if (num <= 1)
        {
            return false;
        }

        if (primeNumbers == null) //Generate numbers
        {
            generatePrim();
        }

        return primeNumbers[num];
    }

    /**
     * check if number is sum of dividers
     *
     * @param num
     * @return
     */
    static boolean isPerfect(int num)
    {
        int sum_of_dividers = 0;

        for (int i = 1; i < num; i++)
        {
            if (num % i == 0)
            {
                sum_of_dividers += i;
            }
        }

        return sum_of_dividers == num;
    }

    /**
     * Inverts the number 132 will be turned into 231
     *
     * @param num
     * @return
     */
    static int reverseNumber(int num)
    {
        String numberstring = String.valueOf(num); //I'll use dirty string parse method
        int output = 0;

        while (numberstring.length() != 0)
        {
            int digit = Integer.parseInt("" + numberstring.charAt(numberstring.length() - 1));
            output += digit * (int) Math.pow(10, numberstring.length() - 1); //digit * 10^(length - 1)

            numberstring = numberstring.substring(0, numberstring.length() - 1); //Evil
        }

        return output;
    }

    /**
     * If number is a palindrome
     *
     * @param num
     * @return
     */
    static boolean isPalindrome(int num)
    {
    	if(num == 0) //0 ist Sonderfall
    		return true;
    	if((num % 10) == 0) //Eine Zahl mit einer Null am Ende KANN kein Palindrom sein Bew: 550 != 055
    		return false;
    	
    	int reverse = reverseNumber(num);
    	
        return num == reverse;
    }

    /**
     * Check if Lychrel number
     *
     * @param num
     * @return
     */
    static boolean isLychrel(int num)
    {
        int _current = num;

        do
        {
            _current = _current + reverseNumber(_current);
            
            //System.out.println(_current);
            
            if(_current < 0)
            {
            	return true; //owerflow
            }

            if (isPalindrome(_current))
            {
                return false;
            }
        }   
        while (_current > 0);

        return true; //owerflow
    }

    /**
     * Check if number is happy number
     *
     * @param num
     * @return
     */
    static boolean isHappy(int num)
    {
        String numberstring = String.valueOf(num);
        ArrayList<Integer> alreadyused = new ArrayList<Integer>();
        
        //int iterations = 0;
        
        int sum = -1;
        int oldsum = -1;

        do
        {
            oldsum = sum;
            sum = 0;

            for (int i = 0; i < numberstring.length(); i++)
            {
                int digit = Integer.parseInt("" + numberstring.charAt(i));
                sum += digit * digit;
            }
            
            numberstring = String.valueOf(sum);
            
           //iterations++;
            
            if(!alreadyused.contains(sum))
                alreadyused.add(sum);
            else
                break;
        }
        while (oldsum != sum /*&& iterations < 1000000*/); //max iterations otherwise it will be infinite

        return sum == 1;
    }

    /**
     * Checks number for random property
     *
     * @param num
     */
    static void checkNumber(int num)
    {
        switch (RANDOM.nextInt(4))
        {
            case 0:
                if (isPrime(num))
                {
                    System.out.println(num + " ist eine Primzahl.");
                }
                else
                {
                    System.out.println(num + " ist KEINE Primzahl.");
                }
                break;
            case 1:
                if (isPerfect(num))
                {
                    System.out.println(num + " ist eine perfekte Zahl.");
                }
                else
                {
                    System.out.println(num + " ist KEINE perfekte Zahl.");
                }
                break;
            case 2:
                if (isLychrel(num))
                {
                    System.out.println(num + " ist eventell eine Lychrel-Zahl.");
                }
                else
                {
                    System.out.println(num + " ist KEINE Lychrel-Zahl.");
                }
                break;
            case 3:
                if (isHappy(num))
                {
                    System.out.println(num + " ist eventell eine fröhliche Zahl :)");
                }
                else
                {
                    System.out.println(num + " ist eine traurige Zahl :(");
                }
                break;
        }
    }

    /**
     *
     * @return 20 integers (random) in array 1-500
     */
    static int[] generateNumbers()
    {
        int[] numbers = new int[20];

        for (int i = 0; i < numbers.length; i++)
        {
            numbers[i] = 1 + RANDOM.nextInt(500);
        }

        return numbers;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        for (int i : generateNumbers())
        {
            checkNumber(i);
        }
        
        System.out.println(isLychrel(196)); 
    }

}

/*
Purpose: Find the median number from two sorted arrays
Author: Robert Gagnon
Date: 11/1/2019
Known_Issues: if the user enters non-numeric chacters the code will fail
*/

import java.util.Scanner;
import java.util.Arrays;

public class FindMeanNumber {

    private static Float[] TurnStringToFloatArray(String myNumString) {
        int i;
        String[] numStringArray = myNumString.split(" ");;
        Float[] numFloatArray = new Float[numStringArray.length];

        for (i=0; i< (numStringArray.length);i++){
            numFloatArray[i] = Float.parseFloat(numStringArray[i]);
        }
        Arrays.sort(numFloatArray);
        return numFloatArray;
    }

    private static Float FindSum(Float[] myNumList){
        Float mySum = 0.0F;
        int i; 
        for (i=0; i < myNumList.length; i++){
            mySum = mySum + myNumList[i];
        }
        return mySum;
    }

    private static Float FindMean(Float totalSum, int totalItemCount){
        Float myMean = 0.0F;
        myMean = totalSum / totalItemCount;
        return myMean;
    }

    private static Float FindMidean(Float[] firstArray, Float[] secondArray){
        Float myMedian;
        Float[] joinedArray = new Float[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, joinedArray, 0, firstArray.length);  
        System.arraycopy(secondArray, 0, joinedArray, firstArray.length, secondArray.length);
        Arrays.sort(joinedArray);

        if (joinedArray.length % 2 == 0){ 
            //If there is an even number of array elements, we need to find the average of the two
            //numbers around the center point of the array
            myMedian = (joinedArray[joinedArray.length/2-1]+joinedArray[joinedArray.length/2])/2;
        } else {
            myMedian = joinedArray[joinedArray.length/2];
        }
        System.out.println("The joined sorted array: "+ Arrays.toString(joinedArray));
        return myMedian;
    }

    private static String GetSomeNumbersFromUser(){
        Scanner scan = new Scanner(System.in);  
        String userString = "";

        while (userString.equals("")){
            System.out.print("Enter a list of numbers: ");
            userString = scan.nextLine();
            // this makes things look nicer if user just hits return
            System.out.print("\n");
        }
        return userString;
    }
    public static void main(String[] args) {
        String myNumList1 = ""; 
        String myNumList2 = "";     
     
        myNumList1 = GetSomeNumbersFromUser();
        myNumList2 = GetSomeNumbersFromUser();

        // Since user input is character strings, we need to translate 
        // to arrays of floats
        Float[] numArray1 = TurnStringToFloatArray(myNumList1);
        System.out.println("The first sorted array: "+ Arrays.toString(numArray1));

        Float[] numArray2 = TurnStringToFloatArray(myNumList2);
        System.out.println("The second sorted array: "+ Arrays.toString(numArray2));

        // Just checking my work along the way
        int myItemCount = numArray1.length + numArray2.length;
        System.out.println("The combined array lengths are: "+ myItemCount);
        
        Float totalSum = FindSum(numArray1) + FindSum(numArray2);
        System.out.println("The total sum is: "+ totalSum);

        // Checking the average
        Float myMean = FindMean(totalSum, myItemCount);
        System.out.println("The Arithmatic Mean is: "+ myMean);

        // Checking the median
        Float myMedian = FindMidean(numArray1, numArray2);
        System.out.println("The Median is: "+ myMedian);
    }
}
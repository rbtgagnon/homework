/*
Purpose: Find the median number from two sorted arrays
Author: Robert Gagnon
Date: 11/1/2019
Known_Issues: if the user enters non-numeric chacters the code will fail
*/

import java.util.Scanner;
import java.util.Arrays;

public class FindMeanNumber {

    private static Float FindMidean (Float[] myArray1, Float[] myArray2){
        /* Only three potential solutions 
        (a) everyting in array1 is smaller than the first item in array2
         a[1,2,3] b[4,5,6]

        (b) evertying in Array2 is smaller than the first item in array1
          a[4,5,6] b[1,2,3]

        (c) there is a mix of size values between the two arrays
        a[2,5,9] b[3,4,10]
        */

        int maxMedianElementIndex  = (myArray1.length + myArray2.length)/2;
        Float myMedian = -1.0F; // initiallize it to my error state

        //if the arrays are not the same size, we could overrun the index, so I'll switch to the cheat
        //when the arrays are not the same size

        if (myArray1.length != myArray2.length){
            myMedian = FindMideanCheat(myArray1, myArray2);
            System.out.println("FYI: I had to cheat beause the arrays were of differnt sizes");
            return myMedian;
        }

        // (a) Check if everything in Array1 is larger
        if (myArray1[myArray1.length-1] > myArray2[0]){
            myMedian = ((myArray1[myArray1.length-1] + myArray2[0])/2.0F);
            return myMedian;
        }

        // (b) Check if everything in Array1 is larger
        if (myArray2[myArray2.length-1] > myArray1[0]){
            myMedian = ((myArray2[myArray2.length-1] + myArray1[0])/2.0F);
            return myMedian;
        }

        // (c) Need to search for the median
        int i = 0; //iterator for myArray1
        int j = 0; //iterator for myArray2
        int count = 0;//count of items in my search for a median
        float medianCandidateArray1 = myArray1[0];
        float medianCandidateArray2 = myArray2[0];

        while (count < maxMedianElementIndex){
            if (medianCandidateArray1 <= medianCandidateArray2){
                i++;
                medianCandidateArray1 = myArray1[i];
            } else if(medianCandidateArray2 < medianCandidateArray1){ 
                j++;
                medianCandidateArray2 = myArray2[j];
            }
            count++; //each time we found the smaller number, we increase the count toward the median
        }
        myMedian = (medianCandidateArray1 + medianCandidateArray2)/2;
        return myMedian;
    }

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

    private static Float FindMideanCheat(Float[] firstArray, Float[] secondArray){
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
        System.out.println("The Median the smarter way: "+ myMedian);
    }
}
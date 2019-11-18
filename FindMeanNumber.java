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
        /* Only four potential solutions 
        (a) everything in array1 is smaller than the first item in array2
         a[1,2,3] b[4,5,6]

        (b) everything in array2 is smaller than the first item in array1
          a[4,5,6] b[1,2,3]

        (c) the max of array1 = the min of array2 then the median is that overlapping number
        a[1,2,3] b[3,4,5]
        I'm going to lump this in with example (a) when we evaluate 

        (d) there is a mix of size values between the two arrays
        a[2,5,9] b[3,4,10]
        */

        // We only need to search 1/2 of the elements to find a midean
        int maxMedianElementIndex  = (myArray1.length + myArray2.length)/2;
        
        Float myMedian; 

        // If the arrays are not the same size, we could overrun the index,
        // so I'll switch to the cheat method
        if (myArray1.length != myArray2.length){
            myMedian = FindMideanCheat(myArray1, myArray2);
            System.out.println("FYI: I had to cheat beause the arrays were of differnt sizes");
            return myMedian;
        }

        // (a) Check if everything in array1 is larger or the same as smallest value in array2
        if (myArray1[myArray1.length-1] <= myArray2[0]){
            myMedian = ((myArray1[myArray1.length-1] + myArray2[0])/2.0F);
            return myMedian;
        }

        // (b) Check if everything in Array1 is larger
        if (myArray2[0] > myArray1[myArray1.length-1]){
            myMedian = ((myArray2[0] + myArray1[myArray1.length-1])/2.0F);
            return myMedian;
        }

        // (c) was combined with check (a)

        // (d) Need to search for the median and find the two values 
        // that surround the median and gets its average
        // Search each array simultaneously until we reach a count that indicates we've 
        // gotten through 1/2 of the total items

        int i = 0; //iterator for myArray1
        int j = 0; //iterator for myArray2
        int count = 0; //count of items in my search for a median
        float medianCandidateArray1 = myArray1[0];
        float medianCandidateArray2 = myArray2[0];

        while (count < maxMedianElementIndex -1){
            if (medianCandidateArray1 <= medianCandidateArray2){
                i++;
                medianCandidateArray1 = myArray1[i];
            } else if(medianCandidateArray2 < medianCandidateArray1){ 
                j++;
                medianCandidateArray2 = myArray2[j];
            }
            count++; //each time we found the smaller number, we increase the count toward the median
        }
        // because in this path the combined array lengths always result in an evan number
        // the median is always the average of the two numbers around the 
        // center of the combined array
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

    private static Float[] GetSomeNumbersFromUser(){
        Scanner scan = new Scanner(System.in);  
        String userString = "";

        while (userString.equals("")){
            System.out.print("Enter a list of numbers: ");
            userString = scan.nextLine();
            // this makes things look nicer if user just hits return
            System.out.print("\n");
        }
        Float[] numArray = TurnStringToFloatArray(userString);
        return numArray;
    }
    public static void main(String[] args) {
        Float[] numArray1; 
        Float [] numArray2;     
     
        numArray1 = GetSomeNumbersFromUser();
        numArray2 = GetSomeNumbersFromUser();

        System.out.println("The first array is: "+ Arrays.toString(numArray1));
        System.out.println("The second array is: "+ Arrays.toString(numArray2));

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
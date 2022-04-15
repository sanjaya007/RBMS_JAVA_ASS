package com.assignment;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final String DEV_TXT = "Sakar Regmi";
    public static final float GUITAR_PRICE = 199.00F;
    public static final float LESSON_PRICE = 29.95F;
    public static final int MAX_Count = 3;

    public static void main(String[] args) {
        byte counter = 1;

        Scanner scanner = new Scanner(System.in);

        String[] bookingArr = {"", "", "", "", ""};
        String[] booking1 = {"", "", "", "", ""};
        String[] booking2 = {"", "", "", "", ""};
        String[] booking3 = {"", "", "", "", ""};
        int[] lessonArr = {0, 0, 0};


//      heading
        System.out.println("");
        System.out.println("Welcome to the Rocky Blues Management System");
        System.out.println("");

        while (counter <= MAX_Count){
            while (true){
                System.out.print("Please enter booking name " + counter + " ==> ");
                String bookingName = scanner.nextLine().trim();
                if (isBlank(bookingName)) {
                    System.out.println("ERROR: Booking name cannot be blank.");
                }else if (isNumeric(bookingName)){
                    System.out.println("Error: Only alphabets are allowed.");
                }else {
                    bookingArr[0] = bookingName;
                    break;
                }
            }

            while (true) {
                System.out.print("Enter the number of lessons for " + bookingArr[0] + " ==> ");
                String lessonCount = scanner.nextLine().trim();

                if (isBlank(lessonCount)) {
                    System.out.println("ERROR:  You forget to add number of lessons.");
                }else if (!isNumeric(lessonCount)){
                    System.out.println("Error: Only valid numeric positive digits are allowed.");
                }else if(Integer.parseInt(lessonCount) < 1) {
                    System.out.println("Error: Number of lessons must be greater than or equal to 1.");
                }else {
                    String totalLessonPrice = "";
                    bookingArr[1] = lessonCount;
                    if (Integer.parseInt(lessonCount) > 10){
                        float result = twentyPercentDiscount(Float.parseFloat(lessonCount));
                        String finalResult = String.format("%.2f", result);
                        totalLessonPrice = finalResult;
                    } else if(Integer.parseInt(lessonCount) > 5 && Integer.parseInt(lessonCount) <= 10){
                        float result = tenPercentDiscount(Float.parseFloat(lessonCount));
                        String finalResult = String.format("%.2f", result);
                        totalLessonPrice = finalResult;
                    }else{
                        float result = noDiscount(Float.parseFloat(lessonCount));
                        String finalResult = String.format("%.2f", result);
                        totalLessonPrice = finalResult;
                    }
                    bookingArr[2] = totalLessonPrice;
                    break;
                }
            }

            while (true) {
                System.out.print(bookingArr[0] + " do you want to purchase a guitar for $199.00 (y/n) ==> ");
                String wantGuitar = scanner.nextLine().trim().toLowerCase();

                if (!isBool(wantGuitar)){
                    System.out.println("ERROR: Enter 'y' or 'n' or 'yes' or 'no'.");
                }else{
                    String guitarPrice = "";
                    if (wantGuitar.equals("y") || wantGuitar.equals("yes")){
                        String finalResult = String.format("%.2f", GUITAR_PRICE);
                        guitarPrice = finalResult;
                    }else{
                        guitarPrice = "0";
                    }
                    bookingArr[3] = guitarPrice;
                    break;
                }
            }

    //      total price
            float totalPrice = Float.parseFloat(bookingArr[2]) + Float.parseFloat(bookingArr[3]);
            String finalTotalPrice = String.format("%.2f", totalPrice);
            bookingArr[4] = finalTotalPrice;
            if (bookingArr[3].equals("0")){
                System.out.println("The charge for " + bookingArr[0] + " for " + bookingArr[1] + " lessons is $" + bookingArr[4]);
            }else{
                System.out.println("The charge for " + bookingArr[0] + " for " + bookingArr[1] + " lessons and a guitar purchase is $" + bookingArr[4]);
            }

//          cloning array
            if (counter == 1){
                booking1 = bookingArr.clone();
            }else if (counter == 2){
                booking2 = bookingArr.clone();
            }else{
                booking3 = bookingArr.clone();
            }

//          empty array
            for (int i = 0; i < bookingArr.length; i++) {
                bookingArr[i] = "";
            }

            System.out.println("");
            counter++;
        }

//      statistical info
        System.out.println("");

        System.out.println("Statistical information for Rocky Blues");
        System.out.println("");

//      final calculation
        lessonArr[0] = Integer.parseInt(booking1[1]);
        lessonArr[1] = Integer.parseInt(booking2[1]);
        lessonArr[2] = Integer.parseInt(booking3[1]);

        Arrays.sort(lessonArr);
        int maxLesson = lessonArr[MAX_Count - 1];
        int minLesson = lessonArr[0];

        if (lessonArr[0] == lessonArr[1] && lessonArr[0] == lessonArr[2]){
            System.out.println("All have same number of " + lessonArr[0] + " lessons");
        }else{
            String maxLessonStr = String.valueOf(maxLesson);
            String minLessonStr = String.valueOf(minLesson);
            String maxLessonHolder = "";
            String minLessonHolder = "";
            boolean isPluralMaxHolders = false;
            boolean isPluralMinHolders = false;

//          checking max lesson holder
            if (Arrays.stream(booking1).anyMatch(maxLessonStr::equals)){
                if (maxLessonHolder.equals("")){
                    maxLessonHolder = booking1[0];
                }else{
                    maxLessonHolder += " and " + booking1[0];
                    isPluralMaxHolders = true;
                }
            }

            if (Arrays.stream(booking2).anyMatch(maxLessonStr::equals)){
                if (maxLessonHolder.equals("")){
                    maxLessonHolder = booking2[0];
                }else{
                    maxLessonHolder += " and " + booking2[0];
                    isPluralMaxHolders = true;
                }
            }

            if (Arrays.stream(booking3).anyMatch(maxLessonStr::equals)){
                if (maxLessonHolder.equals("")){
                    maxLessonHolder = booking3[0];
                }else{
                    maxLessonHolder += " and " + booking3[0];
                    isPluralMaxHolders = true;
                }
            }

//          checking min lesson holder
            if (Arrays.stream(booking1).anyMatch(minLessonStr::equals)){
                if (minLessonHolder.equals("")){
                    minLessonHolder = booking1[0];
                }else{
                    minLessonHolder += " and " + booking1[0];
                    isPluralMinHolders = true;
                }
            }

            if (Arrays.stream(booking2).anyMatch(minLessonStr::equals)){
                if (minLessonHolder.equals("")){
                    minLessonHolder = booking2[0];
                }else{
                    minLessonHolder += " and " + booking2[0];
                    isPluralMinHolders = true;
                }
            }

            if (Arrays.stream(booking3).anyMatch(minLessonStr::equals)){
                if (minLessonHolder.equals("")){
                    minLessonHolder = booking3[0];
                }else{
                    minLessonHolder += " and " + booking3[0];
                    isPluralMinHolders = true;
                }
            }

            System.out.println(minLessonHolder + grammarInit(isPluralMinHolders) + " the minimum number of " + minLessonStr + " lessons.");
            System.out.println(maxLessonHolder + grammarInit(isPluralMaxHolders) + " the maximum number of " + maxLessonStr + " lessons.");
        }

        float totalCharges = Float.parseFloat(booking1[4]) + Float.parseFloat(booking2[4]) + Float.parseFloat(booking3[4]);
        String totalChargesFormatted = String.format("%.2f", totalCharges);

        float averageValue = calculateAverage(Float.parseFloat(booking1[1]), Float.parseFloat(booking2[1]), Float.parseFloat(booking3[1]));
        String averageFormatted = String.format("%.2f", averageValue);

        System.out.println("The average number of lessons per booking is: " + averageFormatted + " lessons.");
        System.out.println("The total charges collected is $" + totalChargesFormatted);

//      thank you text
        System.out.println("");
        System.out.println("");
        System.out.println("Thank you for using the Rocky Blues Management System.");
        System.out.println("Program written by " + DEV_TXT);
        System.out.println("");
        System.out.println("");
    }

    public static boolean isBlank(String value){
        if (value.equals("")){
            return true;
        }
        return false;
    }

    public static boolean isAlphabet(String value) {
        String strRegex = "[a-zA-Z]+";
        if(value.matches(strRegex)){
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String value) {
        String intRegex = "[0-9]+";
        if(value.matches(intRegex)){
            return true;
        }
        return false;
    }

    public static float twentyPercentDiscount(Float value){
        float totalLessonPrice = value * LESSON_PRICE;
        float discountValue = (20F / 100F) * totalLessonPrice;
        float result = totalLessonPrice - discountValue;
        return result;
    }

    public static float tenPercentDiscount(Float value){
        float totalLessonPrice = value * LESSON_PRICE;
        float discountValue = (10F / 100F) * totalLessonPrice;
        float result = totalLessonPrice - discountValue;
        return result;
    }

    public static float noDiscount(Float value){
        float totalLessonPrice = value * LESSON_PRICE;
        float discountValue = 0F;
        float result = totalLessonPrice - discountValue;
        return result;
    }

    public static boolean isBool(String value){
        if (value.equals("y") || value.equals("n") || value.equals("yes") || value.equals("no")){
            return true;
        }
        return false;
    }

    public static float calculateAverage(Float val1, Float val2, Float val3 ){
        float result = (val1 + val2 + val3) / MAX_Count;
        return  result;
    }

    public static String grammarInit(boolean value){
        if (value){
            return " have";
        }else{
            return " has";
        }
    }
}

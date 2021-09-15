import java.util.*;

public class Main {
    static String message = "";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = 0, symbols = 0;

        System.out.println("Input the length of the secret code:");
        String lengthInput = sc.nextLine();
        try {
            length = Integer.parseInt(lengthInput);
        } catch (Exception e) {
            System.out.println("error");
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        String symbolInput = sc.nextLine();
        try {
            symbols = Integer.parseInt(symbolInput);
        } catch (Exception e) {
            // System.out.printf("\"%s\" isn't a valid number.", symbolInput);
            System.out.println("error");
            System.exit(0);
        }
        if(length <= 0 || symbols <= 0) {
            System.out.println("error");
            System.exit(0);
        }
        if(symbols > 36 || length > 36) {
            // System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.out.println("error");
            System.exit(0);
        }
        if(symbols < length) {
            // System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", length, symbols);
            System.out.println("error");
            System.exit(0);
        }
        String code = randomGenerator(symbols, length);
        String guess = "";
        System.out.println(message);
        System.out.println("Okay, let's start a game!");
        int count = 1;
        while(!guess.equalsIgnoreCase(code)) {
            System.out.println("Turn " + count + ":");
            guess = sc.next();
            int bull = bull(code, guess);
            int cow = cow(code, guess);
            bullsAndCows(bull, cow, code, length);
            count++;
            System.out.println();
        }
    }


    public static int bull(String code, String guess) {
        int bull = 0;
        for(int i = 0; i < code.length(); i++) {
            if(code.charAt(i) == guess.charAt(i)){
                bull++;
            }
        }
        return bull;
    }
    public static int cow(String code, String guess){
        int cow = 0;
        for(int i = 0; i < guess.length(); i++){
            if(code.indexOf(guess.charAt(i)) >= 0)
                cow++;
        }
        return cow;
    }

    public static void bullsAndCows(int bulls, int cows, String code, int length){
        String bullString = bulls > 1 ? "bulls" : "bull";
        String cowsString = cows > 1 ? "cows" : "cow";
        System.out.print("Grade: ");
        if(bulls == length) {
            System.out.print(bulls + " " + bullString + ".\n");
            System.out.println("Congrats! The secret code is " + code + ".");
        }else if(bulls > 0 && cows > 0) {
            System.out.print(bulls + " " + bullString + " and " + cows + " " + cowsString + ".");
        }else if(bulls > 0) {
            System.out.print(bulls + " " + bullString + ".");
        }else if(cows > 0) {
            System.out.print(cows + " " + cowsString + ".");
        }else {
            System.out.print(" None.");
        }
    }

    public static String randomGenerator(int length, int codeLength) {
        List<String> numberList = new ArrayList<>(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<String> letterList = new ArrayList<>(List.of("a","b","c","d","e","f","g",
                "h","i","j","k","l","m","n","o","p","q","r", "s", "t", "u", "v", "w", "x" ,"y" ,"z"));
        List<String> combinedList = new ArrayList<>();
        int letterLength = 0;
        if(length > 10) {
            letterLength = length - 10;
            length -= letterLength;
        }

        for(int i = 0; i < length ; i++) {
            combinedList.add(numberList.get(i));
        }
        if(letterLength > 0) {
            for(int i = 0; i < letterLength; i++) {
                combinedList.add(letterList.get(i));
            }
        }
        message = "The secret is prepared :  ";
        for(int i = 0; i < codeLength; i++) {
            message += "*";
        }
        if(letterLength > 0)
            message += String.format(" (0-9, %s-%s).\n", letterList.get(0), letterList.get(letterLength-1));
        else
            message += String.format(" (%s-%s).", numberList.get(0), numberList.get(length-1));
        Collections.shuffle(combinedList);
        String code = "";
        for(int i = 0; i < codeLength; i++) {
            code += combinedList.get(i);
        }
        return code;
    }
}


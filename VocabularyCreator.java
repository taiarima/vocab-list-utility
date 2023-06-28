import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class VocabularyCreator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Provide the file name you would like to make a vocabulary list for: ");
        String fileName = scanner.nextLine();

        
        String[] textFragments = loadTextFromFile(fileName);

        if (textFragments != null) {
            System.out.println("Choose a name for your file");
            String outputFile = scanner.nextLine();

            boolean success = processTextFragments(scanner, textFragments, outputFile);
            if (success) {
                System.out.println("Your vocabulary list has been successfully saved as " + outputFile + ".js");
            } else {
                System.out.println("An error occurred and your work could not be saved.");
            }
        } else {
            System.out.println("Your text file was empty");
        }

        scanner.close();
    }

    private static String[] loadTextFromFile(String fileName) {
        StringBuilder textBuilder = new StringBuilder();
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                textBuilder.append(line).append(" ");
            }

            reader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String allText = textBuilder.toString().replaceAll("\\p{Punct}", "");
        String[] textFragments = allText.split("\\s+");
        return textFragments;
    }

    private static boolean processTextFragments(Scanner scanner, String[] textFragments, String outputFile) {
        try {
            PrintWriter writer = new PrintWriter(outputFile + ".js");

            for (String fragment : textFragments) {
                System.out.println("Text fragment: " + fragment + ". Split into multiple cards?");
                String cardsInput = scanner.nextLine();

                String[] cards;
                if (cardsInput.isEmpty()) {
                    cards = new String[]{fragment};
                } else {
                    cards = cardsInput.split(" ");
                }

                for (String card : cards) {
                    System.out.println("Card " + card + " korean: ");
                    String korean = scanner.nextLine();
                    System.out.println("Card " + card + " english: ");
                    String english = scanner.nextLine();

                    writer.println("{korean: \"" + (korean.isEmpty() ? card : korean) + "\", english: \"" + english + "\"}");
                }
            }

            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

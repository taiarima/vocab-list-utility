import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class VocabularyCreator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide the file name you would like to make a vocabulary list for: ");
        String fileName = scanner.nextLine();

        // Load text from the file here
        
        String text = "한국말은 재미있는 언어예요";

        System.out.println("Choose a name for your file");
        String outputFile = scanner.nextLine();

        try {
            PrintWriter writer = new PrintWriter(outputFile + ".js");

            String[] fragments = text.split(" ");
            for (String fragment : fragments) {
                System.out.println("Text fragment: " + fragment + ". Split into multiple cards?");
                String cardsInput = scanner.nextLine();

                String[] cards;
                if (cardsInput.isEmpty()) {
                    cards = new String[] { fragment };
                } else {
                    cards = cardsInput.split(" ");
                }

                for (String card : cards) {
                    System.out.println("Card " + card + " korean: ");
                    String korean = scanner.nextLine();
                    System.out.println("Card " + card + " english: ");
                    String english = scanner.nextLine();

                    // Here you write the card to the .js file
                    writer.println("{korean: \"" + (korean.isEmpty() ? card : korean) + "\", english: \"" + english + "\"}");
                }
            }

            writer.close();
            scanner.close();
            System.out.println("Your vocabulary list has been successfully saved as " + outputFile + ".js");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred and your work could not be saved.");
            scanner.close();
        }
    }
}

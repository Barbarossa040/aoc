package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordSuggestor {

    private static int upperLimit = 657474;
    private static int lowerLimit = 183564;

    public static void main(String[] args) throws IOException {
        System.out.println("finding all passwords between " + lowerLimit + " and " + upperLimit);
        BruteForcer forcee = new BruteForcer(lowerLimit, upperLimit);
        forcee.findCompliantPasswords();
        File answer = new File("C:\\dev\\aoc\\aoc\\output\\day4\\answer\\answer.txt");
        FileWriter writer = new FileWriter(answer);
        writer.write(createContent(forcee));
        writer.close();
    }

    private static String createContent(BruteForcer forcee) {
        StringBuilder content = new StringBuilder(
                "The answer for the first part is: " + forcee.getCounter1() +".\n\n\n");
        content.append("The answer for the second part is: ").append(forcee.getCounter2()).append(".\n\n\n");
        content.append("Valid rule1Passcodes are:\n");
        forcee.getRule1CompliantPasscodes().forEach(i -> content.append(i).append(", "));
        content.append("\n\nValid rule2Passcodes are:\n");
        forcee.getRule2CompliantPasscodes().forEach(i -> content.append(i).append(", "));
        return content.toString();
    }
}

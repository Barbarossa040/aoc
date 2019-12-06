package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordSuggestor {

    private static final String PATH_FOR_OUTPUT = "C:\\dev\\aoc\\answers\\day4\\answer.txt";

    public static void main(String[] args) throws IOException {
        int upperLimit = 657474;
        int lowerLimit = 183564;

        System.out.println("finding all passwords between " + lowerLimit + " and " + upperLimit);

        app.BruteForcer forcee = new BruteForcer(lowerLimit, upperLimit);
        long startTime = System.currentTimeMillis();
        forcee.findCompliantPasswords();
        long delta = System.currentTimeMillis() - startTime;
        File answer = new File(PATH_FOR_OUTPUT);
        FileWriter writer = new FileWriter(answer);
        writer.write(createContent(forcee, delta));
        writer.close();
    }

    private static String createContent(BruteForcer forcee, long delta) {
        StringBuilder content = new StringBuilder(
                "The answer for the first part is: " + forcee.getCounter1() +".\n\n\n");
        content.append("The answer for the second part is: ").append(forcee.getCounter2()).append(".\n\n\n");
        content.append("Calculating this took: ").append(delta).append(" milliseconds.\n\n\n");
        content.append("Valid rule1Passcodes are:\n");
        forcee.getRule1CompliantPasscodes().forEach(i -> content.append(i).append(", "));
        content.append("\n\nValid rule2Passcodes are:\n");
        forcee.getRule2CompliantPasscodes().forEach(i -> content.append(i).append(", "));
        return content.toString();
    }
}

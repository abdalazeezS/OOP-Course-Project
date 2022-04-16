package project;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ATMLogger implements LoggerInterface {

    private static final LocalDateTime now = LocalDateTime.now();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");

    @Override
    public void log(String message) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("log.txt", true);
            fileWriter.write("[ " + dtf.format(now) + " ] " + message + "\n\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

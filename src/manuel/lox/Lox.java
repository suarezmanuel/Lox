package manuel.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;


public class Lox {

    static boolean hadError = false;

    public static void main (String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile (String path) throws IOException {
        // get bytes from file
        byte [] bytes = Files.readAllBytes(Paths.get(path));
        // run bytes as file
        run (new String (bytes, Charset.defaultCharset()));

        // if there was error in the code that was run
        if (hadError) {
            System.exit(65);
        }
    }

    // run code line by line, just like js or python!
    private static void runPrompt () throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.println("> ");
            String line = reader.readLine();
            // pressing ctl-D it will send EOT signal,
            // in turn will read null
            if (line == null) break;
            run(line);
            // if got here, theres no error
            hadError = false;
        }
    }

    private static void run (String source) {
        Scanner scanner = new Scanner(source);

        // separates string into tokens
        List<Token> tokens = scanner.scanTokens();

        // print the tokens of the string
        for (Token token : tokens) {
            System.out.println(token);
        }

    }

    static void error (int line, String message) {
        report(line, "", message);
    }

    private static void report (int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}
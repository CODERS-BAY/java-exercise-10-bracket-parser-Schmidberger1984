package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Parser {

    private final Deque<Character> elementStack;

    public Parser() {
        elementStack = new ArrayDeque<>();
    }

    public void parseFile(final String filename) throws ParsingException {

        File file = new File(getClass().getClassLoader().getResource(filename).getFile());
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                String words=scanner.nextLine();
                System.out.println(words);
                for (int i=0; i<words.length(); i++) {
                    elementStack.add(words.charAt(i));
                }

            }
        } catch (FileNotFoundException e) {
        }
        int open= (int) elementStack.stream().filter(a->a=='{').count();
        int close= (int) elementStack.stream().filter(a->a=='}').count();
        if (open>close) throw new ParsingException("Error parsing the file. } expected");
        if (open<close) throw new ParsingException("Error parsing the file. { expected");
    }


    public Deque<Character> getElementStack() {

        return elementStack;
    }
}



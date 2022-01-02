package application;

public class Main {
    public static void main(String[] args) throws ParsingException {
        System.out.println("Hello Parser");
        Parser parser = new Parser();
        parser.parseFile("sample1.txt");
    }
}

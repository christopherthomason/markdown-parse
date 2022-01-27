// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParseStackTest {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<Character> stack = new ArrayList<>();
        ArrayList<Integer> stackIndex = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )

        for (int i = 0; i < markdown.length(); i++) {
            Character currentChar = Character.valueOf(markdown.charAt(i));

            if (currentChar == '[') {
                stack.add(currentChar);
                stackIndex.add(Integer.valueOf(i));
            } else if (currentChar == '(') {
                stack.add(currentChar);
                stackIndex.add(Integer.valueOf(i));
            } else if (currentChar == ']') {
                for (int j = 0; j < stack.size(); j++) {
                    if (stack.get(j) == '[') {
                        stack.remove(j);
                        stackIndex.remove(j);
                        break;
                    }
                }
            } else if (currentChar == ')') {
                for (int j = 0; j < stack.size(); j++) {
                    if (stack.get(j) == '(') {
                        if ((markdown.charAt(stackIndex.get(stackIndex.size() - 1) - 1) == ']') && !(stack.contains('['))) {
                            toReturn.add(markdown.substring(stackIndex.get(stackIndex.size() - 1) + 1, i));
                        }
                        stack.remove(j);
                        stackIndex.remove(j);
                        break;
                    }
                }
            }
        }
        //int currentIndex = 0;
        /*
        while(currentIndex < markdown.length()) {
            
            if (markdown.indexOf(")", currentIndex) == -1) {
                break;
            }
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if (markdown.charAt(nextCloseBracket + 1) == '(' && (markdown.indexOf(")", currentIndex) < markdown.indexOf("[", nextCloseBracket) ||
             markdown.indexOf("[", nextCloseBracket) == -1 && markdown.indexOf(")", currentIndex) != -1)) {
                int openParen = nextCloseBracket + 1;
                int closeParen = markdown.indexOf(")", openParen);
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            } else {
                currentIndex = nextCloseBracket;
            }
            
        }
        */
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
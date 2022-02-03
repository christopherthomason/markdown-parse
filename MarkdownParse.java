import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            if (markdown.indexOf(")", currentIndex) == -1) { 
                break;
            }
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = nextCloseBracket + 1; //markdown.indexOf("(", nextCloseBracket);
            char possibleOpenParam = markdown.charAt(openParen);
            if (nextOpenBracket == -1 || nextCloseBracket == -1) break;
            if (possibleOpenParam != '(') break;
            if (markdown.charAt(nextCloseBracket + 1) == '(' && (markdown.indexOf(")", currentIndex) < markdown.indexOf("[", nextCloseBracket) ||
             markdown.indexOf("[", nextCloseBracket) == -1 && markdown.indexOf(")", currentIndex) != -1)) {
                int closeParen = markdown.indexOf(")", openParen);
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            } else {
                currentIndex++;
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
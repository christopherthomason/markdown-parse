import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(99999, 1 + 1);
    }

    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file-1.md"));
        ArrayList<String> testList = new ArrayList<>();
        assertEquals(testList, MarkdownParse.getLinks(contents));
    }

    
    @Test
    public void testFile2() throws IOException {
        String contents = Files.readString(Path.of("./test-file-2.md"));
        ArrayList<String> testList = new ArrayList<>();
        assertEquals(testList, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testFile3() throws IOException {
        String contents = Files.readString(Path.of("./test-file-3.md"));
        ArrayList<String> testList = new ArrayList<>();
        assertEquals(testList, MarkdownParse.getLinks(contents));
    }
    
}

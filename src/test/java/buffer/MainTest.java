package buffer;

import buffer.core.Main;
import buffer.ds.Trie;
import buffer.model.Paper;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // =========================
    // CSV LOADING TEST
    // =========================
    @Test
    void testLoadKeywordsFromCSV() throws IOException {
        // create temp file
        File tempFile = File.createTempFile("keywords", ".csv");

        try (PrintWriter out = new PrintWriter(tempFile)) {
            out.println("machine");
            out.println("data");
        }

        Trie trie = new Trie();
        Main.loadKeywordsFromCSV(tempFile.getAbsolutePath(), trie);

        List<String> result = trie.suggest("ma");

        assertTrue(result.contains("machine"));
    }


    // =========================
    // CSV APPEND TEST
    // =========================
    @Test
    void testAppendKeywordToCSV() throws Exception {
        File tempFile = File.createTempFile("keywords", ".csv");

        // call method using reflection (since it's private)
        java.lang.reflect.Method method = Main.class.getDeclaredMethod("appendKeywordToCSV", String.class, String.class);
        method.setAccessible(true);
        method.invoke(null, tempFile.getAbsolutePath(), "ai");

        // verify
        BufferedReader br = new BufferedReader(new FileReader(tempFile));
        String line = br.readLine();

        assertEquals("ai", line);
    }


    // =========================
    // FILTER BY AUTHOR TEST
    // =========================
    @Test
    void testFilterByAuthorLogic() {
        List<Paper> papers = new ArrayList<>();

        papers.add(new Paper("T1", List.of("Alice"), "A", "2022", "L", "src"));
        papers.add(new Paper("T2", List.of("Bob"), "B", "2023", "L", "src"));

        List<Paper> filtered = papers.stream()
                .filter(p -> p.getAuthors().stream()
                        .anyMatch(a -> a.toLowerCase().contains("alice")))
                .toList();

        assertEquals(1, filtered.size());
        assertEquals("T1", filtered.get(0).getTitle());
    }


    // =========================
    // FILTER BY YEAR TEST
    // =========================
    @Test
    void testFilterByYearLogic() {
        List<Paper> papers = new ArrayList<>();

        papers.add(new Paper("Old", List.of("A"), "A", "2018", "L", "src"));
        papers.add(new Paper("New", List.of("B"), "B", "2023", "L", "src"));

        int year = 2020;

        List<Paper> filtered = papers.stream()
                .filter(p -> {
                    String date = p.getPublicationDate();
                    if (date != null) {
                        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\b(\\d{4})\\b").matcher(date);
                        if (matcher.find()) {
                            int pubYear = Integer.parseInt(matcher.group(1));
                            return pubYear >= year;
                        }
                    }
                    return false;
                })
                .toList();

        assertEquals(1, filtered.size());
        assertEquals("New", filtered.get(0).getTitle());
    }


    // =========================
    // EMPTY CSV HANDLING
    // =========================
    @Test
    void testLoadKeywordsEmptyFile() throws IOException {
        File tempFile = File.createTempFile("keywords", ".csv");

        Trie trie = new Trie();
        Main.loadKeywordsFromCSV(tempFile.getAbsolutePath(), trie);

        List<String> result = trie.suggest("any");

        assertTrue(result.isEmpty());
    }


    // =========================
    // INVALID FILE PATH
    // =========================
    @Test
    void testLoadKeywordsInvalidPath() {
        Trie trie = new Trie();

        assertDoesNotThrow(() ->
                Main.loadKeywordsFromCSV("invalid_path.csv", trie)
        );
    }
}
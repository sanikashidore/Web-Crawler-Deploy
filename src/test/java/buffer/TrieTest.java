package buffer;

import buffer.ds.Trie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TrieTest {


    @Test
    void testInsertAndSearch() {
        Trie trie = new Trie();
        trie.insert("machine");

        List<String> result = trie.suggest("mac");

        assertTrue(result.contains("machine"));
    }

    @Test
    void testPrefixNotFound() {
        Trie trie = new Trie();
        trie.insert("data");

        List<String> result = trie.suggest("xyz");

        assertTrue(result.isEmpty());
    }

    @Test
    void testMultipleWords() {
        Trie trie = new Trie();
        trie.insert("data");
        trie.insert("database");
        trie.insert("datascience");

        List<String> result = trie.suggest("data");

        assertEquals(3, result.size());
    }


    @Test
    void testEmptyInput() {
        Trie trie = new Trie();
        List<String> result = trie.suggest("");

        assertTrue(result.isEmpty());
    }

    @Test
    void testDuplicateInsert() {
        Trie trie = new Trie();
        trie.insert("ai");
        trie.insert("ai");

        List<String> result = trie.suggest("a");

        assertEquals(1, result.size());
    }

    @Test
    void testExactMatch() {
        Trie trie = new Trie();
        trie.insert("data");

        List<String> result = trie.suggest("data");

        assertTrue(result.contains("data"));
    }

@Test
    void testPrefixLongerThanWord() {
        Trie trie = new Trie();
        trie.insert("ai");

        List<String> result = trie.suggest("artificial");

        assertTrue(result.isEmpty());
    }

@Test
    void testSpecialCharacters() {
        Trie trie = new Trie();
        trie.insert("machine-learning");

        List<String> result = trie.suggest("machine");

        assertTrue(result.contains("machine-learning"));
    }

@Test
    void testManyInsertions() {
        Trie trie = new Trie();

        for (int i = 0; i < 100; i++) {
            trie.insert("data" + i);
        }

        List<String> result = trie.suggest("data");

        assertEquals(100, result.size());
    }

  
}
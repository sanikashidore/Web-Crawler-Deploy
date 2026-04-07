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
}
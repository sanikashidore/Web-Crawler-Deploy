package buffer;

import buffer.ds.CitationGraph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GraphTest {

    @Test
    void testAddPaper() {
        CitationGraph graph = new CitationGraph();
        graph.addPaper("Paper1");

        assertTrue(graph.getCitations("Paper1").isEmpty());
    }

    @Test
    void testAddCitation() {
        CitationGraph graph = new CitationGraph();
        graph.addCitation("Paper1", "Paper2");

        Set<String> citations = graph.getCitations("Paper1");

        assertTrue(citations.contains("Paper2"));
    }

    @Test
    void testGetCitedBy() {
        CitationGraph graph = new CitationGraph();
        graph.addCitation("Paper1", "Paper2");

        Set<String> citedBy = graph.getCitedBy("Paper2");

        assertTrue(citedBy.contains("Paper1"));
    }

    @Test
    void testSuggestRelevant() {
        CitationGraph graph = new CitationGraph();

        graph.addCitation("A", "B");
        graph.addCitation("B", "C");

        List<String> suggestions = graph.suggestRelevant("A");

        assertTrue(suggestions.contains("B"));
        assertTrue(suggestions.contains("C"));
    }

    @Test
    void testDetectIsolatedPapers() {
        CitationGraph graph = new CitationGraph();

        graph.addPaper("Isolated");
        graph.addCitation("A", "B");

        List<String> isolated = graph.detectIsolatedPapers();

        assertTrue(isolated.contains("Isolated"));
    }
}
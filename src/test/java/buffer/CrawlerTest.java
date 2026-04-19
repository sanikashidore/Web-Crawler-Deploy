package buffer;

import buffer.Crawler.ArvixCrawler;
import buffer.Crawler.GoogleScholarCrawler;
import buffer.model.Paper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CrawlerTest {

    // =========================
    // ARXIV TESTS
    // =========================

    @Test
    void testArxivSearchNotNull() {
        ArvixCrawler crawler = new ArvixCrawler();
        List<Paper> results = crawler.search("machine learning");

        assertNotNull(results);
    }

    @Test
    void testArxivMaxResultsLimit() {
        ArvixCrawler crawler = new ArvixCrawler();
        List<Paper> results = crawler.search("ai");

        assertTrue(results.size() <= 5);
    }

    @Test
    void testArxivPaperFieldsNotNull() {
        ArvixCrawler crawler = new ArvixCrawler();
        List<Paper> results = crawler.search("deep learning");

        for (Paper p : results) {
            assertNotNull(p.getTitle());
            assertNotNull(p.getAuthors());
            assertNotNull(p.getAbstractText());
            assertNotNull(p.getLink());
        }
    }

    @Test
    void testArxivHandlesEmptyKeyword() {
        ArvixCrawler crawler = new ArvixCrawler();
        List<Paper> results = crawler.search("");

        assertNotNull(results); // 
    }


    // =========================
    // GOOGLE SCHOLAR TESTS
    // =========================

    @Test
    void testScholarSearchNotNull() {
        GoogleScholarCrawler crawler = new GoogleScholarCrawler();
        List<Paper> results = crawler.search("data science");

        assertNotNull(results);
    }

    @Test
    void testScholarMaxResultsLimit() {
        GoogleScholarCrawler crawler = new GoogleScholarCrawler();
        List<Paper> results = crawler.search("ai");

        assertTrue(results.size() <= 5);
    }

    @Test
    void testScholarPaperFieldsNotNull() {
        GoogleScholarCrawler crawler = new GoogleScholarCrawler();
        List<Paper> results = crawler.search("machine learning");

        for (Paper p : results) {
            assertNotNull(p.getTitle());
            assertNotNull(p.getAuthors());
            assertNotNull(p.getAbstractText());
            assertNotNull(p.getLink());
        }
    }

    @Test
    void testScholarHandlesInvalidKeyword() {
        GoogleScholarCrawler crawler = new GoogleScholarCrawler();
        List<Paper> results = crawler.search("asdkfjhasdkjfhakjsdhf");

        assertNotNull(results); // 
    }
}
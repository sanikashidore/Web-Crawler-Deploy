# Research Paper Crawler

## Overview

Research Paper Crawler is a Java-based application that retrieves and analyzes academic papers from sources such as arXiv and Google Scholar. It enables users to search using keywords, explore citation relationships, and filter results interactively. The project also demonstrates core data structures like Trie and Graph in a real-world use case.

---

## Features

* Keyword-based search with auto-suggestions using Trie
* Real-time crawling of research papers from arXiv and Google Scholar
* Citation graph construction and traversal
* Related paper recommendations based on citation paths
* Detection of isolated papers (no incoming or outgoing citations)
* Filtering by author and publication year
* CSV-based keyword persistence

---

## Tech Stack

* Java 8+
* Maven (Build Tool)
* Jsoup (HTML parsing)
* JUnit 5 (Testing)
* GitHub Actions (CI/CD)
* SonarCloud (Static Code Analysis)
* Docker (Containerization)

---

## Project Structure

```
devops_t1/
├── .github/workflows/ci.yml
├── src/
│   ├── main/
│   │   ├── java/buffer/
│   │   └── resources/keywords.csv
│   └── test/java/buffer/
├── Dockerfile
├── pom.xml
└── README.md
```

---

## Getting Started

### Prerequisites

* Java 17
* Maven
* Docker (optional for containerized run)

---

## Build and Run (Local)

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="buffer.core.Main"
```

---

## Run with Docker

### Build image

```bash
docker build -t research-crawler-img .
```

### Run container

```bash
docker run -it research-crawler-img
```

---

## Testing

```bash
mvn test
```

---

## CI/CD Pipeline

The project uses GitHub Actions for continuous integration. The pipeline includes:

* Code checkout
* Maven build
* Unit testing
* Static code analysis using SonarCloud
* Docker image build

Pipeline is triggered automatically on every push and pull request.

---

## Static Code Analysis

SonarCloud is integrated to monitor:

* Code quality
* Bugs and vulnerabilities
* Maintainability

---

## Deployment

The application is containerized using Docker. The CI pipeline builds the Docker image automatically, enabling consistent deployment across environments.

---

## Key Concepts Demonstrated

* Trie (for keyword auto-suggestions)
* Graph (for citation relationships)
* Depth-First Search (for traversal)
* Stream API (for filtering)
* Web scraping using Jsoup

---

## Future Enhancements

* Web-based UI for visualization
* Database integration for persistent storage
* Improved ranking algorithms
* API-based integration instead of scraping

---

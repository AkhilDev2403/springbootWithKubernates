package com.akhildev.bookmarker.api;

import com.akhildev.bookmarker.entity.BookMarkerEntity;
import com.akhildev.bookmarker.repository.BookMarkerRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:mysql:8.0.36:///test"
})
public class BookMarkerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookMarkerRepository bookMarkerRepository;

    private List<BookMarkerEntity> bookMarks;

    /**
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.32")
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root");       -> Alternate way to handle this is simply specifying the url at @TestPropertySource(...)

     @BeforeAll
     public static void startContainer() {
     mysqlContainer.start();
     System.setProperty("DB_URL", mysqlContainer.getJdbcUrl());
     System.setProperty("DB_USERNAME", mysqlContainer.getUsername());
     System.setProperty("DB_PASSWORD", mysqlContainer.getPassword());
     }
     
     **/

    @BeforeEach                //before starting the test, removing all data from db
    void setUp() {
        bookMarkerRepository.deleteAllInBatch();

        bookMarks = new ArrayList<>();
        bookMarks.add(new BookMarkerEntity(null, "Moss", "Milly", "https://google.in", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "SpringBlog","Don", "https://spring.io/blog", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "Quarkus", "Shawn" ,"https://quarkus.io/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "Micronaut", "Brent", "https://micronaut.io/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "JOOQ", "Mike",  "https://www.jooq.org/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "VladMihalcea", "Joey", "https://vladmihalcea.com", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "Thoughts On Java", "Philip", "https://thorben-janssen.com/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "DZone", "Leny", "https://dzone.com/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "DevOpsBookmarks", "Rachel", "http://www.devopsbookmarks.com/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "Kubernetes docs", "Ross", "https://kubernetes.io/docs/home/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "Expressjs", "Ken", "https://expressjs.com/", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "Marcobehler", "Sinn", "https://www.marcobehler.com", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "baeldung", "Sam",  "https://www.baeldung.com", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "devglan", "Karen", "https://www.devglan.com", Instant.now()));
        bookMarks.add(new BookMarkerEntity(null, "linuxize", "Ben", "https://linuxize.com", Instant.now()));

        bookMarkerRepository.saveAll(bookMarks);
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
         mvc.perform(get("/api/bookmarks/getAll")).
                 andExpect(status().isOk())
                 .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)))
                 .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(3)))     //total 15 and 1 page will have size 5. so totalPages should be 3
                 .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(1)))
                 .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(true)))
                 .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(false)))
                 .andExpect(jsonPath("$.hasNextPage", CoreMatchers.equalTo(true)))
                 .andExpect(jsonPath("$.hasPreviousPage", CoreMatchers.equalTo(false)));
    }

}

package com.akhildev.bookmarker;

import com.akhildev.bookmarker.entity.BookMarkerEntity;
import com.akhildev.bookmarker.repository.BookMarkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final BookMarkerRepository repository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        repository.save(new BookMarkerEntity(null, "SivaLabs", "Jen", "https://sivalabs.in", Instant.now()));
//        repository.save(new BookMarkerEntity(null, "SpringBolg", "Ken", "https://spring.io/blog", Instant.now()));
//        repository.save(new BookMarkerEntity(null, "Quarks", "Addams", "https://quarks.io/", Instant.now()));
//        repository.save(new BookMarkerEntity(null, "Micronaut", "Wills", "https://micronaut.io/", Instant.now()));
//    }
//}


// we no longer requires this data initializer anymore, cause we have scripts, which will now do the loading of data
// through database migration tool - flyway
// now the whole data will be loaded through this.

/***
 *
 * SQL Scripts
 *      Schema Versioning: The script is responsible for creating a new database table (bookmarks)
 *      and an ID sequence (bm_id_seq) that generates values for the id column.
 *      When developing an application, you often need to add or modify database tables.
 *      These changes should be applied consistently across different environments (development, staging, production).
 *
 * Auto-Incrementing IDs:
 *      In the script, the id column in the bookmarks table is automatically populated using the bm_id_seq sequence.
 *      This is crucial when inserting new rows into the table because it provides unique,
 *      incrementing values for the id column without manually specifying them.
 *
 *      Why use a sequence? While some databases (like MySQL) support AUTO_INCREMENT,
 *      other databases (like PostgreSQL) use sequences for generating unique values.
 *      The sequence ensures that each row gets a unique id automatically, which is necessary for
 *      maintaining the integrity of the primary key.
 *
 *
 *      What Is Flyway Doing Here?
 *      Flyway is a database migration tool that helps manage database schema changes in a version-controlled way.
 *      Here’s what Flyway does in this context:
 *
 *      Version Control for Database Changes:
 *          The SQL file (/db/migration/h2/V1_create_bookmarks_table.sql) is part of a versioned migration.
 *          The V1 in the filename indicates the version of this migration.
 *
 *           Flyway keeps track of which migrations have been applied to the database.
 *          If the database doesn't yet have version 1 applied, Flyway runs the script to create the bookmarks table
 *          and the sequence bm_id_seq.
 *          Flyway stores the version history in a special table (often called flyway_schema_history),
 *          so it knows what migrations have been applied and which are pending.
 *
 *
 *
 * ***/
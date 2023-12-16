package dev.kapilan.contentcalendar.repository;

import dev.kapilan.contentcalendar.model.Content;
import dev.kapilan.contentcalendar.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContentRepository extends ListCrudRepository<Content, Integer> {

    //using Query Derivation
    List<Content> findAllByTitleContains(String keyword);

    // Manually defined query or custom query
    @Query("""
        SELECT * FROM Content
        WHERE status = :status
    """)
    List<Content> listByStatus(@Param("status") Status status);
}

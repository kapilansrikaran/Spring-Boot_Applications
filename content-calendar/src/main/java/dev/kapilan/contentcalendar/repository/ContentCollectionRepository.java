package dev.kapilan.contentcalendar.repository;

import dev.kapilan.contentcalendar.model.Content;
import dev.kapilan.contentcalendar.model.Status;
import dev.kapilan.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    // find all content
    public List<Content> findAll(){
        return contentList;
    }

    // find single content
    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    @PostConstruct
    private void init(){
        Content cantent = new Content(
                1,
                "blog",
                "post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );

        contentList.add(cantent);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }


    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}

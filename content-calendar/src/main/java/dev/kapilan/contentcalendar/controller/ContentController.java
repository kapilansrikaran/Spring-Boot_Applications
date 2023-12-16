package dev.kapilan.contentcalendar.controller;


import dev.kapilan.contentcalendar.model.Content;
import dev.kapilan.contentcalendar.model.Status;
import dev.kapilan.contentcalendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {


//     private final ContentCollectionRepository repository;
//     private final ContentJdbcTemplateRepository repository;


    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }


    // make a request and find al the pieces of content in the system
    @GetMapping("")
    public List<Content> findAll(){
        return contentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Content findByID(@PathVariable Integer id){
        return contentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!"));
    }

    // CRUD - filter | paging and sorting

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){  // @RequestBody - content to be sent as a part of requestBody in a request
        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}") // when update we use PUT Method
    public void update(@RequestBody Content content, @PathVariable Integer id){ //@PathVariable to get that id out of the path
        if(!contentRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id){
        // repository.delete(id);
        contentRepository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword){
        return contentRepository.findAllByTitleContains(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus(@PathVariable Status status){
        return contentRepository.listByStatus(status);
    }

}


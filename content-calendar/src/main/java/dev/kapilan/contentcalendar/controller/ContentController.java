package dev.kapilan.contentcalendar.controller;

import dev.kapilan.contentcalendar.model.Content;
import dev.kapilan.contentcalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentCollectionRepository respository;

    public ContentController(ContentCollectionRepository respository) {
        this.respository = respository;
    }

    // make a request and find al the pieces of content in the system
    @GetMapping("")
    public List<Content> findAll(){
        return respository.findAll();
    }

    @GetMapping("/{id}")
    public Content findByID(@PathVariable Integer id){
        return respository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!"));
    }

    // CRUD - filter | paging and sorting

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){  // @RequestBody - content to be sent as a part of requestBody in a request
        respository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}") // when update we use PUT Method
    public void update(@RequestBody Content content, @PathVariable Integer id){ //@PathVariable to get that id out of the path
        if(!respository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        respository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id){
        respository.delete(id);
    }
}

package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Library;
import io.javabrains.ratingsdataservice.services.LibraryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping
    public List<Library> getLibraries(){
        return libraryService.getLibraries();

    }
}

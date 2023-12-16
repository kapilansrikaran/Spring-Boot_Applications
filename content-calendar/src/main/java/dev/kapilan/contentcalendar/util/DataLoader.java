package dev.kapilan.contentcalendar.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Programmatically Data Loading

//@Component // we can use @controller as well
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello");
    }
}

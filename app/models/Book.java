package models;

import dev.morphia.annotations.*;

@Entity("Books")
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    @Property("price")
    private double cost;
    // constructors, getters, setters and hashCode, equals, toString implementations
}


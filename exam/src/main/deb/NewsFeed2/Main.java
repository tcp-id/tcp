package main.deb.NewsFeed2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


class Feeds {
    public List<Feed> feeds;

    @JsonCreator
    public Feeds(@JsonProperty("feeds") List<Feed> feeds){
        this.feeds = feeds;
    }
}

class Feed {
    public String id;
    public String title;
    public String description;
    public String url;
    public List<Author> authors;
    public List<Item> items;

    @JsonCreator
    public Feed(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("url") String url,
            @JsonProperty("authors") List<Author> authors,
            @JsonProperty("items") List<Item> items) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.authors = authors;
        this.items = items;
    }
}

class Author {
    public String name;
    public String url;

    @JsonCreator
    public Author(
            @JsonProperty("name") String name,
            @JsonProperty("url") String url) {
        this.name = name;
        this.url = url;
    }
}

class Item {
    public String id;
    public String description;
    public String url;
    public List<Author> authors;
    public List<Attachment> attachments;

    @JsonCreator
    public Item(
            @JsonProperty("id") String id,
            @JsonProperty("description") String description,
            @JsonProperty("url") String url,
            @JsonProperty("authors") List<Author> authors,
            @JsonProperty("attachments") List<Attachment> attachments) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.authors = authors;
        this.attachments = attachments;
    }
}

class Attachment {
    public String url;
    public String mimetype;

    @JsonCreator
    public Attachment(
            @JsonProperty("url") String url,
            @JsonProperty("mimetype") String mimetype) {
        this.url = url;
        this.mimetype = mimetype;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        Feeds feeds = new Feeds(objectMapper.readValue(Paths.get("C:\\Users\\arnau\\Documents\\DAM\\M06\\solucions_m06_uf1\\src\\com\\company\\NewsFeed2\\feed.json").toFile(), Feeds.class).feeds);

        for (Feed feed: feeds.feeds) {
            System.out.println("[" + feed.id + "] " + feed.title);
            System.out.println(feed.description);
            System.out.println(feed.url);

            System.out.println("\tAuthors: ");
            for (Author author:feed.authors) {
                System.out.println("\t\t- " + author.name + " ("+author.url+")");
            }

            System.out.println("\tItems: ");
            for (Item item: feed.items) {
                System.out.println("\t\t- [" + item.id + "] " + item.description);
                System.out.println("\t\t  " + item.url);
                System.out.println("\t\t\tAuthors:");
                for (Author author: item.authors) {
                    System.out.println("\t\t\t\t- " + author.name + " ("+author.url+")");
                }
                System.out.println("\t\t\tAttachments:");
                for (Attachment attachment:item.attachments) {
                    System.out.println("\t\t\t\t- " + attachment.url + " (" + attachment.mimetype + ")");
                }
            }
        }
    }
}
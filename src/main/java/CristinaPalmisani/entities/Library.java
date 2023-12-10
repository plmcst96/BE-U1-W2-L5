package CristinaPalmisani.entities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Catalogo> library;
    public Library(){
        this.library =new ArrayList<>();
    }
    private static final Logger log = LoggerFactory.getLogger(Library.class);

    @Override
    public String toString() {
        return this.library.stream().map(Catalogo::toString).collect(Collectors.joining());
    }

    public void save(){
        File file = new File("src/library.json");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(this.library);

        try {
            FileUtils.writeStringToFile(file, jsonStr + System.lineSeparator(), StandardCharsets.UTF_8, false);
        } catch (IOException e){
            log.error(e.getMessage());
        }
    }

    public void load(){
        File file = new File("src/library.json");
        String jsonStr = "";
        List<Catalogo> list = new ArrayList<>();

        try {
            jsonStr = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e){
            log.error(e.getMessage());
        }

        Gson gson = new Gson();
        List<JsonObject> libraryList = gson.fromJson(jsonStr, new TypeToken<List<JsonObject>>(){}.getType());

        libraryList.forEach(elem -> {
            if (elem.asMap().get("author") != null && elem.asMap().get("genre") != null) list.add(gson.fromJson(elem, Book.class));
            else list.add(gson.fromJson(elem, Newspaper.class));
        });
        this.library = list;
    }
    public void addElement(Catalogo element){
        this.library.add(element);
    }

    public void printIndex() {
        for (int i = 0; i < this.library.size(); i++) {
            System.out.print((i + 1) + " - " + this.library.get(i));
        }
    }
    public void rem(int index) {
        if (index < this.library.size() && index >= 0) this.library.remove(index);
    }
    public void rem(String isbn) {
        this.library = this.library.stream().filter(elm -> (!elm.getCodeISBN().equals(isbn))).toList();
    }

    public List<Catalogo> findIsbn(String filter){
        return this.library.stream().filter(elem->(elem.getCodeISBN().contains(filter))).toList();
    }
    public List<Catalogo> findYear(String filter){
        return this.library.stream().filter(elem->(elem.getYear().contains(filter))).toList();
    }

    public List<Catalogo> findAuthor(String filter){
        return this.library.stream().filter(elem->(elem instanceof Book)).filter(book -> ((Book) book).getAuthor().toLowerCase().contains(filter)).toList();
    }


}

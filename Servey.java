package Sping8.hours.com.example.SpringAPIUnit.Servey;

import java.util.List;

public class Servey {
    public Servey(String id, String description, String title, List<Question> question) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.question = question;
    }
    private String id;
    private String description;
    private String title;
    private List<Question> question;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Servey{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", question=" + question +
                '}';
    }
}

package davem.models;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class Task {

    private final LongProperty id;
    private final StringProperty description;
    private final ListProperty<Category> categories;
    private final ListProperty<Tag> tags;

    public Task(Long id, LocalDate complDate, String description, Boolean status, String title, List<Category> categories, List<Tag> tags) {
        this.id = new SimpleLongProperty(id);
        this.description = new SimpleStringProperty(description);
        this.categories = new SimpleListProperty<>((ObservableList<Category>) categories);
        this.tags = new SimpleListProperty<>((ObservableList<Tag>) tags);
    }

    public Task(Long id, LocalDate complDate, String description, Boolean status, String title) {
        this.id = new SimpleLongProperty(id);
        this.description = new SimpleStringProperty(description);
        this.categories = new SimpleListProperty<>(null);
        this.tags = new SimpleListProperty<>(null);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public ObservableList<Category> getCategories() {
        return categories.get();
    }

    public ListProperty<Category> categoriesProperty() {
        return categories;
    }

    public void setCategories(ObservableList<Category> categories) {
        this.categories.set(categories);
    }

    public ObservableList<Tag> getTags() {
        return tags.get();
    }

    public ListProperty<Tag> tagsProperty() {
        return tags;
    }

    public void setTags(ObservableList<Tag> tags) {
        this.tags.set(tags);
    }
}

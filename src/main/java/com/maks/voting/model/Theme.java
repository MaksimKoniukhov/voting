package com.maks.voting.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    private boolean enabled;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private List<Item> items;

    public Theme() {
    }

    public Theme(String question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", enabled=" + enabled +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return id.equals(theme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.maks.voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String path;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Theme theme;

    public Reference() {
    }

    public Reference(String path, Theme theme) {
        this.path = path;
        this.theme = theme;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}

package com.tomadev.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlElement;

public class Article {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();

    public final void setId(int value) {
        id.set(value);
    }

    @XmlElement(name = "ID")
    public final int getId() {
        return id.get();
    }

    public final IntegerProperty idProperty() {
        return id;
    }
    @XmlElement(name = "TITLE")
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String toJsonString() {
        JsonObjectBuilder answer = Json.createObjectBuilder();
        answer.add("ID", getId());
        answer.add("TITLE", getTitle());
        return answer.build().toString();
    }
}

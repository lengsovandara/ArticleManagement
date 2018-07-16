package com.tomadev.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.xml.bind.annotation.XmlElement;

public class Response {
    private final ObjectProperty<Article> data = new SimpleObjectProperty<>();

    public final void setData(Article value) {
        data.set(value);
    }

    @XmlElement(name = "DATA")
    public final Article getData() {
        return data.get();
    }

    public final ObjectProperty<Article> dataProperty() {
        return data;
    }
}

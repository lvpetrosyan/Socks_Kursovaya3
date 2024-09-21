package me.petros.skypro.kursovaya3.model;

import lombok.Getter;

@Getter
public enum Color {
    WHITE("БЕЛЫЙ"),BLACK("ЧЕРНЫЙ"),PINK("РОЗОВЫЙ"),RED("КРАСНЫЙ"),BROW("КОРИЧНЕВЫЙ");
    private final String color;
    Color(String color) {
        this.color = color;
    }
}

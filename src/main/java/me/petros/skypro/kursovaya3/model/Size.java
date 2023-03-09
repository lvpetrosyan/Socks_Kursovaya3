package me.petros.skypro.kursovaya3.model;

import lombok.Getter;

@Getter
public enum Size {
   S(32,36),M(36,40),L(40,44),XL(44,50);
    private final int to;
    private final int from;
    Size(int to, int from) {
        this.to = to;
        this.from = from;
    }
}

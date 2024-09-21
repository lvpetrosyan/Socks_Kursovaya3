package me.petros.skypro.kursovaya3.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Socks {
    @NotNull(message = "WHITE, BROW, RED, BLACK, PINK")
    private Color color;
    @NotNull(message = "S, M, L, XL")
    private Size size;
    @Range(min = 0, max = 100, message = "Процентное соотношение хлопка")
    private int cottonPart;
    @Positive
    @Min(0)
    @Setter
    @EqualsAndHashCode.Exclude
    private int quantity;

    public Socks(Color color, Size size, int cottonPart) {
        this.color = color;
        this.size = size;
        this.cottonPart = cottonPart;
    }
}

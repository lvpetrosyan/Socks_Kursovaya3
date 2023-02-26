package me.petros.skypro.kursovaya3.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Socks {
    @NotNull(message = "WHITE, BROW, RED, BLACK, PINK")
    private Color color;
    @NotNull(message = "S, M, L, XL")
    private Size size;
    @Range(min = 0, max = 100, message = "Процентное соотношение хлопка")
    private int cottonPart;

}

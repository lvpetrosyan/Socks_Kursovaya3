package me.petros.skypro.kursovaya3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SocksException extends RuntimeException {
    public SocksException(){super("Проблема при чтении файла");}

    public SocksException(String message) {
        super(message);
    }
}



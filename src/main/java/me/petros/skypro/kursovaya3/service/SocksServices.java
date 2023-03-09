package me.petros.skypro.kursovaya3.service;

import me.petros.skypro.kursovaya3.model.Color;
import me.petros.skypro.kursovaya3.model.Size;
import me.petros.skypro.kursovaya3.model.Socks;

public interface SocksServices {
    int addSocks(Socks socks);

    boolean deleteSocksDefective(Socks socks);

    boolean sellSocks(Socks socks);

    Integer getSocks(Size size, Color color, Integer cottonMin, Integer cottonMax);
}

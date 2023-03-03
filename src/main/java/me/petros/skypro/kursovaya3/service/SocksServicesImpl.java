package me.petros.skypro.kursovaya3.service;

import me.petros.skypro.kursovaya3.exception.SocksException;
import me.petros.skypro.kursovaya3.model.Color;
import me.petros.skypro.kursovaya3.model.Size;
import me.petros.skypro.kursovaya3.model.Socks;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServicesImpl implements SocksServices {
    Map<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public int addSocks(Socks socks) {
        Socks socks1= new Socks(socks.getColor(), socks.getSize(),socks.getCottonPart());
        if (socksMap.containsKey(socks)) {
            socksMap.put(socks, socksMap.get(socks) + socks.getQuantity());
        } else socksMap.put(socks, socks.getQuantity());
        return socks.getQuantity();
    }

    @Override
    public boolean deleteSocksDefective(Socks socks) {
        deleteSocks(socks);
        return true;
    }

    @Override
    public boolean sellSocks(Socks socks) {
        deleteSocks(socks);
        return true;
    }

    @Override
    public Integer getSocks(Size size, Color color, Integer cottonMin, Integer cottonMax) {
        Integer count = 0;
        for (Map.Entry<Socks, Integer> socksIntegerEntry : socksMap.entrySet()) {
            if (socksIntegerEntry.getKey().getColor().equals(color) &&
                    socksIntegerEntry.getKey().getSize().equals(size) &&
                    cottonMin <= socksIntegerEntry.getKey().getCottonPart() &&
                    cottonMax >= socksIntegerEntry.getKey().getCottonPart()) {
                count += socksIntegerEntry.getValue();
            }
        }
        return count;
    }

    private void deleteSocks(Socks socks) {
        Socks socks1= new Socks(socks.getColor(),socks.getSize(),socks.getCottonPart());
        int socksQuantity = socksMap.getOrDefault(socks, 0);
        if (socksQuantity >= socks.getQuantity()) {
            socksMap.put(socks, socksQuantity - socks.getQuantity());
        } else {
            throw new SocksException("Не найдены носки с такими характеристиками");
        }
    }
}
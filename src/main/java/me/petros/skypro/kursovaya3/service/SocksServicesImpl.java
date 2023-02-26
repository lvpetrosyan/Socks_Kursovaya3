package me.petros.skypro.kursovaya3.service;

import me.petros.skypro.kursovaya3.exception.FileException;
import me.petros.skypro.kursovaya3.model.Socks;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServicesImpl implements SocksServices {
    private static int id = 0;
    Map<Integer, Socks> socksMap = new HashMap<>();

    @Override
    public int addSocks(Socks socks) {
        socksMap.put(id++, socks);
        return id;
    }

    @Override
    public boolean deleteSocks(Socks socks) {
        int i = iterValue(socks);
        socksMap.remove(i);
        return true;
    }

    private int iterValue(Socks socks) {
        int i = 0;
        for (Map.Entry<Integer, Socks> socksEntry : socksMap.entrySet()) {
            if (socks.equals(socksEntry.getValue())) {
                i = socksEntry.getKey();
            } else {
                throw new FileException("Носков с такими характеристиками найти не удалось");
            }
        }
        return i;
    }
}
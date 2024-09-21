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
/**
Метод добавляет носки на склад и возвращает количество добавленных носков.
@param socks объект носков для добавления на склад
@return количество добавленных носков
*/
    @Override
    public int addSocks(Socks socks) {
        Socks socks1= new Socks(socks.getColor(), socks.getSize(),socks.getCottonPart());
        if (socksMap.containsKey(socks)) {
            socksMap.put(socks, socksMap.get(socks) + socks.getQuantity());
        } else socksMap.put(socks, socks.getQuantity());
        return socks.getQuantity();
    }
/**
Метод списывает носки, являющиеся бракованными, со склада.
@param socks объект носков для списания
@return true, если списание выполнено успешно, иначе false
*/
    @Override
    public boolean deleteSocksDefective(Socks socks) {
        deleteSocks(socks);
        return true;
    }
/**
Метод "продает" носки, списывая их со склада.
@param socks объект носков для продажи
@return true, если продажа выполнена успешно, иначе false
*/
    @Override
    public boolean sellSocks(Socks socks) {
        deleteSocks(socks);
        return true;
    }
/**
Метод возвращает общее количество носков на складе, удовлетворяющих заданным критериям.
@param size размер носков
@param color цвет носков
@param cottonMin минимальное значение процента хлопка
@param cottonMax максимальное значение процента хлопка
@return общее количество носков, удовлетворяющих заданным критериям
*/
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

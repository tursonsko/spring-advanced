package pl.strefakursow.springadvanced.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.strefakursow.springadvanced.entity.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    List<Item> getItemsWithQuantityOverTwenty();

    List<Item> getItemsWithQuantityOver(int minQuantityThreshold);

    List<Item> getItemsWithNameLike(String regex);

    List<Item> findByQuantity(Integer quantity);

    List<Item> findByQuantityBetween(Integer minQuantity, Integer maxQuantity);

    List<Item> findByQuantityGreaterThanEqualOrderByQuantityDesc(Integer minQuantity);

    Page<Item> findAll(Pageable pageable);

}

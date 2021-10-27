package pl.strefakursow.springadvanced.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import pl.strefakursow.springadvanced.entity.Item;

import java.util.List;

public interface ItemService {

    void saveItem(Item item);

    List<Item> getItemsWithQuantityOverTwenty();

    List<Item> getItemWithQuantityOver(int minQuantityThreshold);

    List<Item> getItemWithNameLike(String regex);

    List<Item> findByQuantity(Integer quantity);

    List<Item> findAllByQuantityBetween(Integer minQ, Integer maxQ);

    List<Item> findAllByQuantityGreaterThanEqualOrderByQuantityDesc(Integer minQ);

    Page<Item> findAll(Pageable pageable);
}

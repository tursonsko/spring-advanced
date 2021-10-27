package pl.strefakursow.springadvanced.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import pl.strefakursow.springadvanced.entity.Item;

import java.util.List;

public interface ItemService {

    public void saveItem(Item item);

    public List<Item> getItemsWithQuantityOverTwenty();

    public List<Item> getItemsWithQuantityOver(int minQuantityThreshold);

    public List<Item> getItemsWithNameLike(String regex);

    public List<Item> findByQuantity(Integer quantity);

    public List<Item> findByQuantityBetween(Integer minQuantity, Integer maxQuantity);

    public List<Item> findByQuantityGreaterThanEqualOrderByQuantityDesc(Integer minQuantity);

    public Page<Item> findAll(Pageable pageable);
}

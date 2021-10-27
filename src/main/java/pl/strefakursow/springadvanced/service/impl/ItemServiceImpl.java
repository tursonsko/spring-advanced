package pl.strefakursow.springadvanced.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.strefakursow.springadvanced.repository.ItemPagingAndSortingRepository;
import pl.strefakursow.springadvanced.entity.Item;
import pl.strefakursow.springadvanced.repository.ItemRepository;
import pl.strefakursow.springadvanced.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;
    ItemPagingAndSortingRepository itemPagingAndSortingRepository;
    
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemPagingAndSortingRepository itemPagingAndSortingRepository) {
        this.itemRepository = itemRepository;
        this.itemPagingAndSortingRepository = itemPagingAndSortingRepository;
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    @Override
    public List<Item> getItemsWithQuantityOverTwenty() {
        return itemRepository.getItemsWithQuantityOverTwenty();
    }


    @Override
    public List<Item> getItemsWithQuantityOver(int minQuantityThreshold ) {
        return itemRepository.getItemsWithQuantityOver(minQuantityThreshold);
    }


    @Override
    public List<Item> getItemsWithNameLike(String regex) {
        return itemRepository.getItemsWithNameLike(regex);
    }


    @Override
    public List<Item> findByQuantity(Integer quantity) {
        return itemRepository.findByQuantity(quantity);
    }


    @Override
    public List<Item> findByQuantityBetween(Integer minQuantity, Integer maxQuantity) {
        return itemRepository.findByQuantityBetween(minQuantity, maxQuantity);
    }


    @Override
    public List<Item> findByQuantityGreaterThanEqualOrderByQuantityDesc(Integer minQuantity) {
        return itemRepository.findByQuantityGreaterThanEqualOrderByQuantityDesc(minQuantity);
    }


    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemPagingAndSortingRepository.findAll(pageable);
    }

}

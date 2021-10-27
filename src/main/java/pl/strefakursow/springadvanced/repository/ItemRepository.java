package pl.strefakursow.springadvanced.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.strefakursow.springadvanced.entity.Item;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("select i from Item i where i.quantity > 20")
    List<Item> getItemsWithQuantityOverTwenty();

    @Query("select i from Item i where i.quantity > :minQuantityThreshold")
    List<Item> getItemWithQuantityOver(int minQuantityThreshold);

    @Query("select i from Item i where i.name like :regex")
    List<Item> getItemWithNameLike(@Param("regex") String regex);

    List<Item> findByQuantity(Integer quantity);

    List<Item> findAllByQuantityBetween(Integer minQ, Integer maxQ);

    List<Item> findAllByQuantityGreaterThanEqualOrderByQuantityDesc(Integer minQ);
}

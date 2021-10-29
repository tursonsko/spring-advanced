package pl.strefakursow.springadvanced.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.strefakursow.springadvanced.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

	@Query("select i from Item i where i.quantity>20")
	List<Item> getItemsWithQuantityOverTwenty();
	
	@Query("select i from Item i where i.quantity>:minQuantityThreshold")
	List<Item> getItemsWithQuantityOver(@Param("minQuantityThreshold")int minQuantityThreshold);
	
	@Query("select i from Item i where i.name like :regex")
	List<Item> getItemsWithNameLike(@Param("regex") String regex);
	
	List<Item> findByQuantity(Integer quantity);
	
	List<Item> findByQuantityBetween(Integer minQuantity, Integer maxQuantity);
	
	List<Item> findByQuantityGreaterThanEqualOrderByQuantityDesc(Integer minQuantity);
	
}
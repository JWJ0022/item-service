package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    public void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    public void save() {
        //given
        Item item =new Item("itemA", 1000, 10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    public void findAll() {
        //given
        Item item1 =new Item("item1", 1000, 10);
        Item item2 =new Item("item2", 2000, 20);

        Item savedItem1 = itemRepository.save(item1);
        Item savedItem2 = itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }

    @Test
    public void updateItem() {
        //given
        Item item1 =new Item("item1", 1000, 10);

        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();
        //when
        Item updateParam = new Item("item2", 2000, 30);
        itemRepository.update(itemId,updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}

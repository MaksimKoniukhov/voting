package com.maks.voting.service;

import com.maks.voting.model.Item;
import com.maks.voting.model.Theme;
import com.maks.voting.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.maks.voting.util.ValidationUtil.*;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItemOfTheme(Theme theme, Item item) {
        return check(theme, item);
    }

    @Override
    public Item voiceRegistration(Theme theme, Item item) {
        check(theme, item);

        checkThemeEnabled(theme);
        item.setTotalVotes(item.getTotalVotes() + 1);
        return itemRepository.save(item);
    }

    private Item check(Theme theme, Item item) {
        checkNotFound(theme, "Theme");
        checkNotFound(item, "Item");
        checkThemeContainsItem(theme, item);
        return item;
    }
}

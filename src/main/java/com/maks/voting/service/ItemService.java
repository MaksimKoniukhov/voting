package com.maks.voting.service;

import com.maks.voting.model.Item;
import com.maks.voting.model.Theme;

public interface ItemService {
    Item getItemOfTheme(Theme theme, Item item);

    Item voiceRegistration(Theme theme, Item item);
}

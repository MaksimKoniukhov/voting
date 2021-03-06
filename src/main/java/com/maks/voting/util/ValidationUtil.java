package com.maks.voting.util;

import com.maks.voting.HasId;
import com.maks.voting.model.Item;
import com.maks.voting.model.Theme;

import javax.persistence.EntityNotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFound(T object, String message) {
        if (object == null)
            throw new EntityNotFoundException(message + " not found");
        return object;
    }

    public static void checkNew(HasId object) {
        if (!object.isNew()) {
            throw new IllegalArgumentException(object + " must be new (id=null)");
        }
    }

    public static void checkThemeEnabled(Theme theme) {
        if (theme.getPath() == null)
            throw new IllegalStateException(theme + " disabled");
    }

    public static void checkThemeContainsItem(Theme theme, Item item) {
        if (!item.getTheme().equals(theme))
            throw new IllegalArgumentException(String.format("%s doesn't contain such %s", theme, item));
    }
}

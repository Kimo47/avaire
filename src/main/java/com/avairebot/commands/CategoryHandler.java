package com.avairebot.commands;

import com.avairebot.AvaIre;
import com.avairebot.contracts.commands.Command;
import com.avairebot.utilities.RandomUtil;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CategoryHandler {

    private static final List<Category> VALUES = new ArrayList<>();

    public static boolean addCategory(AvaIre avaire, @Nonnull String name, @Nonnull String defaultPrefix) {
        for (Category category : VALUES) {
            if (category.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        VALUES.add(new Category(avaire, name, defaultPrefix));
        return true;
    }

    public static Category fromLazyName(@Nonnull String name) {
        name = name.toLowerCase();

        for (Category category : getValues()) {
            if (category.getName().toLowerCase().startsWith(name)) {
                return category;
            }
        }
        return null;
    }

    public static Category fromCommand(@Nonnull Command command) {
        Category category = command.getCategory();
        if (category != null) {
            return category;
        }

        String[] path = command.getClass().getName().split("\\.");
        String commandPackage = path[path.length - 2];

        for (Category cat : getValues()) {
            if (cat.getName().equalsIgnoreCase(commandPackage)) {
                return cat;
            }
        }
        return null;
    }

    public static Category random() {
        return VALUES.get(RandomUtil.getInteger(VALUES.size()));
    }

    public static List<Category> getValues() {
        return VALUES;
    }
}
package com.crystal.pattern.factory.simple;

import com.crystal.pattern.factory.Fridge;

public interface Factory {
    Fridge produceFridge(String name);
}

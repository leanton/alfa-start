package me.antonle.alfastart.common.domain;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public enum Ccy {
    EUR("EUR"),
    USD("USD"),
    RUB("RUB");

    private final String name;

    private static Map<String, Ccy> map = new HashMap<>();

    static {
        for (Ccy ccy : Ccy.values()) {
            map.put(ccy.name, ccy);
        }
    }

    Ccy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Nullable
    public static Ccy findByName(String ccyName) {
        return map.get(ccyName);
    }
}

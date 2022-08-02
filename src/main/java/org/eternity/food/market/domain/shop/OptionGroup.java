package org.eternity.food.market.domain.shop;

import lombok.Data;
import org.eternity.food.market.domain.shop.Option;

import java.util.List;

@Data
public class OptionGroup {
    private String name;
    private List<Option> options;

    public OptionGroup(String name, List<Option> options) {
        this.name = name;
        this.options = options;
    }
}

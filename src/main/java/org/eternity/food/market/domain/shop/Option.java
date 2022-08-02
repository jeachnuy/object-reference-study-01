package org.eternity.food.market.domain.shop;

import lombok.Builder;
import lombok.Data;
import org.eternity.food.market.domain.generic.money.Money;

@Data
public class Option {
    private String name;
    private Money price;

    @Builder
    public Option(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}

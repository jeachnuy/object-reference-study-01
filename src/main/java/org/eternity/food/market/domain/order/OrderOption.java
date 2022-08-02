package org.eternity.food.market.domain.order;

import lombok.Getter;
import org.eternity.food.market.domain.generic.money.Money;
import org.eternity.food.market.domain.shop.Option;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderOption {
    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Money price;

    public OrderOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    OrderOption() {}

    public Option convertToOption() {
        return new Option(name, price);
    }
}

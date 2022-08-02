package org.eternity.food.market.domain.order;

import lombok.Getter;
import org.eternity.food.market.domain.generic.money.Money;
import org.eternity.food.market.domain.shop.OptionGroup;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@Getter
public class OrderOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OPTION_GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ElementCollection
    @CollectionTable(name = "ORDER_OPTIONS", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
    private List<OrderOption> orderOptions;

    public OrderOptionGroup(String name, List<OrderOption> options) {
        this(null, name, options);
    }

    public OrderOptionGroup(Long id, String name, List<OrderOption> options) {
        this.id = id;
        this.name = name;
        this.orderOptions = options;
    }

    OrderOptionGroup() {}

    public Money calculatePrice() {
        return Money.sum(orderOptions, OrderOption::getPrice);
    }

    public OptionGroup convertToOptionGroup() {
        return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(Collectors.toList()));
    }
}

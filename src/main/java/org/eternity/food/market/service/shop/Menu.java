package org.eternity.food.market.service.shop;

import lombok.Getter;
import org.eternity.food.market.domain.shop.Shop;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "MENUS")
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "FOOD_NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "MENU_ID")
    private Shop shop;

    private List<OptionGroupSpecification> optionGroupSpecs = new ArrayList<>();

    public Menu(Shop shop, String name, String description, OptionGroupSpecification basic, OptionGroupSpecification... groups) {
        this(null, shop, name, description, basic, Arrays.asList(groups));
    }
}

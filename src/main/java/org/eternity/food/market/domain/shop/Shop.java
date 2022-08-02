package org.eternity.food.market.domain.shop;

import lombok.Getter;
import org.eternity.food.market.domain.generic.money.Money;
import org.eternity.food.market.domain.generic.money.Ratio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SHOPS")
@Getter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OPEN")
    private boolean open;

    @Column(name = "MIN_ORDER_AMOUNT")
    private Money minOrderAmount;

    @Column(name = "COMMISSION_RATE")
    private Ratio commissionRate;

    @Column(name = "COMMISSION")
    private  Money commission = Money.ZERO;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHOP_ID")
    private List<Menu> menus = new ArrayList<>();

    public Shop(String name, boolean open, Money minOrderAmount) {
        this(name, open, minOrderAmount, Ratio.valueOf(0), Money.ZERO);
    }

    public Shop(String name, boolean open, Money minOrderAmount, Ratio commissionRate, Money commission) {
        this(null, name, open, minOrderAmount, commissionRate, commission);
    }

    public Shop(Long id, String name, boolean open, Money minOrderAmount, Ratio commissionRate, Money commission) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
        this.commission = commission;
    }

    Shop() {}

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public boolean isValidOrderAmount(Money amount) {
        return amount.isGreaterThanOrEqual(minOrderAmount);
    }

    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = false;
    }

    public void modifyCommissionRate(Ratio commissionRate) {
        this.commissionRate = commissionRate;
    }

    public void billCommissionFee(Money price) {
        commission = commission.plus(commissionRate.of(price));
    }
}

package com.xebia.common.domain;

import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Immutable
public class Order {
    public Order(Long customerId, OrderState status, Address shippingAddress, LocalDateTime created) {
        this.customerId = customerId;
        this.status = status;
        this.created = created;
        this.shippingAddress = shippingAddress;
    }

    public Order(Long customerId, OrderState status, Address shippingAddress) {
        this.customerId = customerId;
        this.status = status;
        this.created = LocalDateTime.now();
        this.shippingAddress = shippingAddress;
    }

    private Order() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Embedded
    private Address shippingAddress;

    @Column(name = "claim_id")
    private Long claimId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderState status;

    @Column(name = "created")
    private LocalDateTime created;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private final List<OrderLine> lines = new ArrayList<OrderLine>();

    public Long getId() { return id; }

    public OrderState getStatus() {
        return status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public List<OrderLine> getLines() { return lines; }

    public Long getCustomerId() { return customerId; }

    public Order withClaim(Long claimId) {
        this.claimId = claimId;
        return this;
    }

    public Order add(OrderLine orderLine) {
        lines.add(orderLine);
        return this;
    }

    public Order remove(Long orderLineId) {
        lines.removeIf(l -> l.getId() == orderLineId);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(customerId, order.customerId) &&
                status == order.status &&
                Objects.equals(created, order.created) &&
                Objects.equals(lines, order.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, status, created, lines);
    }
}

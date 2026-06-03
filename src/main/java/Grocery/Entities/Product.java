package Grocery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost_price" , nullable = false)
    private double costPrice;

    @Column(name = "sell_price" , nullable = false)
    private double sellPrice;

    @Column(name = "sku", nullable = false , unique = true)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne( cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<SaleDetails> saleDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PurchaseDetails> purchaseDetails;
}

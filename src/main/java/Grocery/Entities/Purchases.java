package Grocery.Entities;

import Grocery.Enum.PurchaseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private PurchaseStatus status;

    @Column(name = "order_date",nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount",nullable = false)
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "purchases", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PurchaseDetails> purchaseDetails;
}

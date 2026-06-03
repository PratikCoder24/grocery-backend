package Grocery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone" , nullable = false,unique = true)
    private String phone;

    @Column(name = "email" , nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Purchases> purchases = new ArrayList<>();
}

package Main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity<Long> {

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    @Column(name = "original_price")
    private Double originalPrice;

    private String category;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}

package com.projeto.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_table")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 36, nullable = false, updatable = false)
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String photo;

    public ProductEntity(String name, String description, Double price, String photo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;

    }

//    Relacionamentos
//    Produto -> categoria
    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<CategoryEntity> categories = new HashSet<>();
    //    Pedidos -> item do pedido
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItemEntity> items = new HashSet<>();
    public Set<OrderItemEntity> getItems(){
        return items;
    }
    public List<OrderEntity> getOrder(){
        return items.stream().map(x -> x.getOrder()).toList();
    }
 }

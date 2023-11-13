package com.example.experis.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(length = 500)
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @Column(length = 1000)
    @URL(message = "URL is not valid")
    @Size(max = 1000, message = "URL must be less than 1000 characters")
    private String url;

    @Column(nullable = false)
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price must be positive")
    private BigDecimal price;
}

package com.example.ex5_db_springboot.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Length(min = 10, max = 100, message = "Name must be between 10 and 100 characters")
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    private Instant createdDate;
    @Column(name = "updated_date")
    private Instant updatedDate;
    @Length(min = 6, max = 20, message = "Code must be between 6 and 20 characters")
    @Column(name = "code")
    private String code;
    @Length(min = 6, max = 20, message = "Name must be between 6 and 20")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ProductEntity> products;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

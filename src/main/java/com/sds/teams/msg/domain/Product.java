package com.sds.teams.msg.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    protected Product() {

    }

    public Product(long id, String name, String company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public Product(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(company, product.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, company);
    }
}

package com.example.core08advancedjpa.repository.querydsl;

import com.example.core08advancedjpa.entity.Product;
import com.example.core08advancedjpa.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name) {
        QProduct qProduct = QProduct.product;
        return from(qProduct)
                .select(qProduct)
                .where(qProduct.name.eq(name))
                .fetch();
    }
}

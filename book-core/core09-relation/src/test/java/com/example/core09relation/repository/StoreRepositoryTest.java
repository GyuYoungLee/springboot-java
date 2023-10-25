package com.example.core09relation.repository;

import com.example.core09relation.entity.Product;
import com.example.core09relation.entity.Store;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Test
    public void testRelation() {
        Product product1 = saveProduct("짜파게티", 500);
        Product product2 = saveProduct("신라면", 100);
        Product product3 = saveProduct("진라면", 200);

        Store store1 = saveStore("이마트");
        Store store2 = saveStore("홈플러스");

        store1.getProducts().add(product1);  // store -> product
        store1.getProducts().add(product2);
        store2.getProducts().add(product2);
        store2.getProducts().add(product3);
        storeRepository.saveAll(List.of(store1, store2));

        // 단방향
        System.out.println(storeRepository.findById(store1.getId()).get()
                .getProducts());

        product1.getStores().add(store1);  // product -> store
        product2.getStores().add(store1);
        product2.getStores().add(store2);
        product3.getStores().add(store2);
        productRepository.saveAll(List.of(product1, product2, product3));

        // 단방향
        System.out.println(productRepository.findById(product1.getId()).get()
                .getStores());
    }

    private Product saveProduct(String name, Integer price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productRepository.save(product);
    }

    private Store saveStore(String name) {
        Store store = new Store();
        store.setName(name);
        return storeRepository.save(store);
    }
}

package com.example.core09relation.repository;

import com.example.core09relation.entity.Maker;
import com.example.core09relation.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MakerRepositoryTest {
    @Autowired
    private MakerRepository makerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testRelation() {
        Maker maker = new Maker();
        maker.setName("농심");
        makerRepository.save(maker);

        Product product = new Product();
        product.setName("짜파게티");
        product.setPrice(1000);
        product.setMaker(maker);          // product -> maker
        productRepository.save(product);

        // 단방향
        System.out.println(productRepository.findById(product.getId()).get()
                .getMaker());

        // 양방향
        System.out.println(makerRepository.findById(maker.getId()).get()
                .getProducts());
    }
}

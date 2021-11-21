package br.com.tisemcensura.fullstack.services;

import br.com.tisemcensura.fullstack.entities.Product;
import br.com.tisemcensura.fullstack.exceptions.ResourceFindByIdNotFoundException;
import br.com.tisemcensura.fullstack.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj = productRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceFindByIdNotFoundException(id));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product, Long id) {
        product.setId(id);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}

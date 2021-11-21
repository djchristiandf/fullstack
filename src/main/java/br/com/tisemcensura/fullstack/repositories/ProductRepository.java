package br.com.tisemcensura.fullstack.repositories;

import br.com.tisemcensura.fullstack.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

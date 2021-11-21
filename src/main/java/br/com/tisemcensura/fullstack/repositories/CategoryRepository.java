package br.com.tisemcensura.fullstack.repositories;

import br.com.tisemcensura.fullstack.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

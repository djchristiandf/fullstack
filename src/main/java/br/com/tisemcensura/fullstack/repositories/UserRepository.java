package br.com.tisemcensura.fullstack.repositories;

import br.com.tisemcensura.fullstack.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}

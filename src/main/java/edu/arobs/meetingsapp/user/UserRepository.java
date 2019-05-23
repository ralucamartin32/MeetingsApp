package edu.arobs.meetingsapp.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmailAndPassword(String emil, String password);
}

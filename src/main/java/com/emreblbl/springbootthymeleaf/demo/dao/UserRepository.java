package com.emreblbl.springbootthymeleaf.demo.dao;

import com.emreblbl.springbootthymeleaf.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    // add a method to sort by last Name
    public List<User> findAllByOrderByLastNameAsc(); // it's created automatically by JPA and find ALL from User order by LastName asc.
    @Query("select  u from User u Where u.lastName like %:surname%")
    public List<User> findByLastName(@Param("surname") String surname);
    public List<User> findByLastNameContainingIgnoreCase(String surname);
    @Query("SELECT u FROM User u WHERE u.firstName LIKE ?1"
            + " OR u.lastName LIKE ?1"
            + " OR u.email LIKE ?1")
    public List<User> search(String keyword);
}

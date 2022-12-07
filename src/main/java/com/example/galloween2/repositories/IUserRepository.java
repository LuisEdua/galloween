package com.example.galloween2.repositories;

import com.example.galloween2.entities.User;
import com.example.galloween2.entities.projections.UserProjection;
import com.example.galloween2.entities.projections.UserReservationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT users.id AS userId, users.age AS userAge, users.cellphone AS userCellphone, "+
    "users.full_name AS userFullName, users.email AS userEmail, users.password AS userPassword, roles.role AS userRole FROM users " +
    "INNER JOIN roles ON users.role_id=roles.id " +
    "WHERE users.email = :email AND users.password = :password ;", nativeQuery = true)
    UserProjection findUserByEmailAndPassword(String email, String password);

    Optional<User> findOneByEmail(String email);
}

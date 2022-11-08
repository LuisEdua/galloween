package com.example.galloween2.repositories;

import com.example.galloween2.entities.User;
import com.example.galloween2.entities.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value = "select users.id as userId, users.age as userAge, users.cellphone as userCellphone, "+
    "users.full_name as userFullName, roles.role as userRole from users " +
    "inner join roles on users.role_id=roles.id " +
    "where users.email = :email and users.password = :password ;", nativeQuery = true)
    UserProjection findUserByEmailAndPassword(String email, String password);
}

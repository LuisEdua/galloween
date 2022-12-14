package com.example.galloween2.repositories;

import com.example.galloween2.entities.Comment;
import com.example.galloween2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {


}

package com.lamiskid.OAuth2project.repository;


import com.lamiskid.OAuth2project.model.ERole;
import com.lamiskid.OAuth2project.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
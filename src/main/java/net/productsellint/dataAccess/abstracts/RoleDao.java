package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}

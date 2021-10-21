package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.AmountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountTypeDao extends JpaRepository<AmountTypeEntity, Integer> {
    AmountTypeEntity getByType(String type);
    AmountTypeEntity getById(Integer id);
}

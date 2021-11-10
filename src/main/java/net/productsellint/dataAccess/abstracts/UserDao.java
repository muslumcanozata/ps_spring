package net.productsellint.dataAccess.abstracts;

import net.productsellint.entities.concretes.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    @Modifying
    @Query("update UserEntity u set u.entityStatus = 1 where u.id = :id")
    void delete(@Param("id") Integer id);

    @Modifying
    @Query("update UserEntity u set u.entityStatus = 0 where u.id = :id")
    void activate(@Param("id") Integer id);

    @Modifying
    @Query("update UserEntity u set u.entityStatus = 2 where u.id = :id")
    void disable(@Param("id") Integer id);
}

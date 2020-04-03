package com.likefood.domain.user.repository;

import com.likefood.domain.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(
         value = "select * from t_role where id in (  select role_id from t_user_role where user_id = :userid )",
         nativeQuery = true
    )
    List<Role> findByUserid(@Param("userid")Long userid);

    List<Role> findByIdNotNullOrderById();

    Role findByIdAndIdNotNull(@Param("id") Long id);
}

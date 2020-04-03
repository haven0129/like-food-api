package com.likefood.domain.user.repository;

import com.likefood.domain.user.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query(
            value = "select * from t_permission where id in (select permission_id from t_role_permission where role_id = :roleid) ",
            nativeQuery = true
    )
    List<Permission> findByRoleId(@Param("roleid")Long roleid);

    @Query(value = "select p.* from t_user_role ur " +
            "        left join t_role r on ur.role_id = r.id " +
            "        right join t_role_permission rp on rp.role_id=r.id  " +
            "        left join t_permission p on p.id = rp.permission_id " +
            "        where  ur.user_id = :userid", nativeQuery = true)
    List<Permission> findPermissionByUserId(@Param("userid") Long userId);
}

package com.likefood.domain.user.repository;

import com.likefood.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndStatus(@Param("username")String username, @Param("status")Long status);
    User findByIdAndIdNotNull(@Param("id")Long id);
    User findByIdAndStatus(@Param("id")Long id, @Param("status")Long status);
    @Query(
            value = "select * from t_user where status = 0 and id in(select user_id from t_user_role where role_id = :roleid) " +
                    "order by createtime \n-- #pageable\n",
            countQuery = "select * from t_user where status = 0 and id in(select user_id from t_user_role where role_id = :roleid) ",
            nativeQuery = true
    )
    Page<User> findByRoleIdOrderByCreateTime(@Param("roleid")Long roleid, Pageable pageable);

    @Query(
            value = "select * from t_user where status = 999 and id in(select user_id from t_user_role where role_id = :roleid) " +
                    "order by updatetime desc \n-- #pageable\n",
            countQuery = "select * from t_user where status = 999 and id in(select user_id from t_user_role where role_id = :roleid) ",
            nativeQuery = true
    )
    Page<User> findDelByRoleIdOrderByUpdateTimeDesc(@Param("roleid")Long roleid, Pageable pageable);

    Page<User> findAll(Specification<User> spec, Pageable pageable);
}

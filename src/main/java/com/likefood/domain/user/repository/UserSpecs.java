package com.likefood.domain.user.repository;

import com.likefood.domain.user.model.User;
import com.likefood.domain.user.model.UserRole;
import com.likefood.pojo.user.UserDtoS;
import com.likefood.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserSpecs {
    public static Specification<User> setQuery(final String searchName) {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                String key = "%" + searchName + "%";

                Path<String> path1 = root.get("username");
                Path<String> path2 = root.get("mobile");
                Path<String> path3 = root.get("email");

                Predicate predicate = builder.or(builder.like(path1, key), builder.like(path2, key),builder.like(path3, key));

                if (predicate == null) {
                    query.where();
                } else {
                    query.where(predicate);
                }
                return predicate;
            }
        };
    }

    public static Specification<User> setQuery(UserDtoS userDtoS) {
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final  String name = userDtoS.getName();
                final  String username = userDtoS.getUsername();
                final  Long roleId = userDtoS.getRoleId();
                final  Long status = userDtoS.getStatus();

                List<Predicate> predicates = new ArrayList<Predicate>();
                if (StringUtils.isNotEmptyOrNull(name)) {
                    predicates.add(cb.like(root.<String>get("name"), "%" + name + "%"));
                }
                if (StringUtils.isNotEmptyOrNull(username)) {
                    predicates.add(cb.like(root.<String>get("username"), "%" + username + "%"));
                }
                if (status != null) {
                    predicates.add(cb.equal(root.<Long>get("status"), status));
                }
                if (roleId != null) {
                    Join<User, UserRole> join = root.join("roleList", JoinType.LEFT);
//                    predicates.add(cb.equal(root.join("roleList").<Long>get("role_id"), roleId));
                    predicates.add(cb.equal(join.<Long>get("id"), roleId));
                }

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }
}

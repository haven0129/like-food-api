package com.likefood.security;

public interface SecurityService {
    boolean authorized(Long userId, Object targetDomainObject, Object permission);
}

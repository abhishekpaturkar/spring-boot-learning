package com.abhishek.module5.utils;

import com.abhishek.module5.entities.enums.Permissions;
import com.abhishek.module5.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionMapping {

    private static final Map<Role, Set<Permissions>> map = Map.of(
            Role.USER, Set.of(Permissions.USER_VIEW, Permissions.POST_VIEW),
            Role.CREATOR, Set.of(Permissions.POST_CREATE, Permissions.POST_UPDATE, Permissions.USER_UPDATE),
            Role.ADMIN, Set.of(
                    Permissions.POST_CREATE, Permissions.POST_UPDATE, Permissions.USER_UPDATE,
                    Permissions.USER_DELETE, Permissions.USER_CREATE, Permissions.POST_DELETE
            )
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}

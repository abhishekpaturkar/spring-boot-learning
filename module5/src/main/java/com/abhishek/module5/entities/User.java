package com.abhishek.module5.entities;

import com.abhishek.module5.entities.enums.Permissions;
import com.abhishek.module5.entities.enums.Role;
import com.abhishek.module5.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
UserDetails is an interface that represents core user information required for authentication
and authorization, while UserDetailsService is the interface responsible for loading
user-specific data (typically from a database) to support that process.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;

    // Single role
    // private Role role;

    // We want roles everytime we fetch user, so we use EAGER fetching strategy
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    // Authorities are roles like ADMIN, USER
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            Set<SimpleGrantedAuthority> permissions = PermissionMapping.getAuthoritiesForRole(role);
            authorities.addAll(permissions);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}

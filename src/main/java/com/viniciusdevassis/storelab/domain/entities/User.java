package com.viniciusdevassis.storelab.domain.entities;

import com.viniciusdevassis.storelab.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class User implements UserDetails {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private UUID storeId;
    private String name;
    private String email;
    private String password;
    private OffsetDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User newUser(String name, String email, String password) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(OffsetDateTime.now());
        user.setRole(Role.USER);
        return user;
    }

    public static User newOwner(UUID storeId, String name, String email, String password) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setStoreId(storeId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(OffsetDateTime.now());
        user.setRole(Role.OWNER);
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

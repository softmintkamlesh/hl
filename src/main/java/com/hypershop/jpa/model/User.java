package com.hypershop.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypershop.constants.EntityPrefixes;
import com.hypershop.constants.Role;
import com.hypershop.utils.GeneratorUtil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 10)
    private String mobile;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant  updatedAt;

    @PrePersist
    protected void onCreate() {
        if (userId == null || userId.isEmpty()) {
            userId = GeneratorUtil.ulidWithPrefix(EntityPrefixes.USER);
        }
        createdAt = Instant .now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant .now();
    }

    // UserDetails implementation
    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }


}
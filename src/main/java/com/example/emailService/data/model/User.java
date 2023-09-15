package com.example.emailService.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(unique = true, nullable= false)
    private String email;
   private String password;
    private boolean isEnabled;
    private long numberOfLinks;
    private boolean loggedIn;
    private long numberOfFriends;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Links> listOfLinks = new ArrayList<Links>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private final List<FriendsConnection> listOfFriends = new ArrayList<FriendsConnection>();
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    @ElementCollection
//    @CollectionTable(name = "user_links", joinColumns = @JoinColumn(name = "user_id"))
//    @MapKeyColumn(name = "link_key")
//    @Column(name = "link_value")
//    private Map<String, Long> links = new HashMap<>();



}

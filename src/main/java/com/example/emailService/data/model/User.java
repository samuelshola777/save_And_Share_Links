package com.example.emailService.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
//    @ElementCollection
//    @CollectionTable(name = "user_links", joinColumns = @JoinColumn(name = "user_id"))
//    @MapKeyColumn(name = "link_key")
//    @Column(name = "link_value")
//    private Map<String, Long> links = new HashMap<>();



}

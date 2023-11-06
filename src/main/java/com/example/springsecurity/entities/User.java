package com.example.springsecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    @Column(unique = true)
    private String email;

    private String lastName;

    private String firstName;

    @JsonIgnore
    private String password;

    private boolean nonLockAccount;

    @JsonIgnore
    private int numberAttempt;

    private boolean isAdmin;

    private Boolean status;

    @Column(name = "lock_time")
    private Date lockTime;
    @Column(name = "lock_time_duration")
    private int lockTimeDuration;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

}

package com.nocountry.wallet.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;


    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Table(name = "user")
    @SQLDelete(sql = "UPDATE user SET soft_delete = true where id=?")
    @Where(clause = "soft_delete=false")
    @Embeddable
    public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true)
        private Long id;
        @Column(nullable = false, name = "first_name")
        private String firstName;

        @Column(nullable = false, name = "last_name")
        private String lastName;

        @Column(unique = true, nullable = false, name = "email")
        private String email;

        @Column(unique = true, nullable = false, name = "verication")
        private Boolean verify;
        @Column(unique = true, nullable = false, name = "dni")
        private String dni;

        @Column(nullable = false, name = "password")
        private String password;

        @Column(name = "photo")
        private String photo;
        // un string vacio cuando no se carga.

        @Column(name = "timestamp", nullable = false)
        @CreationTimestamp
        private Timestamp timestamp;

        @Column(name = "soft_delete", nullable = false)
        private Boolean softDelete = Boolean.FALSE;

        @OneToMany(mappedBy = "user")
        private List<AccountEntity> accounts;
        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
        @JoinTable(name = "user_role",
                joinColumns = {@JoinColumn(name = "id_user")},
                inverseJoinColumns = {@JoinColumn(name = "role_id")})
        private Set<RoleEntity> roles;


        public UserEntity(String firstName, String lastName, String email, String dni, String password, String photo,
                          Timestamp timestamp, Set<RoleEntity> roleEntity, List<AccountEntity> accounts) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.dni = dni;
            this.password = password;
            this.photo = photo;
            this.timestamp = timestamp;
            this.roles = roleEntity;
            this.accounts = accounts;
        }
    }


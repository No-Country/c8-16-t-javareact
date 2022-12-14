package com.nocountry.wallet.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
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

        //Verify correo que le mande digitos. Una ruta para el correo. Generacion de los 6 digitos.

        @Column(unique = true, nullable = false, name = "dni")
        private String dni;

        @Column(nullable = false, name = "password")
        private String password;

        @Column(name = "photo")
        private String photo;
        // un string vacio cuando no se carga.
        // Cargar la imagen directamente. Form data
        @Column(name = "creation_date", nullable = false)
        @CreationTimestamp
        private Timestamp creationDate;

        @Column(name = "soft_delete", nullable = false)
        private Boolean softDelete = Boolean.FALSE;
/*
        @Column(name= "birth_date", nullable = false)
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        private LocalDate birthDate;
*/
        @Column(name= "birth_date", nullable = false)
        private String birthDate;


        @Column(name = "verify")
        private Boolean verify = Boolean.FALSE;

        @OneToMany(mappedBy = "user")
        private List<AccountEntity> accounts;
        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
        @JoinTable(name = "user_role",
                joinColumns = {@JoinColumn(name = "id_user")},
                inverseJoinColumns = {@JoinColumn(name = "role_id")})
        private Set<RoleEntity> roles;


        public UserEntity(String firstName, String lastName, String email, String dni, String password, String photo,
                          Timestamp creationDate, Set<RoleEntity> roleEntity, List<AccountEntity> accounts, String birthDate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.dni = dni;
            this.password = password;
            this.photo = photo;
            this.creationDate = creationDate;
            this.roles = roleEntity;
            this.birthDate=  birthDate;
            this.accounts = accounts;
        }
    }


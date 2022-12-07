package com.nocountry.wallet.models.response;

import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.entity.RoleEntity;
import com.nocountry.wallet.models.request.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Collection;


@Data@AllArgsConstructor@NoArgsConstructor
public class UserDetailDTO {

    String firstName;
    String lastName;
    String email;
    Integer dni;
    String password;
    String photo;
    String birthDate;
    Collection<RoleEntity> roles;
    Collection<AccountWithoutUserDTO> accounts;

}

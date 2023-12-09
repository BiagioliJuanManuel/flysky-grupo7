package com.grupo2.flysky.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSaveDto {
    private String userName;
    private String password;
    private String email;
    private LocalDate birthdate;
    private Long phoneNumber;
    private Long identityNumber;

}

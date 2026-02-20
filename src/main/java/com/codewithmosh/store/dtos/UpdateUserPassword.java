package com.codewithmosh.store.dtos;

import lombok.Data;

@Data
public class UpdateUserPassword {
    private String oldPassword;
    private String newPassword;
}

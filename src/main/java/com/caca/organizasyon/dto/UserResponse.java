package com.caca.organizasyon.dto;

import com.caca.organizasyon.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String username;

    private Role role;

    private boolean approved;
}

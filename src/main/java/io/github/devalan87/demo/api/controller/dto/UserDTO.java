package io.github.devalan87.demo.api.controller.dto;

import io.github.devalan87.demo.domain.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserDTO {

    private Integer id;
    private String username;
    private String email;
    private UserType type;
    private boolean enabled;

}

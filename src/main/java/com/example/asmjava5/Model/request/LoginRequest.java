package com.example.asmjava5.Model.request;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull(message="Usernamerỗng")
    @NotEmpty(message="Username rỗng")
    private String username;

    @NotNull(message = "Mật khẩu rỗng")
    @NotEmpty(message = "Mật khẩu rỗng")
    private String password;
}

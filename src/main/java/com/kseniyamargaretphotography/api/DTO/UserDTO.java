package com.kseniyamargaretphotography.api.DTO;

import com.kseniyamargaretphotography.api.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;


@Data
@NoArgsConstructor
public class UserDTO {

    @Column(name = "first_name")
    @Size(min = 3, max = 50, message = "First Name length is invalid")
    @NotBlank(message = "{default.value.required}")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3, max = 50, message = "First Name length is invalid")
    @NotBlank(message = "{default.value.required}")
    private String lastName;

    @Size(min = 3, max = 50)
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "{default.value.required}")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{default.value.invalid}")
    private String email;

    @NotBlank(message = "{default.value.required}")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "{default.value.invalid}")
    private String userName;

    @NotBlank(message = "{default.value.required}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "{default.value.invalid}")
    private String password;

    private Collection<@NotNull @NotEmpty String> roles = new HashSet<String>() {{
        add(UserRole.USER.toString());
    }};
}

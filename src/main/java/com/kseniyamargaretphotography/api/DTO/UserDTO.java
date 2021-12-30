package com.kseniyamargaretphotography.api.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Size(min = 3, max = 50, message = "{default.invalid.length}")
    @NotBlank(message = "{default.value.required}")
    private String lastName;

    @Size(min = 3, max = 50)
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "{default.value.required}")
    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "{default.value.invalid}")
    private String email;

    @NotBlank(message = "{default.value.required}")
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "{default.value.invalid}")
    private String userName;

    @NotBlank(message = "{default.value.required}")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "{password.invalid}")
    private String password;

    private Collection<@NotNull @NotEmpty String> roles = new HashSet<String>() {{
        add(UserRole.USER.toString());
    }};
}

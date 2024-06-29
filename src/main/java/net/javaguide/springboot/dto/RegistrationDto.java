package net.javaguide.springboot.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Please enter your first Name")
    private String firstName;
    @NotEmpty(message = "Please enter your last Name")

    private String lastName;

    @NotEmpty(message = "Please enter your email")
    private String email;
    @NotEmpty(message = "Please enter your password")
    private String password;
}

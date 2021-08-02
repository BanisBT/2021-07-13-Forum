package lt.tomasbarauskas.blog.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
public class UserRegistrationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.not.empty.user}")
    private String username;

    @NotBlank(message = "{validation.not.empty.user}")
    @Size(min = 5, message = "{validation.size.user.password}")
    private String password;

    @NotBlank(message = "{validation.not.empty.user}")
    private String passwordConfirm;

    private String name;

    private String surname;

    @Email(message = "{validation.email.user.email}")
    private String email;

    @Min(value = 18, message = "{validation.min.user.age}")
    @Max(value = 125, message = "{validation.max.user.age}")
    private Integer age;

    private String fullName;
}

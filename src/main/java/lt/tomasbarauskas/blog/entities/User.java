package lt.tomasbarauskas.blog.entities;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user_table")
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "username")
    private String username;

    @NotEmpty
    @Size(min = 5)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @Email
    @Column(name = "email")
    private String email;

    @Min(value = 18, message = "Age must be more then {}")
    @Max(125)
    @Column(name = "age")
    private Integer age;

    private Set<Role> roles;

//    @OneToMany(mappedBy = "user")
    @Transient
    private List<Comment> comments;

    //@Column(name = "created_at")
    @Transient
    private LocalDateTime createdAt;

    //@Column(name = "updated_at")
    @Transient
    private LocalDateTime updatedAt;

    @Tolerate
    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package pl.flez.reactapi.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;

@Document
@Getter
@Setter
public class User  extends Auditable implements UserDetails {

    @Id
    private ObjectId id;
    @NotBlank
    @Indexed(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8,max = 70)
    private String password;
   // @Size(min = 5,max = 40)
     @Indexed(unique = true)
    private String email;

    @JsonIgnore
    private String accountActivationHash;

    private AdditionalUserData userData;

    @NotNull
    private boolean accountNonExpired;
    @NotNull
    private boolean accountNonLocked;
    @NotNull
    private boolean credentialsNonExpired;
    @NotNull
    private boolean enabled;
    private HashSet<Role> authorities;
    private LocalDateTime lastLoginTime;
    private  UserType type;

}

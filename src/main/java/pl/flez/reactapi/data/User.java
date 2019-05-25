package pl.flez.reactapi.data;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;

@Document
@Getter
@Setter
public class User  extends Auditable {

    @Id
    private ObjectId id;
    //@Indexed(unique = true)
   // @NotBlank
    //@Size(min = 3,max = 20)
    private String username;
   // @Size(min = 8,max = 70)
    private String password;
   // @Size(min = 5,max = 40)
    private String email;

    private String accountActivationHash;

    @NotNull
    private boolean accountNonExpired;
    @NotNull
    private boolean accountNonLocked;
    @NotNull
    private boolean credentialsNonExpired;
    @NotNull
    private boolean enabled;
    private HashSet<String> authorities;
    private LocalDateTime lastLoginTime;

}

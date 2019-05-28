package pl.flez.reactapi.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AdditionalUserData {
    private String fullName;
    private String familyName;
    private String givenName;
    private String picture;
    private String locale;
}

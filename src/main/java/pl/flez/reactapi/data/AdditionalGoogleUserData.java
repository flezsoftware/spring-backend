package pl.flez.reactapi.data;

import lombok.*;


@Getter
@Setter
public class AdditionalGoogleUserData extends  AdditionalUserData {
    private String iss;
    private String azp;
    private String subject;
}

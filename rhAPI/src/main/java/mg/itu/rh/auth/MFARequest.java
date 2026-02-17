package mg.itu.rh.auth;

import lombok.Data;

@Data
public class MFARequest {
    private String email;
    private int code;
}

package mg.itu.rh.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MFARequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    private int code;

    private String scratchCode;
}

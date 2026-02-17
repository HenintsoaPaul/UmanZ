package mg.itu.rh.auth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorService {
    private final GoogleAuthenticator gAuth;

    public TwoFactorService() {
        this.gAuth = new GoogleAuthenticator();
    }

    public String generateNewSecret() {
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getSecret();
    }

    public String generateQrCodeUri(String secret, String email) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("UmanZ", email,
                new GoogleAuthenticatorKey.Builder(secret).build());
    }

    public boolean verifyCode(String secret, int code) {
        return gAuth.authorize(secret, code);
    }
}

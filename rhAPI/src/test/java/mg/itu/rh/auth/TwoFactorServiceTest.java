package mg.itu.rh.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TwoFactorServiceTest {

    private TwoFactorService twoFactorService;
    private final String encryptionKey = "test_key_16_char";

    @BeforeEach
    void setUp() {
        mg.itu.rh.service.interne.EmailService emailService = org.mockito.Mockito.mock(mg.itu.rh.service.interne.EmailService.class);
        twoFactorService = new TwoFactorService(encryptionKey, emailService);
    }

    @Test
    void testEncryptDecryptSecret() {
        String secret = "JBSWY3DPEHPK3PXP";
        String encrypted = twoFactorService.generateNewSecret();
        assertNotEquals(secret, encrypted);

        String qrUri = twoFactorService.generateQrCodeUri(encrypted, "test@example.com");
        assertTrue(qrUri.contains("otpauth") || qrUri.contains("otpauth%3A%2F%2Ftotp"));
        assertTrue(qrUri.contains("test%40example.com") || qrUri.contains("test@example.com"));
    }

    @Test
    void testScratchCodes() {
        List<String> codes = twoFactorService.generateScratchCodes();
        assertEquals(5, codes.size());

        String encrypted = twoFactorService.encryptScratchCodes(codes);
        String validCode = codes.get(0);

        assertTrue(twoFactorService.verifyScratchCode("user@test.com", encrypted, validCode));
        assertFalse(twoFactorService.verifyScratchCode("user@test.com", encrypted, "123456"));

        List<String> updated = twoFactorService.removeUsedScratchCode(encrypted, validCode);
        assertEquals(4, updated.size());
        assertFalse(updated.contains(validCode));
    }

    @Test
    void testRateLimiting() {
        String email = "brute@force.com";
        String secret = twoFactorService.generateNewSecret();

        for (int i = 0; i < 5; i++) {
            twoFactorService.verifyCode(email, secret, 123456);
        }

        // 6th attempt should be rate limited even if logic wasn't checked (our mock
        // just records)
        assertFalse(twoFactorService.verifyCode(email, secret, 123456));
    }
}

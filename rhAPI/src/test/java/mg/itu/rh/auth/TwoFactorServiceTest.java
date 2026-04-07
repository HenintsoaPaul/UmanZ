package mg.itu.rh.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mg.itu.rh.auth.service.TwoFactorService;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TwoFactorServiceTest {

    private TwoFactorService twoFactorService;
    private final String encryptionKey = "test_key_16_char";

    @BeforeEach
    void setUp() {
        twoFactorService = new TwoFactorService(encryptionKey);
    }

    @Test
    void testDirectEncryptionDecryption() {
        String data = "some-arbitrary-data";
        String encrypted = twoFactorService.encryptScratchCodes(List.of(data));
        String decrypted = twoFactorService.decrypt(encrypted);
        assertEquals(data, decrypted);
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
    void testScratchCodesEdgeCases() {
        assertFalse(twoFactorService.verifyScratchCode("user@test.com", null, "123456"));
        assertFalse(twoFactorService.verifyScratchCode("user@test.com", "", "123456"));
    }

    @Test
    void testEmailCode() {
        String email = "user@test.com";
        String code = twoFactorService.generateEmailCode();
        assertNotNull(code);
        assertEquals(6, code.length());

        // Test verifyEmailCode using a code encrypted via encryptScratchCodes
        // (which uses the same underlying encryption logic)
        List<String> codes = Arrays.asList(code);
        String encrypted = twoFactorService.encryptScratchCodes(codes);

        assertTrue(twoFactorService.verifyEmailCode(email, encrypted, code));
        assertFalse(twoFactorService.verifyEmailCode(email, encrypted, "wrong"));
    }

    @Test
    void testEmailCodeNull() {
        assertFalse(twoFactorService.verifyEmailCode("user@test.com", null, "123456"));
    }

    @Test
    void testLastScratchCodeRemoved() {
        List<String> codes = List.of("123456");
        String encrypted = twoFactorService.encryptScratchCodes(codes);

        List<String> updated = twoFactorService.removeUsedScratchCode(encrypted, "123456");
        assertTrue(updated.isEmpty());

        String encryptedEmpty = twoFactorService.encryptScratchCodes(updated);
        assertFalse(twoFactorService.verifyScratchCode("user@test.com", encryptedEmpty, "123456"));
    }

    @Test
    void testRateLimitingReset() {
        String email = "user@test.com";
        List<String> codes = twoFactorService.generateScratchCodes();
        String encrypted = twoFactorService.encryptScratchCodes(codes);
        String validCode = codes.get(0);

        // 4 failed attempts
        for (int i = 0; i < 4; i++) {
            twoFactorService.verifyScratchCode(email, encrypted, "wrong");
        }

        // Successful verification resets attempts
        assertTrue(twoFactorService.verifyScratchCode(email, encrypted, validCode));

        // 4 more failed attempts shouldn't block
        for (int i = 0; i < 4; i++) {
            assertFalse(twoFactorService.verifyScratchCode(email, encrypted, "wrong"));
        }

        // 5th failed attempt blocks
        assertFalse(twoFactorService.verifyScratchCode(email, encrypted, "wrong"));
        // 6th attempt is blocked
        assertFalse(twoFactorService.verifyScratchCode(email, encrypted, validCode));
    }
}

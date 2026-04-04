package mg.itu.rh.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TwoFactorService {
    private final String encryptionKey;
    private final Map<String, Integer> attempts = new ConcurrentHashMap<>();
    private static final int MAX_ATTEMPTS = 5;

    public TwoFactorService(@Value("${umanz.mfa.encryption.key}") String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public String generateEmailCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public boolean verifyEmailCode(String email, String encryptedStoredCode, String userInputCode) {
        if (isRateLimited(email)) {
            return false;
        }

        if (encryptedStoredCode == null) {
            return false;
        }

        String storedCode = decrypt(encryptedStoredCode);
        boolean isValid = storedCode.equals(userInputCode);

        if (isValid) {
            resetAttempts(email);
        } else {
            recordAttempt(email);
        }

        return isValid;
    }

    public List<String> generateScratchCodes() {
        SecureRandom random = new SecureRandom();
        return random.ints(5, 100000, 1000000)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }

    public boolean verifyScratchCode(String email, String encryptedScratchCodes, String code) {
        if (isRateLimited(email)) {
            return false;
        }

        if (encryptedScratchCodes == null || encryptedScratchCodes.isEmpty()) {
            return false;
        }

        List<String> codes = Arrays.asList(decrypt(encryptedScratchCodes).split(","));
        if (codes.contains(code)) {
            resetAttempts(email);
            return true;
        }

        recordAttempt(email);
        return false;
    }

    public String encryptScratchCodes(List<String> codes) {
        return encrypt(String.join(",", codes));
    }

    public List<String> removeUsedScratchCode(String encryptedScratchCodes, String code) {
        List<String> codes = new ArrayList<>(Arrays.asList(decrypt(encryptedScratchCodes).split(",")));
        codes.remove(code);
        return codes;
    }

    private boolean isRateLimited(String email) {
        return attempts.getOrDefault(email, 0) >= MAX_ATTEMPTS;
    }

    // --- ATTEMPTS ---

    private void recordAttempt(String email) {
        attempts.merge(email, 1, Integer::sum);
    }

    private void resetAttempts(String email) {
        attempts.remove(email);
    }

    // --- ENCRYPTION ---

    private String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}

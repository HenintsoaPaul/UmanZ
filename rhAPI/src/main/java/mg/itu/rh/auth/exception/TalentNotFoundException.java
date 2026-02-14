package mg.itu.rh.auth.exception;

public class TalentNotFoundException extends Exception {
    public TalentNotFoundException() {
        super("Talent not found");
    }
}

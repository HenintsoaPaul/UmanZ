package mg.itu.rh.exception.interne;

public class CongeException extends Exception {
    long monthDiff = -1;

    public CongeException( long monthDiff ) {
        this.monthDiff = monthDiff;
    }

    public CongeException( String message ) {
        super( message );
    }

    @Override
    public String getMessage() {
        if ( this.monthDiff != -1 ) {
            return "Il faut avoir fait au moins 12 mois de service pour demander des conges. " +
                    "Vous avez fait " + this.monthDiff + " mois de service.";
        }
        return super.getMessage();
    }
}

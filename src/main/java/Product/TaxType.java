package Product;

public enum TaxType {
    FREE,
    NORMAL,
    LUXURY;

    public String toString() {
        return switch (this) {
            case FREE -> "Free";
            case NORMAL -> "Normal";
            case LUXURY -> "Luxury";
        };
    }

    public float getPercentage() {
        return switch (this) {
            case FREE -> 0;
            case NORMAL -> (float) 0.1;
            case LUXURY -> (float) 0.2;
        };
    }


}

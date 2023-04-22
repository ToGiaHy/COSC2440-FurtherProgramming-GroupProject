package Product;

public enum TaxType {
    // Tax type values
    FREE,
    NORMAL,
    LUXURY;

    // Override toString() for the display
    public String toString() {
        return switch (this) {
            case FREE -> "Free";
            case NORMAL -> "Normal";
            case LUXURY -> "Luxury";
        };
    }

    /**
     * Get the tax percentage based on the tax type
     *
     * @return a float value representing the percentage
     */

    public float getPercentage() {
        return switch (this) {
            case FREE -> 0;
            case NORMAL -> (float) 0.1;
            case LUXURY -> (float) 0.2;
        };
    }

    /**
     * Get the TaxType based on the input
     *
     * @param tax the user input of the tax
     * @return a value from TaxType
     */
    public static TaxType getType(String tax) {
        return switch (tax.toLowerCase()) {
            case "free" -> FREE;
            case "normal" -> NORMAL;
            case "luxury" -> LUXURY;
        };
    }


}

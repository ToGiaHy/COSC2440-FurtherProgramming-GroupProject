package Product;

public class Coupon {
    //attributes
    private String type;
    private String name;
    private double value;
    //constructors

    public Coupon(String type, String name, double value) {
        this.type = checkType(type);
        this.name = name;
        this.value = checkValue(value);
    }
//getters
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public double getValue() {
        return value;
    }

    //setters
    public void setType(String type) {
        this.type = checkType(type);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(double value) {
        this.value = checkValue(value);
    }
    //validation method for the type of coupon
    public static String checkType(String type){
        if(type.equalsIgnoreCase("price") || type.equalsIgnoreCase("percent")){
            return type;
        }
        else{
            System.out.println("Type can only be price or percent");
        }
        return null;
    }
    //validation method for the value of coupon
    public double checkValue(double value){
        if(this.type.equalsIgnoreCase("percent") && value <= 1){
            return value;
        } else if (this.type.equalsIgnoreCase("price") && value > 0 ) {
            return value;
        }
        return 1;
    }
}

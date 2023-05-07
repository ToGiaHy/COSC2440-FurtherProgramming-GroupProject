/**
 * @author Group 11
 */
package io;

import utils.Coupon;
import utils.PercentCoupon;
import utils.PriceCoupon;
import utils.TaxType;
import product.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class ReadProductsFile implements FileActions {
    ProductController productController;

    /**
     * Read data from the product.txt file to PRODUCTS
     */
    public ReadProductsFile(ProductController productController) {
        this.productController = productController;
    }

    /**
     * Read each line as an instance of Product
     * Create products based on types then added into the product controller hashmap
     */
    public void read() {
        // Read from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/data/products.txt"));
            Product product;
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    String type = tokenizer.nextToken().toUpperCase();
                    String name = tokenizer.nextToken();
                    String description = tokenizer.nextToken();
                    int quantityAvailable = Integer.parseInt(tokenizer.nextToken());
                    double price = Double.parseDouble(tokenizer.nextToken());
                    String taxTypeStr = tokenizer.nextToken();
                    String coupons = tokenizer.nextToken().trim();
                    // Convert the strings to related types
                    TaxType taxType = TaxType.getType(taxTypeStr);
                    // Map from the file store the coupon's name and its value
                    coupons = coupons.substring(1, coupons.length() - 1);
                    Coupon coupon;
                    Map<String, Coupon> couponsMap = new HashMap<>();
                    if(coupons.length() > 2){
                        Map<String, String> couponValues = Arrays.stream(coupons.split(";"))
                                .map(entry -> entry.split("="))
                                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
                        // Create CouponList for Products
                        for (Map.Entry<String, String> entry : couponValues.entrySet()) {
                            try {
                                double value = Double.parseDouble(entry.getValue());
                                coupon = new PriceCoupon(entry.getKey(), value);
                            } catch (Exception e) {
                                coupon = new PercentCoupon(entry.getKey(), Integer.parseInt(entry.getValue()));
                            }
                            couponsMap.put(coupon.getCouponCode(), coupon);
                        }
                    }
                    switch (type) {
                        case "DIGITALPRODUCT" -> product =
                                new DigitalProduct(
                                        name,
                                        description,
                                        quantityAvailable,
                                        price,
                                        taxType,
                                        couponsMap
                                );
                        case "PHYSICALPRODUCT" -> {
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            product = new PhysicalProduct(
                                    name,
                                    description,
                                    quantityAvailable,
                                    price,
                                    taxType,
                                    weight
                            );
                        }
                        case "GIFTDIGITALPRODUCT" -> {
                            String message = tokenizer.nextToken();
                            product =
                                    new DigitalProductCanBeGifted(
                                            name,
                                            description,
                                            quantityAvailable,
                                            price,
                                            taxType,
                                            message
                                    );
                        }
                        case "GIFTPHYSICALPRODUCT" -> {
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            String message = tokenizer.nextToken();
                            product =
                                    new PhysicalProductCanBeGifted(
                                            name,
                                            description,
                                            quantityAvailable,
                                            price,
                                            taxType,
                                            weight,
                                            couponsMap,
                                            message
                                    );
                        }
                        default -> throw new IOException("Invalid product type: " + type);
                    }
                    productController.productList().put(product.getName(), product);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }
}

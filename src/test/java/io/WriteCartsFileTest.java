package io;

import Product.DigitalProduct;
import Product.PhysicalProduct;
import Product.PhysicalProductCanBeGifted;
import Product.DigitalProductCanBeGifted;
import Product.TaxType;
import ShoppingCart.ShoppingCart;
import Product.ProductManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class WriteCartsFileTest {
    public final ArrayList<ShoppingCart> testCarts = new ArrayList<>();


    @BeforeEach
    void setUp() {
        ProductManager.addProduct(new DigitalProduct("p1", "testing", 10, 2, TaxType.NORMAL));
        ProductManager.addProduct(new PhysicalProduct("p2", "testing", 15, 2, TaxType.LUXURY,12));
        ProductManager.addProduct(new PhysicalProductCanBeGifted("p3", "testing", 9, 2, TaxType.FREE, 12, "Mie is the best shuddup!!!"));
        ProductManager.addProduct(new DigitalProductCanBeGifted("p4", "testing", 6, 2, TaxType.LUXURY, "Mike Wazowski agrees"));

        ShoppingCart cart1 = new ShoppingCart();
        cart1.addItem("p1", 1);
        cart1.addItem("p2", 1);
        cart1.addItem("p3", 1);
        cart1.addItem("p4", 1);

        ShoppingCart cart2 = new ShoppingCart();
        cart2.addItem("p1", 2);
        cart2.addItem("p2", 2);
        cart2.addItem("p3", 2);
        cart2.addItem("p4", 2);

        ShoppingCart cart3 = new ShoppingCart();
        cart3.addItem("p1", 3);
        cart3.addItem("p2", 3);
        cart3.addItem("p3", 3);
        cart3.addItem("p4", 3);

        testCarts.add(cart1);
        testCarts.add(cart2);
        testCarts.add(cart3);
    }

    @Test
    void writeCartsToDatabase() throws IOException {
        // Call the method being tested
        WriteCartsFile.writeCartsToDatabase(
                testCarts,
                "./src/test/data/testcarts.txt",
                "./src/test/data/testcartproducts.txt"
        );

        // Check that the file has been written correctly
        String cartsFileContent = Files.readString(Paths.get("./src/test/data/testcarts.txt"));
        assertEquals(
                "1,null,0.00,0.00,0.00\n" +
                "2,null,0.00,0.00,0.00\n" +
                "3,null,0.00,0.00,0.00\n",
                cartsFileContent
        );

        String cartProductsFileContent = Files.readString(Paths.get("./src/test/data/testcartproducts.txt"));
        assertEquals(
                "DigitalProduct,p1,testing,4,2.00,Normal,1\n" +
                "PhysicalProduct,p2,testing,9,2.00,Luxury,12.00,1\n" +
                "GiftPhysicalProduct,p3,testing,3,2.00,Free,12.00,Mie is the best shuddup!!!,1\n" +
                "GiftDigitalProduct,p4,testing,0,2.00,Luxury,Mike Wazowski agrees,1\n" +
                "DigitalProduct,p1,testing,4,2.00,Normal,2\n" +
                "PhysicalProduct,p2,testing,9,2.00,Luxury,12.00,2\n" +
                "GiftPhysicalProduct,p3,testing,3,2.00,Free,12.00,Mie is the best shuddup!!!,2\n" +
                "GiftDigitalProduct,p4,testing,0,2.00,Luxury,Mike Wazowski agrees,2\n" +
                "DigitalProduct,p1,testing,4,2.00,Normal,3\n" +
                "PhysicalProduct,p2,testing,9,2.00,Luxury,12.00,3\n" +
                "GiftPhysicalProduct,p3,testing,3,2.00,Free,12.00,Mie is the best shuddup!!!,3\n" +
                "GiftDigitalProduct,p4,testing,0,2.00,Luxury,Mike Wazowski agrees,3\n",
                cartProductsFileContent
        );

        // Clean up test data
        File file = new File("./src/test/data/testcarts.txt");
        assertTrue(file.delete());
        file = new File("./src/test/data/testcartproducts.txt");
        assertTrue(file.delete());
    }
}
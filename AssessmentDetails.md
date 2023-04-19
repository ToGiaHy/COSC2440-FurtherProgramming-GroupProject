Group Project
---

**Important**: make sure that you only use built-in classes/interfaces in a default installation of Java JDK whose version <= 19). You can also add the dependencies we used in the lectures/tutorials (e.g., JUnit, JDBC, etc.). Besides the required classes/interfaces, your code must contain one Main class containing the entry public static void main method. We will launch your code from that point. In addition, you must create appropriate unit tests for testable functions using JUnit 5 and use Maven as the build tool for this project. For works that do not follow the above rules, their scores can be deducted up to 50% of the final score.

For every Java file, you must insert the following Javadoc comment at the top of the file (after package declaration)

```
/**
* @author <your group number>
  */
```
  **Background**: in this assignment, you will extend/modify the work you have done in the Individual Project.

You are developing an online shopping service. This service allows users to browse, search, view, and buy products. Each product contains the following information:

- name (String, unique among all products)
- description (String)
- quantity available (non-negative integer)
- price (double, but use only two digits after the decimal point)

The information about each product is not visible outside of the product itself. As such, you must provide the ability to change or view them to the external code.

Each product can only be a digital product or a physical product at the moment. No general product can exist in the service. For each kind of product (i.e., digital or physical), you must provide a different String representation.

For a digital product, the String representation of it is:

"DIGITAL - <product name>" (you must replace <product name> with the actual name of the product)

For a physical product, the String representation of it is;

"PHYSICAL - <product name>" (you must replace <product name> with the actual name of the product)

Physical products also have a weight (double). Again, weight is not visible outside of the physical product itself. As such, you must provide the necessary operations to change/view this value to the external code.

A shopping cart contains a collection of product items. You are free to decide how you want to store this collection of product items. Different from the Individual Project, the quantity of each product in a shopping cart can be greater than one.

A shopping cart must support these operations:

- boolean addItem(<you decide the number of parameters and their types>)

add a product item or some product items to the shopping cart; if the quantity available is not enough, do nothing and return false; otherwise, store/update necessary information and return true. You must ensure the integrity of your data.

- boolean removeItem(<you decide the number of parameters and their types>)

  remove one or more product items from the shopping cart; return true if the removal operation is successful and return false otherwise. Again, you must ensure the integrity of your data.

When creating a new product, you can decide whether the new product can be used as a gift or not. For the products that can be used as gifts, their product items must support two methods:

- void setMessage(String msg)

  and
- String getMessage()

  to set and retrieve the greeting message for the product item. Note that the greeting message is set for the individual gift product item, not for the general product.

The system also supports coupon usage. Each coupon is tied to a specific product and has a unique String value. That means a coupon for product X can only be used on a shopping cart that contains at least one item of product X. Once applying a coupon on a shopping cart, that coupon will apply to every corresponding product item.

A coupon can be a price coupon or a percent coupon. A price coupon contains a double value which is the amount reduced in the product price when applying this coupon. For example, if a product has a price of 12.3, and a price coupon has a value of 1.2, then the price of the product when applying the coupon is 11.1. A percent coupon contains an integer from 1 to 99 which is the percent reduced in the product price when applying this coupon. For example, if a product has a price of 20, a percent coupon has a value of 10, then the price of the product when applying this coupon is 18 (10% off).

You must decide the class/method that allows you to apply a coupon to a shopping cart. A shopping cart contains at most one coupon. So, if you apply a new coupon, the existing coupon (if any) will be removed.

Besides product prices, customers must pay taxes associated with them. Each product has a tax type, which can be either: tax-free, normal tax, or luxury tax. You can set the amount of those tax types to 0%, 10%, and 20% respectively. But make sure that we can update those numbers later easily without changing much code. The tax amount is calculated based on the product price without applying any coupon. For example, if a product has a price of 20 and has a luxury tax type, the tax amount of that product is 4.

The final amount customers must pay for their purchases also includes a shipping fee component. The shipping fee of each cart depends on the total weight of all physical product items in the cart. Note that the shipping fee is not taxable. The following formula is used to calculate the shipping fee of a cart:

shipping fee = (total weight of all physical products) * (base fee)

Let's use a fixed value of 0.1 for the base fee. However, you must ensure that it's easy to update this base fee when required.

You need to add a method to calculate and return the total amount a customer has to pay for a shopping cart. This total amount includes product price, tax, and shipping fee.

You must also support the printing of purchase receipts for the shopping carts. The purchase receipt must include the product name, price, quantity, tax, shipping fee, and date of purchase. The result of printing can be output to the console or stored in a text file. After printing a receipt, no further change can be made to the related shopping cart. When printing a receipt, make sure that you use the latest product information (e.g., a product is updated after you add some of its items to a shopping cart).

To speed up the delivery process, all shopping carts are sorted based on the total weight (in increasing order) of all physical items in each cart. You must ensure that Java can compare two shopping cart objects based on their total weights.

**Data Input**

To support the testing of your program, you must store around 30-50 products of all different types (e.g., digital, physical, gift, tax, coupon, etc.) in a text file products.txt (you decide the format of the file, however, it must be a text file and can be readable by a normal text editor program like notepad). Then, when your program starts, it must use the Stream API to read this file and create all necessary products. In addition, you must create another file carts.txt to store information about 5-10 carts. The information stored for each cart includes its product items (and quantity), the greeting messages for gift product items (if there are gift product items in the specified cart), the coupon applied to the cart (if any), etc. Similar to products.txt, you must use Stream API to read carts.txt and create the required shopping carts.

**User Interaction**

UI requirement: this is a console application.

First, your program reads products.txt, carts.txt and creates all product/shopping cart objects (according to the above description).

Then, display a menu for users to:

- View/add/edit products
- Add/remove product items to/from shopping carts
- Update/view messages for gift product items
- Apply/remove coupons
- Select a cart and view its details including total price, tax, and shipping fee
- Sorting carts
- Print purchase receipts to the screen or a text file (users specify a name for the file)
**Report**

The report must be in PDF format. It must include the following content: An explanation of your OOP design (classes/interfaces/methods/attributes/exceptions/design patterns/OOP principles, etc.); UML class diagrams; the automatic and manual tests that you perform to ensure the quality of your system. The total length of the report must not exceed 8 pages, including everything. All text/images must be legible with a 100% zoom level.

**Video**

Your video must be publicly accessible. You can upload it to YouTube or OneDrive but the access permission must be public. Show a complete demo of your application in the video. The video length must not exceed 7 minutes at normal playback speed. The video must have sound. It's not required to show your face. It is also not required to include all team members. Put the link to your video in the README.txt file.

**Contribution Score**

The starting score for each student is 3 points. Each group can decide to add/remove points to/from each member, but the total contribution point of the whole group is kept unchanged (it is = (the number of members) * 3). Additional rules:

- The maximum point for a member is 5
- The contribution score precision is 1 digit after the decimal point
- If a member gets zero points => that member gets zero for the whole group project assignment. In this case, the total contribution point of the whole group does not include this member (the total contribution point now is = (the number of members whose scores > zero) * 3
- The contribution score must be agreed upon by all members. If there are disagreements, you must inform the lecturer/coordinator at least one week before the due time
- The maximum score for the group project is 30. If you get more than 30 (due to a high contribution score), the final score is 30
- The contribution score is specified in README.txt. If it is missing, equal contributions from all members are assumed
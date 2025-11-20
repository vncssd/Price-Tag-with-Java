package src.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import src.db.DB;
import src.enums.ProductType;
import src.exceptions.DBException;
import src.exceptions.DateException;
import src.entities.Product;
import src.entities.UsedProduct;
import src.entities.ImportedProduct;

public class Program {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        PreparedStatement stmt = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        List<Product> products = new ArrayList<>(); 
        boolean continueAdding = true;

        while (continueAdding) {

            System.out.print("Enter the number of products: ");
            int n = sc.nextInt();

            int commomRowsAffected = 0;
            int usedRowsAffected = 0;
            int importedRowsAffected = 0;
            try {
                for (int i = 1; i <= n; i++) {
                    sc.nextLine();
                    System.out.println("Product #" + i + " data:");
                    System.out.print("Common, used or imported (c/u/i)? ");
                    String productType = sc.next();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Price: ");
                    Double price = sc.nextDouble();
                    if (productType.equals("u")) {
                        stmt = conn.prepareStatement("INSERT INTO used_product (Name, Price, ManufactureDate) " +
                                "VALUES (?,?,?)");
                        System.out.print("Manufacture date (DD/MM/YYYY): ");
                        Date manufactureDate = sdf.parse(sc.next());
                        Product usedProduct = new UsedProduct(name, price, manufactureDate, ProductType.USED);
                        long dateInMiliseconds = manufactureDate.getTime();
                        java.sql.Date manufacutreDateSql = new java.sql.Date(dateInMiliseconds);
                        stmt.setString(1, name);
                        stmt.setDouble(2, price);
                        stmt.setDate(3, manufacutreDateSql);
                        usedRowsAffected = stmt.executeUpdate();
                        products.add(usedProduct);

                    }
                    if (productType.equals("i")) {
                        System.out.print("Customs fee: ");
                        double customsFee = sc.nextDouble();
                        double importedProductTotalPrice = price + (price*customsFee/100);
                        Product importedProduct = new ImportedProduct(name, price, customsFee, ProductType.IMPORTED);
                        stmt = conn.prepareStatement("INSERT INTO imported_product (Name, Price, CustomsFee) " +
                                "VALUES (?,?,?)");

                        stmt.setString(1, name);
                        stmt.setDouble(2, importedProductTotalPrice);
                        stmt.setDouble(3, customsFee);
                        importedRowsAffected = stmt.executeUpdate();

                        products.add(importedProduct);
                    }
                    if (productType.equals("c")) {
                        Product commomProduct = new Product(name, price, ProductType.COMMOM);
                        stmt = conn.prepareStatement("INSERT INTO commom_product (Name, Price) " +
                                "VALUES (?,?)");

                        stmt.setString(1, name);
                        stmt.setDouble(2, price);
                        commomRowsAffected = stmt.executeUpdate();
                        products.add(commomProduct);
                    }
                }
            } catch (ParseException e) {
                throw new DateException(e.getMessage());
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
            System.out.println("NEW PRODUCTS ADDED: ");
            System.out.println("COMMMOM PRODUCTS: " + commomRowsAffected);
            System.out.println("USED PRODUCTS: " + usedRowsAffected);
            System.out.println("IMPORTED PRODUCTS: " + importedRowsAffected);
            System.out.println("");
            for (Product p : products) {
                System.out.println(p);
            }

            System.out.println("");
            System.out.print("ADD MORE PRODUCTS (Y/N)? ");
            String option = sc.next();
            if (option.equals("n")) {
                continueAdding = false;
            }
        }

        System.out.print("PROGRAM FINISHED!");
        DB.closeConnection();
        sc.close();
        DB.closeStatement();
    }

                
}


// package com.example.ex5_db_springboot.input;

// import java.math.BigDecimal;
// import java.util.Scanner;

// import com.example.ex5_db_springboot.model.ProductModel;
// import org.springframework.stereotype.Service;

// @Service
// public class InputProduct {
//     private ProductModel productModel;

//     public InputProduct(ProductModel productModel) {
//         this.productModel = productModel;
//     }

//     public ProductModel productModel(int choice) {
//         Scanner scanner = new Scanner(System.in);
//         if (choice == 3) {
//             System.out.println("Enter product id: ");
//             productModel.setId(scanner.nextLong());
//         } else if (choice != 1) {
//             System.out.println("Enter product id: ");
//             productModel.setId(Long.valueOf(scanner.nextLine()));
//         } else {
//             System.out.println("Enter product category: ");
//             productModel.setCategory(Long.valueOf(scanner.nextLine()));
//             System.out.println("Enter product storage: ");
//             productModel.setStorage(Long.valueOf(scanner.nextLine()));
//             System.out.println("Enter product name: ");
//             productModel.setName(scanner.nextLine());
//             System.out.println("Enter product price: ");
//             productModel.setPrice(BigDecimal.valueOf(Long.parseLong(scanner.nextLine())));
//             System.out.println("Enter product quantity: ");
//             productModel.setQuantity(Integer.parseInt(scanner.nextLine()));
//         }
//         scanner.close();
//         return productModel;
//     }
// }

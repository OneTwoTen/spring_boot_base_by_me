// package com.example.ex5_db_springboot.input;

// import java.util.Scanner;

// import com.example.ex5_db_springboot.model.CategoryModel;
// import org.springframework.stereotype.Service;

// import static java.lang.System.out;

// @Service
// public class InputCategory {
//     public CategoryModel categoryModel;

//     public InputCategory(CategoryModel categoryModel) {
//         this.categoryModel = categoryModel;
//     }

//     public CategoryModel categoryModel(int choice) {
//         Scanner scanner = new Scanner(System.in);
//         Long id;
//         String code;
//         String name;
//         String description;
//         if (choice == 3) {
//             out.println("Enter category id: ");
//             if (scanner.hasNextLong()) {
//                 id = scanner.nextLong();
//                 categoryModel.setId(id);
//             }
//         } else if (choice != 1 || choice != 6) {
//             out.println("Enter category id: ");
//             if (scanner.hasNextLong()) {
//                 id = scanner.nextLong();
//                 categoryModel.setId(id);
//             } else {
//                 out.println("Id must be a number");
//                 scanner.close();
//                 return null;
//             }
//         } else {
//             out.println("Enter code: ");
//             if (scanner.hasNext()) {
//                 code = scanner.nextLine();
//                 categoryModel.setCode(code);
//             } else {
//                 out.println("Code must be a string");
//                 scanner.close();
//                 return null;
//             }
//             out.println("Enter name: ");
//             if (scanner.hasNextLine()) {
//                 name = scanner.nextLine();
//                 categoryModel.setName(name);
//             } else {
//                 out.println("Name must be a string");
//                 scanner.close();
//                 return null;
//             }
//             out.println("Enter description: ");
//             if (scanner.hasNextLine()) {
//                 description = scanner.next();
//                 categoryModel.setDescription(description);
//             } else {
//                 out.println("Description must be a string");
//                 scanner.close();
//                 return null;
//             }


//         }
//         scanner.close();
//         return categoryModel;
//     }
// }

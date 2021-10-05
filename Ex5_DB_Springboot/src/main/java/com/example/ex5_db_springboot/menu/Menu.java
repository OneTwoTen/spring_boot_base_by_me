// package com.example.ex5_db_springboot.menu;

// import java.util.Scanner;

// import com.example.ex5_db_springboot.dao.CategoryDao;
// import com.example.ex5_db_springboot.dao.ProductDao;
// import com.example.ex5_db_springboot.input.InputCategory;
// import com.example.ex5_db_springboot.input.InputProduct;
// import com.example.ex5_db_springboot.input.InputStorage;
// import com.example.ex5_db_springboot.model.CategoryModel;
// import com.example.ex5_db_springboot.model.ProductModel;
// import com.example.ex5_db_springboot.model.StorageModel;
// import com.example.ex5_db_springboot.service.StorageService;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Service;

// @Service
// public class Menu implements CommandLineRunner {

//     private final Logger logger = LoggerFactory.getLogger(Menu.class);
//     private final ProductDao productDao;
//     private final CategoryDao categoryDao;
//     private final StorageService storageService;
//     private final ProductModel productModel;
//     private final CategoryModel categoryModel;
//     private final StorageModel storageModel;
//     private final InputProduct inputProduct;
//     private final InputCategory inputCategory;
//     private final InputStorage inputStorage;

//     public Menu(ProductDao productDao, CategoryDao categoryDao, StorageService storageService,
//                 ProductModel productModel, CategoryModel categoryModel, InputProduct inputProduct,
//                 InputCategory inputCategory, InputStorage inputStorage, StorageModel storageModel) {
//         this.productDao = productDao;
//         this.categoryDao = categoryDao;
//         this.storageService = storageService;
//         this.productModel = productModel;
//         this.categoryModel = categoryModel;
//         this.inputProduct = inputProduct;
//         this.inputCategory = inputCategory;
//         this.inputStorage = inputStorage;
//         this.storageModel = storageModel;
//     }


//     @Override
//     public void run(String... args) {
//         int choice;
//         Scanner scanner = new Scanner(System.in);
//         logger.info("1. Product menu: ");
//         logger.info("2. Category menu: ");
//         logger.info("3. Storage menu");
//         logger.info("4. Exit");
//         logger.info("Enter your choice: ");
//         choice = scanner.nextInt();
//         switch (choice) {
//             case 1:
//                 product();
//                 break;
//             case 2:
//                 category();
//                 break;
//             case 3:
//                 storage();
//                 break;
//             case 4:
//                 logger.info("Exit");
//                 break;
//             default:
//                 logger.info("Invalid choice");
//                 break;
//         }
//         scanner.close();
//     }

//     public void product() {
//         Scanner scanner = new Scanner(System.in);
//         logger.info("1. Add new product");
//         logger.info("2. Show all products");
//         logger.info("3. Delete product");
//         logger.info("4. Update product");
//         logger.info("6: Find by Id");
//         logger.info("0. Return");
//         logger.info("5. Exit");
//         logger.info("Enter your choice: ");
//         int choice = scanner.nextInt();
//         switch (choice) {
//             case 1:
//                 logger.info("Add new product");
//                 inputProduct.productModel(choice);
//                 productDao.insertProduct(productModel);
//                 break;
//             case 2:
//                 logger.info("Show all products");
//                 productDao.getAllProducts();
//                 break;
//             case 3:
//                 logger.info("Delete product");
//                 inputProduct.productModel(choice);
//                 productDao.deleteProduct(productModel.getId());
//                 break;
//             case 4:
//                 logger.info("Update product");
//                 inputProduct.productModel(choice);
//                 productDao.updateProduct(productModel);
//                 break;
//             case 6:
//                 logger.info("Find product");
//                 inputProduct.productModel(choice);
//                 productDao.findById(productModel.getId());
//                 break;
//             case 5:
//                 logger.info("Exit");
//                 break;
//             case 0:
//                 run();
//                 break;
//             default:
//                 logger.info("Invalid choice");
//                 break;
//         }
//         scanner.close();
//     }

//     public void category() {
//         Scanner scanner = new Scanner(System.in);
//         logger.info("1. Add new category");
//         logger.info("2. Show all category");
//         logger.info("3. Delete category");
//         logger.info("4. Update category");
//         logger.info("6. find category");
//         logger.info("0. Return");
//         logger.info("5. Exit");
//         logger.info("Enter your choice: ");
//         int choice = scanner.nextInt();
//         switch (choice) {
//             case 1:
//                 logger.info("Add new category");
//                 inputCategory.categoryModel(choice);
//                 categoryDao.insert(categoryModel);
//                 break;
//             case 2:
//                 logger.info("Show all category");
//                 categoryDao.findAll();
//                 break;
//             case 3:
//                 logger.info("Delete category");
//                 inputCategory.categoryModel(choice);
//                 categoryDao.delete(categoryModel.getId());
//                 break;
//             case 4:
//                 logger.info("Update category");
//                 inputCategory.categoryModel(choice);
//                 categoryDao.update(categoryModel);
//                 break;
//             case 6:
//                 logger.info("Find category");
//                 inputCategory.categoryModel(choice);
//                 categoryDao.findById(categoryModel.getId());
//                 break;
//             case 5:
//                 logger.info("Exit");
//                 break;
//             case 0:
//                 run();
//                 break;
//             default:
//                 logger.info("Invalid choice");
//                 break;
//         }
//         scanner.close();
//     }

//     public void storage() {
//         Scanner scanner = new Scanner(System.in);
//         logger.info("1. Add new storage");
//         logger.info("2. Show all storage");
//         logger.info("3. Delete storage");
//         logger.info("4. Update storage");
//         logger.info("6. Find storage");
//         logger.info("0. Return");
//         logger.info("5. Exit");
//         logger.info("Enter your choice: ");
//         int choice = scanner.nextInt();
//         switch (choice) {
//             case 1:
//                 logger.info("Add new storage");
//                 inputStorage.storageModel(choice);
//                 storageService.create(storageModel);
//                 break;
//             case 2:
//                 logger.info("Show all storage");
//                 storageService.findAll();
//                 break;
//             case 3:
//                 logger.info("Delete storage");
//                 inputStorage.storageModel(choice);
//                 storageService.delete(storageModel.getId());
//                 break;
//             case 4:
//                 logger.info("Update storage");
//                 inputStorage.storageModel(choice);
//                 storageService.update(storageModel);
//                 break;
//             case 5:
//                 logger.info("Exit");
//                 break;
//             case 0:
//                 run();
//                 break;
//             default:
//                 logger.info("Invalid choice");
//                 break;
//         }
//         scanner.close();
//     }
// }

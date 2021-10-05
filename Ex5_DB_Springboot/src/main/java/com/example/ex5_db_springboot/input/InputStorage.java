// package com.example.ex5_db_springboot.input;

// import java.util.Date;
// import java.util.Scanner;

// import com.example.ex5_db_springboot.model.StorageModel;
// import org.springframework.stereotype.Service;

// import javax.validation.Valid;

// import static java.lang.System.out;

// @Service
// public class InputStorage {
//     private final StorageModel storageModel;

//     public InputStorage(StorageModel storageModel) {
//         this.storageModel = storageModel;
//     }
//     @Valid
//     public StorageModel storageModel(int choice) {
//         Scanner scanner = new Scanner(System.in);
//         long id;
//         String name;
//         String address;
//         if (choice == 3) {
//             out.println("Enter id: ");
//             if (scanner.hasNext()) {
//                 id = scanner.nextLong();
//                 storageModel.setId(id);
//             }
//         } else if (choice != 1) {
//             out.println("Enter id: ");
//             if (scanner.hasNext()) {
//                 id = scanner.nextLong();
//                 storageModel.setId(id);
//             }
//             storageModel.setCreatedDate(new Date());
//         }
//         storageModel.setUpdatedDate(new Date());

//         out.println("Enter updatedDate: ");

//         out.println("Enter name: ");
//         if (scanner.hasNext()) {
//             name = scanner.nextLine();
//             storageModel.setName(name);
//         }
//         out.println("Enter address: ");
//         if (scanner.hasNext()) {
//             address = scanner.nextLine();
//             storageModel.setAddress(address);

//         }
//         scanner.close();

//         return storageModel;
//     }
// }

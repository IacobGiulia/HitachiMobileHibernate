package com.example;

import com.example.entity.Customer;
import com.example.entity.SimDetails;
import com.example.repository.CustomerRepositoryImpl;
import com.example.repository.SimDetailsRepositoryImpl;
import com.example.service.CustomerService;
import com.example.service.SimDetailsService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CustomerService customerService = new CustomerService(new CustomerRepositoryImpl());
        SimDetailsService simService = new SimDetailsService(new SimDetailsRepositoryImpl());

        boolean running = true;

        while (running) {
            System.out.println("\n===== Hitachi Mobile SIM Portal =====");
            System.out.println("1. List Customers in Bangalore");
            System.out.println("2. List Active SIM Details");
            System.out.println("3. Fetch SIM Status");
            System.out.println("4. Update SIM Status to Active");
            System.out.println("5. Update Customer Address");
            System.out.println("6. List All Customers");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        List<Customer> bangaloreCustomers = customerService.getCustomersFromBangalore();
                        bangaloreCustomers.forEach(System.out::println);
                        break;

                    case 2:
                        List<SimDetails> activeSims = simService.getActiveSims();
                        activeSims.forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter SIM Number: ");
                        Long simNumber = sc.nextLong();
                        System.out.print("Enter Service Number: ");
                        Long serviceNumber = sc.nextLong();
                        sc.nextLine();
                        String status = simService.getSimStatus(simNumber, serviceNumber);
                        System.out.println("SIM Status: " + status);
                        break;

                    case 4:
                        System.out.print("Enter SIM Id to activate: ");
                        Integer simId = sc.nextInt();
                        sc.nextLine();
                        simService.activateSim(simId);
                        System.out.println("SIM status updated to Active!");
                        break;

                    case 5:
                        System.out.print("Enter Customer Unique Id: ");
                        Long custId = sc.nextLong();
                        sc.nextLine();
                        System.out.print("Enter new City: ");
                        String city = sc.nextLine();
                        System.out.print("Enter new State: ");
                        String state = sc.nextLine();
                        customerService.updateCustomerAddress(custId, city, state);
                        System.out.println("Customer address updated successfully!");
                        break;

                    case 6:
                        List<Customer> allCustomers = customerService.getAllCustomers();
                        allCustomers.forEach(System.out::println);
                        break;

                    case 7:
                        running = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}

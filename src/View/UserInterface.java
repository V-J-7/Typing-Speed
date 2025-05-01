package View;

import Controller.ManageStatistics;
import Model.Authentication;
import Model.TypingTest;
import Model.UserManagement;
import Model.Validator;

import java.util.Scanner;

public class UserInterface {
    UserManagement userManager=new UserManagement();
    ManageStatistics statisticsManager=new ManageStatistics();
    Scanner sc = new Scanner(System.in);
    void start(){
        while (true){
            System.out.println("Welcome to the Typing Test!");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Update User details");
            System.out.println("4.Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    void register(){
        System.out.println("----------Register----------");
        System.out.print("Enter your email:");
        String email=sc.next();
        while (!Validator.validateEmail(email)){
            System.out.print("Enter your email:");
            email = sc.next();
        }
        System.out.print("Enter your name:");
        String name = sc.nextLine();
        sc.nextLine();
        System.out.print("Enter your password:");
        String password=sc.next();
        while (!Validator.validatePassword(password)){
            System.out.print("Enter your password:");
            password = sc.next();
        }
        userManager.addUser(email,name,password);
    }
    void login(){
        System.out.println("----------Login----------");
        System.out.print("Enter your email:");
        String email = sc.next();
        System.out.print("Enter your password:");
        String password = sc.next();
        if (Authentication.authenticate(email,password)){
            insideLogin(email);
        }
    }
    void insideLogin(String email){
        while (true){
            System.out.println("1.View Statistics");
            System.out.println("2.Take Typing Test");
            System.out.println("3.Logout");
            System.out.print("Enter your choice:");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    statisticsManager.getStatistics(email);
                    break;
                case 2:
                    TypingTest typingTest=new TypingTest();
                    if (insideTypingTest(typingTest)){
                        statisticsManager.storeStatistics(email,typingTest);
                    }
                    break;
                case 3:
                    System.out.println("----------Exiting----------");
                    start();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    boolean insideTypingTest(TypingTest typingTest){
        System.out.println("----------Typing Tests----------");
        System.out.println("1.Easy");
        System.out.println("2.Medium");
        System.out.println("3.Hard");
        System.out.println("4.Exit");
        System.out.print("Choose the difficulty:");
        int difficulty=sc.nextInt();
        switch(difficulty){
            case 1:
                typingTestLength(typingTest,1);
                break;
            case 2:
                typingTestLength(typingTest,2);
                break;
            case 3:
                typingTestLength(typingTest,3);
                break;
            case 4:
                return false;
            default:
                System.out.println("Invalid choice");
        }
        return true;
    }
    void typingTestLength(TypingTest typingTest,int difficulty){
        System.out.println("----------Length----------");
        System.out.println("1.Short");
        System.out.println("2.Medium");
        System.out.println("3.Long");
        System.out.print("Choose the length of the test:");
        int length=sc.nextInt();
        switch (length){
            case 1:
                typingTest.shortTests(difficulty-1);
                break;
            case 2:
                typingTest.mediumTests(difficulty-1);
                break;
            case 3:
                typingTest.longTests(difficulty-1);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    void updateUser(){
        System.out.println("----------Update User----------");
        System.out.print("Enter your email:");
        String email=sc.next();
        if (!userManager.emailRegistered(email)){
            System.out.println("Email not registered");
            return;
        }
        String newPassword="",newUsername="";
        System.out.print("Enter your old password:");
        String oldPassword=sc.next();
        while (!userManager.correctPassword(email,oldPassword)){
            System.out.print("Enter your old password:");
            oldPassword=sc.next();
            System.out.println("Wrong password");
        }
        System.out.print("Enter your new password (type n if you don't want to update):");
        newPassword=sc.next();
        while (!newPassword.equals("n") && !Validator.validatePassword(newPassword)){
            System.out.print("Enter your new password:");
            newPassword=sc.next();
        }
        System.out.print("Enter your new username (type n if you don't want to update):");
        newUsername=sc.next();
        userManager.updateUser(email,oldPassword,newPassword,newUsername);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Book> listBook = new ArrayList<>();
    
    public static void main(String[] args) {
        menuBook();
    }

    public static void menuBook() {

        while (true) {
            System.out.println("------MenuBook------");
            System.out.println("1. Add Book");
            System.out.println("2. Save Book.");
            System.out.println("3. Display Book.");
            System.out.println("4. Exit.");
            System.out.println("Please enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    saveBook();
                    break;
                case 3:
                    displayBook();
                    break;
                case 4:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Plesa enter choi");
                    break;
            }
            if (choice == 4) {
                break;
            }
        }
    }
    
    public static void addBook(){
        System.out.println("Please input book info: ");
        System.out.print("Book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Book Name: ");
        String bookName = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Price: ");
        int price = scanner.nextInt();
        Book book = new Book(bookID, bookName, author, price);
        listBook.add(book);
    }
    
    public static void saveBook(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("book.dat");
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream,Charset.forName("utf-8"));
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(String.format("%15s, %30s, %30s, %15s","BookId","Book Name", "Author", "Price"));
            bw.newLine();
            for (Book book : listBook) {
                bw.write(String.format("%15s, %30s,%30s, %15s", book.getBookId(), book.getBookName(),
                        book.getAuthor(),"$"+ String.valueOf(book.getPrice())));
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void displayBook(){
        try {
            FileInputStream fileInputStream = new FileInputStream("book.dat");
            InputStreamReader isr = new InputStreamReader(fileInputStream, Charset.forName("utf-8"));
            BufferedReader br = new BufferedReader(isr);
            
            String line;
            while ((line = br.readLine()) != null) {                
                System.out.println(line);
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

package Level4;

import Level3Version2.Bavli;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Methods4.catchChapter(0, "פרק א", Methods4.indexChapters());
        Bavli bavli = new Bavli();
        bavli.setPath(Methods4.createBavliDirctory());
        Methods4.createBookAndNames(bavli);
        System.out.println(Arrays.toString(bavli.getBooksNames()));
        String[] chapterIndex = Methods4.indexChapters();
        Arrays.sort(chapterIndex);
        boolean flag = true;
        while (flag){
            System.out.println("Press 1 for print chapter: ");
            System.out.println("Press 2 for break: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter name of book:");
                    int numBook = Methods4.exportLocationBook("מסכת " + scanner.nextLine(), bavli.getBooksNames());
                    System.out.println(numBook);

                    System.out.println("Enter chapter: ");
                    String chapterName = scanner.nextLine();
                    Methods4.catchChapter(numBook, chapterName, chapterIndex);
                    break;
                case 2:
                    flag = false;
                    break;
            }
        }


    }
}

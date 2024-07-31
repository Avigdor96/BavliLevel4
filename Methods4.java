package Level4;

import Level3Version2.Bavli;
import Level3Version2.Book;
import Level3Version2.PageFather;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Methods4 {
    public static String typeLine(String line) {
        if (line.isEmpty()) {
            return "Empty";
        }
//        if (line.split(" ")[line.length()].equals("פרק")){
//            return "Chapter";
//        }

        if (line.startsWith("מסכת ") && line.contains("פרק א")) {
            return "Book";
        }
        if (line.startsWith("דף ") && (line.endsWith(" א") || line.endsWith(" ב")) && (line.split(" ").length < 4)) {
            return "Page";
        }
        if (line.startsWith("גמרא") || line.startsWith("משנה")) {
            return "Content";
        }
        return "";
    }
    public static String correctBookName(String line){
        return line.substring(0, line.indexOf(" פרק"));
    }
    public static String correctNamePage(String line) {
        return line.split(" ")[0] + " " + line.split(" ")[1];
    }
    public static String createBavliDirctory() {
        Bavli bavli = new Bavli();
        String path = "C:\\Users\\a0556\\OneDrive\\שולחן העבודה\\Bavli";
        File base = new File("C:\\Users\\a0556\\OneDrive\\שולחן העבודה\\Bavli");
        if (!base.exists()) {
            base.mkdir();

            String textPath = "C:\\Users\\a0556\\OneDrive\\שולחן העבודה\\__תלמוד בבלי טקסט_.txt";
            File fileOfText = new File(textPath);
            String currentBook = "";
            //String currentFatherPage = "";
            String currentPage = "";
            try {
                Scanner scanner = new Scanner(fileOfText);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    switch (Methods4.typeLine(line)) {
                        case "Book":
                            String correctBookName = correctBookName(line);
                            File newBook = new File(base, correctBookName);
                            //System.out.println(bavli.getBooks()[0]);
                            if (!newBook.exists()) {
                                newBook.mkdir();
                            }
                            currentBook = newBook.getAbsolutePath().trim();
                            break;
                        case "Page":
                            File fatherPage = new File(currentBook, correctNamePage(line));
                            if (!fatherPage.exists()) {
                                fatherPage.mkdir();
                            }

                            currentPage = currentBook + "/" + fatherPage.getName();
//
                            File file = new File(currentPage, line);
                            if (!file.exists()) {
                                file.createNewFile();
                            }

                            currentPage = file.getAbsolutePath().trim();
                            break;


                        case "Content":
                            FileWriter fw = new FileWriter(currentPage, true);
                            fw.write(line + "\n");
                            fw.close();
                            break;

                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return path;
    }
    public static void createBookAndNames(Bavli bavli) {
        File file = new File(bavli.getPath());
        File[] files = file.listFiles();
        String[] namesBooks = new String[files.length];
        Book[] books = new Book[files.length];
        for (int i = 0; i < files.length; i++) {
            namesBooks[i] = files[i].getName();
            books[i] = new Book(files[i].getName());
            books[i].setPath(files[i].getPath());
        }
        bavli.setBooksNames(namesBooks);
        bavli.setBooks(books);
        createFatherPages(books);
    }

    public static void createFatherPages(Book[] books) {
        for (int i = 0; i < books.length; i++) {
            File file = new File(books[i].getPath());
            File[] files = file.listFiles();
            String[] pageFathersNames = new String[files.length];
            PageFather[] pageFathers = new PageFather[files.length];
            for (int j = 0; j < pageFathersNames.length; j++) {
                pageFathersNames[j] = (files[j].getName());
                pageFathers[j] = new PageFather(files[j].getName());
                pageFathers[j].setPath(files[j].getPath());
            }
            books[i].setPageFathers(pageFathers);
            books[i].setPageFatherNames(pageFathersNames);
        }
    }

    public static int exportLocationBook(String bookName, String[] booksNames) {
        int location = binarySearch(booksNames, bookName);
        return location;
    }

    public static int checkValidBook(Bavli bavli) {
        System.out.println("Enter name of book: ");
        Scanner scanner = new Scanner(System.in);
        String name = "מסכת " + scanner.nextLine();
        int i = exportLocationBook(name, bavli.getBooksNames());
        if (i == -1) {
            System.out.println("Book not found enter again.");
            return checkValidBook(bavli);
        }
        return i;
    }

    public static int exportLocationPage(String pageName, String[] pagesNames) {
        int location = binarySearch(pagesNames, pageName);
        return location;
    }

    public static int checkValidPage(Bavli bavli, int bookLocation) {
        System.out.println("Enter name of page: ");
        Scanner scanner = new Scanner(System.in);
        String page = "דף " + scanner.nextLine();
        int i = exportLocationPage(page, bavli.getBooks()[bookLocation].getPageFatherNames());
        if (i == -1) {
            System.out.println("Page not found enter again.");
            return checkValidPage(bavli, bookLocation);
        }
        return i;
    }

    public static void printPage(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            try {
                Scanner scanner1 = new Scanner(file1);
                while (scanner1.hasNextLine()) {
                    String line = scanner1.nextLine();
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static int binarySearch(String[] strings, String string) {
        Arrays.sort(strings);
        int mid = 0;
        int low = 0;
        int high = strings.length - 1;
        while (high >= low) {
            mid = (low + high) / 2;
            int x = strings[mid].compareTo(string);
            if (x == 0) {
                return mid;
            } else if (x < 0) {
                low = mid + 1;
            } else if (x > 0) {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static void catchMishnha(Page page){
        File file1 = new File(page.getName());
        try{
            Scanner scanner = new Scanner(file1);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.startsWith("משנה ")){
                    page.setIfMishna(true);
                }
                else{
                    page.setIfMishna(false);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static String[] indexChapters(){
        String[] chapterIndex = new String[37];
        File file = new File("C:\\Users\\a0556\\OneDrive\\שולחן העבודה\\__תלמוד בבלי טקסט_.txt");
        try{
            int i = -1;
            String currentBook = "";
            String currentChapter = "";
            String currentPage = "";
            String res = "";
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.startsWith("מסכת ") && line.contains("פרק א")){
                    currentBook = line.substring(line.indexOf(" "), line.indexOf("פרק")).trim();
                    i += 1;
                    chapterIndex[i] = "";



                }
                if (line.contains("פרק ") && line.split(" ").length <= 5){
                    currentChapter = line.substring(line.indexOf("פרק ")).trim();
                    //System.out.println(currentChapter);
//                    if (! chapterIndex[i].contains(line.substring(line.indexOf("פרק")))){
//                        chapterIndex[i] += line.substring(line.indexOf("פרק")) + ":" + " ";
//                    }
                }
                if (line.startsWith("דף ") &&  (line.split(" ").length < 4) && line.endsWith(" א")){
//                    if (!chapterIndex[i].contains(line.split(" ")[0] + line.split(" ")[1])){
//                        chapterIndex[i] += line.split(" ")[0] + line.split(" ")[1] + ",";
//                    }
                    currentPage = line.split(" ")[0] + " " + line.split(" ")[1].trim();
                    res = currentBook + " " +  currentChapter + " " + currentPage + " ,";
                    if (! chapterIndex[i].contains(res)){
                        chapterIndex[i] += res;
                    }


                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return chapterIndex;
    }
    public static String[] catchChapter(int bookLocation, String chapterName, String[] strings){
        String[] allChapter = strings[bookLocation].split(" ,");
        String res = "";
        for (int i = 0; i < allChapter.length; i++) {
            if (allChapter[i].contains(chapterName)){
                System.out.println(allChapter[i]);
                res += allChapter[i] + ",";
            }
        }
        String[] res2 = res.split(",");
        return res2;
    }
}

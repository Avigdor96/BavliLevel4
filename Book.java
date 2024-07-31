package Level4;

import Level3Version2.Page;

public class Book {
    private String name;
    private String path;
    private Page[] pages;
    private String[] pagesName;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Page[] getPages() {
        return pages;
    }

    public void setPages(Page[] pages) {
        this.pages = pages;
    }

    public String[] getPagesName() {
        return pagesName;
    }

    public void setPagesName(String[] pagesName) {
        this.pagesName = pagesName;
    }
}

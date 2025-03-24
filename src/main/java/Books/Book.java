package Books;

public class Book  {

            // instance variables

            public  String title;
        
            public  String author;
        
            private String isbn;
        
            public  int publicationYear;


            // constructor

            public Book(String title, String author, String isbn, int publicationYear) {
                this.title = title;
                this.author = author;
                this.isbn = isbn;
                this.publicationYear = publicationYear;
        
            }
        
            public Book() {
                //TODO Auto-generated constructor stub
            }

             
            // getters
            public String getTitle() {
                return title;
            }
        
            public String getAuthor() {
                return author;
            }
        
            public int getPublicationYear() {
                return publicationYear;
            }
        
            public String getIsbn() {
                return isbn;
            }

        
            // setters
            public void setTitle(String title) {
                this.title = title;
            }
        
            public void setAuthor(String author) {
                this.author = author;
            }
        
            public void setPublicationYear(int publicationYear) {
                this.publicationYear = publicationYear;
            }
        
            public void setIsbn(String isbn) {
                this.isbn = isbn;
            }
        
            public String toString() {
                return "Title: " + title + ", Author: " + author + ", Publication Year: " + publicationYear;
            }
}

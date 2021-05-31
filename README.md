# PC Book Project
JAVA VERSION -> 11
* This project is a simple Java Spring Boot application to store Books and Authors. It stores Ebooks and Print Copy books.
These types are arrangedin constants folder as an enum in the project.
***
# Functionality And Approach Taken:
PC Book Project uses PostgreSQL and consists of database named "pc-book-library" which has two tables: "author" and "book".
The author table consists of following columns: 1)id 2)first_name 3)last_name 4)year_of_birth.
The book table consists of following columns: 1)id 2)title 3)author_id 4)isbn 5)year_of_publish 6)type 7)number_of_pages 8)weight 9)format 10)size.
'number_of_pages', 'weight', 'format' and 'size' are optional, depending on the request, either for Ebook or Print Copy Book.
JPA/Jibernate is used to communicate with databse. In java project the database structure is present in "domain" folder. This project supports following functionality:
* Addition of author endpoint: "/api/authors" - This is the root endpoint for the author controller class.
* Update A Author: The endpoint is "/api/authors/{authorId}" where authorId is path variable. Here we pass the AuthorRequest object too.
While updating if "authorId" does not exist, it gives custom exception.
* Delete A Author: The endpoint is the same as update author, "/api/authors/{authorId}, where authorId is path variable. It returns a ResponseEntity status, success: true.
If "authorId" is not found, it gives custom exception.
* Get Author By Id: It gets all the details of author for that id. The end point for this function is: "/api/authors/{authorId}". Here authorId is the path variable.
* Get Page Of Authors: It returns a specification all the authors that are registered, as a page. The url for this function is: "/api/authors".
It recieves three request param for first name, last name and year of birth, which are not required and can be used for filtering.
* Get Authors With More Than Three Books Written: The end point for this function is: "/api/authors/authorsWithMoreThanThreeBooks".
* Get Authors Born In The Same Decade As Published Books: This returns all authors if there are books published in the same decade as their year of birth.
The end point for this function is: "/api/authors/authorsBornInTheSameDecadeAsPublishedBooks".
* Unit Tests: The unit test is done in service level. The unit test is done using JUnit and Mockito. It has over 80% line code coverage.
* Addition of book: Addition of book is done by two end points.
  * "/api/books/ebook": If the book that you want to register is a EBook.
  * "/api/books/print-copy": If the book that you want to register is a Print Copy book.
* Update of book is also done by two end points.
  * "/api/books/ebook/{bookId}": If the book that you want to update is a Ebook. Here the path variable is bookId. If bookId is a Print Copy it will throw exception.
  If bookId is not found it will throw exception aswell.
  * "/api/books/print-copy/{bookId}": If the book that you want to update is a Print Copy Book. Here the path variable is bookid. If bookId is a EBook it will throw exception.
  If bookId is not found it will throw exception aswell.
* Delete a book: The endpoint for this function is: "/api/books/{bookId}". Here bookId is a path variable. If bookId is not found it will throw exception.
It returns a ResponseEntity status, success: true.
* Get book by id: It gets all the details of a book for that id. The end point for this function is: "/api/books/{bookId}". Here bookId is the path variable.
* Get page of books: It returns a specification for all the books that are registered, as a page. The url for this function is: "/api/books".
It recieves three request param for title, isbn and year of publish, which are not required and can be used for filtering.
* Get All Books Ordered By Oldest To Newest: This will return a list of all the books starting from oldest to newest. The url for this function is: "/api/books/orderByOldestToNewest".
* Get Books By First Letter Of Authors Last Name: The end point for this function is: "/api/books/byFirstLetterOfAuthorsLastName".
* Get Oldest Book: The end point is: "/api/books/oldest".
* Get Newest Book: The end point is: "/api/books/newest".
***
# Tools/Framework
* PostgreSQL: PostgreSQL is a free and open-source relational database management system emphasizing extensibility and SQL compliance.
* JPA: Java Persistance API(JPA) is a Java programming interface specification that describes the management of relational data in applications using Java Platform.
* JUnit/Mockito: JUnit5 is the unit testing framework for the Java programming language. Mockito is a mocking framework.
* Lombok: Project Lombok is a java library that automatically plugs into editor and build tools, spicing up java. 
Getter, Setters, Constructors can be created with annotation without writing the code with the help of Lombok.
* Flyway: Flyway is an open-source database-migration tool.
***
# Steps To Run The Application
* This project uses project Lombok. So, if you do not have Lombok plugins inserted into your IDE, please install it.
* Instal PostgreSQL in your laptop/PC if you do not have one. It also installs the PgAdmin database management tool for PostgreSQL.
* In your PgAdmin interface make a database named: pc-book-library.
* Now in your IDE, open the application.yml file, change the spring datasource url, username and password. 
Also change you server port if you are using the current one which is 8095.
Right now the spring datasource url is "jdbc:postgresql://localhost:5432/pc-book-library" which is likely to be same in your case too.
If noy please put on your datasource url. Also please put in your username and password that you've set.
* Now you can run your application. The tables "author" and "book" will be automatically migrated in "pc-book-library" becayse if Flyway.
* Once you have set up your databse, you can also run the unit tests. For that you can go to the project root folder and run the following command:
  * mvn clean test

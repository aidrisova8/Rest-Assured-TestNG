package calls;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BooksPojo;
import utilities.RequestMethod;
import utilities.ResponseMethod;

public class GetOrder {
    /*
    List of all books in order
    validate response body
    status code
     */
    Response response;
    @Test
    public void getTest(){
        response=utilities.RequestMethod.getListOfBook();
        response.prettyPrint();
        BooksPojo [] listBooks=response.body().as(BooksPojo[].class);
        String nameOfBooks="";
     //   int countBooks=0;
        for(int i=0;i<listBooks.length;i++) {
            nameOfBooks+=listBooks[i].getName()+"\n";
        }
        System.out.println(nameOfBooks);
       ResponseMethod.verify200Status(response);
       ResponseMethod.verifyListOfBooks(nameOfBooks,listBooks.length);
    }

    @Test
    public void getSingleBook(){
  response=RequestMethod.getSingleIdBook(2); //7 coming from request
  response.prettyPrint();
  BooksPojo getBookInfoId=response.body().as(BooksPojo.class);
//  ResponseMethod.verify200Status(response);
  ResponseMethod.verifySingleBookIdInformation(getBookInfoId,response);
        System.out.println(response.getStatusLine());

    }
}

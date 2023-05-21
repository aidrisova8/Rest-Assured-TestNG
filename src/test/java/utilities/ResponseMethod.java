package utilities;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import pojo.BooksPojo;
import pojo.OrderPojo;

import javax.print.attribute.standard.OrientationRequested;

public class ResponseMethod {

    public static final String allBooksName = "The Russian\n" +
            "Just as I Am\n" +
            "The Vanishing Half\n" +
            "The Midnight Library\n" +
            "Untamed\n" +
            "Viscount Who Loved Me\n";

    public static final int countOfBooks=6;
    public static final String invalidErrorOrderMsg="Invalid or missing bookId.";
    //Status code
    public static void verify201Status(Response response) {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Unexpected response code!");
    }

    public static void verify200Status(Response response) {
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Unexpected response code");
    }

    public static void verify400Status(Response response){
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_BAD_REQUEST,"Unexpected response code");
    }
    //validating body response
    public static void verifyOrderSuccess(OrderPojo orderPojo) {
        Assert.assertTrue(orderPojo.getCreated());//true
        Assert.assertNotNull(orderPojo.getOrderId()); //value
    }

    public static void verifyGetOrderInfo(OrderPojo postOrderResponse, String customerName, int bookId, OrderPojo getCallInfo) {
        Assert.assertEquals(postOrderResponse.getOrderId(), getCallInfo.getId(), "Id that has been provided are not matching!");//id
        Assert.assertEquals(getCallInfo.getCustomerName(), customerName, "Customer name is not correct");// customer name
        Assert.assertEquals(getCallInfo.getBookId(), bookId, "BookId is not matching");
    }
    public static void verifyListOfBooks(String bookResponse, int countBooks){
Assert.assertEquals(bookResponse,allBooksName,"Books  that are provided are not matching");
Assert.assertEquals(countBooks,countOfBooks,"Number of books doesn't match");
    }

    public static void verifyInvalidErrorMsg(OrderPojo ordersResponse){
        Assert.assertEquals(ordersResponse.getError(),invalidErrorOrderMsg,"Error message was not received");

    }
    public static void verifySingleBookIdInformation(BooksPojo getResponseBook,Response response){
        if(getResponseBook.getId()==1){
            Assert.assertEquals(getResponseBook.getName(),"The Russian","The name for id 1 should be: The Russian");
            Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        }else if(getResponseBook.getId()==2){
            Assert.assertEquals(getResponseBook.getName(),"Just as I Am");
            Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        }else if(getResponseBook.getId()==3){
            Assert.assertEquals(getResponseBook.getName(),"The Vanishing Half");
            Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        }else if(getResponseBook.getId()==4){
            Assert.assertEquals(getResponseBook.getName(),"The Midnight Library");
            Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        }else if(getResponseBook.getId()==5){
            Assert.assertEquals(getResponseBook.getName(),"Untamed");
            Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        }else if(getResponseBook.getId()==6){
            Assert.assertEquals(getResponseBook.getName(),"Viscount Who Loved Me");
            Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        }else if(getResponseBook.getId()>6 ){
            Assert.assertEquals(getResponseBook.getErrorMessage(),"No book with id "+getResponseBook.getId());
            Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND,"Unexpected Response code");
        }
    }

    public static void verifyGetFileInfo(OrderPojo getInfo,String bookId,String customerName){
        Assert.assertTrue(bookId.contains(getInfo.getBookId()+""));
        Assert.assertTrue(customerName.contains(getInfo.getCustomerName()));
    }
}

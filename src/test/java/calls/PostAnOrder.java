package calls;

import groovy.transform.AutoImplement;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.OrderPojo;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class PostAnOrder extends DataStorage{

    Response response;
    @Test(dataProvider = "PostAnOrderPositive",groups="regression")
    public void test1(int bookId,String customerName){
        /*
        POST /orders/
Authorization: Bearer <YOUR TOKEN>

{
  "bookId": 1,
  "customerName": "John"
}
         */
//        response=given().baseUri(ConfigReader.getInstance().getUri())
//                .and().contentType(ContentType.JSON)
//                .and().header("Authorization","Bearer "+ ConfigReader.getInstance().getToken())
//                .and().body("{\n" +
//                        "  \"bookId\": 1,\n" +
//                        "  \"customerName\": \"John\"\n" +
//                        "}")
//                .when().post("/orders/");
//        response.prettyPrint();
//        System.out.println(response.getStatusLine());
//        Assert.assertEquals(response.getStatusCode(),201);
//        String createdResp=response.body().jsonPath().getString("created");
//        String orderId=response.body().jsonPath().get("orderId");
//      //  System.out.println(createdResp);
//        Assert.assertEquals(createdResp,"true");
//        Assert.assertNotNull(orderId);
        OrderPojo orderPojo=new OrderPojo(bookId,customerName);
        System.out.println("Post Order:");
        response= utilities.RequestMethod.postOrderRequest(orderPojo);//method will help to send request
        response.prettyPrint();
        OrderPojo responseData=response.body().as(OrderPojo.class);//pojo post call
      //  System.out.println(responseData.getOrderId());
        utilities.ResponseMethod.verify201Status(response);
        utilities.ResponseMethod.verifyOrderSuccess(responseData);
       // System.out.println(responseData.getCreated());

        System.out.println("Get Call Order:");
        response=utilities.RequestMethod.getAnOrderInfo(responseData);
        response.prettyPrint();
        OrderPojo getResponseData=response.body().as(OrderPojo.class);//get response
        response.prettyPrint();
        utilities.ResponseMethod.verify200Status(response);//validation for resp code
        utilities.ResponseMethod.verifyGetOrderInfo(responseData,customerName,bookId,getResponseData);

    }
    @Test(dataProvider = "PostNegativeValues")
    public void negativePost(int bookId,String customerName){
        /*
        Post call with invalid values
        will validate response code
        validate error code response
         */

OrderPojo requestBody=new OrderPojo(bookId,customerName);
response=utilities.RequestMethod.postOrderRequest(requestBody);
response.prettyPrint();
       // System.out.println(response.getStatusLine());
        utilities.ResponseMethod.verify400Status(response);
        OrderPojo responseInfo=response.body().as(OrderPojo.class);
        System.out.println(responseInfo.getError());
        utilities.ResponseMethod.verifyInvalidErrorMsg(responseInfo);
    }
}

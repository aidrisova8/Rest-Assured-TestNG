package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.OrderPojo;

import static io.restassured.RestAssured.given;

public class RequestMethod {
    // post an Order
    public static Response postOrderRequest(OrderPojo orderPojo){
return given().baseUri(ConfigReader.getInstance().getUri())
        .and().contentType(ContentType.JSON)
        .and().header("Authorization","Bearer "+ ConfigReader.getInstance().getToken())
        .and().body(orderPojo)
        .when().post("/orders/");
    }

    public static Response getAnOrderInfo(OrderPojo orderPojo){
        return given().baseUri(ConfigReader.getInstance().getUri())
                .and().contentType(ContentType.JSON)
                .and().header("Authorization","Bearer "+ ConfigReader.getInstance().getToken())
                .and().body(orderPojo)
                .when().get("/orders/"+orderPojo.getOrderId());

    }

    //get method of list of book
    public static Response getListOfBook(){
        return given().baseUri(ConfigReader.getInstance().getUri())
                .and().contentType(ContentType.JSON)
                .and().get("/books");
    }
    /*
    GET /books/:bookId
     */
    public static Response getSingleIdBook(int id){
        return given().baseUri(ConfigReader.getInstance().getUri())
                .and().contentType(ContentType.JSON).and().get("/books/"+id);

    }
    public static Response postOrderRequestWithFileBody(String fileBody){
        return given().baseUri(ConfigReader.getInstance().getUri())
                .and().contentType(ContentType.JSON)
                .and().header("Authorization","Bearer "+ ConfigReader.getInstance().getToken())
                .and().body(fileBody)
                .when().post("/orders");
    }
}

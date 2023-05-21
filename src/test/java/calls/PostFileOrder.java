package calls;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.OrderPojo;
import utilities.RequestMethod;
import utilities.ResponseMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;

public class PostFileOrder {
    Response response;
    @Test
    public void postFileBodyOrder() throws IOException {
      /*
      Post Order
        {
  "bookId": 1,
  "customerName": "John"
}
       */


        String readerBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\aydi1\\Desktop\\RequestBody.json")));
        System.out.println("Request:\n" + readerBody);
        response = RequestMethod.postOrderRequestWithFileBody(readerBody);
        System.out.println("Response: ");
        response.prettyPrint();
        ResponseMethod.verify201Status(response);
        OrderPojo responseInfo = response.body().as(OrderPojo.class);
        System.out.println(responseInfo.getCreated());
        ResponseMethod.verifyOrderSuccess(responseInfo);
        System.out.println("Get call Book Order Id Information:");
        response = RequestMethod.getAnOrderInfo(responseInfo);
        response.prettyPrint();
        OrderPojo getResponse=response.body().as(OrderPojo.class);
        /*
        We are going to retrieve data from json file
        Ex: cuatomerName=Patel
        Ex; bookId=1
         */
        JsonParser jsonParser=new JsonParser();//coming from gson google dependency
        Object object=jsonParser.parse(readerBody);
        JsonObject jsonObject=(JsonObject) object;//coming from gson google dependency key value
        String customerName=String.valueOf(jsonObject.get("customerName"));
        String bookId=String.valueOf(jsonObject.get("bookId"));
        System.out.println(customerName);
        System.out.println(bookId);
        ResponseMethod.verify200Status(response);
        ResponseMethod.verifyGetFileInfo(getResponse,bookId,customerName);

    }
}

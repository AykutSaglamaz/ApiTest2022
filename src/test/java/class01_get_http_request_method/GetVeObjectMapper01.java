package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetVeObjectMapper01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        Url'e GET Request gonder
    Then
       Status code is 200
       And response body is like {
                                    "userId": 10,
                                    "id": 198,
                                    "title": "quis eius est sint explicabo",
                                    "completed": true
                                  }
     */

    @Test
    public void getVeObjectMapper01(){
        //1. adim url'i set et

        spec.pathParams("first", "todos", "second",198 );

        //2. adim beklenen (expected ) datayi set
        //1.yol
//         String beklenenData = "{\n" +
//                 " \"userId\": 10,\n" +
//                 "\"id\": 198,\n" +
//                 " \"title\": \"quis eius est sint explicabo\",\n" +
//                 "\"completed\": true\n" +
//                 "  }";
//
//      Map<String, Object> beklenenDataMap=   JsonUtil.jsoniJavayaCevir(beklenenData, HashMap.class);
//        System.out.println(beklenenDataMap);
        //2.yol
        JsonPlaceHolderTestData data = new JsonPlaceHolderTestData();
         String beklenenDataMap2  = data.beklenenDataStringFormatinda(10, "quis eius est sint explicabo", true );

        Map<String, Object> beklenenDataMap = JsonUtil.jsoniJavayaCevir(beklenenDataMap2, HashMap.class);
        System.out.println(beklenenDataMap);


      //3. adim: request gonder, response al
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

        Map<String, Object> actualMap = JsonUtil.jsoniJavayaCevir(response.asString(), HashMap.class);
        System.out.println(actualMap);

        //4.adim :assertion yap
        assertEquals(200, response.getStatusCode());
        assertEquals(beklenenDataMap.get("userId"), actualMap.get("userId"));
        assertEquals(beklenenDataMap.get("title"), actualMap.get("title"));




    }
}

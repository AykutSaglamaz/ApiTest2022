package class05_delete_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            Url'e DELETE Request gonder
        Then
            Status code is 200
            And Response body is {}
     */
    @Test
    public void delete01(){
        //1. adim: url'i set et
        spec.pathParams("first", "todos", "second", 198);
        //2.adim: expected datayi set et
        Map<String, Object> beklenenDataMap = new HashMap<>();
        System.out.println(beklenenDataMap);

        //3. adim: request gonder, respond al
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Gson ==> De-Serialization yapiyouruz

        Map<String, Object> actualMap= response.as(HashMap.class);
        System.out.println(actualMap);

        //4. Assertion yap /verification

        response.then().assertThat().statusCode(200);

        assertEquals(beklenenDataMap, actualMap);
        assertTrue(actualMap.size()==0);



    }
}

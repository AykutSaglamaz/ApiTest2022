package class04_patch_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
       /*
    Given
	        https://jsonplaceholder.typicode.com/todos/198
	        {
                "title": "Wash the dishes"
            }
    When
	 		I send PATCH Request to the Url
	Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */
    @Test
    public void patch01(){
        //1. Url'i set et
        spec.pathParams("first", "todos", "second", 198);
        //2. request body set et
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataSetUpKismiKey(null, "Wash the dishes", null);
        System.out.println(requestBodyMap);

        //3.adim: request gonder, response al
       Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
       response.prettyPrint();

       //4. adim: assetion yap
        //1. mantik: dokunmadigimiz (degistirmedigimiz) datayi assert etmeye gerek yoktur
        response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")) );

        //2. mantik:
        Map<String, Object> beklenenDataMap =   requestBody.expectedDataSetUpTumKey(10,"Wash the dishes", true );
        response.then().assertThat().statusCode(200).body("userId", equalTo(beklenenDataMap.get("userId")),
                "title", equalTo(beklenenDataMap.get("title")),
                "completed", equalTo(beklenenDataMap.get("completed")));

        // Gson kullanarak assertion yap
        Map<String, Object> dataMap =  response.as(HashMap.class);
        System.out.println(dataMap);

        assertEquals(requestBodyMap.get("title"), dataMap.get("title"));


    }

}

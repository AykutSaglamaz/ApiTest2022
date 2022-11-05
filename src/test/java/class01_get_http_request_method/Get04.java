package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {
        /*
    When
	 	GET request gonder =>  REST API URL https://jsonplaceholder.typicode.com/todos
    And
        Accept (Kabul edilen) type : “application/json”
	Then
	    HTTP Status Code : 200
	And
	    Response formati : “application/json”
	And
	     200 tane todos olmali
	And
	    “quis eius est sint explicabo”, todos'lardan biri olmali
	And
	    userIds'ler arasinda 2, 7, ve 9 bulunmali

	      //1.adim: url'e set et

        //2.adim:beklenen datayi set et

        // 3.adim: get request gonder ve get response al

        //4. assertion yap
     */
    @Test
    public void get04(){
        //1.adim: url'e set et
        spec.pathParam("birinci", "todos");

        //2.adim:beklenen datayi set et

        // 3.adim: get request gonder ve get response al
         Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{birinci}");
         response.prettyPrint();

        //4. assertion yap
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasSize(200)).
                body("title", hasItem("quis eius est sint explicabo")).
                body("userId", hasItems(2, 7, 9));


    }

}

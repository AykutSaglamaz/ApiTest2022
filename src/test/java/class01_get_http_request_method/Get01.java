package class01_get_http_request_method;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
// Junit bir framework (cerceve)dir ve test yapmamizi kollaylastirir

public class Get01 {
    /*
                API testing'de Gherkin dilini kullaniriz
    Gherkin dilinde bazi anahtar kavramlar kullanacagiz: Given, when, Then, And
     (size bir kitap verildi (given), bu kitabi okudugunuzda (when), basarili olursunuz (then)
     Given : On kosullari bildirir (sartlar, baslangic)
     When  : Hareketleri (yapacaginiz isi) bildirmek icin kullanilir
     Then:  Sonuc icin kullanilir
     And : Coklu  'Given, When, Then' icin kullanilir
     */
/*          Task ==> Test case olusturma
       Given
           https://restful-booker.herokuapp.com/booking/3
       When
           Kullanici GET Request'i Url'e (APi) gonderir
           User send a GET Request to the url (API)
       Then
            HTTP Statu Kodu 200 olmali
           HTTP Status Code should be 200
       And
           Content Type'i JSON olmali
           Content Type should be JSON
       And
           Statu Line(duzeyi) HTTP/1.1 200 OK olmali
           Status Line should be HTTP/1.1 200 OK
    */
@Test
    public void get01(){
    //1. adim: set the url
    String url = "https://restful-booker.herokuapp.com/booking/3";

    //2.adim: Beklenen data (expected data) set et

    //3. Get request gonderilir ve Get Response alinir

    Response response =  given().when().get(url);
    response.prettyPrint();

    //4.adim : assertion yap
    /*
    Eger Assertion'da coklu hata varsa, kodun calismasi ilk hata durur, sonraki kodlar calismaz
    Yani ikinci, ucuncu gibi hatalar hakkinda hicbir bilgi alamayiz, Bu aslinda iyi bisaey degildir
    Bu nedenle bu tip Assertion'a "hard assertion" denir.
    Diger tip assetion ise "Soft Assetion"dir
    Soft assertion (verification)da butun kodlar calisir/kosar ve butun assertionlar icin bir hata raporu alirsiniz
     */
    response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

    System.out.println("Status Code: "+ response.getStatusCode());
    System.out.println("Content-Type: "+ response.getContentType());
    System.out.println("Status Line: "+ response.getStatusLine());
    System.out.println("Time: "+ response.getTime());

    System.out.println("Headers: \n "+ response.getHeaders());
    System.out.println("Via: "+ response.getHeader("Via"));



}

}

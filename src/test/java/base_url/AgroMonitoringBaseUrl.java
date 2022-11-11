package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgroMonitoringBaseUrl {
   // base url baska bir sinifa olustururum ve ihtiyacim oldugunda gider kullanirim

    // RequestSpecification data type bir obje olusturulur
    protected RequestSpecification spec;

    //Eger methodun urunde @Before anotation kullanirsaniz, bu method her bir test methoddan once calir
    //@Before anotation ne kullanirsiniz
    //Cevap: Eger ben bir methodun herbir test methodundan once calismasini istiyorsam @Before anotation  kullanirim

    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();

    }

}

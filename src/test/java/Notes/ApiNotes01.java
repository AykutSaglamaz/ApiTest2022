package Notes;

public class ApiNotes01 {
/*
	 API : Application Programing Interface: uygulama programlama Arayuzu

	 API'larin arayuzleri yoktur (bu yuzden programlama Arayuzu denir)
	 API kullanirken kullanici hicbir hareketlilik gormeyiz, fakat ne yaptiginizi biliriz)
	 API'ler birbirleriyle iletisim kurmak icin vardir.
	 orn:
	 	Amazon => API       API <= Visa
	 	(request=istek -->   <-- response=cevap)

	 	Request--Respond baglantisi su sekilde gerceklesir

	 	  Visa'dan API'a, API'dan API'a, API'dan Amazona
	 	  JSON data formati API icin kullanilan bir formattir, JSON data Java'daki Map gibidir
	 	  Ayrica XML (extensible markup language)  format kullanilir ve HTML (Hypertext Markup Language) sintaksi gibidir
	 	API'lar XML veya JSON data formatlarini request olarak kabul eder
	 	API'lar XML veya JSON data formatlarini respond olarak uretir

	 	API icin yaygin kutuphaneler  RestAssured, restful

	 	Bir API olusturdugumuzda , bizin URL veya endpoint'a ihtiyacimiz var
	 	API, database'de yeni datalar (insert) olusturabilir
	 	API, database'de bazi datalari (update) guncelleyebilir
	 	API, database'deki bazi datalari (delete) silebilir
	 	API, database'den datalari (read/get) okuyabilir
	 		1) Insert -->  url
	 		2) update -->  url
	 		3) Delete -->  url
	 		4) Delete -->  url

	 	Bu islemler icin URL'e ihtiyacimiz var URL'lere 'Endpoint' denir
	 	 orn: Guncelleme islemi icin 10 URL, datayi okumak icin 20 URL olabilir

	 	Insert (olustur) islemleri icin birden fazla URL ihtiyacimiz var
	 	Neden? Cunku bazen database'e ogrenci, ebeveyn, ders notlari eklemek isteriz, bu yuzden her biri icin ayri ayri URL ihtiyacimiz var
	 	 Farkli her insert (olustur) islemi icin, birer tane endpoint(URL) olusturulur.

	            ---Mulakat Sorusu---Atomasyon Tester olarak ne yaparsiniz
	 Atomasyon Tester olarak butun ENDPOINT'lar icin farkli senaryolar deneyerek beklenen sonucu verdigini test/check ederiz
	 Genellikle bu senaryolar gereksinim formunda (requirement document) size verilir (ama bazen bunlarin disinda da senaryo denemeniz lazim)
	 	----------------------------------------------------------------------------
	==> Swagger dokumanlari API icin siklikla kullanilir. -> https://petstore.swagger.io/
    Swagger dokumanlarini butun detaylari icin check edilir.Dokumana gore test case olustur, manuel olarak test et sonra otomasyon icin kod yaz

            ==> HTTP Request Methodlari:
    1) GET method: DataBase'den data almak/okumak icin kullanilir. (Read --> R in CRUD)
    	GET Methodunu kullanmak icin sadece Endpoint'e ihtiyacimiz var (endpoint = URL)

    2) POST method: DataBase'de yeni data eklemek icin kullanilir. (Create --> C in CRUD)
    	POST Request kullanmak/gondermek icin Endpoint + Request Body'ye ihtiyacimiz var

    3) PUT method: DataBase'deki tum data'yi guncelemek icin kullanilir. (Update --> U in CRUD)
    	orn: Database'de fName,lName,address var ve siz butun bunlari guncellemek istiyorsaniz bu durumda PUT kullanilir.
		PUT Request kullanmak/gondermek icin Endpoint + Fully Request Body'ye ihtiyacimiz var

    4) PATCH method: Database'deki data'yi kismi olarak gunceller. (Update --> U in CRUD) use PATCH to partial update.
    	orn:Database'de fName,lName,address var ve siz sadece bir alani (fName) guncellemek istiyorsaniz bu durumda PATCH kullanilir
    	PATCH Request kullanmak/gondermek icin Endpoint + Partial Request Body'ye ihtiyacimiz var

    5) DELETE method: Database'den data silmek icin kullanilir. (Delete --> R in CRUD)
     	DELETE request kullanmak/gondermek icin sadece Endpoint'e ihtiyacimiz var


    Petstore API response olarak sadece JSON data uretir.

     **Mulakat Q:
     GET/PUT/PATCH.. METHOD'larini kullanmak icin neye ihtiyacimiz var
    	Sadece Endpoint or Endpoint + Body ... (Cevaba yukaridaki kismi soyleyin)
            ----------------------------------------
              ==> HTTP  Statu Kodlari (Status Codes):
            1XX :  request alindi ve surec devam ediyor.
        	2XX : islem basarili bir sekilde alindi, anlasildi ve kabul edildi
        	        (Herseyin OK oldugunu API icin bir problem olmadigini ifade eder. Beklenen statu kodudur
            3XX : requestin tamamlanmasi icin daha ileri bir islem gerekli. (Bu kodu alirsaniz, lutfen request'e tekrar bakiniz, bakin bir sey eksik mi?)
                    (Bu statu kodunu alirsaniz, bir seyleri yanlis yaptiginizi anlayin, manajere veya takim liderine soylemeyin)
            4XX : Request'in dogru olmayan bir sintaks icerdigi ve islemin tamamlanamayacagini ifade eder.
                    (Eger bunu alirsaniz, requesti tekrar kontrol edin)
            5XX : Server'in coktugunu ifade eder. (Sunu yapin: "Ben 5XX error aliyorum, baska bu problemi yasayan var mi"
                    Eger bu kodu alirsaniz serverin duzeltilmesi lazim sizin yapacaginiz bir sey yoktur)

            *** NOTE:***
    INTERVIEW Q:  API testing'de ,ilk neyi test edersiniz?
    API testing'de, STATUS CODE ilk once test edilmeli.
    Eger statu kodu 2XX  ise sonra detaylari test edebilirsiniz.
----------------------------------------------------------------
    POSTMAN: It is an app to test endpoints of an API, MANUALLY. It is MANUAL TESTING APP.
            postman API endpointlerini test etmek icin kullanilan bir applikasyondur ve sadece MANUEL test icin kullanilir
    REST ASSURED LIBRARY : Java da var olan bir kutuphanedir ve automation ile endpoints test edilir.

    Q: API testi nasil yapilir?
     Automation icin; Rest assured library kullanirim,  manual test icin ise; POSTMAN kullanirim.
    Once manuel olrak test yapilmali daha sonra manuel testin sonucuna gore automation SCRIPT yazilir.
    UTOMATION script yazmak icin once Manual test yapilir.

            QA: uses Postman and RestAssured.
    Manual testers: Uses only POSTMAN.
            POSTMAN:  If there is minor differences, you ask ur manager if it's ok. because minor differences won't affect it.
            Body:
    {
        "sold": 20,
            "placed": 1,
            "string": 159,
            "Busy": 1,
            "pending": 19,
            "available": 686,
            "available;pending;sold": 1,
    }
    Time is used for PERFORMANCE TESTING, just know that. They give a time, you check if it executes in that time or not.
        ------------------------
    REQUEST Body must match RESPONSE Body

*/
}

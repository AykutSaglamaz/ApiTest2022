package class06_pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
// Pojo'ya diyorum ki json formatindan pojo formatina  donustururken, eger bazi degiskenleri tanimiyorsan (unknown) onlari, gormemezlikten gel
public class JsonPlaceHolderPojo {
    //POJO=> plain old java object
    /*
    POJO icinde private degiskenler butun parametrelere sahip constructor ve
    parametreleri olmayan constructor , getter ve setter, ile toString() kullanilmali
     */
    //private degisken olustur
    private Integer userId;
    private String title;
    private Boolean completed;

    // tum parametrelere sahip constructor

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
    // parametresiz constructor

    public JsonPlaceHolderPojo() {
    }
// generate getter ve setter
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    //toString  olustur


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

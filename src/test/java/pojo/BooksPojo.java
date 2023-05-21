package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BooksPojo {
    @SerializedName("id")
    @Expose private int id;

    @SerializedName("name")
    @Expose private String name;

    @SerializedName("type")
    @Expose private String type;

    @SerializedName("available")
    @Expose private Boolean available;

    @SerializedName("error")
    @Expose private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

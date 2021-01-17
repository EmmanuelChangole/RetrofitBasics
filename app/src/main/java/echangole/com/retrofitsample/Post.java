package echangole.com.retrofitsample;

import com.google.gson.annotations.SerializedName;

public class Post
{
    @SerializedName("userId")
    private int UserId;
    private int id;
    private String title;
    @SerializedName("body")
    private String text;

    public int getUserId() {
        return UserId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }



}

package echangole.com.retrofitsample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolder
{

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId, //?,//
            @Query("_sort") String sort,//&
            @Query("_order") String order//&

    );

    @GET("posts")
    Call<List<Post>> getPosts
            (
                    @QueryMap Map<String ,String> parameters

                    );

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);//Sending a post using a Post object

    @FormUrlEncoded //Sending post request using a form encoded
    @POST("posts")
    Call<Post>  createPost(@Field("userId") int userId,
                           @Field("title") String title,
                           @Field("body") String text);

    @FormUrlEncoded
    @POST("posts") //Sending  a post request using a map
   Call<Post> createPost(@FieldMap Map<String,String> fields);






}

package echangole.com.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  TextView textView;
   private JsonPlaceHolder jsonPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.tvPosts);

        Retrofit retrofit=new Retrofit.
                Builder().
                baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       jsonPlaceHolder=retrofit.create(JsonPlaceHolder.class);

       //getPosts();
        getComment();





    }

    private void getComment()
    {
        Call<List<Comment>> call=jsonPlaceHolder.getComments("posts/3/comments");
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("Code"+response.code());
                    return;
                }

                List<Comment> comments=response.body();
                for(Comment comment:comments)
                {
                    String content="";
                    content+="Id:"+comment.getId()+"\n";
                    content+="PostId:"+comment.getPostId()+"\n";
                    content+="name:"+comment.getName()+"\n";
                    content+="email:"+comment.getEmail()+"\n";
                    content+="Body:"+comment.getText()+"\n"+"\n";
                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t)
            {
                textView.setText(t.getCause().getMessage());

            }
        });

    }

    private void getPosts()
    {
        Map<String ,String> parameters=new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("order","desc");
        Call<List<Post>> call=jsonPlaceHolder.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                if(!response.isSuccessful())
                {
                    textView.setText("code"+response.code());
                    return;
                }

                List<Post> posts= response.body();
                for(Post post:posts)
                {
                    String content="";
                    content+="Id: "+post.getId()+"\n";
                    content+="User id: "+post.getUserId()+"\n";
                    content+="Title:" +post.getTitle()+"\n";
                    content+="Body: "+post.getText()+"\n";

                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t)
            {
                textView.setText(t.getCause().getMessage());

            }
        });
    }
}
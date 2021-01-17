package echangole.com.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  TextView textView;

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
        JsonPlaceHolder jsonPlaceHolder=retrofit.create(JsonPlaceHolder.class);

        Call<List<Post>> call=jsonPlaceHolder.getPosts();
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
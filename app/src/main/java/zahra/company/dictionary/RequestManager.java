package zahra.company.dictionary;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import zahra.company.dictionary.Model.APIResponse;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }
    public Single<List<APIResponse>>  getWordMeaning(String word){
        CallDictionary callDictionary=retrofit.create(CallDictionary.class);
         Single<List<APIResponse>> call =callDictionary.callMeanings(word);
        return call;

    }

    public interface CallDictionary{
        @GET("entries/en/{word}")
        Single<List<APIResponse>> callMeanings(
                 @Path("word") String word
        );

    }
}

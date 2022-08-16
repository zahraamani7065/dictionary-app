package zahra.company.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zahra.company.dictionary.Adapters.MeaningAdapter;
import zahra.company.dictionary.Adapters.PhoneticAdapter;
import zahra.company.dictionary.Model.APIResponse;

public class MainActivity extends AppCompatActivity {
   SearchView search_view;
   TextView textView_word;
   RecyclerView rv_phonetics,rv_meanings;
   ProgressDialog progressDialog;
   PhoneticAdapter phoneticAdapter;
   MeaningAdapter meaningAdapter;
   Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_view=findViewById(R.id.search_view);
        textView_word=findViewById(R.id.textView_word);
        rv_meanings=findViewById(R.id.rv_meanings);
        rv_phonetics=findViewById(R.id.rv_phonetics);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("loading...");
        progressDialog.show();
        RequestManager manager=new RequestManager(MainActivity.this);
        manager.getWordMeaning("hello")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<APIResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onSuccess(List<APIResponse> apiResponses) {
                        progressDialog.dismiss();
                        if(apiResponses==null){
                            Toast.makeText(MainActivity.this, "no data found", Toast.LENGTH_SHORT).show();
                            return;}

                        showData(apiResponses);

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        ;
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                progressDialog.setTitle("Fetching response for "+ query);
                progressDialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getWordMeaning(query)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<APIResponse>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                    disposable=d;
                            }

                            @Override
                            public void onSuccess(List<APIResponse> apiResponses) {
                                progressDialog.dismiss();
                                if(apiResponses==null){
                                Toast.makeText(MainActivity.this, "no data found", Toast.LENGTH_SHORT).show();
                                return;}

                                showData(apiResponses);
                            }

                            @Override
                            public void onError(Throwable e) {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"no data found", Toast.LENGTH_SHORT).show();
                            }
                        });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;


            }
        });




    }
    private void showData(List<APIResponse>  apiResponse) {
         textView_word.setText("Word: "+apiResponse.get(0).getWord());
         rv_phonetics.setHasFixedSize(true);
         rv_phonetics.setLayoutManager(new GridLayoutManager(this,1));
         phoneticAdapter=new PhoneticAdapter(this,apiResponse.get(0).getPhonetics());
         rv_phonetics.setAdapter(phoneticAdapter);

        rv_meanings.setHasFixedSize(true);
        rv_meanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter=new MeaningAdapter(this,apiResponse.get(0).getMeanings());
        rv_meanings.setAdapter(meaningAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
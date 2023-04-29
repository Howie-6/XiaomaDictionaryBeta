package com.itcast.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.itcast.dictionary.Adapters.MeaningAdapter;
import com.itcast.dictionary.Adapters.PhoneticsAdapter;
import com.itcast.dictionary.Models.APIResponse;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SearchView search_view;
    TextView textView_word;
    RecyclerView recycler_phonetics, recycler_meanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstAlert();

        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        recycler_meanings = findViewById(R.id.recycler_meanings);
        progressDialog = new ProgressDialog(this);

        Random rand = new Random();
        Words words=new Words();
        ArrayList<String> anyword=new ArrayList<>();
        words.all_words(anyword);

        progressDialog.setTitle("正在加载中...");
        progressDialog.show();
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener, anyword.get(rand.nextInt(anyword.size())));

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("在搜索 " + query +" 这个单词");
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void FirstAlert() {
        AlertDialog dialog=new AlertDialog.Builder(MainActivity.this)
                .setTitle("注意！")
                .setMessage("此应用程序是小马开发的，请勿外传！")
                .setPositiveButton("俺晓得啦",null).create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.YELLOW);
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if (apiResponse == null) {
                Toast.makeText(MainActivity.this, "No data found!!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        textView_word.setText("Word: " + apiResponse.getWord());
        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());

        recycler_phonetics.setAdapter(phoneticsAdapter);

        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        recycler_meanings.setAdapter(meaningAdapter);
    }
}
package app.mrquan.datastorage.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mrquan.datastorage.R;
import app.mrquan.datastorage.pojo.Book;
import app.mrquan.datastorage.util.BookAdapter;
import app.mrquan.datastorage.util.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private MyDatabaseHelper databaseHelper;
    private BookAdapter adapter;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        databaseHelper = new MyDatabaseHelper(this,"data.db",null,1);
        books = new ArrayList<>();
        adapter = new BookAdapter(MainActivity.this,R.layout.book_listview,books,databaseHelper);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        init();

    }
    private void init(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                books.clear();
                books.addAll(databaseHelper.list());
                adapter.notifyDataSetChanged();
            }
        });
    }
}

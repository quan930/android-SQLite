package app.mrquan.datastorage.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.mrquan.datastorage.R;
import app.mrquan.datastorage.pojo.Book;
import app.mrquan.datastorage.util.MyDatabaseHelper;

public class AddActivity extends AppCompatActivity {
    private EditText editTextID,editTextName,editTextPrice;
    private Button button;
    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTextID = findViewById(R.id.editText_id);
        editTextName = findViewById(R.id.editText_name);
        editTextPrice = findViewById(R.id.editText_price);
        button = findViewById(R.id.add_book);
        databaseHelper = new MyDatabaseHelper(this,"data.db",null,1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book(editTextID.getText().toString(),editTextName.getText().toString(),Double.valueOf(editTextPrice.getText().toString()));
                int add = databaseHelper.add(book);
                Toast.makeText(AddActivity.this,add==0?"失败":"成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        onDestroy();
    }
}

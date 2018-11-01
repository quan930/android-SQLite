package app.mrquan.datastorage.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.mrquan.datastorage.R;
import app.mrquan.datastorage.pojo.Book;


public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;
    private List<Book> books;
    private MyDatabaseHelper databaseHelper;

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects,MyDatabaseHelper databaseHelper) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.books = objects;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Book book= getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);//加载子布局
        }
        TextView textViewId = convertView.findViewById(R.id.book_id);
        TextView textViewName = convertView.findViewById(R.id.book_name);
        TextView textViewPrice = convertView.findViewById(R.id.book_price);
        Button button = convertView.findViewById(R.id.book_button);
        if (book!=null){
            textViewId.setText(book.getId());
            textViewName.setText(book.getName());
            textViewPrice.setText(String.valueOf(book.getPrice()));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("asd", "onClick: ");
                databaseHelper.delete(book.getId());
                books.remove(book);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}

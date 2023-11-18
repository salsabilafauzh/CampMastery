package com.example.CampMastery.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.CampMastery.Model.Bookmark;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;
import com.example.CampMastery.Session.SessionManager;
import com.example.CampMastery.adapter.BookmarkAdapter;
import com.example.CampMastery.db.DbHelper_User;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    private RecyclerView rv;
    private BookmarkAdapter adapter;
    private ArrayList<Bootcamp> list = new ArrayList<>();
    private DbHelper_User dbBookmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        dbBookmark = new DbHelper_User(this);
        list.addAll(dbBookmark.getBookmarkedBootcamps());
        rv = findViewById(R.id.rv_card_Bookmark);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));
        BookmarkAdapter adapter = new BookmarkAdapter(list, this);
        rv.setAdapter(adapter);

    }
}

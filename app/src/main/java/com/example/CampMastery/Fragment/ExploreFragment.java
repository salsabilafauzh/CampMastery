package com.example.CampMastery.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.R;
import com.example.CampMastery.adapter.ExploreAdapter;
import com.example.CampMastery.db.DbHelper_User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
public class ExploreFragment extends Fragment {

    private DbHelper_User dbBootcamp;
    private RecyclerView rv;
    private ArrayList<Bootcamp> list = new ArrayList<>();

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbBootcamp = new DbHelper_User(requireContext());

        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        boolean dataAdded = sharedPreferences.getBoolean("data_added", false);

        if (!dataAdded) {
            dbBootcamp.addBootcamp("Dicoding", "Ini Deskripsi", "10-11-2023"
                    , "11-11-2023", R.drawable.cover_bootcamp1);
            dbBootcamp.addBootcamp("Dicoding 2", "Ini Deskripsi 2", "10-11-2023", "11-11-2023"
                    , R.drawable.cover_bootcamp1);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("data_added", true);
            editor.apply();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        list.addAll(dbBootcamp.getAllBootcamps());
        rv = view.findViewById(R.id.rv_card);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        ExploreAdapter adapter = new ExploreAdapter(list, requireActivity());
        rv.setAdapter(adapter);

        return view;
    }
}

package com.ezzetech.getallcontactsrnd;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseAdapter mydb;
    SelectUserAdapter suAdapter;

    RecyclerView recyclerView;
    SearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb  = new DatabaseAdapter(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.contacts_list);
        setRecyclerview();
        search = (SearchView)findViewById(R.id.searchView);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String stext) {
                suAdapter.filter(stext);
                return false;
            }
        });

    }

    private void setRecyclerview() {
        new ConttactLoader().execute();
    }

    public class ConttactLoader extends AsyncTask<Void, Void, List<SelectUser>> {

        @Override
        protected List<SelectUser> doInBackground(Void... voids) {
            List<SelectUser> MHList = mydb.getData();
            return MHList;
        }

        @Override
        protected void onPostExecute(List<SelectUser> selectUsers) {
            if (selectUsers.isEmpty()==false){
                suAdapter = new SelectUserAdapter(MainActivity.this, selectUsers);

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(suAdapter);
            }
        }
    }
}

package com.prod.adelachour.ldap_gsh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;
import java.util.ArrayList;

public class MainListDepartement extends AppCompatActivity {


    ArrayList<DepartModel> DepartModels;
    ListView listView;
    private static CustomAdapterDepart adapter;
    String NomComplet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_departement);

        final LDAPConnection connection = Recpetion();



        listView=(ListView)findViewById(R.id.list);


        DepartModels= new ArrayList<>();


        /// ---------- Find ----------- \\\

        Filter filter = Filter.createEqualityFilter("objectClass", "groupOfNames");


        SearchRequest searchRequest =
                new SearchRequest("ou=system", SearchScope.SUB, filter,
                        "cn");
        SearchResult searchResult;


        try
        {

            searchResult = connection.search(searchRequest);
            SearchResultEntry[] searchResultEntries = new SearchResultEntry[searchResult.getEntryCount()];
            for (int i = 0; i < searchResult.getEntryCount(); i++){
                searchResultEntries[i] = searchResult.getSearchEntries().get(i);
            }

            for (int i = 0; i < searchResultEntries.length; i++){
                NomComplet = searchResultEntries[i].getAttributeValue("cn");
                System.out.println("NomComplet = "+NomComplet);

                DepartModels.add(new DepartModel(NomComplet));


            }




        }
        catch (LDAPSearchException lse)
        {
            // The search failed for some reason.

            searchResult = lse.getSearchResult();
            ResultCode resultCode = lse.getResultCode();
            String errorMessageFromServer = lse.getDiagnosticMessage();
            System.out.println("Error Real: "+errorMessageFromServer);
            System.out.println("Error Real: "+resultCode);

        }



        /// ---------- Find ----------- \\\





        adapter= new CustomAdapterDepart(DepartModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DepartModel departementModel = DepartModels.get(position);



                String nomDepart = departementModel.getName();


                Intent i = new Intent(MainListDepartement.this, EmployeOf.class);
                i.putExtra("departement", nomDepart);
                System.out.println("Nom du département: "+nomDepart);
                startActivity(i);



    }
});


    }

    public LDAPConnection Recpetion(){
        return ((ObjectWrapperForBinder)getIntent().getExtras().getBinder("object_value")).getData();
    }


}

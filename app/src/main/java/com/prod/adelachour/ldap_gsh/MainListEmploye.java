package com.prod.adelachour.ldap_gsh;

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

public class MainListEmploye extends AppCompatActivity {


    ArrayList<EmployeModel> EmployeModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_employe);

        LDAPConnection connection = Recpetion();



        listView=(ListView)findViewById(R.id.list);


        EmployeModels= new ArrayList<>();


        /// ---------- Find ----------- \\\

        Filter filter = Filter.createEqualityFilter("objectClass", "person");


        SearchRequest searchRequest =
                new SearchRequest("ou=employe,ou=system", SearchScope.SUB, filter,
                        "cn", "telephoneNumber","title");
        SearchResult searchResult;


        try
        {

            searchResult = connection.search(searchRequest);
            SearchResultEntry[] searchResultEntries = new SearchResultEntry[searchResult.getEntryCount()];
            for (int i = 0; i < searchResult.getEntryCount(); i++){
                searchResultEntries[i] = searchResult.getSearchEntries().get(i);
            }

            for (int i = 0; i < searchResultEntries.length; i++){
                String nom = searchResultEntries[i].getAttributeValue("cn");
                String tel = searchResultEntries[i].getAttributeValue("telephoneNumber");
                String title = searchResultEntries[i].getAttributeValue("title");
                System.out.println("Nom = "+nom+" - Tel = "+tel+" - Poste = "+title);

                EmployeModels.add(new EmployeModel(nom, tel, title));


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





        adapter= new CustomAdapter(EmployeModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EmployeModel employeModel = EmployeModels.get(position);

                //Find who


                //Snackbar.make(view, EmployeModel.getName()+"\n"+EmployeModel.getType()+" API: "+EmployeModel.getVersion_number(), Snackbar.LENGTH_LONG)
                  //      .setAction("No action", null).show();
            }
        });


    }

    public LDAPConnection Recpetion(){
        return ((ObjectWrapperForBinder)getIntent().getExtras().getBinder("object_value")).getData();
    }


}

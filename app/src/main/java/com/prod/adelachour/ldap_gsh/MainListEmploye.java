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

public class MainListEmploye extends AppCompatActivity {


    ArrayList<EmployeModel> EmployeModels;
    ListView listView;
    private static CustomAdapter adapter;
    String NomComplet, Tel, Title, nameD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_employe);

        final LDAPConnection connection = Recpetion();



        listView=(ListView)findViewById(R.id.list);


        EmployeModels= new ArrayList<>();


        /// ---------- Find ----------- \\\

        Filter filter = Filter.createEqualityFilter("objectClass", "person");


        SearchRequest searchRequest =
                new SearchRequest("ou=departement,ou=system", SearchScope.SUB, filter,
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
                 NomComplet = searchResultEntries[i].getAttributeValue("cn");
                 Tel = searchResultEntries[i].getAttributeValue("telephoneNumber");
                 Title = searchResultEntries[i].getAttributeValue("title");
                System.out.println("NomComplet = "+NomComplet+" - Tel = "+Tel+" - Poste = "+Title);


                EmployeModels.add(new EmployeModel(NomComplet, Tel, Title));


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

                String cn = ""; String tel = ""; String Poste = ""; String Prenom = ""; String Nom = ""; String HomePhone = ""; String HomePostalAdr = "" ; String Mail = "";


                //Find who
                Filter filter = Filter.createEqualityFilter("cn", employeModel.getName());
                SearchRequest searchRequest =
                        new SearchRequest("ou=departement,ou=system", SearchScope.SUB, filter,
                                "givenName", "sn", "homePhone", "homePostalAddress", "mail", "cn", "telephoneNumber", "title");
                SearchResult searchResult;

                try
                {
                    searchResult = connection.search(searchRequest);

                    for (SearchResultEntry entry : searchResult.getSearchEntries())
                    {
                        cn = entry.getAttributeValue("cn");
                        Prenom = entry.getAttributeValue("givenName");
                        Nom = entry.getAttributeValue("sn");
                        HomePhone = entry.getAttributeValue("homePhone");
                        HomePostalAdr = entry.getAttributeValue("homePostalAddress");
                        Mail = entry.getAttributeValue("mail");
                        tel = entry.getAttributeValue("telephoneNumber");
                        Poste = entry.getAttributeValue("title");
                    }
                }
                catch (LDAPSearchException lse)
                {
                    // The search failed for some reason.
                    searchResult = lse.getSearchResult();
                    ResultCode resultCode = lse.getResultCode();
                    String errorMessageFromServer = lse.getDiagnosticMessage();
                }

                String[] detailEmploye = new String[8];
                detailEmploye[0] = cn;
                detailEmploye[1] = Nom;
                detailEmploye[2] = Prenom;
                detailEmploye[3] = tel;
                detailEmploye[4] = HomePhone;
                detailEmploye[5] = HomePostalAdr;
                detailEmploye[6] = Mail;
                detailEmploye[7] = Poste;



                Intent i = new Intent(MainListEmploye.this, DetailEmploye.class);
                i.putExtra("detail", detailEmploye);
                startActivity(i);



                //Snackbar.make(view, EmployeModel.getName()+"\n"+EmployeModel.getType()+" API: "+EmployeModel.getVersion_number(), Snackbar.LENGTH_LONG)
                  //      .setAction("No action", null).show();
            }
        });


    }

    public LDAPConnection Recpetion(){
        return ((ObjectWrapperForBinder)getIntent().getExtras().getBinder("object_value")).getData();
    }


}

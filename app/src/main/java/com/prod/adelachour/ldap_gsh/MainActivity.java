package com.prod.adelachour.ldap_gsh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;
//import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPSearchResults;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button wel, find ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wel = (Button)findViewById(R.id.welcome);
        find = (Button)findViewById(R.id.find);

        wel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(MainActivity.this, Auth.class);
                //startActivity(i);

                ////

                final LDAPConnection connection = new LDAPConnection();
                try
                {

                    //InetAddress address = InetAddress.getByName("192.168.1.59");

                    //connection.connect(address, 10389, 2000);
                    connection.connect("192.168.1.59", 10389);
                    System.out.println("Here");
                }
                catch (LDAPException e)
                {
                    e.printStackTrace();
                    System.out.println("Connexion échouée");
                    //Toast.makeText(this, "Echouée", Toast.LENGTH_SHORT);
                }
              /*  catch (UnknownHostException ip)
                {
                  ip.printStackTrace();
                }*/



                // --- doesn't work ---
                if (connection.isConnected()){
                    Toast.makeText(MainActivity.this, "Connecté !", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(MainActivity.this, "Echoué", Toast.LENGTH_SHORT);

                }
                // -----




                find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ///
                        String name = "";
                        String mail = "";

                        Filter filter = Filter.createEqualityFilter("uid", "acadel");


                        SearchRequest searchRequest =
                                new SearchRequest("ou=system", SearchScope.SUB, filter,
                                        "cn", "mail");
                        SearchResult searchResult;

                        try
                        {

                            searchResult = connection.search(searchRequest);

                            for (SearchResultEntry entry : searchResult.getSearchEntries())
                            {
                                 name = entry.getAttributeValue("cn");
                                 mail = entry.getAttributeValue("mail");
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

                        System.out.println("Nom et mail: "+name+" - " +mail);


                        ///


                                           }
                });




            }
        });
    }
}

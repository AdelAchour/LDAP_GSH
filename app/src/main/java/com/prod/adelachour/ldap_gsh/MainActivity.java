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
import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPConnection;
import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPSearchResults;

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

                    connection.connect("192.168.1.59", 10389);
                    System.out.println("Here");
                }
                catch (com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPException e)
                {
                    e.printStackTrace();
                    System.out.println("Connexion échouée");
                    //Toast.makeText(this, "Echouée", Toast.LENGTH_SHORT);
                }

                if (connection.isConnected()){
                    Toast.makeText(MainActivity.this, "Connecté !", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(MainActivity.this, "Echoué", Toast.LENGTH_SHORT);
                }


                find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                                           }
                });


       /* String attributeName = "uniqueMember";
        LDAPAttribute attr = new LDAPAttribute(attributeName);
        String assertionValue = "uid=mahesh.joshi,ou=users,ou=sevenSeas,dc=example,dc=com";
        String entryDN = "cn=Java,ou=groups,ou=sevenSeas,dc=example,dc=com";
        CompareRequest compareRequest = new CompareRequest(entryDN, attributeName, assertionValue);
        CompareResult compareResult = null;
        try
        {
            //compareResult = connection.compare(compareRequest);
            boolean ompareResult = connection.compare(entryDN,attr);

            if (ompareResult)
            {
                System.out.println("The user: " + assertionValue + " is a member of the group: " + entryDN);
            }
            else
            {
                System.out.println("The user: " + assertionValue + " is NOT a member of the group: " + entryDN);
            }
        }
        catch (LDAPException e)
        {

            e.printStackTrace();
        }*/


                ///

            }
        });
    }
}

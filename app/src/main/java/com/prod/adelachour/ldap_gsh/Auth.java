package com.prod.adelachour.ldap_gsh;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.CompareRequest;
import com.unboundid.ldap.sdk.CompareResult;
import com.unboundid.ldap.sdk.SimpleBindRequest;
import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPAttribute;
import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPConnection;
import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPException;


public class Auth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        ////

        LDAPConnection connection = new LDAPConnection();
        try
        {
            connection.connect("localhost", 10389);
        }
        catch (com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPException e)
        {
            System.out.println("Connexion échouée");
            //Toast.makeText(this, "Echouée", Toast.LENGTH_SHORT);
        }

        if (connection.isConnected()){
            Toast.makeText(this, "Connecte!", Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(this, "Echouée", Toast.LENGTH_SHORT);
        }

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





}

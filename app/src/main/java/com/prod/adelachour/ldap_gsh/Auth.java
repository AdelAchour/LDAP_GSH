package com.prod.adelachour.ldap_gsh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;
import com.unboundid.ldap.sdk.LDAPConnection;




public class Auth extends AppCompatActivity {

    EditText ipEdit ;
    EditText portEdit;
    Button Connexion ;

    final static LDAPConnection connection = new LDAPConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        ipEdit = (EditText)findViewById(R.id.ip2);
        portEdit = (EditText)findViewById(R.id.port);
        Connexion = (Button)findViewById(R.id.buttonConnect);

        Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String textIP = ipEdit.getText().toString();
                int textPort = Integer.valueOf(portEdit.getText().toString());


                // -------- Connexion --------- \\\


                try
                {

                    connection.connect(textIP, textPort);
                    System.out.println("Here");
                    Toast.makeText(Auth.this, "Connecté avec succès !", Toast.LENGTH_SHORT).show();
                    System.out.println("Connection details: "+connection);
                }
                catch (LDAPException e)
                {
                    e.printStackTrace();
                    System.out.println("Connexion échouée");
                    Toast.makeText(Auth.this, "Echouée", Toast.LENGTH_SHORT).show();
                }

                if (connection.isConnected()){
                    System.out.println("True baby");

                    Intent i = new Intent(Auth.this, Accueil.class);
                    i.putExtras(Envoi(connection));

                    startActivity(i);

                }


                // -------- Connexion --------- \\\



            }
        });


    }

    public static Bundle Envoi(LDAPConnection connection){
        final Bundle bundle = new Bundle();
        bundle.putBinder("object_value", new ObjectWrapperForBinder(connection));

        return  bundle;
    }




}

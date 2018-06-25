package com.prod.adelachour.ldap_gsh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.unboundid.ldap.sdk.LDAPConnection;

public class Accueil extends AppCompatActivity {

    Button employes, departements ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        //Gson gson = new Gson();
        //final LDAPConnection connection = gson.fromJson(getIntent().getStringExtra("myjson"), LDAPConnection.class);

        final LDAPConnection connection = Recpetion();

        employes = (Button)findViewById(R.id.employes);
        departements = (Button)findViewById(R.id.departements);

        employes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(Accueil.this, connection.toString(), Toast.LENGTH_SHORT).show();
                System.out.println("Connection details: "+connection);

               Intent i = new Intent(Accueil.this, MainListEmploye.class);
               i.putExtras(Envoi(connection));

               startActivity(i);


            }
        });

        departements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public LDAPConnection Recpetion(){
        return ((ObjectWrapperForBinder)getIntent().getExtras().getBinder("object_value")).getData();
    }

    public static Bundle Envoi(LDAPConnection connection){
        final Bundle bundle = new Bundle();
        bundle.putBinder("object_value", new ObjectWrapperForBinder(connection));

        return  bundle;
    }
}

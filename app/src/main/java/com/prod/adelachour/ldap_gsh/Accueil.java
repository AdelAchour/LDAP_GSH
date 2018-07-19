package com.prod.adelachour.ldap_gsh;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.unboundid.ldap.sdk.LDAPConnection;

public class Accueil extends AppCompatActivity {

    Button employes, departements ;
    ImageButton Emp, departm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        final LDAPConnection connection = Recpetion();

       // employes = (Button)findViewById(R.id.employes);
        Emp = (ImageButton)findViewById(R.id.employes);
        departm = (ImageButton)findViewById(R.id.departm);
      //  departements = (Button)findViewById(R.id.departements);

        Emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Connection details: "+connection);

               Intent i = new Intent(Accueil.this, MainListEmploye.class);
               i.putExtras(Envoi(connection));

               startActivity(i);


            }
        });

        departm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Accueil.this, MainListDepartement.class);
                i.putExtras(Envoi(connection));

                startActivity(i);

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

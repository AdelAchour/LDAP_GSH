package com.prod.adelachour.ldap_gsh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailEmploye extends AppCompatActivity {


    TextView NomComplet, Nom, Prenom, Mobile, Domicile, Adresse, Mail, Poste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_employe);

        NomComplet = (TextView)findViewById(R.id.nomcomplet);
        Nom = (TextView)findViewById(R.id.nom);
        Prenom = (TextView)findViewById(R.id.prenom);
        Mobile = (TextView)findViewById(R.id.mobile);
        Domicile = (TextView)findViewById(R.id.domicile);
        Adresse = (TextView)findViewById(R.id.adresse);
        Mail = (TextView)findViewById(R.id.mail);
        Poste = (TextView)findViewById(R.id.poste);


        String[] detailEmploye = getIntent().getStringArrayExtra("detail");

        NomComplet.setText(detailEmploye[0]);
        Nom.setText(detailEmploye[1]);
        Prenom.setText(detailEmploye[2]);
        Mobile.setText(detailEmploye[3]);
        Domicile.setText(detailEmploye[4]);
        Adresse.setText(detailEmploye[5]);
        Mail.setText(detailEmploye[6]);
        Poste.setText(detailEmploye[7]);


    }
}

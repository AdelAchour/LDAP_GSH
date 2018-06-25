package com.prod.adelachour.ldap_gsh;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterDepart extends ArrayAdapter<DepartModel> implements View.OnClickListener {

    private ArrayList<DepartModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }



    public CustomAdapterDepart(ArrayList<DepartModel> data, Context context) {
        super(context, R.layout.row_list_depart, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DepartModel DepartModel=(DepartModel)object;




        switch (v.getId())
        {

            case R.id.profil:

                //  Snackbar.make(v, "Release date " +DepartModel.getFeature(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();

                break;


        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DepartModel DepartModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        CustomAdapterDepart.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new CustomAdapterDepart.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_list_depart, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomAdapterDepart.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.txtName.setText(DepartModel.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}

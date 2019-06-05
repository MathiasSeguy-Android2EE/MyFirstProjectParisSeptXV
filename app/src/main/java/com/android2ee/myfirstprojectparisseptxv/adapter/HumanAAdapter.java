/**
 * <ul>
 * <li>HumanAAdapter</li>
 * <li>com.android2ee.myfirstprojectparisseptxv.adapter</li>
 * <li>21/09/2015</li>
 * <p/>
 * <li>======================================================</li>
 * <p/>
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 * <p/>
 * /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ***************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * <p/>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */

package com.android2ee.myfirstprojectparisseptxv.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.myfirstprojectparisseptxv.R;
import com.android2ee.myfirstprojectparisseptxv.model.Human;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 21/09/2015.
 */
public class HumanAAdapter extends ArrayAdapter<Human> {
    /**
     * The inflater that inflates the xml to build view
     */
    LayoutInflater inflater;
    /***
     * The current
     */
    Resources resources;
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param data the list of human
     */
    public HumanAAdapter(Context context, ArrayList<Human>data) {
        super(context, R.layout.activity_main,data);
        inflater=LayoutInflater.from(context);
        resources=context.getResources();
    }

    /***********************************************************
     * GetView
     **********************************************************/

    /**
     * The temp current view
     */
    private static View rowView;
    /**
     * The current human
     */
    private  static Human human;
    /**
     * The current viewHolder
     */
    private ViewHolder viewHolder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //find human
        human=getItem(position);
        //build the view if needed
        rowView=convertView;
        if(rowView==null){
            //inflate
            if(getItemViewType(position)==0){
                rowView=inflater.inflate(R.layout.human_odd,parent,false);
            }else{
                rowView=inflater.inflate(R.layout.human_even,parent,false);
            }
            //link with viewholder
            viewHolder=new ViewHolder(rowView);
            rowView.setTag(viewHolder);
        }
        //update the view with human
        viewHolder= (ViewHolder) rowView.getTag();
        viewHolder.getImvPicture().setImageDrawable(resources.getDrawable(human.getPicture()));
        viewHolder.getTxvMessage().setText(human.getMessage());
        viewHolder.getTxvName().setText(human.getName());
        //and return
        return rowView;
    }

    /***********************************************************
     *  managing odd/even rows
     **********************************************************/
    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /***********************************************************
     *  ViewHolder design pattern
     **********************************************************/
    private class ViewHolder{
        private TextView txvName;
        private TextView txvMessage;
        private ImageView imvPicture;
        private View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        public ImageView getImvPicture() {
            if(imvPicture==null){
                imvPicture= (ImageView) convertView.findViewById(R.id.imvPicture);
            }
            return imvPicture;
        }

        public TextView getTxvName() {
            if(txvName==null){
                txvName= (TextView) convertView.findViewById(R.id.txvName);
            }
            return txvName;
        }

        public TextView getTxvMessage() {
            if(txvMessage==null){
                txvMessage= (TextView) convertView.findViewById(R.id.txvMessage);
            }
            return txvMessage;
        }
    }
}

/**
 * <ul>
 * <li>MainFragment</li>
 * <li>com.android2ee.myfirstprojectparisseptxv</li>
 * <li>25/09/2015</li>
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

package com.android2ee.myfirstprojectparisseptxv;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android2ee.myfirstprojectparisseptxv.adapter.HumanAAdapter;
import com.android2ee.myfirstprojectparisseptxv.model.Human;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 25/09/2015.
 */
public class MainFragment extends Fragment{
    /***********************************************************
     * Constant
     **********************************************************/
    /*
    * The tag for the log
    **/
    public static final String TAG = "MainActivity";
    public static final String KEY_MESSAGE = "MESSAGE";
    public static final String KEY_RESULT = "RESULT";

    /***********************************************************
     * Attributes
     **********************************************************/
    /**
     *  The button add*/
    Button btnAdd;
    /*
     * The edtMessage**/
    EditText edtMessage;
    /*
    * The ListView to display the list of added words**/
    ListView lsvResult;
    /***
     * The ArrayAdapter (the model in a way) of the listview
     */
    HumanAAdapter arrayAdapter;
    /**
     * The list of messages to display
     */
    ArrayList<Human> messages;
    boolean postICS,postJB,postLollipop;
    /***********************************************************
     *  Temp var
     **********************************************************/
    /**
     * The temp string*/
    String messageTemp;

    /***********************************************************
     * Manage Life Cycle
     **********************************************************/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        postICS = getResources().getBoolean(R.bool.postICS);
        postJB = getResources().getBoolean(R.bool.postJB);
        postLollipop = getResources().getBoolean(R.bool.postLollipop);
        //instanciate graphical components
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        edtMessage = (EditText) view.findViewById(R.id.edtMessage);
        lsvResult = (ListView) view.findViewById(R.id.lsvResult);
        messages = new ArrayList<Human>();
        arrayAdapter = new HumanAAdapter(getActivity(), messages);
        lsvResult.setAdapter(arrayAdapter);
//        for(int i=0;i<150;i++){
//            messages.add(new Human("mes n°"+i,i));
//        }
        //add the listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessage();
            }
        });
        lsvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelected(position);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null) {
            edtMessage.setText(savedInstanceState.getString(KEY_MESSAGE));
            messages.clear();
            for (Parcelable human : savedInstanceState.getParcelableArrayList(KEY_RESULT)) {
                messages.add((Human) human);
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_MESSAGE, edtMessage.getText().toString());
        outState.putParcelableArrayList(KEY_RESULT, messages);
    }


    /***********************************************************
     *  Business Method
     **********************************************************/

    /*
    * Copy paste the content of the edtMessage in the result area**/
    private void addMessage(){
        messageTemp=edtMessage.getText().toString();
        //insert in the list
        arrayAdapter.add(new Human(messageTemp,messages.size()));
        edtMessage.setText("");
    }

    /**
     * Copy the selected item in the edittext
     * @param position
     */
    private void itemSelected(int position){
        messageTemp=((Human)arrayAdapter.getItem(position)).getMessage();
        edtMessage.setText(messageTemp);
    }
}

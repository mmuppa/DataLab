package edu.uw.tacoma.mmuppa.datalab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.uw.tacoma.mmuppa.datalab.model.UserInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        ListView userInfo = (ListView) v.findViewById(R.id.user_info);
        List<UserInfo> list =   MainActivity.getUserList(v.getContext());

        ArrayAdapter<UserInfo> adapter = new ArrayAdapter<UserInfo>(v.getContext(),
                android.R.layout.simple_list_item_1
                , android.R.id.text1, list);
        userInfo.setAdapter(adapter);

        userInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserFragment userFragment = new UserFragment();
                Bundle args = new Bundle();
                args.putInt(UserFragment.POSITION, i);
                userFragment.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, userFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });


        //Read from file and set the text
       /* try {
            InputStream inputStream = v.getContext().openFileInput( getString(R.string.ACCT_FILE));
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new
                        InputStreamReader(inputStream);
                BufferedReader bufferedReader = new
                        BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) !=
                        null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                userInfo.setText(stringBuilder.toString()); }
        } catch (Exception e) { e.printStackTrace();
        }*/
        return v;
    }

}

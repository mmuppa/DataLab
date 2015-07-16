package edu.uw.tacoma.mmuppa.datalab;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.uw.tacoma.mmuppa.datalab.data.UserInfoDB;
import edu.uw.tacoma.mmuppa.datalab.model.UserInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    public final static String POSITION = "position";
    private int mPosition = -1;
    private TextView mEmail;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            mPosition = args.getInt(POSITION);
        }
        if (mPosition != -1) {
            updateUserView(mPosition);
        }
    }
    public void updateUserView(int id) {
        Activity activity = getActivity();
        mEmail = (TextView) activity.findViewById(R.id.email_text);
        TextView pwd = (TextView) activity.findViewById(R.id.pwd_text);
        List<UserInfo> list = MainActivity.getUserList(
                activity.getApplicationContext());

        if (id != -1) {
            UserInfo userInfo = (UserInfo) list.get(id);
            mEmail.setText(userInfo.getEmail());
            pwd.setText(userInfo.getPassword());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user, container, false);
        Button delete = (Button) v.findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoDB userInfoDB = new UserInfoDB(view.getContext());
                userInfoDB.deleteUserByEmail(mEmail.getText().toString());
                userInfoDB.closeDB();

                getFragmentManager().popBackStackImmediate();
            }
        });

        return v;

    }


}

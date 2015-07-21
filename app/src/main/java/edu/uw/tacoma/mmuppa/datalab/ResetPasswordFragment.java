package edu.uw.tacoma.mmuppa.datalab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.uw.tacoma.mmuppa.datalab.data.UserInfoDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {


    public static String EMAIL = "email";
    private String mEmail;
    private EditText mPwdText;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        mEmail = args.getString(EMAIL);
        TextView emailText = (TextView) getActivity().findViewById(R.id.email_text);
        emailText.setText(mEmail);
        mPwdText = (EditText) getActivity().findViewById(R.id.new_pwd_text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reset_password, container, false);
        Button resetButton = (Button) v.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEmail.length() != 0 && mPwdText.length() != 0) {
                    UserInfoDB userInfoDB = new UserInfoDB(view.getContext());
                    userInfoDB.updatePassword(mEmail, mPwdText.getText().toString());
                    userInfoDB.closeDB();

                    ((MainActivity) getActivity()).startMenu();
                }
            }
        });
        return v;
    }


}

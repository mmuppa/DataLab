package edu.uw.tacoma.mmuppa.datalab;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public interface MyMenuListener {
        public void startMenu();
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = (Button) v.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =
                        getActivity().getSharedPreferences(
                                getString(R.string.SHARED_PREFS), Context.MODE_PRIVATE);
                sharedPreferences
                        .edit()
                        .putBoolean(getString(R.string.LOGGEDIN), true)
                        .commit();

                ((MyMenuListener )getActivity()).startMenu();
            }
        });

        return v;
    }


}

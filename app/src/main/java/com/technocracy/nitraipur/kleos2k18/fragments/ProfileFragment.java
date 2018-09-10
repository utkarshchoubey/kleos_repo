package com.technocracy.nitraipur.kleos2k18.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.depth.Depth;
import com.github.florent37.depth.DepthProvider;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.activities.LoginActivity;
import com.technocracy.nitraipur.kleos2k18.model.User;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiBase;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiEndpoints;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;

import am.appwise.components.ni.NoInternetDialog;
import es.dmoral.toasty.Toasty;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private Depth depth;
    public ProfileFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserPreferences userPreferences=new UserPreferences(getContext());

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        this.depth = DepthProvider.getDepth(view);
        TextView nameV = (TextView) view.findViewById(R.id.nameV);
        TextView emailV = (TextView) view.findViewById(R.id.emailV);
        TextView phoneV = (TextView) view.findViewById(R.id.phoneV);
        TextView collegeV = (TextView) view.findViewById(R.id.collegeV);

        ApiEndpoints apiBase= ApiBase.getClient().create(ApiEndpoints.class);
        Call<User> userCall=apiBase.getDetails(userPreferences.getUsername());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if(response.isSuccessful()){
                    if(!String.valueOf(response.body().email).equals("")){
                        nameV.setText(String.valueOf(response.body().firstName).concat(" ".concat(String.valueOf(response.body().lastName)) ));
                        collegeV.setText(String.valueOf(response.body().college));
                        phoneV.setText(String.valueOf(response.body().username));
                        emailV.setText(String.valueOf(response.body().email));
                    }
                    else{
                        Toasty.error(getContext(), "Some Thing Went Wrong", Toast.LENGTH_SHORT, true).show();
                    }
                }else{
                    Toasty.error(getContext(), "Some Thing Went Wrong", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
               call.cancel();
                NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(getContext()).build();
            }
        });

        return  depth.setupFragment(10f, 10f, view);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        depth.onFragmentReady(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


}

package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentGastosWeek.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentGastosWeek#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGastosWeek extends android.support.v4.app.Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_gastos_week, container, false);
            return view;
        }
    }

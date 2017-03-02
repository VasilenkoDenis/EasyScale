package ua.com.ics_market.easyscale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    private int page;
    private String title;
    TextView tvTitle;
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(int page) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("Title","Калькулятор");
        args.putInt("Page", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("Page",0);
            title = getArguments().getString("Title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }
}

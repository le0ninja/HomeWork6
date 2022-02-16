package ru.gb.homework6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class NoteDescription extends Fragment {


    public static final String ARG_NOTE = "note";

    private Note note;
    public static NoteDescription newInstance(Note note) {
        NoteDescription fragment = new NoteDescription();
        Bundle bundle= new Bundle();
        bundle.putParcelable(ARG_NOTE,note);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        note = getArguments().getParcelable(ARG_NOTE);
        //TextView tv=  view.findViewById(R.id.tv);
        String[] notes_desc = getResources().getStringArray(R.array.notes_description);
        for (int i=0;i<notes_desc.length;i++){
            String cityName = notes_desc[i];
            TextView textView = new TextView(getContext());
            textView.setTextSize(30f);
            textView.setText(cityName);
            ((LinearLayout) view).addView(textView);
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

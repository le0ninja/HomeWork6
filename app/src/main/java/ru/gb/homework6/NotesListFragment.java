package ru.gb.homework6;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class NotesListFragment extends Fragment {
    public static final String CURRENT_NOTE = "note_current";
    private Note currentNote;
    public static NotesListFragment newInstance() {
        return new NotesListFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(CURRENT_NOTE,currentNote);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState!=null){
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        }else{
            currentNote = new Note(0);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            showLand();
        }
        initView(view);
    }
    private void initView(View view) {
        String[] notes = getResources().getStringArray(R.array.notes);
        for (int i=0;i<notes.length;i++){
            String cityName = notes[i];
            TextView textView = new TextView(getContext());
            textView.setTextSize(30f);
            textView.setText(cityName);
            ((LinearLayout) view).addView(textView);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentNote = new Note(finalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                        showLand();
                    }else{// ??????????????
                        showPort();
                    }
                }
            });
        }
    }


    private void showLand() {
        NoteDescription noteDesc = NoteDescription.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.notes_description,noteDesc).commit();
    }
    private void showPort() {
        NoteDescription noteDesc = NoteDescription.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.notes,noteDesc).addToBackStack("").commit();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
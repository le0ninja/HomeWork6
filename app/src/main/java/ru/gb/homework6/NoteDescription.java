package ru.gb.homework6;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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
        //assert getArguments() != null;
        note = getArguments().getParcelable(ARG_NOTE);
        TextView tv=  view.findViewById(R.id.tv);
        String[] descrips = getResources().getStringArray(R.array.notes_description);
        tv.setText(descrips[note.getIndex()]);
        DatePicker datePicker = view.findViewById(R.id.datePicker);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        // по хорошему нужно вот так запивать textViewDate.setText(new StringBuilder().append(note.getNoteDateYear(getContext())).append(" ").append(note.getNoteDateMonth(getContext())).append(" ").append(note.getNoteDateDay(getContext())).toString());
        textViewDate.setText(note.getNoteDateYear(getContext())+" "+ note.getNoteDateMonth(getContext())+" "+ note.getNoteDateDay(getContext()));
        datePicker.init(note.getNoteDateYear(getContext()), note.getNoteDateMonth(getContext()), note.getNoteDateDay(getContext()), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //сохраним полученные данные
                note.setNoteDate(getContext(),year, monthOfYear,dayOfMonth);
                // обновим поле даты
                textViewDate.setText(new StringBuilder().append(note.getNoteDateYear(getContext())).append(" ").append(note.getNoteDateMonth(getContext())).append(" ").append(note.getNoteDateDay(getContext())).toString());
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

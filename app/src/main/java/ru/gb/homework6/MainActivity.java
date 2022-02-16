package ru.gb.homework6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    if(savedInstanceState ==null) {
        NotesListFragment notelstFragment = NotesListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.notes, notelstFragment).commit();
    }
}


    /** Пришлось перенести наш костыль в onResume
     * так как не onBackPressed() вызывать в onCreate - черевато
     **/
    @Override
    protected void onResume() {
        super.onResume();
        // ищем фрагмент, который сидит в контейнере R.id.note_list_container
        Fragment backStackFragment = (Fragment)getSupportFragmentManager()
                .findFragmentById(R.id.notes);
        // если такой есть, и он является NoteDescription
        if(backStackFragment!=null&&backStackFragment instanceof NoteDescription){
            //то сэмулируем нажатие кнопки Назад
            onBackPressed();
        }
    }
}
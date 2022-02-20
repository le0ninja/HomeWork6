package ru.gb.homework6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private int index;
    private static final String KEY_NOTE_YEAR= "key_note_year_";
    private static final String KEY_NOTE_MONTH= "key_note_monthOfYear_";
    private static final String KEY_NOTE_DAY= "";
    private static final String KEY_PREF= "note_date";
    private int notetIndex;

    public Note(int i) {
        index = i;
    }

    protected Note(Parcel in) {
        index = in.readInt();
    }
    public int getNoteDateYear(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_YEAR+notetIndex, -1);
    }
    public int getNoteDateMonth(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_MONTH+notetIndex, -1);
    }
    public int getNoteDateDay(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_DAY+notetIndex, -1);
    }


    // так как мы получаем год, месяц, день в одну операцию, то и сохраним их сразу
    public void setNoteDate(Context mContext, int year, int monthOfYear, int dayOfMonth) {
        // для хранения даты заметки, будем использовать SharedPreferences
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_NOTE_YEAR+notetIndex, year);
        editor.putInt(KEY_NOTE_MONTH+notetIndex, monthOfYear);
        editor.putInt(KEY_NOTE_DAY+notetIndex, dayOfMonth);
        editor.apply();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
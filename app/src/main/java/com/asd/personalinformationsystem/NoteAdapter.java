package com.asd.personalinformationsystem;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private  List<User> notes = new ArrayList<>();


    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        User currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getName());
        holder.textViewDescription.setText(currentNote.getEmail());
        holder.textViewPhone.setText(String.valueOf(currentNote.getPhone()));
        holder.textViewOcc.setText(currentNote.getOccupation());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<User> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    class  NoteHolder extends  RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPhone;
        private TextView textViewOcc;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPhone = itemView.findViewById(R.id.text_view_phone);
            textViewOcc = itemView.findViewById(R.id.text_view_occ);
        }
    }
}

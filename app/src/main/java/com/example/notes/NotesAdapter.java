package com.example.notes ;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private ArrayList<Note> notes;
    private onNoteClickListener onNoteClickListener;

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    interface onNoteClickListener{
        void onNoteClick(int position);
        void onLongClick(int position);
    }

    public void setOnNoteClickListener(NotesAdapter.onNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
        return new NotesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder notesViewHolder, int i) {
        Note note = notes.get(i);
        notesViewHolder.textViewTitle.setText(note.getTitle());
        notesViewHolder.textViewDescription.setText(note.getDescription());
        notesViewHolder.textViewDayOfWeek.setText(Note.getDayAsString(note.getDayOfWeek() + 1));

        int colorID;
        int priority = note.getPriority();
        switch (priority) {
            case 1:
                colorID = ContextCompat.getColor(notesViewHolder.itemView.getContext(), R.color.red_note);
                notesViewHolder.textViewTitle.setBackgroundColor(colorID);
                break;
            case 2:
                colorID = ContextCompat.getColor(notesViewHolder.itemView.getContext(), R.color.yellow_note);
                notesViewHolder.textViewTitle.setBackgroundColor(colorID);
                break;
            case 3:
                colorID = ContextCompat.getColor(notesViewHolder.itemView.getContext(), R.color.green_note);
                notesViewHolder.textViewTitle.setBackgroundColor(colorID);
                break;
            }
            colorID = ContextCompat.getColor(notesViewHolder.itemView.getContext(), R.color.green_note);
    }
    
    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewDayOfWeek;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
                               itemView.setOnClickListener(new View.OnClickListener() {
                                   @Override
                               public void onClick(View v) {
                                        if(onNoteClickListener != null){
                                            onNoteClickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
                                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                 public boolean onLongClick(View v) {
                                        if (onNoteClickListener != null){
                                onNoteClickListener.onLongClick(getAdapterPosition());
            }
                                return true;
        }
    });
        }
    }
}

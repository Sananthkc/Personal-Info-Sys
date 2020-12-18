package com.asd.personalinformationsystem;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<User>> users;
    private List<User> allUsers;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        users = noteDao.getUsers();
    }

    public  void  insert(User user){
        new InsertNoteAsyncTask(noteDao).execute(user);

    }

    public void update(User user) {
        new UpdateNoteAsyncTask(noteDao).execute(user);
    }

    public void delete(User user) {
        new DeleteNoteAsyncTask(noteDao).execute(user);
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public List<User> getAllUsers(String phone) {
        allUsers = noteDao.getAllUsers(phone);
        return allUsers;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<User, Void, Void> {
        private NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            noteDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<User, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            noteDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<User, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            noteDao.delete(users[0]);
            return null;
        }
    }
}

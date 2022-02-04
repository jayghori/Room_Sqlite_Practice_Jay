package com.example.room_sqlite_practice_jay.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.room_sqlite_practice_jay.dao.UserDao;
import com.example.room_sqlite_practice_jay.model.User;

@Database(entities = User.class,exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {


    public static UserDatabase userDatabase;

    public static UserDatabase getInstance(Context context) {
            if(userDatabase==null){
                userDatabase=Room.databaseBuilder(context,UserDatabase.class,"DataBAse").fallbackToDestructiveMigration().build();
            }
            return userDatabase;
    }

    public abstract UserDao userDao();


}

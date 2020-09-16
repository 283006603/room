package com.example.mvvm_jetpack_practice.database;

import android.util.Log;

import com.example.mvvm_jetpack_practice.bean.PersonStateBean;
import com.example.mvvm_jetpack_practice.database.dao.PersonStateDao;
import com.example.mvvm_jetpack_practice.util.ContextProvider;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {PersonStateBean.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase{

    private static AppDataBase instance;
    private static final String DBName = "testRoom";


    public abstract PersonStateDao getPersonStateDao();

    public static AppDataBase getInstance() {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                if (instance == null) {
                    instance = createDB();
                }
            }
        }
        return instance;
    }


    private static AppDataBase createDB(){
        return Room.databaseBuilder(ContextProvider.get().getContext(), AppDataBase.class, DBName  + ".db").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Log.d("AppDataBase", "oncreat");
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                Log.d("AppDataBase", "onOpen");
            }
        }).addMigrations(MIGRATION_1_2).addMigrations(MIGRATION_2_1).allowMainThreadQueries().build();
    }

    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE IF EXISTS LessonVerBean");
            database.execSQL("CREATE TABLE IF NOT EXISTS LessonVerBean(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "lesson_id INTEGER NOT NULL" +
                    ",version INTEGER NOT NULL,type INTEGER NOT NULL)");
        }
    };

    static Migration MIGRATION_2_1 = new Migration(2, 1) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE IF EXISTS LessonVerBean");
        }
    };

}

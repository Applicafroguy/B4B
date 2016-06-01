package za.co.applicafro.sivuyile.realblessar.repository.User.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import za.co.applicafro.sivuyile.realblessar.config.databases.DBConstants;
import za.co.applicafro.sivuyile.realblessar.domain.Users.User;
import za.co.applicafro.sivuyile.realblessar.repository.User.UserRepository;

/**
 * Created by sivuyile on 6/1/16.
 */
public class UserRepositoryImp extends SQLiteOpenHelper implements UserRepository {

    public static final String TABLE_NAME = "user";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_EMAILADDRESS = "email";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_BIRTHDATE = "birthdate";
    public static final String COLUMN_PHONE = "phone";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FIRSTNAME + " TEXT  NOT NULL , "
            + COLUMN_LASTNAME + " TEXT  NOT NULL , "
            + COLUMN_BIRTHDATE + " DATE NOT NULL , "
            + COLUMN_GENDER + " TEXT NOT NULL , "
            + COLUMN_EMAILADDRESS + " TEXT UNIQUE , "
            + COLUMN_PHONE + " INTEGER UNIQUE NOT NULL );";

    public UserRepositoryImp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public User findById(Long id) {
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_BIRTHDATE,
                        COLUMN_GENDER,
                        COLUMN_EMAILADDRESS,
                        COLUMN_PHONE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final User user = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .birthDate(cursor.getString(cursor.getColumnIndex(COLUMN_BIRTHDATE)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAILADDRESS)))
                    .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .phone(cursor.getInt(cursor.getColumnIndex(COLUMN_PHONE)))
                    .build();
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User save(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BIRTHDATE, entity.getBirthDate());
        values.put(COLUMN_GENDER,entity.getGender());
        values.put(COLUMN_EMAILADDRESS, entity.getEmail());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_PHONE,entity.getPhone());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        User insertedEntity = new User.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public User update(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BIRTHDATE, entity.getBirthDate());
        values.put(COLUMN_GENDER,entity.getGender());
        values.put(COLUMN_EMAILADDRESS, entity.getEmail());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_PHONE,entity.getPhone());

        db.update(
            TABLE_NAME,
            values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );


        return entity;
    }

    @Override
    public User delete(User entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<User> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<User> User = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final User setting = new User.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .birthDate(cursor.getString(cursor.getColumnIndex(COLUMN_BIRTHDATE)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAILADDRESS)))
                        .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .phone(cursor.getInt(cursor.getColumnIndex(COLUMN_PHONE)))
                        .build();
                User.add(setting);
            } while (cursor.moveToNext());
        }
        return User;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

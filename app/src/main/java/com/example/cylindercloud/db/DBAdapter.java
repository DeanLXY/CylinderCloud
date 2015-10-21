package com.example.cylindercloud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cylindercloud.App;
import com.example.cylindercloud.moudle.Token;
import com.example.cylindercloud.utils.IOUtils;

/**
 * Created by Administrator on 2015/10/20 0020.
 */
public class DBAdapter {
    private Context context;
    private DBHelper dbHelper;
    private static DBAdapter instance;
    private SQLiteDatabase database;

    private DBAdapter(Context context) {
        this.context = context;

        dbHelper = new DBHelper(context, "cylinderloud");
    }

    public synchronized static DBAdapter getInstance() {
        if (instance == null) {
            instance = new DBAdapter(App.getInstance());
        }
        return instance;
    }

    public void dissconnect() {
        database.close();
        dbHelper.close();
        dbHelper = null;
        instance = null;
    }

    public SQLiteDatabase getDatabase() {
        if (database != null && database.isReadOnly()) {
            database = dbHelper.getReadableDatabase();
        } else {
            database = dbHelper.getWritableDatabase();
        }
        return database;
    }

    // ---------------------------------------------------------------------
    public synchronized void execSQL(String sql) {
        SQLiteDatabase db = getDatabase();
        db.execSQL(sql);
    }

    public synchronized void execSQL(String sql, Object[] bindArgs) {
        SQLiteDatabase db = getDatabase();
        db.execSQL(sql, bindArgs);
    }

    /**
     * Convenience method for inserting a row into the database.
     *
     * @param table          the table to insert the row into
     * @param nullColumnHack SQL doesn't allow inserting a completely empty row, so if initialValues is empty this column will explicitly be assigned a NULL
     *                       value
     * @param values         this map contains the initial column values for the row. The keys should be the column names and the values the column values
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public synchronized long insert(String table, String nullColumnHack, ContentValues values) {
        SQLiteDatabase db = getDatabase();
        long result = db.insert(table, nullColumnHack, values);
        return result;
    }

    /**
     * Convenience method for updating rows in the database
     *
     * @param table       the table to update in
     * @param values      a map from column names to new column values. null is a valid value that will be translated to NULL.
     * @param whereClause the optional WHERE clause to apply when updating. Passing null will update all rows.
     * @param whereArgs
     * @return the number of rows affected
     */
    public synchronized long update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getDatabase();
        long result = db.update(table, values, whereClause, whereArgs);
        return result;
    }

    /**
     * Convenience method for deleting rows in the database.
     *
     * @param table       the table to delete from
     * @param whereClause the optional WHERE clause to apply when deleting. Passing null will delete all rows.
     * @param whereArgs
     * @return the number of rows affected if a whereClause is passed in, 0 otherwise. To remove all rows and get a count pass "1" as the whereClause.
     */
    public synchronized long delete(String table, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getDatabase();
        long result = db.delete(table, whereClause, whereArgs);
        return result;
    }

    /**
     * Query the given table, returning a Cursor over the result set.
     *
     * @param table         The table name to compile the query against.
     * @param columns       A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage
     *                      that isn't going to be used.
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all
     *                      rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the
     *                      selection. The values will be bound as Strings.
     * @param groupBy       A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause
     *                      the rows to not be grouped.
     * @param having        A filter declare which row groups to include in the cursor, if row grouping is being used, formatted as an SQL HAVING clause
     *                      (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being
     *                      used.
     * @param orderBy       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *                      order, which may be unordered.
     * @return A Cursor object, which is positioned before the first entry. Note that Cursors are not synchronized, see the documentation for more
     * details.
     */
    public synchronized Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                                     String orderBy) {
        SQLiteDatabase db = getDatabase();
        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }

    //-------------------------------------------------------------
    public void addToken(Token token) {
        SQLiteDatabase database = getDatabase();
        database.insert(DBHelper.TABLE_TOKEN, null, token.toContentValues());
    }

    public Token getToken() {
        SQLiteDatabase database = getDatabase();

        Cursor cursor = database.rawQuery("select token,create_time,end_time from " + DBHelper.TABLE_TOKEN + " order by _id desc limit 0,1", null);
        if (cursor.moveToFirst()) {
            String token = cursor.getString(cursor.getColumnIndex("token"));
            long createTime = cursor.getLong(cursor.getColumnIndex("create_time"));
            long endTime = cursor.getLong(cursor.getColumnIndex("end_time"));

            Token tk = new Token(token, createTime, endTime);
            return tk;
        }
        IOUtils.closeSilently(cursor);

        return null;
    }

}
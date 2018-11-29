package com.example.administrator.androidtest262;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.util.Log;

import org.litepal.tablemanager.Connector;

public class MyContentProvider extends ContentProvider {
    public static final String AUTHORITY="com.example.administrator.androidtest262.provider";
    public static final int BOOK_DIR=0;
    public static final int BOOK_ITEM=1;
    private static UriMatcher uriMatcher;
    private MyDataBaseHelper helper;

    static{
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase database=helper.getWritableDatabase();
        int deleteRows=0;
        switch(uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRows=database.delete("book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                deleteRows=database.delete("book","id=?",new String[]{bookId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.administrator.androidtest262.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.administrator.androidtest262.book";
                default:
            break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase database=helper.getWritableDatabase();
        Uri uriReturn=null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId=database.insert("book",null,values);
                uriReturn=Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        helper=new MyDataBaseHelper(getContext(),"BookStore.db",null,1);
        Log.i("创建数据库", "onCreate: "+helper);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase database=helper.getReadableDatabase();
        Cursor cursor=null;
        switch(uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor=database.query("book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                cursor=database.query("book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase database=helper.getWritableDatabase();
        int updateRows=0;
        switch(uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRows=database.update("book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=uri.getPathSegments().get(1);
                updateRows=database.update("book",values,"id=?",new String[]{bookId});
                break;
            default:
                break;
        }
        return updateRows;
    }
}

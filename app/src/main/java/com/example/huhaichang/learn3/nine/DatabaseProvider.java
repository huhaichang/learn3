package com.example.huhaichang.learn3.nine;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.huhaichang.learn3.seven.MyDatabaseHelper;

public class DatabaseProvider extends ContentProvider {
    //MyDatabaseHelper的数据库里有2张表 Book和Category;
    public static final int BOOK_DIR = 0 ;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY ="com.example.huhaichang.learn3.provider";
    private static UriMatcher uriMatcher;   //根据uri判断哪张表用的uriMatcher.match(uri)
    private MyDatabaseHelper dbHelper;

    public DatabaseProvider() {
    }
    //为uri传入路径通配符
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);//如果路径为 AUTHORITY+"book"这返回BOOK_DIR即uriMatcher.match(uri)=BOOK_DIR;
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
      //导入数据库
        dbHelper = new MyDatabaseHelper(getContext(),"BookStore.db",null,10);
        return true;
    }
    //MIME类型的固定格式
    @Override
    public String getType(Uri uri) {
      switch (uriMatcher.match(uri)){
          case BOOK_DIR:
              return "vnd.android.cursor.dir/vnd.com.example.huhaichang.learn3.provider.book";
          case BOOK_ITEM:
              return "vnd.android.cursor.item/vnd.com.example.huhaichang.learn3.provider.book";
          case CATEGORY_DIR:
              return "vnd.android.cursor.dir/vnd.com.example.huhaichang.learn3.provider.category";
          case CATEGORY_ITEM:
              return "vnd.android.cursor.item/vnd.com.example.huhaichang.learn3.provider.category";
      }
      return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    //增加数据
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        //根据传入的uri判断添加哪张表(加数据只能加单行)
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM :
                long newBookId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category",null,values);
                uriReturn= Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    //更新数据
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updatedRows = db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Book",values,"id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updatedRows = db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update("Category",values,"id = ?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return updatedRows;
    }

    //删除数据
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
       // throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deletedRows = db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Book","id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deletedRows = db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Category","id = ?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return deletedRows;

    }

  //查询数据
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //throw new UnsupportedOperationException("Not yet implemented");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id = ?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id = ?",new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

}

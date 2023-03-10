package com.dsy.dsu.Code_For_Firebase_And_Provader;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.SubClassCreatingMainAllTables;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ContentProviderForDataBase extends ContentProvider {
    private   UriMatcher uriMatcher????????????????????????????????????????????????????????????;
    private SQLiteDatabase Create_Database_????????????????SQLite;
    private  PUBLIC_CONTENT public_content??????????????????????????????????????????????????????????????????????????;
    private AsyncTaskLoader<?> asyncTaskLoader;
    private Handler handler;
    private Integer ??????????????????????????????????????????????????????URL=0;
    public ContentProviderForDataBase() throws InterruptedException {
        try{

            CopyOnWriteArrayList<String> ????????????????????????????????????????????=
                    new SubClassCreatingMainAllTables(getContext()).
                            ????????????????????????????????????????????????????????????????????????????????????????(getContext());
            Log.d(this.getClass().getName(), " ???????????????????????????????????????????? "+???????????????????????????????????????????? );
            uriMatcher????????????????????????????????????????????????????????????=new UriMatcher(????????????????????????????????????????????.size());
            ????????????????????????????????????????????.forEach(new Consumer<String>() {
                @Override
                public void accept(String ????????????????????????????) {
                    uriMatcher????????????????????????????????????????????????????????????.addURI("com.dsy.dsu.providerdatabase",????????????????????????????.toString(),??????????????????????????????????????????????????????URL);
                    Log.d(this.getClass().getName(), " ???????????????????????????? "+???????????????????????????? + " ??????????????????????????????????????????????????????URL " +??????????????????????????????????????????????????????URL);
                    ??????????????????????????????????????????????????????URL++;
                }
            });
            Log.d(this.getClass().getName(),  " uriMatcher????????????????????????????????????????????????????????????" +uriMatcher???????????????????????????????????????????????????????????? );
            handler=          new Handler(Looper.getMainLooper(),new Handler.Callback(){
                @Override
                public boolean handleMessage(@NonNull android.os.Message  msg) {
                    try{
                        Log.d(this.getClass().getName(), " msg  "+msg);
                        Bundle bundle=        msg.getData();
                        Log.d(this.getClass().getName(), " bundle  "+bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("  ???????????? ?? ?????????? ???????????? ???????????? ???????????? ?????? ?????????????????? Class_Generation_Errors");
                        ///?????????? ???????????? ???????????? ?? ??????????????
                        Log.e(getContext().getClass().getName(),
                                "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " ??????????  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return true;
                }
            });
            // TODO: 04.10.2022
        } catch (Exception e) {
            e.printStackTrace();
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }

            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    /*@Override
    public int delete(@NonNull Uri uri, @Nullable Bundle extras) {
        Integer ????????????????????????????????????????????????=0;
        try{
            Create_Database_????????????????SQLite=new CREATE_DATABASE(getContext()).get??????????????????????????????????????????();
            if (!Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.beginTransaction();
            }
      String selection =      extras.getString("selection");
      String[] selectionArgs =      extras.getStringArray("selectionArgs");
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 ?????????? ?????????????????????? ???????????????? ??????????????
            String table = ????????????????????????????????????????????(uri);
            if (table!=null) {
                Integer ??????????????????????????????????  = Create_Database_????????????????SQLite.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " ??????????????????????????????????  " + ??????????????????????????????????);/////
                Uri ????????????????????????????????????  = Uri.parse("content://"+??????????????????????????????????.toString());
                String ??????????????????????????????????????=    Optional.ofNullable(????????????????????????????????????).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                ????????????????????????????????????????????????= Integer.parseInt(??????????????????????????????????????);
                    if (????????????????????????????????????????????????>0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.setTransactionSuccessful();
                Create_Database_????????????????SQLite.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Create_Database_????????????????SQLite.endTransaction();
            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ????????????????????????????????????????????????;
    }*/

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Integer ????????????????????????????????????????????????=0;
        try{
         //   Create_Database_????????????????SQLite=new CREATE_DATABASE(getContext()).get??????????????????????????????????????????();
            if (!Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.beginTransaction();
            }
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 ?????????? ?????????????????????? ???????????????? ??????????????
            String table = ????????????????????????????????????????????(uri);
            if (table!=null) {
                Integer ??????????????????????????????????  = Create_Database_????????????????SQLite.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " ??????????????????????????????????  " + ??????????????????????????????????);/////
                Uri ????????????????????????????????????  = Uri.parse("content://"+??????????????????????????????????.toString());
                String ??????????????????????????????????????=    Optional.ofNullable(????????????????????????????????????).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                ????????????????????????????????????????????????= Integer.parseInt(??????????????????????????????????????);
                if (??????????????????????????????????> 0) {
                        getContext().getContentResolver().notifyChange(uri, null);
                }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.setTransactionSuccessful();
            }
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }

            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ????????????????????????????????????????????????;
    }


    @NonNull
    private String ????????????????????????????????????????????(Uri uri) {
        String table = new String();
        try{
            Log.d(this.getClass().getName(), " uri"+ uri);
            table=    Optional.ofNullable(uri).map(Emmeter->Emmeter.toString().replace("content://com.dsy.dsu.providerdatabase/","")).get();
            Log.w(getContext().getClass().getName(),
                    " defaluit table  " + table  + " uri " + uri);/////
            Log.d(this.getClass().getName(), " table"+ table);
        } catch (Exception e) {
            e.printStackTrace();
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }

            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return table;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Uri  ???????????????????????????????????? = null;
        try {
           // Create_Database_????????????????SQLite=new CREATE_DATABASE(getContext()).get??????????????????????????????????????????();
            if (!Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.beginTransaction();
            }
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 ?????????? ?????????????????????? ???????????????? ??????????????
            String table = ????????????????????????????????????????????(uri);


            Long   ????????????????????????????????????????????  = Create_Database_????????????????SQLite.insertOrThrow(table, null, values);
            // TODO: 30.10.2021
            Log.w(getContext().getClass().getName(), " ????????????????????????????????????????????  " + ????????????????????????????????????????????);/////

            ????????????????????????????????????  = Uri.parse("content://"+????????????????????????????????????????????.toString());
            if (????????????????????????????????????????????> 0) {
                if (Create_Database_????????????????SQLite.inTransaction()) {
                    Create_Database_????????????????SQLite.setTransactionSuccessful();
                    // TODO: 22.09.2022 ?????????????????????? ???????????? ????????????
                }
            }
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }
            // TODO: 30.10.2021
            getContext().getContentResolver().notifyChange(uri, null);
        } catch (Exception e) {
            e.printStackTrace();
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }

            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ????????????????????????????????????;
    }

    // TODO: 22.11.2022 INSERT
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        Integer ????????????????????????????????????????????????????????????????????????=0;
        ArrayList<Integer> ??????????????????????????????????BulkInsert = new ArrayList<>();
        try {
            if (!Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.beginTransaction();
            }
            Log.d(this.getClass().getName(), " uri"+uri );
            String table = ????????????????????????????????????????????(uri);
            // TODO: 07.12.2022 ???????????????????????????????? INSERT
            Flowable.fromArray(values)
                    .onBackpressureBuffer(true)
                    .filter(filter->filter.size()>0)
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "???????????? " + throwable + " ?????????? :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).
                                    ??????????????????????????????????????????????????????????(throwable.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .doOnNext(new io.reactivex.rxjava3.functions.Consumer<ContentValues>() {
                        @Override
                        public void accept(ContentValues contentValuesUpdates) throws Throwable {
                            try{
                                Long     id  = 0l;
                                if (contentValuesUpdates!=null  && contentValuesUpdates.size()>0 ) {
                                    id = Create_Database_????????????????SQLite.insertOrThrow(table, null, contentValuesUpdates);
                                }
                                Log.w(this.getClass().getName(), " ?????????????? ???????????????? ?????????? burkInsert   id " +  id);
                                if (0 < id) ??????????????????????????????????BulkInsert.add( Integer.parseInt(id.toString()) );
                                Log.w(this.getClass().getName(), "count  bulkInsert  ????????????????????????????????????bulk.size() "
                                        + ??????????????????????????????????BulkInsert.size()+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                                        " FUTURE FUTURE SIZE  Task "+"\n"+
                                        "  isParallel isParallel isParallel" );
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (Create_Database_????????????????SQLite.inTransaction()) {
                                    Create_Database_????????????????SQLite.endTransaction();
                                }
                                Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 09.11.2022 ?????????????????? ???????????????????? ????????????
                            if (Create_Database_????????????????SQLite.inTransaction()) {
                                Create_Database_????????????????SQLite.setTransactionSuccessful();
                                Create_Database_????????????????SQLite.endTransaction();
                            }
                            // TODO: 07.12.2022 ?????????????????????????? ???????????????? ???????????? ???????????? ???????????? ??????????????????????
                            if(??????????????????????????????????BulkInsert.size()>0){
                                Integer ???????????????????????????????????????????????????????????? =new Class_GRUD_SQL_Operations(getContext())
                                        .new ClassRuntimeExeGRUDOpertions(getContext())
                                        .????????????????????????????????????????????????????(table,
                                                "??????????????????",new PUBLIC_CONTENT(getContext()).??????????????????????????????,"????????????");
                                Log.d(this.getClass().getName(), " ????????????????????????????????????????????????????????????  " + ????????????????????????????????????????????????????????????);
                            }
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "???????????? " + throwable + " ?????????? :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .blockingSubscribe();
            // TODO: 09.11.2022  ???????????????? ????????????????????
            Log.w(this.getClass().getName(), "count bulkInsert ???????????????????????????????????????????????????????????????????????? " + ????????????????????????????????????????????????????????????????????????);
        } catch (Exception e) {
            e.printStackTrace();
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }
            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return    ??????????????????????????????????BulkInsert.size();
    }


    @Override
    public boolean onCreate() {
        try{
        if (Create_Database_????????????????SQLite==null) {
            Log.w(this.getClass().getName(), "Create_Database_????????????????SQLite " + Create_Database_????????????????SQLite);
            Create_Database_????????????????SQLite=new CREATE_DATABASE(getContext()).get??????????????????????????????????????????();
            Log.w(this.getClass().getName(), "Create_Database_????????????????SQLite " + Create_Database_????????????????SQLite + " getContext()) " +getContext());
        }
    } catch (Exception e) {
        e.printStackTrace();
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }

            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        if (Create_Database_????????????????SQLite!=null) {
            return true;
        } else {
            return false;
        }
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs, @Nullable CancellationSignal cancellationSignal) {
        Cursor cursor = null;
        try {
       //     Create_Database_????????????????SQLite=new CREATE_DATABASE(getContext()).get??????????????????????????????????????????();
            Log.d(this.getClass().getName(), " uri"+uri );
            String table = ????????????????????????????????????????????(uri);
            Log.d(this.getClass().getName(), " uri"+uri );
            String finalTable = table;
        String ??????????????????????????????????????????=    queryArgs.getString("selection");
        String[] ????????????????????????????????????????????????=    queryArgs.getStringArray("selectionArgs");
          String ????????????????????=  queryArgs.getString("groupby");
        String ????????????=    queryArgs.getString("havings");
        String ????????????????????=    queryArgs.getString("sortOrder");
        String ??????????=    queryArgs.getString("limit");
            asyncTaskLoader=new AsyncTaskLoader<Cursor>(getContext()) {
                @Override
                public void commitContentChanged() {
                    super.commitContentChanged();
                    getContext().getContentResolver().notifyChange(uri,null);
                    Log.w(this.getClass().getName(), "  commitContentChanged ");
                }
                @Nullable
                @Override
                public Cursor loadInBackground() {
                    Cursor cursor = null;
                    try{
                        SQLiteQueryBuilder          SQLBuilder_??????_GRUD_???????????????? =new SQLiteQueryBuilder();
                        SQLBuilder_??????_GRUD_????????????????.setTables(finalTable);
                        if (??????????????????????????????????????????!=null) {SQLBuilder_??????_GRUD_????????????????.appendWhere(??????????????????????????????????????????);}
/*                 cursor=     SQLBuilder_??????_GRUD_????????????????.query(Create_Database_????????????????SQLite,new String[]{"*"},
                            selection,selectionArgs,null, null, "date_update DESC",null);*/
                        cursor=     SQLBuilder_??????_GRUD_????????????????.query(Create_Database_????????????????SQLite,projection,
                                null,????????????????????????????????????????????????
                                ,????????????????????, ????????????, ????????????????????,??????????);
                        Log.w(getContext().getClass().getName(), " ?????????????????? ?????? ?????????????????? ???????????????????? cursor  " + cursor);/////
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    commitContentChanged();
                    return cursor;
                }
            };
            asyncTaskLoader.startLoading();
            cursor= (Cursor) asyncTaskLoader.loadInBackground();
            Log.w(getContext().getClass().getName(), " ?????????????????? ?????? ?????????????????? ???????????????????? cursor  " + cursor);/////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return cursor;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " ?????????????????? ?????? ?????????????????? ???????????????????? cursor  " );/////
        return super.applyBatch(operations);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull String authority, @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " ?????????????????? ?????? ?????????????????? ???????????????????? cursor  " );/////
        return super.applyBatch(authority, operations);
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String authority, @NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        Uri uri = Uri.parse(authority);
        update(uri,null,null,null);
        return super.call(authority, method, arg, extras);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Integer ??????????????????????????????????????????????????????????????????????????????=0;
        try{
          //  Create_Database_????????????????SQLite=new CREATE_DATABASE(getContext()).get??????????????????????????????????????????();
            if (!Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.beginTransaction();
            }
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 ?????????? ?????????????????????? ???????????????? ??????????????
            String table = ????????????????????????????????????????????(uri);
            if (table!=null) {
                Integer ??????????????????????????????????  = Create_Database_????????????????SQLite.update(table,values, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " ??????????????????????????????????  " + ??????????????????????????????????);/////
                Uri ????????????????????????????????????  = Uri.parse("content://"+??????????????????????????????????.toString());
                String ??????????????????????????????????????=    Optional.ofNullable(????????????????????????????????????).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                ??????????????????????????????????????????????????????????????????????????????= Integer.parseInt(??????????????????????????????????????);
                if (??????????????????????????????????> 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.setTransactionSuccessful();
            }
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (Create_Database_????????????????SQLite.inTransaction()) {
                Create_Database_????????????????SQLite.endTransaction();
            }

            Log.e(this.getClass().getName(), "???????????? " + e + " ?????????? :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " ??????????  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).??????????????????????????????????????????????????????????(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ??????????????????????????????????????????????????????????????????????????????;
    }

}


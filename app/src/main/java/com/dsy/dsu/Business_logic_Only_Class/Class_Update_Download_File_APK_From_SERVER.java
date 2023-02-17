package com.dsy.dsu.Business_logic_Only_Class;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;

import com.dsy.dsu.R;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;



public class Class_Update_Download_File_APK_From_SERVER {
    private   String ТипПодключенияИнтернтаДляСлужбы;
    private Integer СервернаяВерсияПОВнутри = 0;
    private  Integer ЛокальнаяВерсияПО = 0;
    private String PROCESS_ID_UpdateSoft = "19";
    private Context context;
    private  Activity activity;
    private SharedPreferences preferences;
    public Class_Update_Download_File_APK_From_SERVER(Context contextВнутри, Activity activityВнутри) {
    this.    context= contextВнутри;
      this.  activity=activityВнутри;
       // preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences = context.getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    }
    // TODO: 02.04.2021 update service po
    public void МетодНачалаЗапускаОбновленияПО(Integer СервернаяВерсияПОВнутриИзСлужбы ,@NonNull Context context)
            throws ExecutionException, InterruptedException {
        Log.w(this.getClass().getName(), "   МетодНачалаЗапускаОбновленияПО СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри);
        try {
            CompletableFuture.supplyAsync(new Supplier<Object>() {
                @Override
                public Object get() {
                    СервернаяВерсияПОВнутри = СервернаяВерсияПОВнутриИзСлужбы;
                    Log.i(this.getClass().getName(), "СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри +
                            " СервернаяВерсияПОВнутриИзСлужбы" + СервернаяВерсияПОВнутриИзСлужбы);
                    ТипПодключенияИнтернтаДляСлужбы = МетодОпределяемКакойТипПодключениеWIFIилиMobileДляСлужбы(context);

                    if (ТипПодключенияИнтернтаДляСлужбы != null) {
                        Log.i(this.getClass().getName(), "ТипПодключенияИнтернтаДляСлужбы " + ТипПодключенияИнтернтаДляСлужбы);
                        if (ТипПодключенияИнтернтаДляСлужбы.equals("WIFI")  || ТипПодключенияИнтернтаДляСлужбы.equals("Mobile")  ) {
                            // TODO: 17.02.2023 ЗАгрущка
                            МетодЗагрузкиФайлаAPK();
                            Log.i(this.getClass().getName(), "МетодОпределнияВерсийПОСервераКлиентаИПринятиеРешенияНаСкачиваниеОбновлениеПО " );
                        }else {
                            Log.e(this.getClass().getName(), "неТ СВЯЗИ ДЛЯ ЗАГРУЗКИ ПО ТипПодключенияИнтернтаДляСлужбы "  +ТипПодключенияИнтернтаДляСлужбы  );
                        }
                    }
                    return ТипПодключенияИнтернтаДляСлужбы;
                }
            }).exceptionally(new Function<Throwable, Object>() {
                @Override
                public Object apply(Throwable throwable) {
                    Log.d(this.getClass().getName(), " СЛУЖБА ... МЕТОД ОБНОВЛЕНИЯ ПО РАБОТАЕТ......"+new Date());
                    return null;
                }
            }).thenAcceptAsync(new Consumer<Object>() {
                @Override
                public void accept(Object o) {
                    Log.d(this.getClass().getName(), " СЛУЖБА ... МЕТОД ОБНОВЛЕНИЯ ПО РАБОТАЕТ......"+new Date());

                }
            }).complete(null);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
    }

    ////TODO АНАЛИЗИРУЕМ ПРИШЕДШИЙ ФАЙЛ И ПРИНИМАЕМ РЕШЕНИЕ НА СКАЧИВАНЕИ ФАЙЛА ИЛИ НЕТ


    ////TODO
    protected void МетодЗагрузкиФайлаAPK() {
        try {
            Log.d(this.getClass().getName(), " СЛУЖБА ... МЕТОД ОБНОВЛЕНИЯ ПО РАБОТАЕТ......"+new Date());
            //////TODO вторая часть ЕСЛИ ВЕРСИЯ ПРОГРАМЫ НА СЕРВЕРЕ ВЫШЕ ЧЕМ НА АЕДРОЙДЕ ТО ЗАПУСКАЕМ  ЗАГРУЗКИ ПРИЛОЖЕНИЯ (НАПРИМЕР НА АНДРОЙДЕ 102 , А НА СЕРВЕРЕ 103, 104,итд.)
                File  ФайлыДляОбновлениеПО=null;
                        Log.d(this.getClass().getName(), "    ПУТИ В ФАЙЛУ   " + "\n"
                        + ФайлыДляОбновлениеПО);
                PackageInfo ИнформацияОФайле =null;
            // TODO: 02.04.2022 зпускаем работут по анализу  СКАЧКИ ПРОГРАММЫ ТАБЕЛТНЫЙ УЧЁТ С СЕРВЕРА
                    ///TODO загрузка apk ФАЙЛА
                    МетодНепостредственннойЗагрузкиAPKФайлов(ФайлыДляОбновлениеПО, ИнформацияОФайле);
                    ////TODO после успешного  СКАЧЧИВАНИЯ ФАЙЛА МЫ С ОТЛОЖЕНИЕ В 10 СЕКУНД УСТАНВЛИВАЕМ ФАЙЛ
                    ////TODO после успешного  СКАЧЧИВАНИЯ ФАЙЛА МЫ С ОТЛОЖЕНИЕ В 10 СЕКУНД УСТАНВЛИВАЕМ ФАЙЛ
                    ////TODO после успешного  СКАЧЧИВАНИЯ ФАЙЛА МЫ С ОТЛОЖЕНИЕ В 10 СЕКУНД УСТАНВЛИВАЕМ ФАЙЛ
                   /// МетодКоторыйЗапускаетОбновлениеПООтложенныйЗапускна10Секунд();
            ///////
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                   new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void МетодНепостредственннойЗагрузкиAPKФайлов(File файлыДляОбновлениеПО, PackageInfo info) throws IOException {
        try {
            String Adress_String;
/////TODO  загрузка ФАЙЛ.APK ФАЙЛАv
            Handler handlerСообщение=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {

                    switch (msg.what){
                        case 0:
                            Toast toast=     Toast.makeText(context, "Загрузка ПО...", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM,0,50);
                            toast.show();
                            Log.w(this.getClass().getName(), "Загрузка ПО... " );
                            break;
                        case 1:
                            Log.w(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск   bundleУстановитьПО  " + msg);
                              Bundle b=  msg.getData();
                                File    FileAPK  = (File )   b.getSerializable("DOWNAPK");
                                if (FileAPK !=null) {
                                    if (FileAPK.length() > 0) {
                                        try {
                                            // TODO: 25.03.2022 ТУТ МЫ ОТПРВЯЛЕМ ВЕРИСЮ ДАННЫХ И ФАЙЛ ПРИУСТАВНВОЕ по ТАБЕЛЬНЫЙ УЧЁТ
                                            Intent intentДляУстановеПО = new Intent();
                                            intentДляУстановеПО.setAction("CompletePO");
                                            Bundle bundleУстановитьПО = new Bundle();
                                            bundleУстановитьПО.putInt("СервернаяВерсияПОВнутри", СервернаяВерсияПОВнутри);
                                            bundleУстановитьПО.putSerializable("СервернаяВерсияПОCамФайлДляПередачи", FileAPK);
                                            bundleУстановитьПО.putLong("СервернаяВерсияПОРазмерФайла", FileAPK.length());
                                            intentДляУстановеПО.putExtras(bundleУстановитьПО);
                                            Log.w(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск   bundleУстановитьПО  " + bundleУстановитьПО);
                                            LocalBroadcastManager localBroadcastManagerОтправляемНаActivityFaceApp = LocalBroadcastManager.getInstance(context);
                                            localBroadcastManagerОтправляемНаActivityFaceApp.sendBroadcast(intentДляУстановеПО);
                                            Log.w(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри localBroadcastManagerОтправляемНаActivityFaceApp "
                                                    + localBroadcastManagerОтправляемНаActivityFaceApp
                                                    + " СервернаяВерсияПОВнутри " + СервернаяВерсияПОВнутри);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }
                                    }
                                }else {
                                   toast=     Toast.makeText(context, "Нет файла ПО...", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM,0,50);
                                    toast.show();
                                    Log.w(this.getClass().getName(), "Загрузка ПО... " );
                                }
                            break;
                    }
                    return true;
                }
            });
            handlerСообщение.obtainMessage(0,0,0,new Object()).sendToTarget();

            //todo конец главного потока
            PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
            String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
            Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);


            new SubClass_Delete_File_FOr_MainActivity_Face_App().МетодДополнительногоУдалениеФайлов(context);

            // TODO: 19.12.2021  загрузка файда  .apk    УниверсальныйБуферAPKФайлаПОсСервера("dsu1.glassfish/update_android_dsu1/app-release.apk", "update_dsu1.apk",
          File  FileAPK = new Class_MODEL_synchronized(context).
                    УниверсальныйБуферAPKФайлаПОсСервера(new PUBLIC_CONTENT(context).getСсылкаНаРежимСервера()+ "/update_android_dsu1/app-release.apk",
                            "update_dsu1.apk",
                            context, ИмяСерверИзХранилица ,ПортСерверИзХранилица);
            Log.w(this.getClass().getName(), "FileAPK "+ FileAPK);

            Bundle bundle=new Bundle();
            bundle.putSerializable("DOWNAPK",FileAPK);
         Message message=   handlerСообщение.obtainMessage(1,0,0,FileAPK);
         message.setData(bundle);
            handlerСообщение.sendMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    ////TODO метод который отпредеяеть КАКОЙ ТИП ПОДКЛЮЧЕНИ К ИНТРЕНТУ ЧЕРЕЗ WIFI ИЛИ MOBILE
    private String МетодОпределяемКакойТипПодключениеWIFIилиMobileДляСлужбы(Context КонтекстКоторыйДляСинхронизации) {

        try{

        ConnectivityManager cm = (ConnectivityManager) КонтекстКоторыйДляСинхронизации.getSystemService(Context.CONNECTIVITY_SERVICE);
        ////////
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if ( wifiInfo.isConnected()) {

            Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через wifi");

            return "WIFI";
        }else{

            //

            ////////
            NetworkInfo wifiInfoMObile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifiInfoMObile.isConnected()) {

                Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через mobile");

                return "Mobile";
            }
        }



    } catch (Exception e) {
        //  Block of code to handle errors
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return null;
    }

    ///TODO  конец  методы перерд перед  СИНХРОНИЗАЦИЯ ///TODO СИНХРОНИЗАЦИЯ при запуске прилиложения

































}












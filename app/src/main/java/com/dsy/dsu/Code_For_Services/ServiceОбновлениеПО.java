package com.dsy.dsu.Code_For_Services;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.BuildConfig;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.reactivestreams.Publisher;

import java.io.File;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ServiceОбновлениеПО extends IntentService {////Service

    public ServiceОбновлениеПО.localBinderОбновлениеПО binder = new ServiceОбновлениеПО.localBinderОбновлениеПО();
    String ИмяСлужбыУведомленияДляОбновление = "WorkManager NOtofocationforUpdateSoft";
    private String PROCESS_IDSoftUpdate = "19";
    private  Integer ЛокальнаяВерсияПО = 0;
    private SharedPreferences preferences;
    private String ИмяПотока="binderupdatepo";
    private  Activity activity;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ServiceОбновлениеПО() {
        super("Binder_UpdatePO");
    }

    public ServiceОбновлениеПО(String name) {

        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try{
        Log.d(getApplicationContext().getClass().getName(), "ServiceОбновлениеПО "
                + " время: "
                + new Date());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошиюбка СЛУЖБА СЛУЖБАService_ДЛЯ ОБНОВЛЕНИЯ ПО  ДЛЯ ЧАТА onHandleWork Exception ");

    }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
        Log.i(getApplicationContext().getClass().getName(), "Стоп Стоп  Стоп !!!!!!!!!!! СЛУЖБА СЛУЖБАService_Notifications  ДЛЯ Обновление ПО  ДЛЯ ЧАТА onDestroy() время "+new Date());
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications   Обновление ПО  onDestroy() время " + new Date());

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return   binder;
    }
    public class localBinderОбновлениеПО extends Binder {
        public ServiceОбновлениеПО getService() {
            // Return this instance of LocalService so clients can call public methods
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
            return ServiceОбновлениеПО.this;
        }
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try{
            МетодГлавныйОбновленияПО(false,activity);
            Log.i(getApplicationContext().getClass().getName(), " ServiceОбновлениеПО  МетодГлавныйЗапускаОбновлениеПО  " + " время запуска  " + new Date());
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), "С ОШИБКОЙ  Стоп СЛУЖБА СЛУЖБАService_Notifications   Обновление ПО  onDestroy() время " + new Date());

    }
    }


    public void МетодГлавныйОбновленияПО(@NonNull Boolean РежимРаботыСлужбыОбновлениеПО ,
                                         @NonNull Activity  activity){
        try {
            this.activity=activity;
          String  РежимРаботыСети = МетодУзнаемРежимСетиWIFiMobile(getApplicationContext());
            preferences = getApplicationContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                if (РежимРаботыСети.equals("WIFI")  || РежимРаботыСети.equals("Mobile")  ) {
                    Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ "РежимРаботыСети " + РежимРаботыСети);
                    // TODO: 18.02.2023 удаление перед анализо файлов json И .apk 
                    МетодДополнительногоУдалениеФайлов();
                    МетодАнализаВерсииПОJSON();
                    Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                }else {
                    Log.e(this.getClass().getName(), "неТ СВЯЗИ ДЛЯ ЗАГРУЗКИ ПО ТипПодключенияИнтернтаДляСлужбы "  + РежимРаботыСети);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (РежимРаботыСлужбыОбновлениеПО == true) {
                                Toast toast = Toast.makeText(  getApplicationContext(), "Нет связи c Cервер !!!", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM, 0, 40);
                                toast.show();
                                Log.i(this.getClass().getName(),  Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                            }
                        }
                    });
                }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    private File МетодЗагрузкиAPK()  {
        File  FileAPK = null;
        try {
            Log.d(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName()+"Загружаем Файл APK."+new Date());
            String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
            Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
            // TODO: 19.12.2021  загрузка файда  .apk    УниверсальныйБуферAPKФайлаПОсСервера("dsu1.glassfish/update_android_dsu1/app-release.apk", "update_dsu1.apk",
            FileAPK = new Class_MODEL_synchronized(getApplicationContext()).
                    УниверсальныйБуферAPKФайлаПОсСервера(new PUBLIC_CONTENT(getApplicationContext()).
                                    getСсылкаНаРежимСервера()+ "/update_android_dsu1/app-release.apk",
                            "update_dsu1.apk",
                            getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица);
            Log.w(this.getClass().getName(), "FileAPK "+ FileAPK);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return FileAPK;
    }
    private String МетодУзнаемРежимСетиWIFiMobile(Context КонтекстКоторыйДляСинхронизации) {
        String  РежимРаботыСети = new String();
        try{
            ConnectivityManager cm = (ConnectivityManager) КонтекстКоторыйДляСинхронизации.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if ( wifiInfo.isConnected()) {
                Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через wifi");
                return "WIFI";
            }else{
                NetworkInfo wifiInfoMObile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (wifiInfoMObile.isConnected()) {
                    Log.d(Class_MODEL_synchronized.class.getName(), " подключние к интренту через mobile");
                    return "Mobile";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РежимРаботыСети;
    }
    public Integer МетодДополнительногоУдалениеФайлов() {
        Integer РезультатУдаления = 0;
        try {
/////TODO  УДАЛЕНИЕ .JSON ФАЙЛА
            File ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии;
            if (Build.VERSION.SDK_INT >= 30) {
                // TODO: 10.04.2022
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            } else {
                ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS);
            }
            Log.d(this.getClass().getName(), "ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии" + ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии);
            // TODO: 10.04.2022
            Boolean[] ФайлУдаления = new Boolean[10];
            File[] Files = ФайлыДляОбновлениеПОУдалениеПриАнализеJSONВерсии.listFiles();
            if (Files != null) {
                int j;
                for (j = 0; j < Files.length; j++) {
                    String ИмяФайла = Files[j].getName();
                    // TODO: 10.04.2022//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    ФайлУдаления[0] = ИмяФайла.matches("(.*)analysis_version(.*)");//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    ФайлУдаления[1] = ИмяФайла.matches("(.*)output-metadata.json(.*)");
                    ФайлУдаления[2] = ИмяФайла.matches("(.*)update_dsu1(.*)");//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    ФайлУдаления[3] = ИмяФайла.equalsIgnoreCase("update_dsu1.apk");//    boolean ПосикПоНазваниюФайла=Files[j].getName().matches("(.*).json(.*)");
                    // TODO: 10.04.2022
                    Log.d(this.getClass().getName(), " СЛУЖБА  ТАКОГО ФАЙЛА БОЛЬШЕ НЕТ  .JSON АНАЛИЗ " + Files[j].length()
                            + "   путь файла " + Files[j].getAbsolutePath() + "   --- " + new Date() + " ИмяФайла " + ИмяФайла);
                    if (ФайлУдаления[0] = true || ФайлУдаления[1] == true ||
                            ФайлУдаления[2] == true || ФайлУдаления[3] == true) {
                        // TODO: 10.04.2022
                        if (Files[j].exists()) {
                            // TODO: 10.04.2022
                            Files[j].delete();
                            // TODO: 10.04.2022
                            Log.d(this.getClass().getName(), " СЛУЖБА  ТАКОГО ФАЙЛА БОЛЬШЕ НЕТ  .JSON АНАЛИЗ " + Files[j].length()
                                    + "   путь файла " + Files[j].getAbsolutePath() + "   --- " + new Date() + " ИмяФайла " + ИмяФайла);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), " ошибка  faceapp из меню МетодДополнительногоУдалениеФайлов Обновление ПО ");
        }
        return РезультатУдаления;
    }

    @UiThread
    void МетодСообщениеЗапускЗагрущикаПо(@NonNull Integer СервернаяВерсияПОВнутри) {
        try {

            LayoutInflater li = LayoutInflater.from(getApplicationContext());
            View promptsView = li.inflate(R.layout.activity_insertdata, null);
            ProgressBar progressBar=promptsView.findViewById(R.id.prograssbarupdatepo);
            progressBar.setIndeterminate(false);
            promptsView.forceLayout();
            promptsView.refreshDrawableState();
            AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                    .setTitle("Загрущик")
                    .setView(promptsView)
                    .setMessage("Обновление ПО"
                            + "\n" + "ООО Союз-Автодор"
                            + "\n"  +"версия. " + СервернаяВерсияПОВнутри)
                    .setPositiveButton("Загрузить", null)
                    .setNegativeButton("Позже", null)
                    .setIcon(R.drawable.icon_dsu1_update_success)
                    .show();
/////////кнопка
            final Button MessageBoxЗагрущикПО = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Integer finalСервернаяВерсияПОВнутри = СервернаяВерсияПОВнутри;
            MessageBoxЗагрущикПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
// TODO: 18.02.2023 Загрузка Нового файла APK
            activity.runOnUiThread(()->{
            Toast toast = Toast.makeText(getApplicationContext(), "Загрузка ПО ▼  ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 40);
            toast.show();
                progressBar.setIndeterminate(true);
            });
            Log.i(this.getClass().getName(),  "Установщик ПО..." + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
            Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            v2.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
                        Flowable.fromCallable(new Callable<Object>() {
                                    @Override
                                    public Object call() throws Exception {
                                        File   FileAPK=  МетодЗагрузкиAPK();
                                        Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                                " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" +
                                                Thread.currentThread().getName());
                                        return FileAPK;
                                    }
                                })
                                .subscribeOn(Schedulers.single())
                                .doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable+ " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                })
                                .onErrorComplete(new Predicate<Throwable>() {
                                    @Override
                                    public boolean test(Throwable throwable) throws Throwable {
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        return false;
                                    }
                                })
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        progressBar.setIndeterminate(false);
                                        alertDialog.dismiss();
                                        alertDialog.cancel();
                                        Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                                " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" );
                                    }
                                })
                                .subscribe();
                        Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });
            final Button MessageBoxUpdateНеуСтанавливатьПО = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateНеуСтанавливатьПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    alertDialog.cancel();
                    Log.i(this.getClass().getName(),  "Установщик ПО..." + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    private void МетодСообщенияЗагрузкиФайлаAPK(@NonNull Integer СервернаяВерсияПОВнутри) {
        try {
            File ФайлыДляОбновлениеВычисляемНомерВерсииПО = null;
            final PackageManager pm = getApplicationContext().getPackageManager();
            String apkName = "update_dsu1.apk";
            String fullPath;
            if (Build.VERSION.SDK_INT >= 30) {
                fullPath = Environment.getExternalStorageState() + "/" + apkName;
            } else {
                fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;
            }
            fullPath = Environment.DIRECTORY_DOWNLOADS + "/" + apkName;
            PackageInfo info = pm.getPackageArchiveInfo(fullPath, 0);
            if (info != null) {
                Log.d(this.getClass().getName(), "VersionCode : " + info.versionCode + ", VersionName : " + info.versionName);
                СервернаяВерсияПОВнутри = info.versionCode;
            }
            // TODO: 02.04.2022
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            Long ЛокальнаяВерсияПОСравнение = pInfo.getLongVersionCode();
            AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                    .setTitle("Загрущик")
                    .setMessage("Пришло Обновление,"
                            + "\n" + "Союз-Автодор ПО ,"
                            + "\n" + "новая версия. " + СервернаяВерсияПОВнутри + ","//TODO old          + "\n" + "локальная версия. " + ЛокальнаяВерсияПОСравнение + ","
                            + "\n")
                    .setPositiveButton("Загрузить", null)
                    .setNegativeButton("Позже", null)
                    .setIcon(R.drawable.icon_dsu1_update_success)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateОбновитьПО = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Integer finalСервернаяВерсияПОВнутри = СервернаяВерсияПОВнутри;
            MessageBoxUpdateОбновитьПО.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    try {
                        Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА");
                        alertDialog.dismiss();
                        alertDialog.cancel();
                        Log.d(this.getClass().getName(), " СервернаяВерсияПОВнутри" + finalСервернаяВерсияПОВнутри);
                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                        //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
                        // TODO: 16.02.2023  НАчинаем Саму загрузки
                     //   МетодНачалаЗапускаОбновленияПО(finalСервернаяВерсияПОВнутри, context);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });
            final Button MessageBoxUpdateНеуСтанавливатьПО = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateНеуСтанавливатьПО.setOnClickListener(new View.OnClickListener() {
                ///MessageBoxUpdate метод CLICK для DIALOBOX
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "MessageBoxUpdateНеуСтанавливатьПО  ОТМЕНА УСТАНВОКИ НОВГО ПО   dismiss ");
                    alertDialog.cancel();
                    // activity.finishAndRemoveTask(); //// ((Activity) MainActivity_Face_App.КонтекстFaceApp).finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодЗагрузкиНовогоПО() {
        try {
            Log.d(this.getClass().getName(), "  МетодЗапускПослеНажатияНАНовойФормеНАКнопкуУстановитьПослеУспешнойЗагрузкиНовогоПОТабельныйУчётПоказываемЕгоПользователю");
            // TODO: 25.03.2022 Создание Локального БродКстаера
            LocalBroadcastManager localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт;
            BroadcastReceiver broadcastReceiverУстановкаПО;
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт = LocalBroadcastManager.getInstance(  getApplicationContext());
            broadcastReceiverУстановкаПО = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  intent " + intent);
                    Bundle bundle = intent.getExtras();
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  bundle " + bundle);
                    Integer ЗагрузиласьНоваяВерисяПОПровремяем = bundle.getInt("СервернаяВерсияПОВнутри", 0);
                    File ЗагрузкиФайлаОбновенияПОДополнительный = (File) bundle.getSerializable("СервернаяВерсияПОCамФайлДляПередачи");
                    Long СервернаяВерсияПОРазмерФайла = bundle.getLong("СервернаяВерсияПОРазмерФайла", 0l);
                    Log.d(this.getClass().getName(), " ЗагрузиласьНоваяВерисяПОПровремяем  intent "
                            + ЗагрузиласьНоваяВерисяПОПровремяем + "ЗагрузкиФайлаОбновенияПОДополнительный " + ЗагрузкиФайлаОбновенияПОДополнительный
                            + " СервернаяВерсияПОРазмерФайла " + СервернаяВерсияПОРазмерФайла);
                    if (СервернаяВерсияПОРазмерФайла > 0) {
                        МетодУстановкиНовойВерсииПО(ЗагрузиласьНоваяВерисяПОПровремяем, ЗагрузкиФайлаОбновенияПОДополнительный, context);
                        Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  intent " + intent);
                    }
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  intent " + intent);

                }
            };
            // TODO: 25.03.2022 установливам настройки Фильмо к Локальному БродКсстеру
            IntentFilter intentFilterУстановка = new IntentFilter();
            intentFilterУстановка.addAction("CompletePO");
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт.registerReceiver(broadcastReceiverУстановкаПО, intentFilterУстановка);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(  getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //todo Финальный метод в ОБНОВЛЕНИИ ПО УСТАВНКА НЕПОСРЕДСВЕННО ФАЙЛА НА АКТИВТИ ПОЛЬЗОВАТЛЕМ
    @UiThread
    private void МетодУстановкиНовойВерсииПО(@NonNull Integer СервернаяВерсияПОВнутри,
                                             @NonNull File ЗагрузкиФайлаОбновенияПОДополнительный,
                                             @NonNull Context context) {
        try {
            File ФайлыДляОбновлениеВычисляемНомерВерсииПО = null;
            final PackageManager pm = context.getPackageManager();
            String apkName = "update_dsu1.apk";
            String fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;
            if (Build.VERSION.SDK_INT >= 30) {
                fullPath = Environment.getExternalStorageState() + "/" + apkName;
            } else {
                fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;
            }
            fullPath = Environment.DIRECTORY_DOWNLOADS + "/" + apkName;
            PackageInfo info = pm.getPackageArchiveInfo(fullPath, 0);
            if (info != null) {
                Log.d(this.getClass().getName(), "VersionCode : " + info.versionCode + ", VersionName : " + info.versionName);
                СервернаяВерсияПОВнутри = info.versionCode;
            }
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            Long ЛокальнаяВерсияПОСравнение = pInfo.getLongVersionCode();
            AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                    .setTitle("Установщик")
                    .setMessage("Пришло Обновление,"
                            + "\n" + "Союз-Автодор ПО ,"
                            + "\n" + "новая версия. " + СервернаяВерсияПОВнутри + ","//TODO old          + "\n" + "локальная версия. " + ЛокальнаяВерсияПОСравнение + ","
                            + "\n")
                    .setPositiveButton("Установить", null)
                    .setNegativeButton("Позже", null)
                    .setIcon(R.drawable.icon_dsu1_updates_po_success)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateОбновитьПО = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateОбновитьПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА");
                    String ФинальныйПутьДляЗагрузкиФайлаОбновения = null;
                    if (Build.VERSION.SDK_INT >= 30) {
                        ФинальныйПутьДляЗагрузкиФайлаОбновения = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/";  //null
                    } else {
                        ФинальныйПутьДляЗагрузкиФайлаОбновения = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                    }
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА  ФинальныйПутьДляЗагрузкиФайлаОбновения " + ФинальныйПутьДляЗагрузкиФайлаОбновения);
                    String НазваниеФайлаОбновления = "update_dsu1.apk";
                    ФинальныйПутьДляЗагрузкиФайлаОбновения += НазваниеФайлаОбновления;
                    Uri URIПутиДляЗагрузкиФайловЧерезПровайдер = FileProvider.getUriForFile(context,
                            context.getPackageName() + ".provider",
                            ЗагрузкиФайлаОбновенияПОДополнительный);
                    Log.d(this.getClass().getName(), "Установка ЗагрузкиФайлаОбновенияПОДополнительный  " + ЗагрузкиФайлаОбновенияПОДополнительный);
                    Intent intentОбновлениеПО = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                    intentОбновлениеПО.setDataAndType(URIПутиДляЗагрузкиФайловЧерезПровайдер, "application/vnd.android.package-archive");
                    intentОбновлениеПО.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                            | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentОбновлениеПО.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                    intentОбновлениеПО.putExtra(Intent.EXTRA_STREAM, URIПутиДляЗагрузкиФайловЧерезПровайдер);
                    PackageManager МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт = activity.getPackageManager();
                    if (intentОбновлениеПО.resolveActivity(МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт) != null) {
                        //////todo запуск установкика .apk
                        ///     context. startActivity(intent); ////   ((Activity) MainActivity_Face_App.КонтекстFaceApp). startActivity(intent);//  MainActivity_Face_App.КонтекстFaceApp. startActivity(intent);
                        Log.d(this.getClass().getName(), " СЛУЖБА УСТАНОВКА... ОБНОВЛЕНИЯ НА ТЕЛЕФОН (.APK файл)  МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт "
                                + МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт);
                        ////TODO непосрдствено сам запуск новго .apk файла
                        activity.startActivity(intentОбновлениеПО);
                        activity.finishAndRemoveTask(); //// ((Activity) MainActivity_Face_App.КонтекстFaceApp).finish();
                        Log.w(this.getClass().getName(), " ура !!!! УРА !!!!  уСПЕШНАЫЙ ЗАПУСК СКАЧЕННОГО ОБНОВЛЕНЕИ ПО " +
                                "МетодУстановкиНовойВерсииПОТабельныйУчётПоднимаетЕгоНаActrivity  ");
                    } else {
                        ///////TODO ОСТАНАВЛИВАЕМ СЛУЖБУ ЧЕРЕЗ 20 СЕКУНД
                        Log.d(this.getClass().getName(), "Ошибка файл .APK не устнаовлен ОШИБКА СЛУЖБА ОБНОВЛЕНИЯ ...  "
                                + new Date() + " МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт " + МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт);
                    }
                }
            });
            final Button MessageBoxUpdateНеуСтанавливатьПО = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateНеуСтанавливатьПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "MessageBoxUpdateНеуСтанавливатьПО  ОТМЕНА УСТАНВОКИ НОВГО ПО   dismiss ");
                    alertDialog.cancel();
                    // activity.finishAndRemoveTask(); //// ((Activity) MainActivity_Face_App.КонтекстFaceApp).finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 14.09.2022
    void МетодАнализJsonОбновлениеПО() {
        try {

            Log.d(this.getClass().getName(), "  МетодЗапускПослеНажатияНАНовойФормеНАКнопкуУстановитьПослеУспешнойЗагрузкиНовогоПОТабельныйУчётПоказываемЕгоПользователю");
            // TODO: 25.03.2022 Создание Локального БродКстаера
            LocalBroadcastManager localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт;
            BroadcastReceiver broadcastReceiverУстановкаПО;
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт = LocalBroadcastManager.getInstance(  getApplicationContext());
            broadcastReceiverУстановкаПО = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    // TODO: 16.02.2023
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  intent " + intent);
                    Bundle bundle = intent.getExtras();
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  bundle " + bundle);
                    Integer СервернаяВерсияПОРазмерФайла = bundle.getInt("СервернаяВерсияПОВнутри", 0);
                    Log.d(this.getClass().getName(), " СервернаяВерсияПОРазмерФайла " + СервернаяВерсияПОРазмерФайла);
                    if (СервернаяВерсияПОРазмерФайла > 0) {
                        МетодСообщенияЗагрузкиФайлаAPK(СервернаяВерсияПОРазмерФайла);
                    }
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  intent " + intent);
                }
            };
            // TODO: 25.03.2022 установливам настройки Фильмо к Локальному БродКсстеру
            IntentFilter intentFilterУстановка = new IntentFilter();
            // TODO: 25.03.2022
            intentFilterУстановка.addAction("AfterDownloadPO");
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт.registerReceiver(broadcastReceiverУстановкаПО, intentFilterУстановка);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(  getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    public void МетодФиналСлушательУстановщикПО() {
        try {
            Log.d(this.getClass().getName(), "  МетодЗапускПослеНажатияНАНовойФормеНАКнопкуУстановитьПослеУспешнойЗагрузкиНовогоПОТабельныйУчётПоказываемЕгоПользователю");
            // TODO: 25.03.2022 Создание Локального БродКстаера
            LocalBroadcastManager localBroadcastManagerУстановщикПО;
            BroadcastReceiver broadcastReceiverУстановкаПО;
            localBroadcastManagerУстановщикПО = LocalBroadcastManager.getInstance(  getApplicationContext());
            broadcastReceiverУстановкаПО = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(this.getClass().getName(), " localBroadcastManagerУстановщикПО  intent " + intent);
                    Bundle bundle = intent.getExtras();
                    Log.d(this.getClass().getName(), " localBroadcastManagerУстановщикПО  bundle " + bundle);
                    Integer ЗагрузиласьНоваяВерисяПОПровремяем = bundle.getInt("СервернаяВерсияПОВнутри", 0);
                    File ЗагрузкиФайлаОбновенияПОДополнительный = (File) bundle.getSerializable("СервернаяВерсияПОCамФайлДляПередачи");
                    Long СервернаяВерсияПОРазмерФайла = bundle.getLong("СервернаяВерсияПОРазмерФайла", 0l);
                    context.getMainExecutor().execute(() -> {
                        if (СервернаяВерсияПОРазмерФайла > 0) {
                            МетодФиналУстановкаПО(ЗагрузиласьНоваяВерисяПОПровремяем, ЗагрузкиФайлаОбновенияПОДополнительный, context);
                            Log.d(this.getClass().getName(), " МетодФиналСлушательУстановщикПО");
                        }
                        Log.d(this.getClass().getName(), " МетодФиналСлушательУстановщикПО ");
                    });
                }
            };
            // TODO: 25.03.2022 установливам настройки Фильмо к Локальному БродКсстеру
            IntentFilter intentFilterУстановка = new IntentFilter();
            intentFilterУстановка.addAction("CompletePO");
            localBroadcastManagerУстановщикПО.registerReceiver(broadcastReceiverУстановкаПО, intentFilterУстановка);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(  getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void МетодСлушательПрелагаетЗагрузитьПО() {
        try {
            Log.d(this.getClass().getName(), "  МетодЗапускПослеНажатияНАНовойФормеНАКнопкуУстановитьПослеУспешнойЗагрузкиНовогоПОТабельныйУчётПоказываемЕгоПользователю");
            // TODO: 25.03.2022 Создание Локального БродКстаера
            LocalBroadcastManager localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт;
            BroadcastReceiver broadcastReceiverУстановкаПО;
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт = LocalBroadcastManager.getInstance(  getApplicationContext());
            broadcastReceiverУстановкаПО = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  intent " + intent);
                    Bundle bundle = intent.getExtras();
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  bundle " + bundle);
                    Integer СервернаяВерсияПОРазмерФайла = bundle.getInt("СервернаяВерсияПОВнутри", 0);
                    Log.d(this.getClass().getName(), " СервернаяВерсияПОРазмерФайла " + СервернаяВерсияПОРазмерФайла);
                    context.getMainExecutor().execute(() -> {
                        if (СервернаяВерсияПОРазмерФайла > 0) {
                            // TODO: 17.02.2023  загрузка ПО
                            МетодСообщенияЗагрузкиФайлаAPK(СервернаяВерсияПОРазмерФайла);
                        }
                        Log.d(this.getClass().getName(), " МетодСлушательПрелагаетЗагрузитьПО " + intent);
                    });
                }
            };
            IntentFilter intentFilterУстановка = new IntentFilter();
            intentFilterУстановка.addAction("AfterDownloadPO");
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт.registerReceiver(broadcastReceiverУстановкаПО, intentFilterУстановка);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(  getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //todo Финальный метод в ОБНОВЛЕНИИ ПО УСТАВНКА НЕПОСРЕДСВЕННО ФАЙЛА НА АКТИВТИ ПОЛЬЗОВАТЛЕМ
    @UiThread
    private void МетодФиналУстановкаПО(@NonNull Integer СервернаяВерсияПОВнутри,
                                       @NonNull File ЗагрузкиФайлаОбновенияПОДополнительный,
                                       @NonNull Context context) {
        try {
            File ФайлыДляОбновлениеВычисляемНомерВерсииПО = null;
            final PackageManager pm = context.getPackageManager();
            String apkName = "update_dsu1.apk";
            String fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;
            if (Build.VERSION.SDK_INT >= 30) {
                fullPath = Environment.getExternalStorageState() + "/" + apkName;
            } else {
                fullPath = Environment.getExternalStorageDirectory() + "/" + apkName;
            }
            fullPath = Environment.DIRECTORY_DOWNLOADS + "/" + apkName;
            PackageInfo info = pm.getPackageArchiveInfo(fullPath, 0);
            if (info != null) {
                Log.d(this.getClass().getName(), "VersionCode : " + info.versionCode + ", VersionName : " + info.versionName);
                СервернаяВерсияПОВнутри = info.versionCode;
            }
            final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
            AlertDialog alertDialog = new MaterialAlertDialogBuilder(activity)///       final AlertDialog alertDialog =new AlertDialog.Builder( MainActivity_Face_App.КонтекстFaceApp)
                    .setTitle("Установщик")
                    .setMessage("Пришло Обновление,"
                            + "\n" + "Союз-Автодор ПО ,"
                            + "\n" + "новая версия. " + СервернаяВерсияПОВнутри + ","//TODO old          + "\n" + "локальная версия. " + ЛокальнаяВерсияПОСравнение + ","
                            + "\n")
                    .setPositiveButton("Установить", null)
                    .setNegativeButton("Позже", null)
                    .setIcon(R.drawable.icon_dsu1_updates_po_success)
                    .show();
/////////кнопка
            final Button MessageBoxUpdateОбновитьПО = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBoxUpdateОбновитьПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА");
                    String ФинальныйПутьДляЗагрузкиФайлаОбновения = null;
                    if (Build.VERSION.SDK_INT >= 30) {
                        ФинальныйПутьДляЗагрузкиФайлаОбновения = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/";  //null
                    } else {
                        ФинальныйПутьДляЗагрузкиФайлаОбновения = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                    }
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА  ФинальныйПутьДляЗагрузкиФайлаОбновения " + ФинальныйПутьДляЗагрузкиФайлаОбновения);
                    String НазваниеФайлаОбновления = "update_dsu1.apk";
                    ФинальныйПутьДляЗагрузкиФайлаОбновения += НазваниеФайлаОбновления;
                    Uri URIПутиДляЗагрузкиФайловЧерезПровайдер = FileProvider.getUriForFile(context,
                            context.getPackageName() + ".provider",
                            ЗагрузкиФайлаОбновенияПОДополнительный);
                    Log.d(this.getClass().getName(), "Установка ЗагрузкиФайлаОбновенияПОДополнительный  " + ЗагрузкиФайлаОбновенияПОДополнительный);
                    Intent intentОбновлениеПО = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                    intentОбновлениеПО.setDataAndType(URIПутиДляЗагрузкиФайловЧерезПровайдер, "application/vnd.android.package-archive");
                    intentОбновлениеПО.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                            Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentОбновлениеПО.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                    intentОбновлениеПО.putExtra(Intent.EXTRA_STREAM, URIПутиДляЗагрузкиФайловЧерезПровайдер);
                    PackageManager МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт = activity.getPackageManager();
                    if (intentОбновлениеПО.resolveActivity(МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт) != null) {
                        //////todo запуск установкика .apk
                        ///     context. startActivity(intent); ////   ((Activity) MainActivity_Face_App.КонтекстFaceApp). startActivity(intent);//  MainActivity_Face_App.КонтекстFaceApp. startActivity(intent);
                        Log.d(this.getClass().getName(), " СЛУЖБА УСТАНОВКА... ОБНОВЛЕНИЯ НА ТЕЛЕФОН (.APK файл)  МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт "
                                + МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт);
                        ////TODO непосрдствено сам запуск новго .apk файла
                        activity.startActivity(intentОбновлениеПО);
                        activity.finishAndRemoveTask(); //// ((Activity) MainActivity_Face_App.КонтекстFaceApp).finish();
                        Log.w(this.getClass().getName(), " ура !!!! УРА !!!!  уСПЕШНАЫЙ ЗАПУСК СКАЧЕННОГО ОБНОВЛЕНЕИ ПО " +
                                "МетодУстановкиНовойВерсииПОТабельныйУчётПоднимаетЕгоНаActrivity  ");
                    } else {
                        ///////TODO ОСТАНАВЛИВАЕМ СЛУЖБУ ЧЕРЕЗ 20 СЕКУНД
                        Log.d(this.getClass().getName(), "Ошибка файл .APK не устнаовлен ОШИБКА СЛУЖБА ОБНОВЛЕНИЯ ...  "
                                + new Date() + " МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт " + МеханизмПроверкиЗапуститьсяНашИнтентИлиНЕт);
                    }
                }
            });
            final Button MessageBoxUpdateНеуСтанавливатьПО = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            MessageBoxUpdateНеуСтанавливатьПО.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //удаляем с экрана Диалог
                    alertDialog.dismiss();
                    Log.d(this.getClass().getName(), "MessageBoxUpdateНеуСтанавливатьПО  ОТМЕНА УСТАНВОКИ НОВГО ПО   dismiss ");
                    alertDialog.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    Integer МетодАнализаВерсииПОJSON() {
        final Integer[] СервернаяВерсияПОВнутри = new Integer[1];
        try {
            Log.d(this.getClass().getName(), " СЛУЖБА ... МЕТОД АНАЛИЗА ДАННЫХ РАБОТАЕТ......" + new Date());
            Log.w(getApplicationContext().getClass().getName(), " СервернаяВерсияПОВнутри  ОБНОВЛЕНИЕ ПО  НазваниеТекущего Потока " + Thread.currentThread().getName());
            // TODO: 17.12.2021 RXJAVA ПОЛУЧАЕМ JSON  ФАЙЛ ВЕРСИИ ПОРГРАМНОГО ПО ТАБЕЛЬНЫЙ УЧЁТ
            String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
            Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);

            Observable observableПолучаемНовуюВерсиюСервернойВерсииФайлаAPK = Observable.interval(1, TimeUnit.SECONDS)
                    .take(20, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.single())
                    .flatMap((string) -> {
                        PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(getApplicationContext());
                        // TODO: 08.01.2022
                        СервернаяВерсияПОВнутри[0] = new Class_MODEL_synchronized(getApplicationContext()).
                                //   УниверсальныйБуферJSONВерсииПОсСервера("dsu1.glassfish/update_android_dsu1/output-metadata.json", Контекст, public_content.getАдресСервера() , public_content.getПортСервера());
                                        УниверсальныйБуферJSONВерсииПОсСервера(new PUBLIC_CONTENT(getApplicationContext())
                                                .getСсылкаНаРежимСервера()+"/update_android_dsu1/output-metadata.json",
                                        getApplicationContext(), ИмяСерверИзХранилица ,ПортСерверИзХранилица);
                        Log.w(getApplicationContext().getClass().getName(), " СервернаяВерсияПОВнутри" + СервернаяВерсияПОВнутри[0] + "+" +Thread.currentThread().getName());
                        return Observable.fromArray(string).doOnComplete(System.out::println);
                    }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Observable<Object> objectObservable) throws Throwable {
                            activity.runOnUiThread(()->{
                                Toast toast = Toast.makeText(getApplicationContext(), "Поиск ПО ▼", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM, 0, 40);
                                toast.show();});
                            return objectObservable;
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :"
                                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .takeWhile(new Predicate<Object>() {
                        @Override
                        public boolean test(Object o) throws Throwable {
                            Log.w(getApplicationContext().getClass().getName(), "   takeWhile observableПолучаемНовуюВерсиюСервернойВерсииФайлаAPK"  +"\n"+
                                    " Thread.currentThread().getName() " +Thread.currentThread().getName()+ "  o " +o);
                            if (   СервернаяВерсияПОВнутри[0] >0) {
                                Log.w(getApplicationContext().getClass().getName(), "СервернаяВерсияПОВнутри  observableПолучаемНовуюВерсиюСервернойВерсииФайлаAPK ::::" +
                                        "  "+"\n"
                                        + СервернаяВерсияПОВнутри[0] +"\n"+
                                        " Thread.currentThread().getName() " +Thread.currentThread().getName());
                                return false;
                            }else {
                                return true;
                            }
                        }
                    })
                    .observeOn(Schedulers.single())
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                                Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                                        СервернаяВерсияПОВнутри[0]+   " СервернаяВерсияПОВнутри  " + Thread.currentThread().getName());
                            // TODO: 18.02.2023 Анализ Версии
                                МетодАнализВерсийЛокальнаяИСерверная(СервернаяВерсияПОВнутри[0]);
                        }
                    });
// TODO: 07.01.2022 GREAT OPERATIONS подпииска на данные
            observableПолучаемНовуюВерсиюСервернойВерсииФайлаAPK.subscribe();
        } catch (Exception e ) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return СервернаяВерсияПОВнутри[0];
    }

    private void МетодАнализВерсийЛокальнаяИСерверная(@NonNull Integer СервернаяВерсияПОВнутри) {
  try{
      PackageInfo    pInfo = getApplicationContext(). getPackageManager().getPackageInfo(getApplicationContext(). getPackageName(), 0);
        String version = pInfo.versionName;//Version Name
        Integer ЛокальнаяВерсияПО = pInfo.versionCode;//Version Code
        if (СервернаяВерсияПОВнутри >ЛокальнаяВерсияПО ) {
            МетодСообщениеЗапускЗагрущикаПо(СервернаяВерсияПОВнутри);
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + Thread.currentThread().getName());
        }else{
            Log.w(getApplicationContext().getClass().getName(),    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " ЛокальнаяВерсияПО "+ЛокальнаяВерсияПО+  " СервернаяВерсияПОВнутри  "+СервернаяВерсияПОВнутри + " POOLS" + Thread.currentThread().getName());
            activity.runOnUiThread(()->{
                Toast toast = Toast.makeText(getApplicationContext(), "У Вас последняя версия ПО !!! ", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 40);
                toast.show();
            });
        }
    } catch (Exception e ) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}
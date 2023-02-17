package com.dsy.dsu.For_Code_Settings_DSU1;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.BuildConfig;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_Clears_Tables;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.Class_Update_Download_File_APK_From_SERVER;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Code_ForTABEL.MainActivity_List_Tabels;
import com.dsy.dsu.Code_ForTABEL.MainActivity_New_Templates;
import com.dsy.dsu.Code_For_AdmissionMaterials_ПоступлениеМатериалов.MainActivity_AdmissionMaterials;
import com.dsy.dsu.Code_For_Commit_Payments_КодДля_Согласование.MainActivity_CommitPay;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.Service_Async_1C;
import com.dsy.dsu.Code_For_Services.Service_For_Public;
import com.dsy.dsu.Code_For_Services.Service_Notificatios_Для_Согласования;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.Code_For_Services.Service_ДляЗапускаодноразовойСинхронизации;
import com.dsy.dsu.Code_Shipment_of_Materials_ОтгрузкаМатериалов.Fragment1_List_Shipment_of_Materials;
import com.dsy.dsu.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.AtomicDouble;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/////////////////////////////////////////////////////////////////////////
public class MainActivity_Face_App extends AppCompatActivity {
    private ImageView imageView_ЗначекApp;
    private CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;
    private LinearLayout LinearLayoutFaceApp;
    private ScrollView ScrollFaceAppСкорол;
    private Observer observerОдноразоваяFACEAPP;
    private Activity activity;
    private PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    private boolean РежимыПросмотраДанныхЭкрана;
    private Context context;
    // private MaterialCardView КнопкаЗадачи, КнопкаТабель, КнопкаЧат,КнопкаСогласование,КнопкаОтгрузкаМатериалов;
    private MaterialCardView КнопкаТабель, КнопкаСогласование, КнопкаПоступлениеМатериалов;
    // private ProgressBar progressBarTask, progressBarTabel, progressBarChat,progressCommitpay,progressShipment_of_Materials;
    private ProgressBar progressBarTabel, progressCommitpay;
    private Handler handlerFaceAPP;
    private String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";//"WorkManager Synchronizasiy_Data";//  "WorkManager Synchronizasiy_Data"; ///"WorkManager Synchronizasiy_Data";
    private SubClassUpdatePOОбновлениеПО БизнесЛогикаFaceApp;
    private DrawerLayout drawerLayoutFaceApp;
    private NavigationView navigationViewFaceApp;
    private ConstraintLayout constraintLayoutFaceApp;
    private TextView textViewСканированиеBluetooth;
    @Inject
    private Service_Notificatios_Для_Согласования.LocalBinderДляСогласования binderСогласования1C;
    private ServiceConnection connectionСогласования;
    @Inject
    private Fragment1_List_Shipment_of_Materials fragment1_list_shipment_of_materials;
    private Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binderМатериалы;
    private ServiceConnection serviceConnectionМатериалы;
    private Animation animation;
    private AsyncTaskLoader asyncTaskLoader;
    protected Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации binderAsyns;
    protected SharedPreferences preferences;
    private Service_Async_1C service_Async_СинхронизацияОБЩАЯ1С;
    private SubClassUpdatePOОбновлениеПО subClassUpdatePOОбновлениеПО;

    // TODO: 03.11.2022 FaceApp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_face_app);
            Log.w(getPackageName().getClass().getName(), "Сработал  protected void onCreate(Bundle savedInstanceState)  в MainActivity_Face_App");
            LinearLayoutFaceApp = (LinearLayout) findViewById(R.id.LineLayFaceApp); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            context = this;
            activity = this;
            getSupportActionBar().hide(); ///скрывать тул бар
            ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getApplicationContext());
            Create_Database_СсылкаНАБазовыйКласс = new CREATE_DATABASE(getApplicationContext());
            КнопкаТабель = (MaterialCardView) findViewById(R.id.cardview2_For_MainActivity); ///// TODO КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаСогласование = (MaterialCardView) findViewById(R.id.cardviewCommitPay_For_MainActivity); ///// TODO КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаПоступлениеМатериалов = (MaterialCardView) findViewById(R.id.cardviewControlAccess); /////TODO КОНТРОЛЬ ДОСТУПА
            Log.d(this.getClass().getName(), "КнопкаЧат " + " КнопкаЗадачи "
                    + " КнопкаТабель " + КнопкаТабель + " КнопкаСогласование " + КнопкаСогласование + " КнопкаКонтрольДоступа " + КнопкаПоступлениеМатериалов);
            imageView_ЗначекApp = (ImageView) findViewById(R.id.imageView_ЗначекApp); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_dsu1_for_mains_menu_faceapp111);///
            imageView_ЗначекApp.setImageDrawable(drawable);
            progressBarTabel = (ProgressBar) findViewById(R.id.prograessbarTabel_inner_ardview_forMainActivity); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            progressCommitpay = (ProgressBar) findViewById(R.id.prograessbarCommitPay_inner_ardview_forMainActivity4); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            drawerLayoutFaceApp = (DrawerLayout) findViewById(R.id.drawerLayout_faceapp_menu); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            constraintLayoutFaceApp = (ConstraintLayout) findViewById(R.id.constraintLayout_faceapp22); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            navigationViewFaceApp = (NavigationView) findViewById(R.id.navigator_faceapp_main); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            drawerLayoutFaceApp.setBackgroundColor(Color.WHITE);         //TODO устанвливает цвета
            constraintLayoutFaceApp.setBackgroundColor(Color.WHITE);
            drawerLayoutFaceApp.setBackgroundColor(Color.WHITE);
            drawerLayoutFaceApp.setDrawingCacheBackgroundColor(Color.RED);//todo
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_tabel);//TODO animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_vibrator1);
            preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            progressBarTabel.setVisibility(View.INVISIBLE);
            progressCommitpay.setVisibility(View.INVISIBLE);
            // TODO: 04.10.2022 ТЕСТ КОД
            Bundle data = getIntent().getExtras();
            if (data != null) {
                binderМатериалы = (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");
            }
            if (data != null) {
                binderСогласования1C = (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) data.getBinder("binderСогласования1C");
            }
            if (data != null) {
                binderAsyns = (Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации) data.getBinder("binderAsyns");
            }
            // TODO: 17.02.2023 дополнительеные методы которые зарускаются только один раз
            // TODO: 06.04.2022
            МетодДляСлушательБоковойПанелиFaceApp();
            // TODO: 03.11.2022 биндинг служб
            МетодБиндингМатериалы();
            МетодБиндингаСогласования();
            МетодБиндингAsync();
            Handlers();
            // TODO: 16.11.2022  ПОСЛЕ УСТАНОВКИ РАБОТАЕТ ОДИН РАЗ ПРИ СТАРТЕ ЗАРУСК ОБЩЕГО WORK MANAGER
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодЗапускаетОБЩУЮСинхронизацию();
            // TODO: 16.02.2023 Обновление ПО
            subClassUpdatePOОбновлениеПО=new SubClassUpdatePOОбновлениеПО(getApplicationContext(), activity, handlerFaceAPP);
            subClassUpdatePOОбновлениеПО.МЕтодУстанавливаемРазрешенияДляОновлениеПО();
            subClassUpdatePOОбновлениеПО.МетодФиналСлушательУстановщикПО();
            subClassUpdatePOОбновлениеПО.МетодСлушательПрелагаетЗагрузитьПО();
            // TODO: 17.02.2023 другие методы
            МетодЗапускПоступлениеМатериалов();
            МетодFaceApp_СлушательПриНажатииНаКнопки();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
// TODO: 06.06.2021 ЗАПУСК ТРЕХ СЛУЖБ
            Log.w(getPackageName().getClass().getName(), "drawerLayoutFaceApp    " + drawerLayoutFaceApp +
                    "  navigationViewFaceApp " + navigationViewFaceApp);/////////
            МетодПовторныйЗапускУведомений();
            МетодБоковаяПанельОткрытьЗАкрыть();
            // TODO: 17.02.2023 ЗапускАнализа Наличитие Новой Версии ПО
            subClassUpdatePOОбновлениеПО.МетодЗапускАнализаПО(false);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            КнопкаТабель.setAnimation(animation);
            КнопкаСогласование.setAnimation(animation);
            КнопкаПоступлениеМатериалов.setAnimation(animation);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодБоковаяПанельОткрытьЗАкрыть() {
        try {
            if (drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                drawerLayoutFaceApp.closeDrawer(Gravity.LEFT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void МетодЗапускПоступлениеМатериалов() {
        try {
            КнопкаПоступлениеМатериалов.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ИнтентДляЗапускаПолуступлениеМатериалов = new Intent(getApplicationContext(), MainActivity_AdmissionMaterials.class);
                    Bundle data = new Bundle();
                    data.putBinder("binder", binderМатериалы);
                    data.putBinder("binderAsyns", binderAsyns);
                    ИнтентДляЗапускаПолуступлениеМатериалов.putExtras(data);
                    ИнтентДляЗапускаПолуступлениеМатериалов.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(ИнтентДляЗапускаПолуступлениеМатериалов);
                    Log.w(getPackageName().getClass().getName(), "ИнтентДляЗапускаПолуступлениеМатериалов    ");/////////*/
                    //  Snackbar.make(v, "В разработке Получение материалов !!! ", Snackbar.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 03.04.2022
    private void МетодДляСлушательБоковойПанелиFaceApp() {
        // TODO: 06.04.2022
        try {
            imageView_ЗначекApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (navigationViewFaceApp.isShown()) {
                        navigationViewFaceApp.setVisibility(View.GONE);
                        if (drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                            drawerLayoutFaceApp.closeDrawer(Gravity.LEFT);
                        }
                    } else {
                        navigationViewFaceApp.setVisibility(View.VISIBLE);
                        if (!drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                            drawerLayoutFaceApp.openDrawer(Gravity.LEFT);
                        }
                    }
                }
            });
            drawerLayoutFaceApp.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerOpened(View drawerView) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.icon_dsu1_for_mains_menu_faceapp_close222);///
                    imageView_ЗначекApp.setImageDrawable(drawable);
                    navigationViewFaceApp.setVisibility(View.VISIBLE);
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.icon_dsu1_for_mains_menu_faceapp111);///
                    imageView_ЗначекApp.setImageDrawable(drawable);
                    navigationViewFaceApp.setVisibility(View.GONE);
                    super.onDrawerClosed(drawerView);
                }
            });
            navigationViewFaceApp.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        // TODO: 06.04.2022
                        case R.id.one:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() Посмотреть ошибки   " + item.getItemId() + "\n");//////////
                            try {
                                Intent Интент_Меню = new Intent(getApplication(), MainActivity_Errors.class);
                                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                                Log.d(this.getClass().getName(), "" +
                                        "                     case R.id.ПунктМенюПервый:");
                                startActivity(Интент_Меню);
                            } catch (Exception e) {
                                //  Block of code to handle errors
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.two:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() Хотите перерйти в натройки    " + item.getItemId() + "\n");/////////
                            try {
                                МетодЗапускаИзМенюНастроек();
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.tree:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() Сменить пользователя и смена данных    " + item.getItemId() + "\n");/////////
                            try {
                                asyncTaskLoader = new AsyncTaskLoader(getApplicationContext()) {
                                    @Nullable
                                    @Override
                                    public Object loadInBackground() {
                                        Boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных =
                                                new Class_Connections_Server(getApplicationContext()).МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                                        return РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных;
                                    }
                                };
                                asyncTaskLoader.startLoading();
                                asyncTaskLoader.forceLoad();
                                asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener() {
                                    @Override
                                    public void onLoadComplete(@NonNull Loader loader, @Nullable Object data) {
                                        Boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных = (Boolean) data;
                                        if (РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных == true) {
                                            String ПолученыйТекущееИмяПользователя = new Class_MODEL_synchronized(getApplicationContext())
                                                    .МетодПолучениеИмяСистемыДляСменыПользователя(getApplicationContext());
                                            Log.d(this.getClass().getName(), "  ПолученыйТекущееИмяПользователя " +
                                                    ПолученыйТекущееИмяПользователя);
                                                МетодДиалогаДляМеню("Пользователи Системы", "При смене пользователя,"
                                                    + "\n" + " поменяються и данные системы." + "\n"
                                                    + "Поменять пользователя ?" + "\n"
                                                    + " (текущий пользователь : ) " + ПолученыйТекущееИмяПользователя.toUpperCase());
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Для смены данных, нужно подключение к серверу !!! "
                                                    + "\n", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.four:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId()  Синхронизация Данных с Web-сервера ДСУ-1  " + item.getItemId() + "\n");/////////
                            try {
                                // TODO: 28.09.2022 ЗАпускаем синхронизацию
                                МетодЗапускаСинихрниазцииИзМенюНаАктивтиFACEAPP();
                                Log.d(this.getClass().getName(), "Отработала синх.. Из Меню Активти FACEAPP Синхронизация Данных с Web-сервера ДСУ-1 ?");
                            } catch (Exception e) {
                                //  Block of code to handle errors
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.shestoy:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId()  Шаблоны из меню  " + item.getItemId() + "\n");/////////
                            try {
                                Intent Интент_BackВозвращаемАктивти = new Intent();
                                Интент_BackВозвращаемАктивти.setClass(getApplicationContext(), MainActivity_New_Templates.class); // Т
                                Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Интент_BackВозвращаемАктивти.putExtra("ЗапускШаблоновFaceAppБлокировкаКнопкиДа", true);
                                startActivity(Интент_BackВозвращаемАктивти);
                            } catch (Exception e) {
                                //  Block of code to handle errors
                                e.printStackTrace();
                                ///метод запись ошибок в таблицу
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;
                        case R.id.sedmoy:
                            item.setChecked(true);
                            Log.w(getPackageName().getClass().getName(), "item.getItemId() МЕНЮ ОБНОВЛЕНИЕ ПО    " + item.getItemId() + "\n"+item);/////////
                            try {
                                // TODO: 07.10.2022 ЗАПУСК АНАЛИЗА ПО
                                new SubClassUpdatePOОбновлениеПО(getApplicationContext(), activity, handlerFaceAPP).МетодЗапускАнализаПО(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            break;

                        default:
                            // TODO: 06.04.2022
                            return false;
                    }
                    if (drawerLayoutFaceApp.isDrawerOpen(Gravity.LEFT)) {
                        drawerLayoutFaceApp.closeDrawer(Gravity.LEFT);
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 04.04.2022
    }


    // TODO: 02.08.2022  код ля биндинга службы одноразовой синхронизации
    public void МетодБиндингМатериалы() {
        try {
            if (binderМатериалы == null) {
                serviceConnectionМатериалы = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            binderМатериалы = (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) service;
                            if (binderМатериалы.isBinderAlive()) {
                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "    onServiceDisconnected  Service_for_AdminissionMaterial" + " binderСогласованияbinderМатериалы.isBinderAlive() "
                                        + binderМатериалы.isBinderAlive());
                                binderМатериалы.queryLocalInterface("МетодБиндингМатериалы");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 11.05.2021 запись ошибок
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    "  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder" + binderМатериалы);
                            binderМатериалы = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            // TODO: 11.05.2021 запись ошибок
                        }
                    }
                };

               /*    Intent intentЗапускСлужюыыСинхрониазцииБиндинг = new Intent(context, Service_for_AdminissionMaterial.class);
                    context. bindService(intentЗапускСлужюыыСинхрониазцииБиндинг, serviceConnection, Context.BIND_AUTO_CREATE |
                            Context.BIND_ALLOW_OOM_MANAGEMENT |
                            Context.BIND_ADJUST_WITH_ACTIVITY | Context.BIND_IMPORTANT );*/
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Intent intentЗапускСлужюыыСинхрониазцииБиндинг = new Intent(getApplicationContext(), Service_for_AdminissionMaterial.class);
                bindService(intentЗапускСлужюыыСинхрониазцииБиндинг, Context.BIND_AUTO_CREATE |
                        Context.BIND_ALLOW_OOM_MANAGEMENT |
                        Context.BIND_ADJUST_WITH_ACTIVITY | Context.BIND_IMPORTANT, executorService, serviceConnectionМатериалы);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 03.11.2022  1c
    // TODO: 29.03.2022  метод регмстарцмии локального брод кастера доля смен задачи


    private void МетодБиндингаСогласования() {
        try {
            if (binderСогласования1C == null) {
                connectionСогласования = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            binderСогласования1C = (Service_Notificatios_Для_Согласования.LocalBinderДляСогласования) service;
                            if (binderСогласования1C.isBinderAlive()) {
                                Log.i(getApplicationContext().getClass().getName(), "    onServiceConnected  binderСогласованияbinderМатериалы.isBinderAlive()"
                                        + binderСогласования1C.isBinderAlive());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()" + binderСогласования1C.isBinderAlive());
                            binderСогласования1C = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                Intent intentЗапускСлужюыыСинхрониазцииБиндинг1C = new Intent(getApplicationContext(), Service_Notificatios_Для_Согласования.class);
                bindService(intentЗапускСлужюыыСинхрониазцииБиндинг1C, Context.BIND_AUTO_CREATE |
                        Context.BIND_ALLOW_OOM_MANAGEMENT |
                        Context.BIND_ADJUST_WITH_ACTIVITY | Context.BIND_IMPORTANT, executorService, connectionСогласования);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 02.08.2022  код ля биндинга службы одноразовой синхронизации
    public void МетодБиндингAsync() {
        try {
            Intent intentЗапускСлужюыыСинхрониазцииБиндинг = new Intent(context, Service_ДляЗапускаодноразовойСинхронизации.class);
            context.bindService(intentЗапускСлужюыыСинхрониазцииБиндинг, connectionДляОдноразовойСинхронизации, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private ServiceConnection connectionДляОдноразовойСинхронизации = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                binderAsyns = (Service_ДляЗапускаодноразовойСинхронизации.LocalBinderДляЗапускаОдноразовойСнхронизации) service;
                if (binderAsyns.isBinderAlive()) {
                    // TODO: 16.11.2022
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "    onServiceDisconnected  service_дляЗапускаодноразовойСинхронизации binderAsyns.pingBinder() " + binderAsyns.pingBinder());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            try {
                binderAsyns = null;
                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "    onServiceDisconnected  binderAsyns" + binderAsyns);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 11.05.2021 запись ошибок

            }
        }
    };


    private void МетодПовторныйЗапускУведомений() {

        try {
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодЗапускаУведомленияДляЗАДАЧ();
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодЗапускаУведомленияЧАТА();
            Log.w(getPackageName().getClass().getName(), "  " +
                    " new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодПовторногоЗапускаУведомленияДляОдноразовойСинхрониазации()");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    void МетодПредлагаемЗаргузитьНовыюВерсиюПО(@NonNull Integer СервернаяВерсияПОВнутри) {
        try {
            File ФайлыДляОбновлениеВычисляемНомерВерсииПО = null;
            final PackageManager pm =context. getPackageManager();
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
            // TODO: 02.04.2022
            final Object ТекущаяВерсияПрограммы = BuildConfig.VERSION_CODE;
            Integer ЛокальнаяВерсияПОСравнение = Integer.parseInt(ТекущаяВерсияПрограммы.toString());
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
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА");
                    String ФинальныйПутьДляЗагрузкиФайлаОбновения = null;
                    ////
                    Log.d(this.getClass().getName(), " СервернаяВерсияПОВнутри" + finalСервернаяВерсияПОВнутри);
                    Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
                    Class_Update_Download_File_APK_From_SERVER class_update_download_file_apk_from_server = new Class_Update_Download_File_APK_From_SERVER(getApplicationContext(), null);
                    class_update_download_file_apk_from_server.МетодУдалениеИнформационогоТекстовогоФайлаJSONДляПО();
                    Log.i(getApplicationContext().getClass().getName(), " ЗАПУСКАЕМ МетодУдалениеИнформационогоТекстовогоФайлаJSONДляПО();  ");
                    class_update_download_file_apk_from_server.МетодУдалениеСамогоФайлаПрограммыПОТальныйУчётПО_APK();
                    Log.i(getApplicationContext().getClass().getName(), " ЗАПУСКАЕМ МетодУдалениеСамогоФайлаПрограммыПОТальныйУчётПО_APK();  ");
                    try {
                        class_update_download_file_apk_from_server.МетодНачалаЗапускаОбновленияПО(finalСервернаяВерсияПОВнутри, context);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(getApplicationContext().getClass().getName(), " УЖЕ ЗАГРУзили ПО ПОЛЬЗОВАТЕЛЬ НАЖАЛ НА КОНОПКУ ЗАГУРДИТЬ   " +
                            "Service_Notifocations_Для_Чата (intent.getAction()   СЛУЖБА" + finalСервернаяВерсияПОВнутри + " время запуска  " + new Date());

                    alertDialog.dismiss();
                    alertDialog.cancel();
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
    
    void МетодFaceApp_СлушательПриНажатииНаКнопки() {
        try {

            КнопкаТабель.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // TODO: 23.03.2022
                        progressBarTabel.setVisibility(View.VISIBLE);
                        // TODO: 23.03.2022
                        КнопкаТабель.setBackgroundColor(Color.GRAY);
                        Intent Интент_ЗапускТабельногоУчётаПервыйШаг = new Intent();
                        Bundle data = new Bundle();
                        data.putBinder("binder", binderМатериалы);
                        Интент_ЗапускТабельногоУчётаПервыйШаг.putExtras(data);
                        Интент_ЗапускТабельногоУчётаПервыйШаг.setClass(getApplication(), MainActivity_List_Tabels.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                        Интент_ЗапускТабельногоУчётаПервыйШаг.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Log.d(this.getClass().getName(), "" + "    КнопкаТабельныйУчёт.setOnClickListener(new View.OnClickListener() {");
                        startActivity(Интент_ЗапускТабельногоУчётаПервыйШаг);
                        context = null;
                        handlerFaceAPP.postDelayed(() -> {
                            progressBarTabel.setVisibility(View.INVISIBLE);
                            КнопкаТабель.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        }, 3000);
                        context = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }


            });

            КнопкаСогласование.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        progressCommitpay.setVisibility(View.VISIBLE);
                        КнопкаСогласование.setBackgroundColor(Color.GRAY);
                        Log.d(this.getClass().getName(), "Запускает Согласния   ");
                        Intent intentЗапускСогласования1C = new Intent();
                        Bundle data = new Bundle();
                        data.putBinder("binderСогласования1C", binderСогласования1C);
                        intentЗапускСогласования1C.putExtras(data);
                        intentЗапускСогласования1C.setClass(getApplicationContext(), MainActivity_CommitPay.class);//рабочий
                        intentЗапускСогласования1C.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentЗапускСогласования1C);
                        handlerFaceAPP.postDelayed(() -> {
                            progressCommitpay.setVisibility(View.INVISIBLE);
                            КнопкаСогласование.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        }, 3000);
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

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


    private void МетодЗапускаСинихрниазцииИзМенюНаАктивтиFACEAPP() {
        final Boolean[] РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции = {false};
        try {

            final Integer[] ФинальныйРезультатФоновойСинхронизации = {0};
            ProgressDialog progressDialogДляСинхронизации;
            progressDialogДляСинхронизации = new ProgressDialog(activity);
            progressDialogДляСинхронизации.setTitle("Синхронизация");
            progressDialogДляСинхронизации.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialogДляСинхронизации.setProgress(0);
            progressDialogДляСинхронизации.setCanceledOnTouchOutside(false);
            progressDialogДляСинхронизации.setMessage("Обмен данными ....");
            progressDialogДляСинхронизации.show();

            МетодОдноразновгоWorkmnaager();

            asyncTaskLoader = new AsyncTaskLoader(getApplicationContext()) {
                @Nullable
                @Override
                public Object loadInBackground() {
                    // TODO: 02.02.2022 запуск синхрониазции из Активти из МЕНЮ FACEAPP
                    boolean РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию =
                            new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                    Log.d(this.getClass().getName(), "  РезультатПроВеркиУстановкиПользователяРежимРаботыСети "
                            + РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию);
                    Class_Connections_Server class_connections_serverПингаСерераИзАктивтиМеню = new Class_Connections_Server(getApplicationContext());
                    PUBLIC_CONTENT public_contentЗапусСинхрониазцииИМеню = new PUBLIC_CONTENT(getApplicationContext());

                    if (РезультатПроВеркиУстановкиПользователяРежимРаботыСетиСтоитЛиЗапускатьСсинхронизацию == true) {
                        РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции[0]
                                = class_connections_serverПингаСерераИзАктивтиМеню.
                                МетодПингаСервераРаботаетИлиНет(getApplicationContext());


                        if (РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции[0] == true) {

                            Integer ПубличныйIDДляОдноразовойСинхрониазции =
                                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());

                            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).
                                    МетодЗапускаетОДНОРАЗОВУЮСинхронизациюВнутриWorkManager(getApplicationContext(),
                                            Integer.parseInt(ПубличныйIDДляОдноразовойСинхрониазции.toString()));

                            Log.d(this.getClass().getName(), "Синхронизация Данных с Web-сервера ДСУ-1 ?  ФинальныйРезультатФоновойСинхронизации[0] " +
                                    ФинальныйРезультатФоновойСинхронизации[0]);
                        }
                    }
                    return РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции[0];
                }
            };
            asyncTaskLoader.startLoading();
            asyncTaskLoader.forceLoad();
            asyncTaskLoader.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(@NonNull Loader loader, @Nullable Object data) {
                    if ((Boolean) data == false) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast toast = Toast.makeText(getApplicationContext(), "Сервер выкл. !!!", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM, 0, 40);
                                toast.show();
                            }
                        });

                    }
                    handlerFaceAPP.postDelayed(() -> {
                        progressDialogДляСинхронизации.dismiss();
                        progressDialogДляСинхронизации.cancel();
                    }, 2000);
                    System.out.println("doOnTerminate  Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков.poll().get() ");
                    Log.w(this.getClass().getName(), "  doOnComplete " +
                            "  doOnTerminate Синхронизация Данных с Web-сервера ДСУ-1 ? РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции[0] "
                            + РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции[0]);

                    asyncTaskLoader.reset();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }

    void МетодОдноразновгоWorkmnaager() {
        try {
            LifecycleOwner lifecycleOwner = this;
            lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    source.getLifecycle().getCurrentState();
                    event.getTargetState().name();
                }
            });
            String ИмяСлужбыСинхронизациОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
            WorkManager.getInstance(getApplicationContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).observe(lifecycleOwner, new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfos) {
                    workInfos.forEach((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                        try {
                            if (СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0) {
                                Long CallBaskОтWorkManagerОдноразового =
                                        СтастусWorkMangerДляФрагментаЧитатьИПисать.getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая",
                                                0l);
                                // TODO: 22.11.2022 удаление
                                Intent intentУдалениеСтатусаУдаленияТабеля = new Intent();
                                intentУдалениеСтатусаУдаленияТабеля.setClass(getApplicationContext(), Service_For_Public.class);
                                intentУдалениеСтатусаУдаленияТабеля.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
                                startService(intentУдалениеСтатусаУдаленияТабеля);
                                WorkManager.getInstance(getApplicationContext()).cancelUniqueWork(ИмяСлужбыСинхронизациОдноразовая);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }


    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ    ///MESSGABOX ДЛЯ ГЛАВНОГО МЕНЮ
    @UiThread
    protected void МетодДиалогаДляМеню(String ШаблонСообщения, String Самообщение) {
        try {
//////сам вид
            final AlertDialog DialogBox = new MaterialAlertDialogBuilder(activity)
                    .setTitle(ШаблонСообщения)
                    .setMessage(Самообщение)
                    .setPositiveButton("Да", null)
                    .setNegativeButton("Нет", null)
                    .setIcon(R.drawable.icon_dsu1_web_success)
                    .show();
            final Button MessageBox = DialogBox.getButton(AlertDialog.BUTTON_POSITIVE);
            MessageBox.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    try {
                        DialogBox.dismiss();
                        Intent Интент_Меню = new Intent();
                        switch (ШаблонСообщения.trim()) {
                            case "Ошибки системы":
                                try {
                                    Интент_Меню = new Intent(getApplication(), MainActivity_Errors.class);
                                    Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                                    startActivity(Интент_Меню);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                break;
                            case "Данных системы":
                                try {
                                    Integer ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
                                    if (ПубличныйIDДляФрагмента == null) {
                                        ПубличныйIDДляФрагмента = 0;
                                    }
                                    new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).
                                            МетодЗапускаетОДНОРАЗОВУЮСинхронизациюВнутриWorkManager(getApplicationContext(),
                                                    Integer.parseInt(ПубличныйIDДляФрагмента.toString()));
                                    Log.d(this.getClass().getName(), "    case \"Данных системы\": запуск синхрониазции из активти Одноразвой Службой  " + ПубличныйIDДляФрагмента);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                                break;
                            case "Пользователи Системы":
                                Integer РезультатОчистикТАблицИДобалениеДаты = 0;
                                try {
                                    Handler handlerУдалениеТаблицПринудительно = new Handler();
                                    PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(activity);
                                    РезультатОчистикТАблицИДобалениеДаты =
                                            new Class_Clears_Tables(activity, handlerУдалениеТаблицПринудительно)
                                                    .ОчисткаТаблицДляПользователяЗапусксFaceApp(activity,
                                                            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                                                            activity);
                                    Log.d(this.getClass().getName(), "   ЗАПУСК ФОНРезультатОчистикТАблицИДобалениеДаты " +
                                            РезультатОчистикТАблицИДобалениеДаты);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }


                                ////
                                break;
                            ///////
                            case "Настройка системы":
                                /////данные с потока
                                /////TODO ЗАПУСКАМ ОБНОЛВЕНИЕ ДАННЫХ С СЕРВЕРА ПЕРЕРД ЗАПУСКОМ ПРИЛОЖЕНИЯ ВСЕ ПРИЛОЖЕНИЯ ДСУ-1
                                Интент_Меню.setClass(getApplication(), MainActivity_Settings.class); //MainActivity_Visible_Async //MainActivity_Face_App
                                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                                startActivity(Интент_Меню);
                                ////TODO ДАННАЯ КОМАНДА ПЕРЕКРЫВАЕТ НЕ ЗАПУСКАЕМОЕ АКТИВТИ А АКТИВТИ КОТОРЕ ЕГО ЗАПУСТИЛО
                                finish();
                                break;
                        }

//ловим ошибки
                    } catch (Exception e) {
                        e.printStackTrace();
                        ///метод запись ошибок в таблицу
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                        // конец запись в файл
                    }

                }


            });

        } catch (Exception e) {
            e.printStackTrace();
///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР  ///////////СЕРВЕР

    }

    void ВиндингСлужбы1С() {
        try {
            ServiceConnection connectionОБЩЕЙ1СGet = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        Service_Async_1C.LocalBinderGET1С binder = (Service_Async_1C.LocalBinderGET1С) service;
                        if (binder.isBinderAlive() == true) {
                            Log.i(context.getClass().getName(), "    onServiceConnected  " +
                                    "service_Async_СинхронизацияОБЩАЯ1С" + service_Async_СинхронизацияОБЩАЯ1С);
                            service_Async_СинхронизацияОБЩАЯ1С = binder.getService();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    try {
                        Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  " +
                                "service_Async_СинхронизацияОБЩАЯ1С" + service_Async_СинхронизацияОБЩАЯ1С);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    protected void МетодЗапускаИзМенюНастроек() {
        try{
            Intent      Интент_Меню = new Intent(context, MainActivity_Settings.class);
            Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
            Log.d(this.getClass().getName(), "" +
                    "          case R.id.ПунктМенюВторой:");
            context.      startActivity(Интент_Меню);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void Handlers(){
        try{
            handlerFaceAPP=new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                ////TODO НАЧИНАЕМ ЗАГРУЗКИ С ИНТРЕНТА ФАЙЛ А ЕСЛИ ТОЛЬКО ЕГО НЕТ УЖЕ НА КЛИЕНТЕ
                Log.i(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск     УниверсальныйБуферAPKФайлаПОсСервера.subscribe  " +
                        "  УниверсальныйБуферAPKФайлаПОсСервераВнутри " +
                        "\n"+ " Thread.currentThread().getName() " +Thread.currentThread().getName());
               /// msg.getTarget().removeCallbacksAndMessages(msg);
                return true;
            }
        });
       // handler.obtainMessage(0,0,0,new Object()).sendToTarget();

/*       Message handler11=     new Handler(Looper.myLooper()).obtainMessage();
            handler11.getTarget().post(()->{
                Log.i(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск     УниверсальныйБуферAPKФайлаПОсСервера.subscribe  " +
                        "  УниверсальныйБуферAPKФайлаПОсСервераВнутри " +
                        "\n"+ " Thread.currentThread().getName() " +Thread.currentThread().getName());
            });



        Message message=Message.obtain(new Handler(Looper.myLooper()),()->{
            Log.i(this.getClass().getName(), "УниверсальныйБуферAPKФайлаПОсСервераВнутри файл записалься на диск     УниверсальныйБуферAPKФайлаПОсСервера.subscribe  " +
                    "  УниверсальныйБуферAPKФайлаПОсСервераВнутри " +
                    "\n"+ " Thread.currentThread().getName() " +Thread.currentThread().getName());
        });

        Bundle b=new Bundle();
        b.putString("dddd","555555555555555555555");
            message.setData(b);
        message.sendToTarget();*/
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
}


// TODO: 23.02.2022 ВТОРОЙ SUB СЛАСС

class SubClassUpdatePOОбновлениеПО  {

    Context context;
    // TODO: 23.02.2022
    Activity activity;
    Handler handler;

    public SubClassUpdatePOОбновлениеПО(@NonNull Context context,
                                        @NonNull Activity activity,
                                        @NonNull Handler handler) {
        this.context = context;
        this.activity = activity;
        this.handler=handler;
    }


    private void МетодПредлагаемЗаргузитьНовыюВерсиюПО(@NonNull Integer СервернаяВерсияПОВнутри, @NonNull Context context) {
        try {
            File ФайлыДляОбновлениеВычисляемНомерВерсииПО = null;
            final PackageManager pm = context. getPackageManager();
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
            // TODO: 02.04.2022
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            Long  ЛокальнаяВерсияПОСравнение = pInfo.getLongVersionCode();
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
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА");
                    alertDialog.dismiss();
                    alertDialog.cancel();
                    String ФинальныйПутьДляЗагрузкиФайлаОбновения = null;
                    ////
                    Log.d(this.getClass().getName(), " СервернаяВерсияПОВнутри" + finalСервернаяВерсияПОВнутри);
                    Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
                    Class_Update_Download_File_APK_From_SERVER class_update_download_file_apk_from_server=new Class_Update_Download_File_APK_From_SERVER(context,null);
                    class_update_download_file_apk_from_server.МетодУдалениеИнформационогоТекстовогоФайлаJSONДляПО();
                    Log.i(context.getClass().getName(), " ЗАПУСКАЕМ МетодУдалениеИнформационогоТекстовогоФайлаJSONДляПО();  ");
                    class_update_download_file_apk_from_server.МетодУдалениеСамогоФайлаПрограммыПОТальныйУчётПО_APK();
                    Log.i(context.getClass().getName(), " ЗАПУСКАЕМ МетодУдалениеСамогоФайлаПрограммыПОТальныйУчётПО_APK();  ");
                    try {
                        // TODO: 16.02.2023  НАчинаем Саму загрузки
                        class_update_download_file_apk_from_server.МетодНачалаЗапускаОбновленияПО(finalСервернаяВерсияПОВнутри,context);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    Log.i(context.getClass().getName(), " УЖЕ ЗАГРУзили ПО ПОЛЬЗОВАТЕЛЬ НАЖАЛ НА КОНОПКУ ЗАГУРДИТЬ   " +
                            "Service_Notifocations_Для_Чата (intent.getAction()   СЛУЖБА" + finalСервернаяВерсияПОВнутри + " время запуска  " + new Date());
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
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    void МетодЗагрузкиНовогоПО() {
        try {
            Log.d(this.getClass().getName(), "  МетодЗапускПослеНажатияНАНовойФормеНАКнопкуУстановитьПослеУспешнойЗагрузкиНовогоПОТабельныйУчётПоказываемЕгоПользователю");
            // TODO: 25.03.2022 Создание Локального БродКстаера
            LocalBroadcastManager localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт;
            BroadcastReceiver broadcastReceiverУстановкаПО;
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт = LocalBroadcastManager.getInstance(context);
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
                                МетодУстановкиНовойВерсииПО(ЗагрузиласьНоваяВерисяПОПровремяем, ЗагрузкиФайлаОбновенияПОДополнительный,context);
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
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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
            final PackageManager pm =context. getPackageManager();
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
            Long  ЛокальнаяВерсияПОСравнение = pInfo.getLongVersionCode();
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
                        activity.   startActivity(intentОбновлениеПО);
                        activity.     finishAndRemoveTask(); //// ((Activity) MainActivity_Face_App.КонтекстFaceApp).finish();
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
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт = LocalBroadcastManager.getInstance(context);
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
                            МетодПредлагаемЗаргузитьНовыюВерсиюПО(СервернаяВерсияПОРазмерФайла,context);
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
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //TODO метод состоит из двух операцию удаление любой уже скаченой версии программы и обновление новой ПО
    void МетодЗапускАнализаПО(@NonNull Boolean РежимОбновленияЯвныйИлиНет) {
        Boolean[] ПингПередСинхронизациейИлиОбновлениеПО = {false};
        try {
            handler.post(() -> {
                try {
                    boolean РежимСетиВыбраныйПользователем =
                            new Class_Find_Setting_User_Network(context).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                    if (РежимСетиВыбраныйПользователем == true) {
                        CompletionService completionService = new ExecutorCompletionService(Executors.newSingleThreadExecutor());
                        completionService.submit(() -> {
                            ПингПередСинхронизациейИлиОбновлениеПО[0] =
                                    new Class_Connections_Server(context).МетодПингаСервераРаботаетИлиНет(context);
                            Log.w(context.getClass().getName(), " ПингПередСинхронизациейИлиОбновлениеПО[0] " + ПингПередСинхронизациейИлиОбновлениеПО[0]);
                            return ПингПередСинхронизациейИлиОбновлениеПО[0];
                        });
                        ПингПередСинхронизациейИлиОбновлениеПО[0] = (Boolean) completionService.take().get();
                        Log.w(context.getClass().getName(), " ПингПередСинхронизациейИлиОбновлениеПО[0]  "+ПингПередСинхронизациейИлиОбновлениеПО[0] );
                        if (ПингПередСинхронизациейИлиОбновлениеПО[0] == true) {
                            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(context).
                                    МетодЗапускаУведомленияОбновленияПО( РежимОбновленияЯвныйИлиНет, context);
                            Log.w(context.getClass().getName(), " ПингПередСинхронизациейИлиОбновлениеПО[0]  "+ПингПередСинхронизациейИлиОбновлениеПО[0] );
                        } else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (РежимОбновленияЯвныйИлиНет==true) {
                                        Toast toast = Toast.makeText(context, "Нет связи c Cервер !!!", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.BOTTOM, 0, 40);
                                        toast.show();
                                        Log.d(context.getClass().getName(), "  НЕТ СВЯЗИ С СЕРВЕРОМ  МетодВActivityFaveApp_УстанавливаетПрограммноеОбеспечениеПОТабельныйУчёт();  ");
                                    }
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодФиналСлушательУстановщикПО() {
        try {
            Log.d(this.getClass().getName(), "  МетодЗапускПослеНажатияНАНовойФормеНАКнопкуУстановитьПослеУспешнойЗагрузкиНовогоПОТабельныйУчётПоказываемЕгоПользователю");
            // TODO: 25.03.2022 Создание Локального БродКстаера
            LocalBroadcastManager localBroadcastManagerУстановщикПО;
            BroadcastReceiver broadcastReceiverУстановкаПО;
            localBroadcastManagerУстановщикПО = LocalBroadcastManager.getInstance(context);
            broadcastReceiverУстановкаПО = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(this.getClass().getName(), " localBroadcastManagerУстановщикПО  intent " + intent);
                    Bundle bundle = intent.getExtras();
                    Log.d(this.getClass().getName(), " localBroadcastManagerУстановщикПО  bundle " + bundle);
                    Integer ЗагрузиласьНоваяВерисяПОПровремяем = bundle.getInt("СервернаяВерсияПОВнутри", 0);
                    File ЗагрузкиФайлаОбновенияПОДополнительный = (File) bundle.getSerializable("СервернаяВерсияПОCамФайлДляПередачи");
                    Long СервернаяВерсияПОРазмерФайла = bundle.getLong("СервернаяВерсияПОРазмерФайла", 0l);
                    context.getMainExecutor().execute(()->{
                        if (СервернаяВерсияПОРазмерФайла > 0) {
                            МетодФиналУстановкаПО(ЗагрузиласьНоваяВерисяПОПровремяем, ЗагрузкиФайлаОбновенияПОДополнительный,context);
                            Log.d(this.getClass().getName(), " МетодФиналСлушательУстановщикПО" );
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
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодСлушательПрелагаетЗагрузитьПО() {
        try {
            Log.d(this.getClass().getName(), "  МетодЗапускПослеНажатияНАНовойФормеНАКнопкуУстановитьПослеУспешнойЗагрузкиНовогоПОТабельныйУчётПоказываемЕгоПользователю");
            // TODO: 25.03.2022 Создание Локального БродКстаера
            LocalBroadcastManager localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт;
            BroadcastReceiver broadcastReceiverУстановкаПО;
            localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт = LocalBroadcastManager.getInstance(context);
            broadcastReceiverУстановкаПО = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  intent " + intent);
                    Bundle bundle = intent.getExtras();
                    Log.d(this.getClass().getName(), " localBroadcastManagerДляФинальнойУстановкиПОТабельныйУчёт  bundle " + bundle);
                    Integer СервернаяВерсияПОРазмерФайла = bundle.getInt("СервернаяВерсияПОВнутри", 0);
                    Log.d(this.getClass().getName(), " СервернаяВерсияПОРазмерФайла " + СервернаяВерсияПОРазмерФайла);
                    context.getMainExecutor().execute(()->{
                        if (СервернаяВерсияПОРазмерФайла > 0) {
                            // TODO: 17.02.2023  загрузка ПО
                            МетодПредлагаемЗаргузитьНовыюВерсиюПО(СервернаяВерсияПОРазмерФайла,context);
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
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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
                      activity.  startActivity(intentОбновлениеПО);
                        activity.    finishAndRemoveTask(); //// ((Activity) MainActivity_Face_App.КонтекстFaceApp).finish();
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

    void МЕтодУстанавливаемРазрешенияДляОновлениеПО() {
        try {
            //////////////////////TODO SERVICE
            String[] permissions = new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.VIBRATE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.REQUEST_INSTALL_PACKAGES,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_MEDIA_LOCATION,
                    Manifest.permission.INSTALL_PACKAGES,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.WRITE_SECURE_SETTINGS
            };
            ActivityCompat.requestPermissions(activity, permissions, 1);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
    }
}
// TODO: 16.02.2023 кЛАСС ОБНОВЛЕНИЕ ПО


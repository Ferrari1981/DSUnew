package com.dsy.dsu.Code_For_WorkManagers;

import static android.content.Context.ACTIVITY_SERVICE;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.Messenger;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.Service_For_Public;
import com.dsy.dsu.Code_For_Services.Service_For_Remote_Async;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class MyWork_Async_Синхронизация_Общая extends Worker {

    private String ИмяСлужбыСинхронизации="WorkManager Synchronizasiy_Data";
    private WorkInfo WorkManagerОБЩИЙ;
    private   Integer РезультатЗапускаФоновойСинхронизацииСтрогоВФОне=0;
    @Inject
    private Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal class_generation_sendBroadcastReceiver_and_firebase_oneSignal;
    private     Data myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы = null;
    private Service_For_Remote_Async localBinderAsync;
    private  Messenger           messengerWorkManager;
    private  String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";
    private Service_For_Public.LocalBinderОбщий localBinderОбщий;
    // TODO: 28.09.2022
    public MyWork_Async_Синхронизация_Общая(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try{
        Log.i(getApplicationContext().getClass().getName(), " public MyWork_Async_Синхронизация_Общая(@NonNull Context context, @NonNull WorkerParameters workerParams) {  Контекст "+"\n"+ this.getApplicationContext());
            // TODO: 22.12.2022
            МетодБиндингаОбщая();
            // TODO: 26.03.2023
            МетодБиндинuCлужбыPublicPo();
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(context.getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
    }

    private void МетодБиндингаОбщая() throws InterruptedException {
        try {
        Intent intentГлавнаяСинхрониазция = new Intent(getApplicationContext(), Service_For_Remote_Async.class);
        getApplicationContext().bindService(intentГлавнаяСинхрониазция, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                messengerWorkManager = new Messenger(service);
                Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                        + "onServiceConnected  ОБЩАЯ messengerActivity  " + messengerWorkManager.getBinder().pingBinder());
                IBinder binder = messengerWorkManager.getBinder();
                if (binder.isBinderAlive()) {
                    getTaskExecutor().postToMainThread(()->{
                        localBinderAsync = new Service_For_Remote_Async();
                        Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                                + " МетодБиндингасМессажером onServiceConnected  binder.isBinderAlive()  " + binder.isBinderAlive());
                    });
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                        + "onServiceConnected  ОБЩАЯ messengerActivity  ");
            }
        },Context.BIND_AUTO_CREATE);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER  MyWork_Async_Синхронизация_Одноразовая из FaceApp в  MyWork_Async_Синхронизация_Одноразовая Exception  ошибка в классе  MyWork_Async_Синхронизация_Одноразовая" + e.toString());
    }
    }

    @Override
    public void onStopped() {
        super.onStopped();
        try{
            Log.d(getApplicationContext().getClass().getName().toString(), "\n"
                    + "onStopped  onStopped");
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER MyWork_Async_Синхронизация_Общая из FaceApp в MyWork_Async_Синхронизация_Общая Exception  ошибка в классе MyWork_Async_Синхронизация_Общая"
                + e.toString());
    }
    }

    // TODO: 26.12.2021  метод регистации на СЕРВЕРА ONESIGNAL
    private void МетодРегистрацииУстройсвоНАFirebaseAndOneSignal() {
        try{
            Integer  ПубличныйIDДляФрагмента=   new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).
                    МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,ПубличныйIDДляФрагмента);
            //TODO ФУТУРЕ ЗАВЕРШАЕМ
            Log.d(this.getClass().getName(), "  МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,0); " +
                    " РезультатЗаписиНовогоIDОтСервреаOneSignal  " );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    @NonNull
    @Override
    public Executor getBackgroundExecutor() {
        Log.i(getApplicationContext().getClass().getName(),
                "public Executor getBackgroundExecutor() {");
        return  Executors.newSingleThreadExecutor();
    }
    @NonNull
    @Override
    public Result doWork() {
        try {
            // TODO: 25.03.2023  ждем биндинга с службой синхронизации
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal = new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext());
// TODO: 10.12.2022  РЕГЕСТИРУЕМСЯ НА ONESIGNAL FIREBASE
            МетодРегистрацииУстройсвоНАFirebaseAndOneSignal();

            // TODO: 11.01.2022  СВОЕЙ ТЕКУЩИЙ ID ПОЛЬЗОВАТЕЛЯ
            Integer ПубличныйIDДляОбщейСинхрониазции =
                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            // TODO: 01.01.2022
            if (ПубличныйIDДляОбщейСинхрониазции == null) {
                ПубличныйIDДляОбщейСинхрониазции = 0;
            }
            Log.d(this.getClass().getName(), "ПубличныйIDДляОбщейСинхрониазции " + ПубличныйIDДляОбщейСинхрониазции);
            ActivityManager ЗапущенныйПроуессыДляОбщейСинхрониазации = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
            // TODO: 29.09.2021  перед началом СИНХРОНИЗАЦИИ ПРОВЕРЯЕМ УСТАНОВКИ СЕТИ ПОЛЬЗОВАТЕЛЯ НА АКТИВТИ НАСТРОЙКИ
            if (ЗапущенныйПроуессыДляОбщейСинхрониазации != null) {
                // TODO: 24.11.2021
                List<ActivityManager.AppTask> КоличествоЗапущенныйПроуессы = ЗапущенныйПроуессыДляОбщейСинхрониазации.getAppTasks();
                if (КоличествоЗапущенныйПроуессы.size() > 0) {
                    Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК    ВНУТРИ метода        " +
                            " public Result doWork()  MyWork_Async_Синхронизация_Общая  КоличествоЗапущенныйПроуессы " + "\n"
                            + КоличествоЗапущенныйПроуессы.size());
                    // TODO: 01.12.2021
                    for (ActivityManager.AppTask ТекущаяАктивти : КоличествоЗапущенныйПроуессы) {
                        String АктивностьЕслиЕстьTOP = null;
                        // TODO: 20.02.2022
                        if (ТекущаяАктивти != null) {
                            // TODO: 20.02.2022
                            Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК    ВНУТРИ метода         " +
                                    "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                    + ТекущаяАктивти.getTaskInfo().numActivities);
                            // TODO: 20.02.2022
                            if (ТекущаяАктивти.getTaskInfo().numActivities > 0) {
                                // TODO: 20.02.2022
                                АктивностьЕслиЕстьTOP = ТекущаяАктивти.getTaskInfo().topActivity.getClassName().toString();
                            }
                            Log.i(getApplicationContext().getClass().getName(), "ТекущаяАктивти " + ТекущаяАктивти +
                                    " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP +
                                    "ТекущаяАктивти.getTaskInfo().numActivities  " + "\n"
                                    + ТекущаяАктивти.getTaskInfo().numActivities);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                        }
                        if (АктивностьЕслиЕстьTOP != null) {
                            switch (АктивностьЕслиЕстьTOP) {// case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App" :
                                case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Visible_Async":
                                case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Tabels_Users_And_Passwords":
                                case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_Start":
                                case "com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Face_App":
                                    break;
                                // TODO: 01.12.2021 САМ ЗАПУСК WORK MANAGER  СИНХРОНИАЗЦИИ ПРИ ВКЛЮЧЕННОЙ АКТИВТИ
                                default:
                                    Log.i(getApplicationContext().getClass().getName(), "сРАБОТАЛО ......ТекущаяАктивти " + ТекущаяАктивти +
                                            " АктивностьЕслиЕстьTOP  " + АктивностьЕслиЕстьTOP);////   case "com.dsy.dsu.Code_For_Chats_КодДля_Чата.MainActivity_List_Chats" :
                                    МетодЗапускаФоновойСинхронизацииИзОбщегоWorkManager();
                                    Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК   ЗАПУСК в АКТВИНОЙ АКТВИТИ   АктивностьЕслиЕстьTOP"
                                            + АктивностьЕслиЕстьTOP);
                                    ///////todo  КОНЕЦ  код запуска уведомлений для чата
                                    break;
                            }
                        } else {
                            // TODO: 20.02.2022  нет активтиви
                            МетодЗапускаФоновойСинхронизацииИзОбщегоWorkManager();
                            Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК   запуск синхрониазции в фоне когда вообще коиличсетво активти РАВНО 0 " + "\n"
                                    + КоличествоЗапущенныйПроуессы);
                        }
                    }
                } else {
                    МетодЗапускаФоновойСинхронизацииИзОбщегоWorkManager();
                    Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК   запуск синхрониазции в фоне когда вообще коиличсетво активти РАВНО 0 " + "\n"
                            + КоличествоЗапущенныйПроуессы);
                }
                // TODO: 31.12.2021  ЗАПУСК В ФОНЕ
            } else {
                МетодЗапускаФоновойСинхронизацииИзОбщегоWorkManager();
                Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК   ЗАПУСК В ФОНЕ    запуск синхрониазции в фоне когда вообще коиличсетво активти не изместно NULL ");
            }
                WorkManagerОБЩИЙ = WorkManager.getInstance(getApplicationContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизации).get().get(0);
            Log.i(getApplicationContext().getClass().getName(), "СИНХРОНИЗАЦИЯ ПРОШЛА ОБЩАЯ  workmanager РезультатЗапускаФоновойСинхронизацииСтрогоВФОне " + "\n"
                    + РезультатЗапускаФоновойСинхронизацииСтрогоВФОне);
            myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы = new Data.Builder()
                    .putInt("ReturnPublicAsyncWorkMananger",
                            РезультатЗапускаФоновойСинхронизацииСтрогоВФОне)
                    .putLong("WorkManangerVipolil",
                           Long.parseLong(РезультатЗапускаФоновойСинхронизацииСтрогоВФОне.toString()))
                    .build();
// TODO: 25.03.2023
            if (РезультатЗапускаФоновойСинхронизацииСтрогоВФОне>0 ) {
                // TODO: 25.03.2023
                МетодПослеСинхрониазцииУдалениеСтатусаУдаленный();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " РезультатЗапускаФоновойСинхронизацииСтрогоВФОне "+РезультатЗапускаФоновойСинхронизацииСтрогоВФОне );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " ОШИБКА В WORK MANAGER MyWork_Async_Синхронизация_Общая из FaceApp в MyWork_Async_Синхронизация_Общая Exception  ошибка в классе MyWork_Async_Синхронизация_Общая"
                    + e.toString());
        }
        if (РезультатЗапускаФоновойСинхронизацииСтрогоВФОне>0 ) {
            return Result.success(myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы);
       /*    if (WorkManagerОБЩИЙ.getRunAttemptCount()<2) {
                return Result.retry();
            }else {*/
        }else{
               return Result.failure(myDataОтветОБЩЕЙСИНХРОНИЗАЦИИСлужбы);
           }


    }

    // TODO: 16.12.2021 МЕтод ЗАпуска  Сихрониазации Чисто В форне без актвтити
    private void МетодЗапускаФоновойСинхронизацииИзОбщегоWorkManager() {
        try {
            // TODO: 16.11.2022  запускаем синхрониази из work manager ОБЩАЯ
           РезультатЗапускаФоновойСинхронизацииСтрогоВФОне = МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити();
           Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК   ЧИСТЫЙ ФОНОВЫЙ ПОТОК НЕТ АКТИВТИ ПРОГРАММА SWODOWN  ВНУТРИ метода        "+"\n"+
                   " public Result doWork()  MyWork_Async_Синхронизация_Общая  внутри WORK MANAGER "
                   + new Date() + " СТАТУС WORKMANAGER MyWork_Async_Синхронизация_Общая  внутри WORK MANAGER "
                   + WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag("WorkManager Synchronizasiy_Data").get().get(0).getProgress() +
                   " WorkManager Synchronizasiy_Data  "
                   +"\n"+
                   "  РезультатЗапускаФоновойСинхронизацииСтрогоВФОне " +РезультатЗапускаФоновойСинхронизацииСтрогоВФОне );
            if (РезультатЗапускаФоновойСинхронизацииСтрогоВФОне>0 ) {
                Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_HEAVY_CLICK));
                // TODO: 16.11.2022 запускаем ondesingle FIREBASE
                МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления();
            }
       } catch (Exception e) {
           e.printStackTrace();
           Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                   " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
           new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                   Thread.currentThread().getStackTrace()[2].getMethodName(),
                   Thread.currentThread().getStackTrace()[2].getLineNumber());
       }
    }


    // TODO: 24.11.2021  Метод КОТОРЫЕ ОПРЕДЕЛЯЕТ КАК МЫ БУДЕМ ЗАПУСКАТЬ СИНХРОНИЗАЦИЮ В ФОНЕ ИЛИ НЕТ ВЗАВИСИМОСТИ КАКАЯ АКТИВИТИ АКТИВНА
   protected Integer МетодЗапускаемприменяяДваВидаЕстьАктивнаяАктивтиИлиСтроговФоне() throws ExecutionException, InterruptedException {
       Integer РезультатЗапускаФоновойСинхронизации = 0;
        boolean ФлагЗапущенолиКакоеннибутьАктивтиИлинет = false;
        try{
            // TODO: 29.09.2021  перед началом СИНХРОНИЗАЦИИ ПРОВЕРЯЕМ УСТАНОВКИ СЕТИ ПОЛЬЗОВАТЕЛЯ НА АКТИВТИ НАСТРОЙКИ
                Log.i(getApplicationContext().getClass().getName(), "ПОСЛЕ MyWork_Async_Синхронизация_Общая  внутри WORK MANAGER  внутри WORK MANGER  "
                        + new Date() + " СТАТУС WORKMANAGER MyWork_Async_Синхронизация_Общая  внутри WORK MANAGER "
                        + WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag("WorkManager Synchronizasiy_Data").get().get(0).getState());
                // TODO: 24.11.2021  ЗАПУСК СИНХРОНИАЗХЦИИ СТРОГОВ ФОНЕ БЕЗ АКТИВТИ
               РезультатЗапускаФоновойСинхронизации=  МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити();

            Log.i(getApplicationContext().getClass().getName(), "ПОСЛЕ MyWork_Async_Синхронизация_Общая  внутри WORK MANAGER  внутри WORK MANGER  "
                    + new Date() + " СТАТУС WORKMANAGER MyWork_Async_Синхронизация_Общая  внутри WORK MANAGER " + WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag("WorkManager Synchronizasiy_Data").get().get(0).getState()+
                    "   РЕЗУЛЬТАТ MyWork_Async_Синхронизация_Общая  внутри WORK MANAGER   РезультатЗапускаФоновойСинхронизации    " +РезультатЗапускаФоновойСинхронизации );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

    }
return  РезультатЗапускаФоновойСинхронизации;
}



    // TODO: 24.11.2021  Метод КОТОРЫЕ   ЗАПСУКАЮ СИНХРОНИАЗЦИЮ БЕЗ АКТИВТИ СТРГО В ФОНЕ
    protected  Integer МетодЗапускаСинхрониазцииСтрогоВФонеБезАктивити() throws InterruptedException {
     Integer РезультатЗапускаФоновойСинхронизации = 0;
        try{
        // TODO: 22.12.2022  сама запуска синхронищации из workmanager ОБЩЕГО
        boolean ВыбранныйРежимСети =
                new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
        Log.d(this.getClass().getName(), "  ВыбранныйРежимСети ВыбранныйРежимСети "
                + ВыбранныйРежимСети);

        if (ВыбранныйРежимСети == true) {
            // TODO: 21.11.2021  НАЧАЛО СИХРОНИЗХАЦИИИ общая
            РезультатЗапускаФоновойСинхронизации = localBinderAsync.МетодAsyncИзСлужбы(getApplicationContext());
            Log.d(getApplicationContext().getClass().getName().toString(),
                    "\n" + "      MyWork_Async_Синхронизация_Общая       РезультатЗапускаФоновойСинхронизации[0]   " + РезультатЗапускаФоновойСинхронизации);
            Log.d(this.getClass().getName(), "  serviceForTabelAsync " + localBinderAsync);
        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return РезультатЗапускаФоновойСинхронизации;
    }

    // TODO: 26.06.2022  запуска ONESIGNAL из общей синхрониазции



    private void МетодЗапускаПослеУспешнойСтинхронизацииOneSignalИУведомления() {
        try{
         Integer   ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            Log.i(getApplicationContext().getClass().getName(), "ЗАПУСК   зПубличныйIDДляФрагмента "+"\n"
                    + ПубличныйIDДляФрагмента);
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаУведомленияЧАТА();
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ ОДНОРАЗОВАЯ СЛУЖБА
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаУведомленияДляЗАДАЧ();
            // TODO: 14.11.2021  ПОВТОРНО ЗАПУСКАЕМ УВЕДОМЛЕНИЯ ТОЛЬКО ДЛЯ  СЛУЖБА ТОЛЬКО ПРИ СМНИ СТТАУСА ОТКАЗИ ИЛ ВЫПОЛНИЛ
            class_generation_sendBroadcastReceiver_and_firebase_oneSignal.МетодЗапускаУведомленияДляЗАДАЧТолькоПриСменеСтатусаОтказВыполнил();
                Log.d(this.getClass().getName(), "РезультатCallsBackСинхрониазцииЧата " + "\n" + " МОДЕЛЬ ТЕЛЕФОНА  Build.DEVICE   " + Build.DEVICE +
                        "  ПубличныйIDДляФрагмента " + ПубличныйIDДляФрагмента);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    getApplicationContext().getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }




    }
    public void МетодБиндинuCлужбыPublicPo() {
        try {
            Intent intentЗапускPublicPO = new Intent(getApplicationContext(), Service_For_Public.class);
            intentЗапускPublicPO.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
            getApplicationContext().bindService(intentЗапускPublicPO, Context.BIND_AUTO_CREATE, Executors.newSingleThreadExecutor(), new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        localBinderОбщий = (Service_For_Public.LocalBinderОбщий) service;
                        if (service.isBinderAlive()) {
                            // TODO: 16.11.2022
                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  service_дляЗапускаодноразовойСинхронизации binderAsyns.pingBinder() " + service.pingBinder());
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
                        localBinderОбщий = null;
                        Log.d(getApplicationContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + "    onServiceDisconnected  localBinderОбщий" + localBinderОбщий);
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
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
/////
private void МетодПослеСинхрониазцииУдалениеСтатусаУдаленный() {
    try {
        Intent intentПослеСинхроницииРегламентаняРаботаУдалениеДанныхИзWorkManager=new Intent();
        intentПослеСинхроницииРегламентаняРаботаУдалениеДанныхИзWorkManager.setClass(getApplicationContext(), Service_For_Public.class);
        intentПослеСинхроницииРегламентаняРаботаУдалениеДанныхИзWorkManager.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
        // TODO: 25.03.2023 дополнительное удаление после синхрониазции статус Удаленныц
        localBinderОбщий.getService().МетодГлавныйPublicPO(getApplicationContext(),intentПослеСинхроницииРегламентаняРаботаУдалениеДанныхИзWorkManager);
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
}

}






























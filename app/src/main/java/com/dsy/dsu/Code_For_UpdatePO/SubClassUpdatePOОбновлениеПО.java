package com.dsy.dsu.Code_For_UpdatePO;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.BuildConfig;

import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Update_Download_File_APK_From_SERVER;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.Date;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class SubClassUpdatePOОбновлениеПО {

    Context context;
    // TODO: 23.02.2022
    Activity activity;
    Handler handler;

    public SubClassUpdatePOОбновлениеПО(@NonNull Context context,
                                        @NonNull Activity activity,
                                        @NonNull Handler handler) {
        this.context = context;
        this.activity = activity;
        this.handler = handler;
    }


    private void МетодПредлагаемЗаргузитьНовыюВерсиюПО(@NonNull Integer СервернаяВерсияПОВнутри, @NonNull Context context) {
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
            // TODO: 02.04.2022
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
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
                    Log.d(this.getClass().getName(), "Установка Обновления .APK СЛУЖБА");
                    alertDialog.dismiss();
                    alertDialog.cancel();
                    String ФинальныйПутьДляЗагрузкиФайлаОбновения = null;
                    ////
                    Log.d(this.getClass().getName(), " СервернаяВерсияПОВнутри" + finalСервернаяВерсияПОВнутри);
                    Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    //TODO ПЕРЕД СОЗДАНИЕМ НОВОГО СООБЕЩНИЯ ОБНУЛЯЕМ ПРДЫДУЩЕЕ
                    Class_Update_Download_File_APK_From_SERVER class_update_download_file_apk_from_server = new Class_Update_Download_File_APK_From_SERVER(context, null);
                    class_update_download_file_apk_from_server.МетодУдалениеИнформационогоТекстовогоФайлаJSONДляПО();
                    Log.i(context.getClass().getName(), " ЗАПУСКАЕМ МетодУдалениеИнформационогоТекстовогоФайлаJSONДляПО();  ");
                    class_update_download_file_apk_from_server.МетодУдалениеСамогоФайлаПрограммыПОТальныйУчётПО_APK();
                    Log.i(context.getClass().getName(), " ЗАПУСКАЕМ МетодУдалениеСамогоФайлаПрограммыПОТальныйУчётПО_APK();  ");
                    try {
                        // TODO: 16.02.2023  НАчинаем Саму загрузки
                        class_update_download_file_apk_from_server.МетодНачалаЗапускаОбновленияПО(finalСервернаяВерсияПОВнутри, context);
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
                        МетодПредлагаемЗаргузитьНовыюВерсиюПО(СервернаяВерсияПОРазмерФайла, context);
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
    public void МетодЗапускАнализаПО(@NonNull Boolean РежимОбновленияЯвныйИлиНет) {
        Boolean[] ПингПередСинхронизациейИлиОбновлениеПО = {false};
        try {
            handler.postDelayed(() -> {
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
                        Log.w(context.getClass().getName(), " ПингПередСинхронизациейИлиОбновлениеПО[0]  " + ПингПередСинхронизациейИлиОбновлениеПО[0]);
                        if (ПингПередСинхронизациейИлиОбновлениеПО[0] == true) {
                            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(context).
                                    МетодЗапускаУведомленияОбновленияПО(РежимОбновленияЯвныйИлиНет, context);
                            Log.w(context.getClass().getName(), " ПингПередСинхронизациейИлиОбновлениеПО[0]  " + ПингПередСинхронизациейИлиОбновлениеПО[0]);
                        } else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (РежимОбновленияЯвныйИлиНет == true) {
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
            },1000);
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void МетодФиналСлушательУстановщикПО() {
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
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    public void МетодСлушательПрелагаетЗагрузитьПО() {
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
                    context.getMainExecutor().execute(() -> {
                        if (СервернаяВерсияПОРазмерФайла > 0) {
                            // TODO: 17.02.2023  загрузка ПО
                            МетодПредлагаемЗаргузитьНовыюВерсиюПО(СервернаяВерсияПОРазмерФайла, context);
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

    public void МЕтодУстанавливаемРазрешенияДляОновлениеПО() {
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

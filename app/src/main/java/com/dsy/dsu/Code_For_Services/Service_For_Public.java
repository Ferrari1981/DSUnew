package com.dsy.dsu.Code_For_Services;
import android.app.IntentService;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dsy.dsu.Business_logic_Only_Class.CREATE_DATABASE;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_UUID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Weekend_For_Tabels;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.DATE.Class_Generation_Data;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassMONTHONLY;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassMONTHONLY_ТолькоАнализ;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassYEARONLY;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassYearHONLY_ТолькоАнализ;
import com.dsy.dsu.Business_logic_Only_Class.SubClassUpVersionDATA;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Service_For_Public extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    private String ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре;
    public LocalBinderОбщий binder = new LocalBinderОбщий();
    protected         SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца;
    private Context context;
    public Service_For_Public() {
        super("Service_For_Public");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца=new SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        return   binder;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=newBase;
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!" +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!! " +"\n"+
                " УДАЛЕНИЕ СТАТУСА Удаленная !!!!!   Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context=getApplicationContext();
        МетодЗапускаОбщиеКоды(getApplicationContext(),intent);
// TODO: 30.06.2022 сама не постредствено запуск метода
    }

    public class LocalBinderОбщий extends Binder {
        public Service_For_Public getService() {
            // Return this instance of LocalService so clients can call public methods
            return Service_For_Public.this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

            data = Parcel.obtain();
            reply = Parcel.obtain();
            data.writeString("fffff");
            return    reply.readBoolean();
        }
    }

    public Integer МетодЗапускаОбщиеКоды(@NonNull Context context, @NonNull Intent intent) {
        Integer РезультатСинхрониазции=0;
        try{
            if( this.context==null){
                this.context=context;
            }
            switch (intent.getAction()) {
                case "ЗапускЗаполенеияИзПрошлыхМесяцев":
                    // TODO: 28.09.2022 Запуск Само Заполенеия Данных из Прошлого Месяца
                    sibClassApplyFromBackPeriodof_заполененияТабеляИзПрошлогоМесяца.    МетодЗапускЗаполенеияИзПрошлыхМесяцев(context, intent);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                // TODO: 25.09.2022 удаление статуса удаленных строк
                case "ЗапускУдалениеСтатусаУдаленияСтрок":
                    new SubClassFromDeleteRemoteRows_УдалениеСтатусУдаленныхСтрок(context).МетодУдаленияСтатусаУдаленных(intent);
                    Log.w(this.getClass().getName(), "   intent.getAction()  " + intent.getAction());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return РезультатСинхрониазции;
    }
// TODO: 15.02.2023
public Cursor МетодПолучениеДанныхЧерезCursorLoader(@NonNull Context context, @NonNull Intent intent) {
    Cursor cursor=null;
    try{
        if( this.context==null){
            this.context=context;
        }
        Log.w(this.getClass().getName(), "   МетодПолучениеДанныхЧерезCursorLoader  " + context);
        if (intent.getAction().contentEquals("ДляУдаление")) {
            cursor=      (Cursor)     new SubClassCursorLoader(). CursorLoaders(context, intent.getExtras());
        }
        Log.w(this.getClass().getName(), "   cursor  " + cursor);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    return cursor;
}

    // TODO: 28.09.2022  класс заполнения из прошлых месяцев

    class SibClassApplyFromBackPeriodof_ЗаполененияТабеляИзПрошлогоМесяца {
        Integer ГодСейчас = 0;
        Integer МесяцСейчас=0;
        private void МетодЗапускЗаполенеияИзПрошлыхМесяцев(@NonNull Context context, @NonNull Intent intent) {
            try {

                SubClassCursorLoader cursorLoader=     new SubClassCursorLoader();
                Log.w(this.getClass().getName(), "   context  " + context);
                SQLiteDatabase sqLiteDatabaseДляЗаполенеяИзПрошлогоМесяца = new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу();
                Integer ПубличныйIDДляЗаполененияИзПрошлогоМесяца = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
                Bundle bundleПолучаемДанных = intent.getExtras();
                Long UUIDПОискаПредыдущегоМЕсяцаТАбеля = bundleПолучаемДанных.getLong("UUIDРОДИТЕЛЬСКАЯУжеСозданогоТАбеля", 0l);
                Integer СФОУжеСозданогоТАбеля = bundleПолучаемДанных.getInt("СФО", 0);
                ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре = bundleПолучаемДанных.getString("ДепартаментТабеляПослеПодбораBACK", "");
                //  Long ГенерироватьUUIDРОДИТЕЛЬСКАЯ = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
                // TODO: 21.09.2022
                String ПолученяМесяцПростоАнализа = new SubClassMONTHONLY_ТолькоАнализ(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                 МесяцСейчас = Optional.ofNullable(ПолученяМесяцПростоАнализа).map(Integer::new).orElse(0);
                Log.d(this.getClass().getName(), "ПолученяМесяцПростоАнализа " + ПолученяМесяцПростоАнализа);
                switch (МесяцСейчас) {
                    case 12:
                        String   ПолученаяДатаТолькоГод12 = new SubClassYEARONLY(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                        ГодСейчас = Optional.ofNullable(ПолученаяДатаТолькоГод12).map(Integer::new).orElse(0);
                        break;
                    default:
                        String    ПолученаяДатаТолькоГод = new SubClassYearHONLY_ТолькоАнализ(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                        ГодСейчас = Optional.ofNullable(ПолученаяДатаТолькоГод).map(Integer::new).orElse(0);
                        break;
                }
                // TODO: 22.09.2022
                Log.d(this.getClass().getName(), "ГодСейчас " + ГодСейчас + " МесяцСейчас " + МесяцСейчас);
                //TODO ВЫЧИСЛЯЕМ ДАННЫЕ КОТОРЫЕ НА ВСТАВИТЬ
                final Integer[] РезультатВставкиИзПрошлогоМесяца = {0};
                // TODO: 15.02.2023 ГОД бежим по ЦИКЛЦУ
                GregorianCalendar gregorianCalendarИзПрошлыхМесяцев = new GregorianCalendar();
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.YEAR, ГодСейчас);
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.MONTH,МесяцСейчас);
                gregorianCalendarИзПрошлыхМесяцев.set(GregorianCalendar.DATE, 1);
                Log.d(this.getClass().getName(), " gregorianCalendarИзПрошлыхМесяцев" + gregorianCalendarИзПрошлыхМесяцев.getTime().toLocaleString());


                // TODO: 21.03.2023  Заполение из прошлого месяца
                Flowable.range(1,100)
                        .onBackpressureBuffer(true)

                        .takeWhile(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Throwable {
                                if (РезультатВставкиИзПрошлогоМесяца[0] >0) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " РезультатВставкиИзПрошлогоМесяца  " + РезультатВставкиИзПрошлогоМесяца[0]);
                                    return false;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " РезультатВставкиИзПрошлогоМесяца  " + РезультатВставкиИзПрошлогоМесяца[0]);
                                    return true;
                                }
                            }
                        })
                        .map(new Function<Integer, Object>() {
                            @Override
                            public Object apply(Integer integer) throws Throwable {
                                Log.d(this.getClass().getName(), " ДО  gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR)" +  gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR)
                                        + "   gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH) "+    gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH));
                                // TODO: 21.03.2023  уменяем месяц
                                gregorianCalendarИзПрошлыхМесяцев.add(Calendar.MONTH, -1);
                                return integer;
                            }
                        })
                        .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Throwable {
                                Integer ФиналМесяцВставки=       gregorianCalendarИзПрошлыхМесяцев.get(Calendar.MONTH);
                                Integer ФиналГодВставки=       gregorianCalendarИзПрошлыхМесяцев.get(Calendar.YEAR);
                                if(ФиналМесяцВставки==0){
                                    ФиналМесяцВставки=12;
                                    ФиналГодВставки--;
                                }
                                Log.d(this.getClass().getName(), " ПОСЛЕ ФиналГодВставки" +  ФиналГодВставки + "  ФиналМесяцВставки "+  ФиналМесяцВставки);
                                Bundle bundle=new Bundle();
                                bundle.putString("СамЗапрос","  SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?");
                                bundle.putStringArray("УсловияВыборки" ,new String[]{String.valueOf(ФиналГодВставки),
                                        String.valueOf( ФиналМесяцВставки),String.valueOf(СФОУжеСозданогоТАбеля),"Удаленная"});
                                bundle.putString("Таблица","viewtabel");
                                Cursor     Курсор_ВытаскиваемПоследнийМесяцТабеля=      (Cursor)    cursorLoader.  CursorLoaders(context, bundle);
                                Log.d(this.getClass().getName(), " Курсор_ВытаскиваемПоследнийМесяцТабеля" + Курсор_ВытаскиваемПоследнийМесяцТабеля);
                                if (Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount() > 0 ) {
                                    Log.d(this.getClass().getName(), "Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount() " + Курсор_ВытаскиваемПоследнийМесяцТабеля.getCount());
                                    Class_GRUD_SQL_Operations       class_grud_sql_operationЗаполнениеИзПрошлогоМесяца = new Class_GRUD_SQL_Operations(getApplicationContext());
                                    String ДатаОперации = new SubClassMONTHONLY(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                                    // TODO: 16.02.2023 сама вставка
                                    РезультатВставкиИзПрошлогоМесяца[0] =      МетодЗаопленияДаннымиИзПрошлогоМесяца(context,
                                            UUIDПОискаПредыдущегоМЕсяцаТАбеля, ГодСейчас,
                                            МесяцСейчас
                                            , Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                            class_grud_sql_operationЗаполнениеИзПрошлогоМесяца);
                                    Log.d(this.getClass().getName(), "  РезультатВставкиИзПрошлогоМесяца[0] " +  РезультатВставкиИзПрошлогоМесяца[0] );
                                }
                                Log.d(this.getClass().getName(), "  РезультатВставкиИзПрошлогоМесяца[0] " +  РезультатВставкиИзПрошлогоМесяца[0] );
                                Курсор_ВытаскиваемПоследнийМесяцТабеля.close();
                            }
                        })
                        .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                                // TODO: 25.11.2022 выход
                                МетодОтображениеОперацииИзПрошлогоМЕсяца(context, "ВыходИзВставкиИзПрошлогоМесяца", РезультатВставкиИзПрошлогоМесяца[0]);
                            }
                        })

                        .subscribe();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
        }

        private Integer МетодЗаопленияДаннымиИзПрошлогоМесяца(@NonNull Context context,
                                                           Long UUIDПОискаПредыдущегоМЕсяцаТАбеля,
                                                           Integer ГодНазадДляЗаполнени,
                                                           Integer МесяцИзПрошлогоМесяца,
                                                           Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля,
                                                           Class_GRUD_SQL_Operations class_grud_sql_operationЗаполнениеИзПрошлогоМесяца) {
            ArrayList<Integer> integerArrayListВствавкаИзПрошлогоМесяц = null;
            try {
              integerArrayListВствавкаИзПрошлогоМесяц=new ArrayList<>();
                ReentrantLock reentrantLock = new ReentrantLock();
                Condition condition = reentrantLock.newCondition();
                Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToFirst();
                do {
                    try {
                        reentrantLock.lock();
                        // TODO: 23.09.2022 сама вставка в таблиц Дата ТАбель #2
                        Integer РезультатВставкиВНИжнуюТаюблицу = МетодВставкивТаблицуДата_Табель(context,
                                class_grud_sql_operationЗаполнениеИзПрошлогоМесяца,
                                Курсор_ВытаскиваемПоследнийМесяцТабеля, UUIDПОискаПредыдущегоМЕсяцаТАбеля, ГодНазадДляЗаполнени, МесяцИзПрошлогоМесяца);
                        Log.d(this.getClass().getName(), " Вторая Таблиуа Из Прошлого МЕссяца РезультатВставкиВНИжнуюТаюблицу"
                                + РезультатВставкиВНИжнуюТаюблицу);
                        if (РезультатВставкиВНИжнуюТаюблицу > 0) {
                            integerArrayListВствавкаИзПрошлогоМесяц.add(РезультатВставкиВНИжнуюТаюблицу);

                        }
                        // TODO: 21.09.2022 отображае
                        condition.await(300, TimeUnit.MILLISECONDS);
                        condition.signal();
                        // TODO: 25.11.2022 выход
                        МетодОтображениеОперацииИзПрошлогоМЕсяца(context, "РезультатДобавенияСотрудникаИзПрошлогоМесяца",integerArrayListВствавкаИзПрошлогоМесяц.size());
                        Log.d(this.getClass().getName(), "insertData   ");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                    } finally {
                        reentrantLock.unlock();
                    }
                } while (Курсор_ВытаскиваемПоследнийМесяцТабеля.moveToNext());//TODO конец цикла заполения даными
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return  integerArrayListВствавкаИзПрошлогоМесяц.size();
        }


        private Integer МетодВставкивТаблицуДата_Табель(@NonNull Context context,
                                                        @NonNull Class_GRUD_SQL_Operations class_grud_sql_operationЗаполнениеИзПрошлогоМесяца,
                                                        @NonNull Cursor Курсор_ВытаскиваемПоследнийМесяцТабеля
                ,@NonNull Long ParentUUID,
                                                        @NonNull Integer ПолученаяДатаТолькоГод,
                                                        @NonNull Integer  МесяцИзПрошлогоМесяца) {
            String ответОперцииВставки = null;
            try {
                String НазваниеОбрабоатываемойТаблицы = "data_tabels";
                ContentValues contentValuesДляДатаТабель = new ContentValues();
                int ИндексСтолбикаДляЗаполненияФИО = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("fio");
                contentValuesДляДатаТабель.put("fio", Курсор_ВытаскиваемПоследнийМесяцТабеля.getLong(ИндексСтолбикаДляЗаполненияФИО));
                int ИндексFIOuser_update = Курсор_ВытаскиваемПоследнийМесяцТабеля.getColumnIndex("user_update");
                contentValuesДляДатаТабель.put("user_update", Курсор_ВытаскиваемПоследнийМесяцТабеля.getInt(ИндексFIOuser_update));
                String СгенерированованныйДатаДляДаннойОперации = new Class_Generation_Data(getApplicationContext()).ГлавнаяДатаИВремяОперацийСБазойДанных();
                contentValuesДляДатаТабель.put("date_update", СгенерированованныйДатаДляДаннойОперации);
                // TODO: 23.09.2022 сама вставка в таблиц ТАБЕЛЬ  #1
                Long ДляНовойЗаписиUUID = (Long) new Class_Generation_UUID(getApplicationContext()).МетодГенерацииUUID(getApplicationContext());
                contentValuesДляДатаТабель.put("uuid", ДляНовойЗаписиUUID);;
                contentValuesДляДатаТабель.put("uuid_tabel", ParentUUID);
                contentValuesДляДатаТабель.put("status_send", " ");
                contentValuesДляДатаТабель.put("status_carried_out", "False");
                // TODO: 22.09.2022 дополнительные параменты ДатаТабель
                // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
                Long РезультатУвеличиваемВерсияДатаТАбель =
                        new SubClassUpVersionDATA().МетодПовышаемВерсииCurrentTable(    НазваниеОбрабоатываемойТаблицы,getApplicationContext(),new CREATE_DATABASE(getApplicationContext()).getССылкаНаСозданнуюБазу());
                Log.d(this.getClass().getName(), " РезультатУвеличиваемВерсияДатаТАбель  " + РезультатУвеличиваемВерсияДатаТАбель);
                // TODO: 18.11.2022
                contentValuesДляДатаТабель.put("current_table", РезультатУвеличиваемВерсияДатаТАбель);
                // TODO: 22.09.2022
                // TODO: 30.08.2021  ОРМИРУЕМ КОРКАТ БУДЩЕЙ ВСТАВКИ ДАННЫХ
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + НазваниеОбрабоатываемойТаблицы + "");
                //  Uri uri = Uri.parse("content://MyContentProviderDatabase/" +НазваниеОбрабоатываемойТаблицы + "");
                ContentResolver resolver = context.getContentResolver();
                // TODO: 22.09.2022 Само выполенение
                Uri insertData = resolver.insert(uri, contentValuesДляДатаТабель);
                if (insertData!=null) {
                    ответОперцииВставки = Optional.ofNullable(insertData).map(Emmeter -> Emmeter.toString().replace("content://", "")).get();


                    Integer РезультатВставкаВыходныхДНей=
                            new Class_Generation_Weekend_For_Tabels(getApplicationContext())
                                    .МетодТретийАвтоматическаяВставкаВыходныхДней(ДляНовойЗаписиUUID,ПолученаяДатаТолькоГод,МесяцИзПрошлогоМесяца );
                    Log.d(this.getClass().getName(), "   РезультатВставкаВыходныхДНей  "+  РезультатВставкаВыходныхДНей);



                }else {
                    ответОперцииВставки="0";
                }
                Log.d(this.getClass().getName(), "insertData   " + insertData + "  ответОперцииВставки " + ответОперцииВставки);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
            return Integer.parseInt(ответОперцииВставки);
        }




        protected void МетодОтображениеОперацииИзПрошлогоМЕсяца(@NonNull Context context, String Статус, Integer Значение) {
            try {
                Intent intentЗапускаемИзСлужбыДляЛистТАбеля = new Intent();
                intentЗапускаемИзСлужбыДляЛистТАбеля.setAction("LocalBroadcastВставкаИзПрошлогоМесяцаТабель");
                Bundle bundleЗапускемBackДанные = new Bundle();
                bundleЗапускемBackДанные.putInt("Значения", Значение);
                bundleЗапускемBackДанные.putString("Статус", Статус);///"В процесс"
                bundleЗапускемBackДанные.putString("ДепартаментТабеляПослеПодбораBACK", ПолученныйПоследнийМесяцДляСортировкиЕгоВСпиноре);///"В процесс"
                intentЗапускаемИзСлужбыДляЛистТАбеля.putExtras(bundleЗапускемBackДанные);
                Log.w(this.getClass().getName(), "   bundleЗапускемBackДанные  " + bundleЗапускемBackДанные);
                LocalBroadcastManager localBroadcastManagerИзСлужбыServiceForAllTabel = LocalBroadcastManager.getInstance(context);
                localBroadcastManagerИзСлужбыServiceForAllTabel.sendBroadcast(intentЗапускаемИзСлужбыДляЛистТАбеля);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
        }


    }

    // TODO: 25.09.2022 класс удаление статуса удаленных записей
    class  SubClassFromDeleteRemoteRows_УдалениеСтатусУдаленныхСтрок extends  AsyncQueryHandler{
        Uri uri;
        public SubClassFromDeleteRemoteRows_УдалениеСтатусУдаленныхСтрок(@NonNull  Context context) {
            super(context.getContentResolver());
        }
        public SubClassFromDeleteRemoteRows_УдалениеСтатусУдаленныхСтрок(ContentResolver cr) {
            super(cr);
        }

        // TODO: 25.09.2022 запуск метода
        public void МетодУдаленияСтатусаУдаленных(@NonNull Intent intent){
            try{
                Integer Toren=new Random().nextInt();
                Stream<String> streamУдалениеСтатусаУдаленный=Stream.of("data_tabels","tabel","get_materials_data");
                streamУдалениеСтатусаУдаленный.forEachOrdered(new Consumer<String>() {
                    @Override
                    public void accept(String Таблица) {
                        uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                        ContentResolver resolver = context.getContentResolver();
                      Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"status_send=?",new String[]{"Удаленная"});
                        Log.i(this.getClass().getName(),  "УдалениеДанныхСоСтатусомУдаленная  "+УдалениеДанныхСоСтатусомУдаленная
                                + Thread.currentThread().getStackTrace()[2].getMethodName()+ " время " +new Date().toLocaleString() );
                    }


                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
            }
        }
    }
class SubClassCursorLoader {
    // TODO: 25.11.2022 новы метод получение данных для всех
    public Cursor CursorLoaders(@NonNull Context context, @NonNull Bundle bundle) {
        Cursor cursor=null;
        CursorLoader cursorLoader=null;
        try{
            cursorLoader=new CursorLoader(context);
            String[] УсловияВыборки=      bundle.getStringArray("УсловияВыборки");
            String  СамЗапрос=      bundle.getString("СамЗапрос");
            String  Таблица=      bundle.getString("Таблица");
            Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecursorloader/" + Таблица + "");
            cursorLoader.setUri(uri);
            cursorLoader.setSelection(СамЗапрос);
            cursorLoader.setSelectionArgs(УсловияВыборки);//МесяцПростоАнализа
            cursor=    cursorLoader.loadInBackground();
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Log.d(this.getClass().getName(), "cursor.getCount() "
                        + cursor.getCount());
                cursorLoader.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }finally {
            cursorLoader.commitContentChanged();
        }
        return  cursor;
    }
}
}
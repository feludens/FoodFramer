package com.spadatech.mobile.android.foodframer.helpers;

import android.content.Context;

import com.spadatech.mobile.android.foodframer.models.Grocery;
import com.spadatech.mobile.android.foodframer.models.Meal;
import com.spadatech.mobile.android.foodframer.models.Prep;
import com.spadatech.mobile.android.foodframer.models.Weekday;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Felipe S. Pereira on 4/29/16.
 */
public class WeekdayHelper {

    private static WeekdayHelper instance;
    private Weekday mWeekday;
    private static List<String> mWeekdayNameList = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    public synchronized static WeekdayHelper get() {
        if (instance == null) {
            instance = new WeekdayHelper();
        }
        return instance;
    }

    public static RealmList<Weekday> newWeekdayList(Realm realm, Context context) {
        RealmList<Weekday> weekdayList = new RealmList<>();

        while (weekdayList.size() < 7){
            weekdayList.add(createNewWeekday(realm, weekdayList.size(), context));
        }

        return weekdayList;
    }

    private static Weekday createNewWeekday(Realm realm, int size, Context context) {
        int resourceId = context.getResources().getIdentifier(mWeekdayNameList.get(size).toLowerCase(), "drawable", context.getPackageName());
        Weekday newWeekday = realm.createObject(Weekday.class);
        newWeekday.setWeekdayName(mWeekdayNameList.get(size));
        newWeekday.setImage(resourceId);
        newWeekday.setGroceryList(new RealmList<Grocery>());
        newWeekday.setMealList(new RealmList<Meal>());
        newWeekday.setPrepList(new RealmList<Prep>());

        return newWeekday;
    }

    public Weekday getWeekday() {
        return mWeekday;
    }

    public void setWeekday(Weekday weekday) {
        this.mWeekday = weekday;
    }
}

package com.libify.epick.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.libify.epick.models.Pick;
import com.libify.epick.models.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ofeka_000 on 11/13/2015.
 */
public class PicksStorage {

    public static final String IS_INIT = "isInit";
    private static PicksStorage instance;
    private SharedPreferences sp;

    private static final String KEY_PICKS = "picks";

    public static PicksStorage getInstance(Context context) {
        if (instance == null)
            instance = new PicksStorage(context);

        return instance;
    }

    private PicksStorage(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Collection<Pick> getAllPicks() {
        String jsonPicks = read(KEY_PICKS);
        return new Gson().fromJson(jsonPicks, new TypeToken<List<Pick>>() {
        }.getType());
    }

    public void addPicks(Collection<Pick> picks) {
        String jsonPicks = new Gson().toJson(picks);
        write(KEY_PICKS, jsonPicks);
    }

    public void addPick(Pick pick) {

        Collection<Pick> picks = getAllPicks();
        picks.add(pick);

        write(KEY_PICKS, new Gson().toJson(picks));
    }

    public void updatePick(String oldId, Pick newPick){
        Collection<Pick> picks = getAllPicks();
        Pick pickToRemove = null;
        for (Pick p : picks) {
            if (p.pickId.equals(oldId)){
                pickToRemove = p;
            }
        }
        if (pickToRemove != null){
            picks.remove(pickToRemove);
            picks.add(newPick);
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        initialize();
        for (Pick item : picks) {
            addPick(item);
        }
    }

    private void write(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void initialize(){
        SharedPreferences.Editor editor = sp.edit();

        boolean isInitilized = sp.getBoolean(IS_INIT, false);

        if(isInitilized){
            return;
        }

        addPicks(new ArrayList<Pick>());

        editor.putBoolean(IS_INIT,true);
        editor.apply();
    }

    private String read(String key) {
        return sp.getString(key, null);
    }


}

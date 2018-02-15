package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject nameJsonObject = jsonObject.getJSONObject("name");

            ArrayList<String> alsoKnownAsList = new ArrayList<>();
            ArrayList<String> ingredientsList = new ArrayList<>();

            JSONArray alsoKnownAs;
            JSONArray ingredients;

            if (nameJsonObject.has("alsoKnownAs")){
                alsoKnownAs = nameJsonObject.getJSONArray("alsoKnownAs");
                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    alsoKnownAsList.add(alsoKnownAs.getString(i));
                }
            }

            if (jsonObject.has("ingredients")) {
                ingredients = jsonObject.getJSONArray("ingredients");
                for (int i = 0; i < ingredients.length(); i++) {
                    ingredientsList.add(ingredients.getString(i));
                }
            }

            Sandwich sandwich = new Sandwich(
                    nameJsonObject.getString("mainName"),
                    alsoKnownAsList,
                    jsonObject.getString("placeOfOrigin"),
                    jsonObject.getString("description"),
                    jsonObject.getString("image"),
                    ingredientsList
            );
            return sandwich;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.mcbridebrandon.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link BakingWidgetConfigureActivity BakingWidgetConfigureActivity}
 */
public class BakingWidget extends AppWidgetProvider {
    private final String APP_TITLE = "BakingApp";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,String ingredients, String recipeName) {


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.setTextViewText(R.id.appwidget_recipe_name, recipeName);
        views.setTextViewText(R.id.appwidget_ingredient_text, ingredients);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    private String getIngredients(Context context){
        SharedPreferences pref = context.getSharedPreferences(APP_TITLE, 0);
        String WIDGET_INGREDIENTS = "ingredients";
        return pref.getString(WIDGET_INGREDIENTS, null);
    }

    private String getRecipeName(Context context){
        SharedPreferences pref = context.getSharedPreferences(APP_TITLE, 0);
        String WIDGET_RECIPE_NAME = "recipeName";
        return pref.getString(WIDGET_RECIPE_NAME, null);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId,getIngredients(context), getRecipeName(context));
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


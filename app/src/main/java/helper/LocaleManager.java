package helper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LocaleManager {
    public static void setLocale(Context context, String language, String country){
        Locale newLocale = new Locale(language, country);
        Locale.setDefault(newLocale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(newLocale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}

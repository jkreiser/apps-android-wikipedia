package org.wikipedia;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnitRunner;

import org.wikipedia.dataclient.okhttp.TestStubInterceptor;
import org.wikipedia.espresso.MockInstrumentationInterceptor;
import org.wikipedia.settings.Prefs;
import org.wikipedia.settings.PrefsIoUtil;

public class WikipediaTestRunner extends AndroidJUnitRunner {
    @Override
    public void onStart() {
        TestStubInterceptor.setCallback(new MockInstrumentationInterceptor(InstrumentationRegistry.getContext()));
        clearAppInfo();
        disableOnboarding();

        super.onStart();
    }

    private void disableOnboarding() {
        // main onboarding screen
        Prefs.setInitialOnboardingEnabled(false);

        // onboarding feed cards
        PrefsIoUtil.setBoolean(R.string.preference_key_feed_readinglists_sync_onboarding_card_enabled, false);
        PrefsIoUtil.setBoolean(R.string.preference_key_toc_tutorial_enabled, false);
        PrefsIoUtil.setBoolean(R.string.preference_key_feed_customize_onboarding_card_enabled, false);
        PrefsIoUtil.setBoolean(R.string.preference_key_offline_onboarding_card_enabled, false);
        PrefsIoUtil.setBoolean(R.string.preference_key_select_text_tutorial_enabled, false);
    }

    private void clearAppInfo() {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(WikipediaApp.getInstance());
        prefs.edit().clear().commit();
        WikipediaApp.getInstance().deleteDatabase("wikipedia.db");
    }
}


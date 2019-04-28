package com.example.dictionary.activities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.*
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.dictionary.R
import com.example.dictionary.views.activities.DictionaryActivity
import com.example.dictionary.views.activities.MenuActivity
import com.example.dictionary.views.activities.TranslationActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestMenuActivity {

    @get:Rule
    var menuActivityRule: ActivityTestRule<MenuActivity> =
        ActivityTestRule<MenuActivity>(MenuActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() = init()

    @Test
    @Throws(Exception::class)
    fun testButtonTranslation() {
        onView(withId(R.id.btn_translation))
            .check(matches(allOf(
                withText(R.string.tv_translation),
                withEffectiveVisibility(Visibility.VISIBLE),
                isClickable(),
                isFocusable())))
            .perform(click())
        intended(hasComponent(TranslationActivity::class.java.name))
    }

    @Test
    @Throws(Exception::class)
    fun testButtonDictionary() {
        onView(withId(R.id.btn_dictionary))
            .check(matches(allOf(
                withText(R.string.tv_dictionary),
                withEffectiveVisibility(Visibility.VISIBLE),
                isClickable(),
                isFocusable())))
            .perform(click())
        intended(hasComponent(DictionaryActivity::class.java.name))
    }

    @Test
    @Throws(Exception::class)
    fun testButtonSettings() {
        onView(withId(R.id.settings))
            .check(matches(allOf(
                withEffectiveVisibility(Visibility.VISIBLE),
                isClickable(),
                isFocusable())))
            .perform(click())
        onView(withId(R.id.np_pagination)).check(matches(isDisplayed()))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() = release()
}

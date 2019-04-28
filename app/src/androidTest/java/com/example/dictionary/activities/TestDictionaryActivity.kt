package com.example.dictionary.activities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.init
import android.support.test.espresso.intent.Intents.release
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.dictionary.R
import com.example.dictionary.views.activities.DictionaryActivity
import com.example.dictionary.views.activities.WordDetailActivity
import com.example.dictionary.views.adapters.DictionaryAdapter
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestDictionaryActivity {

    @get:Rule
    val dictionaryActivityRule: ActivityTestRule<DictionaryActivity> =
        ActivityTestRule<DictionaryActivity>(DictionaryActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() = init()

    @Test
    @Throws(Exception::class)
    fun testList() {
        onView(withId(R.id.list_translations))
            .check(matches(allOf(
                isDisplayed(),
                isFocusable())))
            .perform(scrollToPosition<DictionaryAdapter.ViewHolder>(3))
            .perform(scrollToPosition<DictionaryAdapter.ViewHolder>(1))
            .perform(scrollToPosition<DictionaryAdapter.ViewHolder>(2))
            .perform(actionOnItemAtPosition<DictionaryAdapter.ViewHolder>(1, click()))
        Intents.intended(IntentMatchers.hasComponent(WordDetailActivity::class.java.name))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() = release()
}

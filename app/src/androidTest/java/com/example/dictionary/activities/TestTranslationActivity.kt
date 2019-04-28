package com.example.dictionary.activities

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.dictionary.R
import com.example.dictionary.views.activities.TranslationActivity
import com.example.dictionary.views.adapters.TranslationAdapter
import org.hamcrest.BaseMatcher
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestTranslationActivity {

    @get:Rule
    val wordDetailActivityRule: ActivityTestRule<TranslationActivity> =
        ActivityTestRule<TranslationActivity>(TranslationActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun testSpinners() {
        onView(withId(R.id.spin_transl_from))
            .check(matches(isDisplayed()))
            .perform(click())
        onData(anything()).atPosition(16).perform(click())
        onView(withId(R.id.spin_transl_from))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.spin_transl_to))
            .check(matches(isDisplayed()))
            .perform(click())
        onData(anything()).atPosition(18).perform(click())
        onView(withId(R.id.spin_transl_to))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    @Throws(Exception::class)
    fun testEditTextTranslFrom() {
        onView(withId(R.id.et_trans_from))
            .check(matches(allOf(
                withEffectiveVisibility(Visibility.VISIBLE),
                isClickable(),
                isFocusable(),
                withText(""))))
            .perform(typeText("Initial D"))
        closeSoftKeyboard()
    }

    @Test
    @Throws(Exception::class)
    fun testEditTextTranslTo() {
        onView(withId(R.id.et_trans_to))
            .check(matches(not(allOf(
                withEffectiveVisibility(Visibility.VISIBLE),
                isClickable(),
                isFocusable()))))
            .check(matches(withText("")))
    }

    @Test
    @Throws(Exception::class)
    fun testButtonTranslate() {
        onView(withId(R.id.btn_trans))
            .check(matches(allOf(
                withEffectiveVisibility(Visibility.VISIBLE),
                isClickable(),
                isFocusable(),
                withText(R.string.btn_translate))))
    }

    @Test
    @Throws(Exception::class)
    fun testList() {
        onView(withId(R.id.list_translations))
            .check(matches(allOf(
                isDisplayed())))
            .perform(RecyclerViewActions.scrollToPosition<TranslationAdapter.ViewHolder>(3))
            .perform(RecyclerViewActions.scrollToPosition<TranslationAdapter.ViewHolder>(1))
            .perform(RecyclerViewActions.scrollToPosition<TranslationAdapter.ViewHolder>(2))
    }

    @Test
    @Throws(Exception::class)
    fun testTranslation() {
        onView(withId(R.id.spin_transl_from)).perform(click())
        onData(anything()).atPosition(16).perform(click())

        onView(withId(R.id.spin_transl_to)).perform(click())
        onData(anything()).atPosition(18).perform(click())

        onView(withId(R.id.et_trans_from))
            .check(matches(isCompletelyDisplayed()))
            .perform(typeText("Initial D"))
//        onView(withId(R.id.btn_trans)).perform(click())
    }

    inner class SpinnerMatcher(private val name: String) : BaseMatcher<Any>() {
        override fun matches(item: Any?): Boolean = item.toString() == name
        override fun describeTo(description: org.hamcrest.Description?) {}
    }
}

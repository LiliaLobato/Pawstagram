package com.example.lilialobato.pawstagramV3;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import com.example.lilialobato.pawstagramV3.fragments.HomeFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

class Matchers {
    public static Matcher<View> withListSize (final int size) {
        return new TypeSafeMatcher<View>() {
            @Override public boolean matchesSafely (final View view) {
                return ((ListView) view).getCount () == size;
            }

            @Override public void describeTo (final Description description) {
                description.appendText ("ListView should have " + size + " items");
            }
        };
    }
}

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


@Before
public void init(){
    mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_content,new HomeFragment());

}

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.company.insta.instagram", appContext.getPackageName());
    }



    @Test
    public void testifDataAddedAfterScroll()throws Exception{

       Thread.sleep(10000);
        onView(withId(R.id.feed_lv)).perform(ViewActions.swipeUp());
        Thread.sleep(3000);
      //  onView(withId(R.id.feed_lv)).check(ViewAssertions.matches(Matchers.withListSize(1)));

        onData(anything()).inAdapterView(withId(R.id.feed_lv)).atPosition(1).check(ViewAssertions.matches(isDisplayed()));
    }



}



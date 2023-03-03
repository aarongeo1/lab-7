package com.example.cmput_301_customlist_thursday;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }

    @Test
    public void start() throws Exception {

        Activity activity = rule.getActivity();
    }


    /**
     * Add a city to the listview and check the city name using assertTrue
     * Clear all the cities from the listview and check again with assertFalse
     */
    @Test
    public void checkList(){
// Asserts that the current activity is the MainActivity. Otherwise, show “Wrong Activity”
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY"); //Click ADD CITY Button
//Get view for EditText and enter a city name
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnView(solo.getView(R.id.button_confirm)); //Select CONFIRM Button
        solo.clearEditText((EditText) solo.getView(R.id.editText_name)); //Clear the EditText
/* True if there is a text: Edmonton on the screen, wait at least 2 seconds and find
minimum one match. */
        assertTrue(solo.waitForText("Edmonton", 1, 2000));
        solo.clickOnButton("ClEAR ALL"); //Select ClEAR ALL
//True if there is no text: Edmonton on the screen
        assertFalse(solo.searchText("Edmonton"));
    }
    /**
     * Check item taken from the listview
     */
    @Test
    public void checkCiyListItem(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnView(solo.getView(R.id.button_confirm));
        solo.waitForText("Edmonton", 1, 2000);
// Get MainActivity to access its variables and methods.
        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        final ListView cityList = activity.cityList; // Get the listview
        String city = (String) cityList.getItemAtPosition(0); // Get item from first position
        assertEquals("Edmonton", city);
    }

    @Test
    public void testActivitySwitch() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnView(solo.getView(R.id.button_confirm));
        solo.waitForText("Edmonton", 1, 2000);
        solo.clickInList(0); // Click on the first item in the list
        solo.assertCurrentActivity("Activity not switched", showActivity.class); // Check if the ShowActivity is displayed
    }

    @Test
    public void testCityNameConsistency() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnView(solo.getView(R.id.button_confirm));
        solo.waitForText("Edmonton", 1, 2000);
        String expectedCityName = "Edmonton";
        solo.clickInList(0); // Click on the first item in the list
        boolean cityNameDisplayed = solo.waitForText(expectedCityName, 1, 2000); // Wait up to 2 seconds for the city name to be displayed
        assertTrue("City name not displayed correctly", cityNameDisplayed); // Check if the city name is displayed correctly
    }

    @Test
    public void testBackButton() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnView(solo.getView(R.id.button_confirm));
        solo.waitForText("Edmonton", 1, 2000);
        solo.clickInList(0); // Click on the first item in the list
        solo.goBack(); // Simulate pressing the back button
        solo.assertCurrentActivity("Activity not switched", MainActivity.class); // Check if the MainActivity is displayed again
    }

    /**
     * Closes the activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception{
            solo.finishOpenedActivities();
    }



}

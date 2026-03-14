package mmcs.assignment4_compose

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun resetCalculator() {
        composeTestRule.onNodeWithText("AC").performClick()
    }

    private fun click(vararg buttons: String){
        buttons.forEach { composeTestRule.onNodeWithText(it).performClick() }
    }

    @Test
    fun testInitialDisplayIsZero() {
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("0")
    }

    @Test
    fun testOperationAdd() {
        click("1", "+", "2", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("3")
    }

    @Test
    fun testOperationSub() {
        click("4", "-", "3", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("1")
    }

    @Test
    fun testOperationDiv() {
        click("9", "÷", "6", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("1.5")
    }

    @Test
    fun testOperationMul() {
        click("5", "×", "6", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("30")
    }

    @Test
    fun testOperationPercent() {
        click("9", "%", "1", "0", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("0.9")
    }

    @Test
    fun testOperationNeg() {
        click("9", "+/-", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("-9")
    }

    @Test
    fun testAddPoint() {
        click("8", ".", "5", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("8.5")
    }

    @Test
    fun testReset() {
        click("9", ".", "5", "AC")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("0")
    }

    @Test
    fun testClear() {
        click("6", "+", "5", "C", "7", "=")
        composeTestRule.onNodeWithTag("DISPLAY").assertTextEquals("13")
    }
}
package dev.babananick.userstask

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.babananick.userstask.ui.theme.LocalSnackBarHost
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavHostInstrumentedTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val resources = context.resources

    @Test
    fun checkNavGraphStartDestination() {
        val headerText = with(resources) {
            getString(
                R.string.current_data_source_type_template,
                getString(R.string.data_source_type_remote)
            )
        }

        composeTestRule.setContent {
            MaterialTheme {
                AppHavHost()
            }
        }
        composeTestRule.waitUntil(
            condition = {
                composeTestRule.onAllNodesWithText(headerText).fetchSemanticsNodes().isNotEmpty()
            }
        )
    }

    @Test
    fun successfulNavigationToDetails() {
        val contentDestination = resources.getString(R.string.acsb_user_trailing_icon_description)
        val addressHeaderText = resources.getString(R.string.user_data_address_title)
        val companyHeaderText = resources.getString(R.string.user_data_company_title)

        composeTestRule.setContent {
            MaterialTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                CompositionLocalProvider(
                    LocalSnackBarHost provides snackbarHostState
                ) {
                    AppHavHost()
                }
            }
        }
        composeTestRule.waitUntil(
            condition = {
                composeTestRule.onAllNodesWithContentDescription(contentDestination)
                    .fetchSemanticsNodes()
                    .isNotEmpty()
            }
        )
        composeTestRule.onAllNodesWithContentDescription(contentDestination).onFirst()
            .performClick()
        composeTestRule.waitUntil(
            condition = {
                composeTestRule.onAllNodesWithText(addressHeaderText).fetchSemanticsNodes()
                    .isNotEmpty()
            }
        )
        composeTestRule.onNodeWithText(companyHeaderText).assertExists()
    }
}
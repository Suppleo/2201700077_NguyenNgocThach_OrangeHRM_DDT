import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// ── Variables (bound from Test Suite) ───────────────────────────────────────
// keyword, expectedName, shouldFound, testCaseID

WebUI.comment("▶ Running: ${testCaseID} | Keyword: ${keyword} | ShouldFound: ${shouldFound}")

// ── Open Browser ─────────────────────────────────────────────────────────────
WebUI.openBrowser('https://opensource-demo.orangehrmlive.com')
WebUI.maximizeWindow()

// ── Login with Admin account ──────────────────────────────────────────────────
WebUI.setText(findTestObject('Object Repository/Login/txt_Username'), 'Admin')
WebUI.setText(findTestObject('Object Repository/Login/txt_Password'), 'admin123')
WebUI.click(findTestObject('Object Repository/Login/btn_Login'))

// ── Navigate to PIM → Employee List ──────────────────────────────────────────
WebUI.click(findTestObject('Object Repository/PIM/mnu_PIM'))
WebUI.click(findTestObject('Object Repository/PIM/mnu_EmployeeList'))

// ── Search Employee ───────────────────────────────────────────────────────────
WebUI.setText(findTestObject('Object Repository/PIM/txt_SearchName'), keyword)
WebUI.click(findTestObject('Object Repository/PIM/btn_Search'))

// ── Verify Based on shouldFound ───────────────────────────────────────────────
if (shouldFound.toString().toLowerCase() == 'true') {
    WebUI.verifyElementVisible(findTestObject('Object Repository/PIM/itm_FirstResult'))
    String resultName = WebUI.getText(findTestObject('Object Repository/PIM/itm_FirstResult'))
    WebUI.verifyMatch(resultName, expectedName, false)
    WebUI.comment("✅ ${testCaseID}: Found employee '${resultName}'")
} else {
    WebUI.verifyElementVisible(findTestObject('Object Repository/PIM/lbl_NoRecord'))
    WebUI.comment("✅ ${testCaseID}: 'No Records Found' displayed as expected")
}

// ── Logout & Close Browser ────────────────────────────────────────────────────
WebUI.click(findTestObject('Object Repository/Login/btn_profile_dropdown'))
WebUI.click(findTestObject('Object Repository/Login/lnk_Logout'))
WebUI.closeBrowser()
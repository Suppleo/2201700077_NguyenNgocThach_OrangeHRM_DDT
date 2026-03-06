import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// ── Variables (bound from Test Suite) ───────────────────────────────────────
// username, password, expectedResult, testCaseID

WebUI.comment("▶ Running: ${testCaseID} | Expected: ${expectedResult}")

// ── Open Browser ─────────────────────────────────────────────────────────────
WebUI.openBrowser('https://opensource-demo.orangehrmlive.com')
WebUI.maximizeWindow()

// ── Input Credentials ─────────────────────────────────────────────────────────
WebUI.setText(findTestObject('Object Repository/Login/txt_Username'), username)
WebUI.setText(findTestObject('Object Repository/Login/txt_Password'), password)
WebUI.click(findTestObject('Object Repository/Login/btn_Login'))

// ── Verify Based on Expected Result ───────────────────────────────────────────
switch (expectedResult) {

    case 'Login Success':
        WebUI.verifyElementVisible(findTestObject('Object Repository/Login/lbl_Dashboard'))
        WebUI.comment("✅ ${testCaseID}: Dashboard visible — login success")
		WebUI.click(findTestObject('Object Repository/Login/btn_profile_dropdown'))
        WebUI.click(findTestObject('Object Repository/Login/lnk_Logout'))
        break

    case 'Invalid credentials':
        WebUI.verifyElementVisible(findTestObject('Object Repository/Login/lbl_Error'))
        String errorMsg = WebUI.getText(findTestObject('Object Repository/Login/lbl_Error'))
        WebUI.verifyMatch(errorMsg, 'Invalid credentials', false)
        WebUI.comment("✅ ${testCaseID}: Error message displayed")
        break

    case 'Username cannot be empty':
        WebUI.verifyElementVisible(findTestObject('Object Repository/Login/lbl_ErrorUsername'))
        WebUI.comment("✅ ${testCaseID}: Username error message displayed")
        break

    case 'Password cannot be empty':
        WebUI.verifyElementVisible(findTestObject('Object Repository/Login/lbl_ErrorPassword'))
        WebUI.comment("✅ ${testCaseID}: Password error message displayed")
        break

    default:
        WebUI.comment("⚠️ ${testCaseID}: Unknown expectedResult '${expectedResult}'")
        break
}

// ── Close Browser ─────────────────────────────────────────────────────────────
WebUI.closeBrowser()
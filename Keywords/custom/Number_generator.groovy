package custom
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.apache.commons.lang.RandomStringUtils as RandStr
import java.util.concurrent.ThreadLocalRandom

/**
 Custom Keyword - 'Number_generator':
 1   - Genera un número 'Random' (aleatorio)
 2   - Parametros:
 2.1 - minimum: contiene el valor minimo que puede generar
 2.2 - maximum: tiene el valor maximo y usa esta cantidad de carateres para generar el valor
 (si 'maximum' es '999999' genera un valor de 6 digitos)
 */

public class Number_generator {

	@Keyword
	def randomNumber(TestObject to, int minimum, int maximum) {
		def randomNumber = ThreadLocalRandom.current().nextInt(minimum, maximum + 1)
		WebUI.findWebElement(to).sendKeys(String.valueOf(randomNumber))
	}
}

/**
 * Refresh browser
 @Keywordf
 def refreshBrowser() {
 KeywordUtil.logInfo("Refreshing")
 WebDriver webDriver = DriverFactory.getWebDriver()
 webDriver.navigate().refresh()
 KeywordUtil.markPassed("Refresh successfully")
 }
 */
/**
 * Click element
 * @param to Katalon test object
 @Keyword
 def clickElement(TestObject to) {
 try {
 WebElement element = WebUiBuiltInKeywords.findWebElement(to);
 KeywordUtil.logInfo("Clicking element")
 element.click()
 KeywordUtil.markPassed("Element has been clicked")
 } catch (WebElementNotFoundException e) {
 KeywordUtil.markFailed("Element not found")
 } catch (Exception e) {
 KeywordUtil.markFailed("Fail to click on element")
 }
 }
 */
/**
 * Get all rows of HTML table
 * @param table Katalon test object represent for HTML table
 * @param outerTagName outer tag name of TR tag, usually is TBODY
 * @return All rows inside HTML table
 @Keyword
 def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
 WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
 List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
 return selectedRows
 }
 */

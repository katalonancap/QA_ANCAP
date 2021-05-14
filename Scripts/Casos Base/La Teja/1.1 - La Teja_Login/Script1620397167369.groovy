import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.net.URLEncoder as URLEncoder

/**
 Teste Case - Login: Hace login en el sistema ANTARES
 1.1 - Abre el navegafor
 1.2 - Maximiza el navegador
 2   - Accede a la URL (ya con Usuario y Contraseña)
 3   - Verifica que el login se hizo en la 'Planta' correcta
 4   - Selecciona el grupo (perfil del usuario)
 5   - Hace clic en el botón para login
 6   - Realiza nueva validación de que está en la HomePage de la planta correcta 
 */
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(URLLogin)

WebUI.waitForPageLoad(0)

WebUI.verifyElementText(findTestObject('Page_Bienvenida al sistema/span_texto_fenplanta'), Planta)

WebUI.selectOptionByValue(findTestObject('Page_Bienvenida al sistema/select_grupo_login'), SelectGrupo, true)

WebUI.click(findTestObject('Page_Bienvenida al sistema/input_btn_login'))

WebUI.waitForPageLoad(0)

WebUI.verifyTextPresent(Planta, false)


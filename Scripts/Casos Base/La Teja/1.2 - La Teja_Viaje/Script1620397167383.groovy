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

/**
 Teste Case - Viaje: Crea un nuevo Viaje
 1   - Accede a la pantalla 'Trabajar con Viaje'
 2   - Verifica si está presente el texto 'Trabajar con Viajes' (está en la pantalla correcta)
 3   - Presiona el botón 'Nuevo'
 4   - Verifica que el campo 'Fecha' está presente 
 5   - Pone foco en el campo Fecha y ingresa la fecha
 6   - Ingresa ID del Distribuidor, Camión y Documento del Conductor 
 7   - Presiona el botón 'Aceptar'
 8   - Verifica que está en la pestaña 'Detalle Documento de Carga'
 */
WebUI.click(findTestObject('Page_Antares/span_menu_plantas'))

WebUI.click(findTestObject('Page_Antares/td_menu_trabajar_con_viaje'))

WebUI.waitForPageLoad(0)

WebUI.verifyElementText(findTestObject('Page_Trabajar con Viajes/span_txt_viaje_titulo'), 'Trabajar con Viajes')

WebUI.click(findTestObject('Page_Trabajar con Viajes/input_viaje_btn_nuevo'))

WebUI.waitForPageLoad(0)

WebUI.verifyElementVisible(findTestObject('Page_Trabajar con Viajes/img_calendar_viaje_fecha'))

WebUI.focus(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_fecha'))

WebUI.sendKeys(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_fecha'), FechaViaje, FailureHandling.STOP_ON_FAILURE)

WebUI.sendKeys(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_fecha'), Keys.chord(Keys.TAB))

WebUI.click(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_cliente_o_distribuidora'))

WebUI.setText(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_cliente_o_distribuidora'), IdDistribuidor)

WebUI.setText(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_camion'), IdCamion)

WebUI.setText(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_conductor'), DocConductor)

WebUI.click(findTestObject('Page_Trabajar con Viajes/input_btn_viaje_aceptar'))

WebUI.waitForPageLoad(0)

WebUI.verifyElementVisible(findTestObject('Page_Trabajar con Viajes/span_tab_viaje_documento_Carga'))

def NroViajeAncap = WebUI.getText(findTestObject('Page_Trabajar con Viajes/span_txt_viaje_id_viaje_ancap'))

println('*******>>>>>>>>>>>>Nro del Viaje ANCAP:' + NroViajeAncap)


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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.click(findTestObject('Page_Antares/span_menu_plantas'))

WebUI.click(findTestObject('Object Repository/Page_Antares/td_Trabajar con Ingreso'))

WebUI.waitForPageLoad(0)

WebUI.verifyElementText(findTestObject('Page_Ingreso/span_txt_viaje_titulo_ingreso'), 'Ingreso')

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_nuevo_ingreso'))

WebUI.setText(findTestObject('Page_Ingreso Planta/input_cbx_viaje_ingreso_camion'), '10')

WebUI.sendKeys(findTestObject('Page_Ingreso Planta/input_cbx_viaje_ingreso_camion'), Keys.chord(Keys.TAB))

validaviajeagenda()

WebUI.setText(findTestObject('Page_Ingreso Planta/input_cbx_viaje_ingreso_conductor'), '11111111')

WebUI.click(findTestObject('Page_Ingreso Planta/input_btn_viaje_ingreso_aceptar'))

WebUI.waitForPageLoad(0)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_control_ingreso'))

WebUI.verifyElementText(findTestObject('Page_Controles de Entrada/span_txt_viaje_ingreso_titulo_ctrl_Ingreso'), 'Controles de Ingreso')

WebUI.selectOptionByLabel(findTestObject('Page_Controles de Entrada/select_sbx_viaje_ctrl_ingreso_apto_ingreso'), 'Si', 
    false)

WebUI.selectOptionByLabel(findTestObject('Page_Controles de Entrada/select_sbx_viaje_ctrl_ingreso_prod_transito'), 'No', 
    true)

WebUI.click(findTestObject('Page_Controles de Entrada/input_btn_viaje_ctrl_ingreso_aceptar'))

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_asociar_viaje'))

'Cambia para iframe (Popup)'
WebUI.switchToFrame(findTestObject('Page_Ingreso/iframe_form_viaje_ingreso_asociar_viaje'), 5)

asociaviaje()

'Vuelve a la pantalla base'
WebUI.switchToDefaultContent()

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_acreditar'))

WebUI.delay(10)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_cargadero'))

WebUI.verifyElementText(findTestObject('Page_Carga  Descarga/span_txt_viaje_ingreso_titulo_cargadero'), 'Carga / Descarga')

WebUI.setText(findTestObject('Page_Carga  Descarga/input_cbx_viaje_ingreso_carga_prd_temp'), '16')

WebUI.click(findTestObject('Page_Carga  Descarga/input_btn_viaje_ingreso_carga_cantidad_pedida'))

WebUI.click(findTestObject('Page_Carga  Descarga/input_btn_viaje_ingreso_carga_finalizar'))

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_liquidacion'))

WebUI.verifyElementText(findTestObject('Page_Liquidacin/span_txt_viaje_ingreso_titulo_liquidacion'), 'Liquidación')

WebUI.click(findTestObject('Page_Liquidacin/input_btn_viaje_ingreso_liq_finalizar'))

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_emitir_comprobante'))

'Cambia para iframe (Popup)'
WebUI.switchToFrame(findTestObject('Page_Ingreso/iframe_form_viaje_ingreso_asociar_viaje'), 5)

WebUI.setText(findTestObject('Page_Ingreso/iframe_cbx_viaje_ingreso_comprob_obs'), 'Ingreso del viaje')

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_comprob_aceptar'))

WebUI.switchToDefaultContent()

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_verificacion'))

WebUI.verifyElementText(findTestObject('Page_Verificacion/span_txt_viaje_ingreso_titulo_verificacion'), 'Verificacion')

WebUI.click(findTestObject('Page_Verificacion/input_btn_viaje_ingreso_aprobar'))

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_ctrl_egreso'))

WebUI.verifyElementText(findTestObject('Page_Controles Egreso/span_txt_viaje_ingreso_ctrl_egreso_estado'), 'Verificado')

WebUI.click(findTestObject('Page_Controles Egreso/input_btn_viaje_ingreso_ctrl_egreso_aceptar'))

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_egreso'))

WebUI.click(findTestObject('Page_Egreso/input_btn_viaje_ingreso_egreso_aceptar'))

def validaviajeagenda() {
    'RECORRE TABLA "VIAJE AGENDA"'

    'Valor esperado en la tabla'
    String ExpectedValue = GlobalVariable.NroViajeAncap

    WebDriver driver = DriverFactory.getWebDriver()

    'Se ubica la tabla'
    WebElement table = driver.findElement(By.id('GridviajesagendaContainerTbl'))

    'Para ubicar filas en la tabla se captura todas las filas disponibles en un arreglo'
    List<WebElement> Rows = table.findElements(By.tagName('tr'))

    'Busca LA COINCIDENCIA DE TEXTO "NroViajeAncap" para realizar una acción'

    'Loop permite hacer la busqueda en todas las filas de la tabla'
    table: for (int i = 0; i < Rows.size(); i++) {
        'Se ubican las columnas de una fila especifica (se ubica la celda)'
        List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))

        println(Rows.get(i))

        println('BUSCANDO EN LAS FILAS')

        for (int j = 0; j < Cols.size(); j++) {
            println('BUSCANDO EN LAS COLUMNAS')

            'Se verifica el texto esperado celda por celda'
            if (Cols.get(j).getText().equalsIgnoreCase(ExpectedValue)) {
                println('BUSCANDO EN LAS CELDAS')

                'Para ubicar el ancla en la fila coincidente del valor esperado para realizar la acción'
                def nrovjeagendatb = Cols.get(j).findElement(By.tagName('span')).getText()

                WebUI.verifyMatch(nrovjeagendatb, ExpectedValue, false)

                println('*******>>>>>>>>>>>>NRO VIAJE AGENDA:' + nrovjeagendatb)

                println()

                break
            }
        }
    }
}

def asociaviaje() {
    'RECORRE TABLA "ASOCIAR VIAJE"'

    'Valor esperado en la tabla'
    String ExpectedValue = GlobalVariable.NroViajeAncap

    WebDriver driver = DriverFactory.getWebDriver()

    'Se ubica la tabla'
    WebElement table = driver.findElement(By.id('GridContainerTbl'))

    'Para ubicar filas en la tabla se captura todas las filas disponibles en un arreglo'
    List<WebElement> Rows = table.findElements(By.tagName('tr'))

    'Busca LA COINCIDENCIA DE TEXTO "NroViajeAncap" para realizar una acción'

    'Loop permite hacer la busqueda en todas las filas de la tabla'
    table: for (int i = 0; i < Rows.size(); i++) {
        'Se ubican las columnas de una fila especifica (se ubica la celda)'
        List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))

        println(Rows.get(i))

        println('BUSCANDO EN LAS FILAS')

        for (int j = 0; j < Cols.size(); j++) {
            println('BUSCANDO EN LAS COLUMNAS')

            'Se verifica el texto esperado celda por celda'
            if (Cols.get(j).getText().equalsIgnoreCase(ExpectedValue)) {
                println('BUSCANDO EN LAS CELDAS')

                'Para ubicar el ancla en la fila coincidente del valor esperado para asociar el viaje'
                def nrovjeasociadotb = Cols.get(j).findElement(By.tagName('span')).getText()

                Cols.get(j - 2).findElement(By.tagName('input')).click()

                WebUI.acceptAlert()

                println('*******>>>>>>>>>>>NRO VIAJE ASOCIADO:' + nrovjeasociadotb)

                println()

                break
            }
        }
    }
}

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


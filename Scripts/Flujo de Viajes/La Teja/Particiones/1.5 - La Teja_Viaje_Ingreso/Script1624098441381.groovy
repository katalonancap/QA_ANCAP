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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

/**
 Teste Case - Viaje_Ingreso: Ingresa el camión a la planta
 1   - Ingresa a la pantalla 'Trabajar con Ingreso'
 2   - Presiona el botón 'Nuevo Ingreso'
 3   - Ingresa Camión y Conductor ya grabados en las 'variables Globales'
 4   - Invoca el método 'validaviajeagenda()'
 5   - Ingresa a 'Control de Ingreso' y selecciona las opciones
 6   - Ingresar a 'Asociar Viaje' (ejecuta en iframe)
 7   - Invoca el método 'asociaviaje()'
 8   - Presiona el botón 'Acreditar'
 9   - Ingresa a 'Cargadero' y escribe la temperatura y presionar el botón 'Cantidad Pedida'
 10  - Ingresa a 'Liquidación'
 11  - Presiona el botón 'Emitir Comprobantes' (ejecutar en iframe)
 12  - Ingresa a 'Verificación' y presiona 'aceptar'
 13  - Ingresa a 'Egreso'
 14  - En 'Trabajar con viaje' busca el viaje en el filtro
 15  - Invoca el método 'validaviajefinalizado()'
 */
/**
4   - Invoca el método 'validaviajeagenda()'
4.1 - Recorre la tabla 'Viaje Agenda'
4.2 - Confirma que el viaje está en la lista
*/
/**
 7   - Invoca el método 'asociaviaje()'
 7.1 - Recorre la tabla 'Asociar Viaje'
 7.2 - Identifica el nro del viaje (Nro de viaje está grabado en la variable global)
 7.3 - Hace clic en el botón 'Asociar' del viaje identificado
 */
/**
 15   - Invoca el método 'validaviajefinalizado()'
 15.1 - Recorre la tabla 'Trabajar con Viajes'
 15.2 - Identifica el viaje creado y ingresado
 15.3 - Imprime el Número y el Estado del viaje (debe estar Finalizado)
 */
WebUI.click(findTestObject('Page_Antares/span_menu_plantas'))

WebUI.click(findTestObject('Page_Antares/td_menu_trabajar_con_ingreso'))

WebUI.waitForPageLoad(0)

WebUI.verifyElementText(findTestObject('Page_Ingreso/span_txt_viaje_titulo_ingreso'), 'Ingreso')

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_nuevo_ingreso'))

WebUI.setText(findTestObject('Page_Ingreso Planta/input_cbx_viaje_ingreso_camion'), GlobalVariable.IdCamionGlob)

WebUI.sendKeys(findTestObject('Page_Ingreso Planta/input_cbx_viaje_ingreso_camion'), Keys.chord(Keys.TAB))

validaviajeagenda()

WebUI.setText(findTestObject('Page_Ingreso Planta/input_cbx_viaje_ingreso_conductor'), GlobalVariable.DocConductGlob)

WebUI.click(findTestObject('Page_Ingreso Planta/input_btn_viaje_ingreso_aceptar'))

WebUI.waitForPageLoad(0)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_control_ingreso'))

WebUI.verifyElementText(findTestObject('Page_Controles de Entrada/span_txt_viaje_ingreso_titulo_ctrl_Ingreso'), 'Controles de Ingreso')

WebUI.selectOptionByLabel(findTestObject('Page_Controles de Entrada/select_sbx_viaje_ctrl_ingreso_apto_ingreso'), AptoIngreso, 
    false)

WebUI.selectOptionByLabel(findTestObject('Page_Controles de Entrada/select_sbx_viaje_ctrl_ingreso_prod_transito'), ProdTransito, 
    true)

WebUI.click(findTestObject('Page_Controles de Entrada/input_btn_viaje_ctrl_ingreso_aceptar'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_asociar_viaje'))

'Cambia para iframe (Popup)'
WebUI.switchToFrame(findTestObject('Page_Ingreso/iframe_form_viaje_ingreso_asociar_viaje'), 5)

asociaviaje()

'Vuelve a la pantalla base'
WebUI.switchToDefaultContent()

WebUI.delay(10)

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_acreditacion'))

WebUI.waitForPageLoad(0)

'Cambia para iframe (Popup)'
WebUI.switchToFrame(findTestObject('Page_Ingreso/iframe_form_viaje_ingreso_acredita_planta_flete'), 5, FailureHandling.OPTIONAL)

WebUI.selectOptionByLabel(findTestObject('Page_Ingreso/select_sbx_viaje_ingreso_acredita_planta_flete'), PFleteAcredita, 
    false, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Page_Ingreso/span_btn_viaje_ingreso_acredita_planta_flete_finalizar'), FailureHandling.OPTIONAL)

'Vuelve a la pantalla base'
WebUI.switchToDefaultContent(FailureHandling.OPTIONAL)

WebUI.delay(3)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_cargadero'))

WebUI.verifyElementText(findTestObject('Page_Carga  Descarga/span_txt_viaje_ingreso_titulo_cargadero'), 'Carga / Descarga')

WebUI.setText(findTestObject('Page_Carga  Descarga/input_cbx_viaje_ingreso_carga_prd_temp'), ProdTemperatura)

WebUI.click(findTestObject('Page_Carga  Descarga/input_btn_viaje_ingreso_carga_cantidad_pedida'))

WebUI.click(findTestObject('Page_Carga  Descarga/input_btn_viaje_ingreso_carga_finalizar'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_liquidacion'))

WebUI.verifyElementText(findTestObject('Page_Liquidacin/span_txt_viaje_ingreso_titulo_liquidacion'), 'Liquidación')

WebUI.click(findTestObject('Page_Liquidacin/input_btn_viaje_ingreso_liq_finalizar'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_emitir_comprobante'))

'Cambia para iframe (Popup)'
WebUI.switchToFrame(findTestObject('Page_Ingreso/iframe_form_viaje_ingreso_emitir_comprobante'), 5)

WebUI.setText(findTestObject('Page_Ingreso/input_cbx_viaje_ingreso_comprob_obs'), 'Ingreso del viaje')

WebUI.click(findTestObject('Page_Ingreso/input_btn_viaje_ingreso_comprob_aceptar'))

WebUI.switchToDefaultContent()

WebUI.delay(15)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_verificacion'))

WebUI.verifyElementText(findTestObject('Page_Verificacion/span_txt_viaje_ingreso_titulo_verificacion'), 'Verificacion')

WebUI.click(findTestObject('Page_Verificacion/input_btn_viaje_ingreso_aprobar'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_ctrl_egreso'))

WebUI.verifyElementText(findTestObject('Page_Controles Egreso/span_txt_viaje_ingreso_ctrl_egreso_estado'), 'Verificado')

WebUI.click(findTestObject('Page_Controles Egreso/input_btn_viaje_ingreso_ctrl_egreso_aceptar'))

WebUI.delay(5)

WebUI.click(findTestObject('Page_Ingreso/img_btn_viaje_ingreso_egreso'))

WebUI.click(findTestObject('Page_Egreso/input_btn_viaje_ingreso_egreso_aceptar'))

WebUI.waitForPageLoad(0)

WebUI.click(findTestObject('Page_Antares/span_menu_plantas'))

WebUI.click(findTestObject('Page_Antares/td_menu_trabajar_con_viaje'))

WebUI.verifyElementText(findTestObject('Page_Trabajar con Viajes/span_txt_viaje_titulo'), 'Trabajar con Viajes')

WebUI.clearText(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_filtro_fecha_desde'))

WebUI.clearText(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_filtro_fecha_hasta'))

WebUI.setText(findTestObject('Page_Trabajar con Viajes/input_cbx_viaje_filtro_viaje'), GlobalVariable.NroViajeAncap)

WebUI.click(findTestObject('Page_Trabajar con Viajes/span_btn_viaje_filtro_buscar'))

validaviajefinalizado()

WebUI.closeBrowser()

def IngresaDireccion_viaEntrega() {
    'Ingresa el ID de la "Dirección" y ID de la "Via de Entrega"'
    WebUI.click(findTestObject('Page_Detalle de rden de Carga/td_viaje_doc_carga_area_direccion_entrega'))

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_direccion'), IdDireccion)

    WebUI.click(findTestObject('Page_Detalle de rden de Carga/tr_viaje_doc_carga_area_cliente'))

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_via_entrega'), IdViaEntrega)

    WebUI.sendKeys(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_via_entrega'), Keys.chord(Keys.TAB))
}

def IngresaProducto() {
    'Llena los campos que se refieren al "Producto"'
    WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_producto'))

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_producto'), IdProducto)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_prd_cantidad'), ProdCantidad)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_unidad'), ProdUnidad)
}

def IngresaSolicitud_Orden() {
    'Llena los campos que se refieren al "Solicitud de Entrega" y "Orden Oficial"'
    WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_solicitud_auto'), 
        SolicitudAuto, false, FailureHandling.OPTIONAL)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viajes_doc_carga_solicitud_entrega'), SolicitudEntrega, 
        FailureHandling.OPTIONAL)

    WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viaje_doc_carga_orden_oficial'), FailureHandling.OPTIONAL)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viaje_doc_carga_orden_oficial'), OrdenOficial, 
        FailureHandling.OPTIONAL)
}

def validaviajeagenda() {
    'RECORRE TABLA "VIAJE AGENDA"'
    String ExpectedValue = GlobalVariable.NroViajeAncap

    WebDriver driver = DriverFactory.getWebDriver()

    'Se ubica la tabla'
    WebElement table = driver.findElement(By.id('GridviajesagendaContainerTbl'))

    'Para ubicar filas en la tabla se captura todas las filas disponibles en un arreglo'
    List<WebElement> Rows = table.findElements(By.tagName('tr'))

    'Loop permite hacer la busqueda en todas las filas de la tabla - Busca LA COINCIDENCIA DE TEXTO "NroViajeAncap" para realizar una acción'
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

def validaviajefinalizado() {
    'RECORRE TABLA "TRABAJAR CON VIAJES"'
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

                'Para ubicar el ancla en la fila coincidente del valor esperado para realizar la acción'
                def nrovjefintb = Cols.get(j).findElement(By.tagName('span')).getText()

                println('*******>>>>>>>>>>>>NRO VIAJE FINALIZADO:' + nrovjefintb)

                println()

                def estadovje = Cols.get(j + 10).findElement(By.tagName('span')).getText()

                println('*******>>>>>>>>>>>>ESTADO DEL VIAJE:' + estadovje)

                println()

                break
            }
        }
    }
}


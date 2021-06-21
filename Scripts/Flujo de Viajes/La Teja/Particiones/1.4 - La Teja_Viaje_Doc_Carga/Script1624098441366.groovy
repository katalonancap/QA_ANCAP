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

/**
 Teste Case - Viaje_Doc_Carga: Crea un Documento de Carga para el viaje
 1   - Verifica si está en la pantalla 'Detalle de Documentos de Carga del viaje'
 2   - Presiona el botón 'Nuevo'
 **Usar 3.1 o 3.2 (comentar la opción que NO será usada)
 3.1 - Llama la KeyWord randomNumber: genera un nro aleatorio para el campo Nro de Orden'
 3.2 - Permite ingresar un Nro de Orden especifico (linea 40 comentada)
 4   - Sigue llenando los demás campos
 **Para Cliente Oficial
 5.1 - Verifica si el texto 'Solicitud automática ?' está presente
 5.2 - Si está presente seleccionar una opción (Sí/No) Para Cliente Oficial
 5.3 - Llena 'Solicitud de Entrega' y 'Orden Oficial' si estan presentes
 ** Para EESS/Particular
 5.4 - Si el texto 'Solicitud automática ?' NO está presente llena los campos
 5.5 - Llena 'Solicitud de Entrega' si está presente
 */
/**
 While: Trata el error por Nro de Orden duplicado
 1 - Verifica si el error 'Número de Orden Duplicado' está presente
 2 - Si está ingresa un nuevo Nro de Orden aleatorio
 3 - Sigue llenando los demás campos y presiona Aceptar
 */


WebUI.verifyTextPresent('Detalle de Documentos de Carga del viaje', false)

WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_detalle_doc_carga_nuevo'))

WebUI.waitForPageLoad(0)

'Genera un numero aleatorio para el campo "Nro de Orden"'
CustomKeywords.'custom.Number_generator.randomNumber'(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
   10, 999999)

//--- Ingresa un Nro de Orden fijo (solamente para el caso de hacer alguna prueba especifica)
//WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), '13700')

'Define la variable "NroOrdenGenerado" y guarda el "Nro de Orden"'
def NroOrdenGenerado = WebUI.getAttribute(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
    'value')

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_cliente'), IdCliente)

IngresaDireccion_viaEntrega()

WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), IdPolitica, FailureHandling.OPTIONAL)

WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_negocio'), Negocio, true, 
    FailureHandling.OPTIONAL)

'Ingresa CLIENTES PARTICULARES/EESS O CLIENTES OFICIALES'
if (WebUI.verifyTextPresent('Solicitud automática ?', false, FailureHandling.OPTIONAL)) {
    IngresaSolicitud_Orden()
} else {
	'Selecciona "Planta Flete" para clientes Particularesq/EESS '
    WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_planta_flete'), PlantaFlete, 
        false)
}

IngresaProducto()

WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))

WebUI.waitForPageLoad(0)
'TrataERROR: Sigue ingresando "nro de orden" nuevo hasta deje de presentar el error "Numero de Orden Duplicado"'
while (WebUI.verifyTextPresent('Número de Orden Duplicado', false, FailureHandling.OPTIONAL) == true) {
    CustomKeywords.'custom.Number_generator.randomNumber'(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
        10, 999999)
	'Actualiza la varible "NroOrdenGenerado"'
    NroOrdenGenerado = WebUI.getAttribute(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
        'value', FailureHandling.OPTIONAL)

    IngresaDireccion_viaEntrega()

    WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), FailureHandling.OPTIONAL)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), IdPolitica, FailureHandling.OPTIONAL)

    WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_negocio'), Negocio, 
        true, FailureHandling.OPTIONAL)

    WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_planta_flete'), PlantaFlete, 
        false, FailureHandling.OPTIONAL)

    IngresaSolicitud_Orden()

    IngresaProducto()

    WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))

    break
}


'RECORRE TABLA "DOCUMENTO DE CARGA"'
String ExpectedValue = NroOrdenGenerado

WebDriver driver = DriverFactory.getWebDriver()

'Se ubica la tabla'
WebElement table = driver.findElement(By.id('W0036W0025GridContainerTbl'))

'Para ubicar filas en la tabla se captura todas las filas disponibles en un arreglo'
List<WebElement> Rows = table.findElements(By.tagName('tr'))

//println('*******>>>>>>>>>>>>No. de filas: ' + Rows.size())

'Loop permite hacer la busqueda en todas las filas de la tabla - Busca LA COINCIDENCIA DE TEXTO "NroOrdenGenerado" para realizar una acción'
table: for (int i = 0; i < Rows.size(); i++) {
    'Se ubican las columnas de una fila especifica (se ubica la celda)'
    List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))

    println(Rows.get(i))

    //println('*******>>>>>>>>>>>>No. de columnas: ' + Cols.size())

    println('BUSCANDO EN LAS FILAS')

    for (int j = 0; j < Cols.size(); j++) {
        println('BUSCANDO EN LAS COLUMNAS')

        'Se verifica el texto esperado celda por celda'
        if (Cols.get(j).getText().equalsIgnoreCase(ExpectedValue)) {
            println('BUSCANDO EN LAS CELDAS')

            'Para ubicar el ancla en la fila coincidente del valor esperado para realizar la acción'
			nroordentb = Cols.get(j).findElement(By.tagName('span')).getText()
			WebUI.verifyMatch(nroordentb, ExpectedValue, false)
			
			nrodoctb = Cols.get(j - 1).findElement(By.tagName('a')).getText()
			println('*******>>>>>>>>>>>>NRO DEL DOCUMENTO:' + nrodoctb)
			
			println('*******>>>>>>>>>>>>NRO DE ORDEN:' + nroordentb)
			
			println ()
			
            break
        }
    }
}



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

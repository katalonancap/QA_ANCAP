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
WebUI.verifyTextPresent('Detalle de Documentos de Carga del viaje', false)

WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_detalle_doc_carga_nuevo'))

WebUI.waitForPageLoad(0)

//---Genera un numero aleatorio para el campo 'Nro de Orden'
CustomKeywords.'custom.Number_generator.randomNumber'(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
    10, 999999)

//--- Ingresa un Nro de Orden fijo (solamente para el caso de hacer alguna prueba especifica)
//WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), '13700')

def NroOrdenGenerado = WebUI.getAttribute(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 'value')

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_cliente'), IdCliente)

IngresaDireccion_viaEntrega()

WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), IdPolitica, FailureHandling.OPTIONAL)
		
WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_negocio'), Negocio, true, 
    FailureHandling.OPTIONAL)

if (WebUI.verifyTextPresent('Solicitud automática ?', false, FailureHandling.OPTIONAL)) {
    WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_solicitud_auto'), 
        SolicitudAuto, false)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viajes_doc_carga_solicitud_entrega'), SolicitudEntrega, 
        FailureHandling.OPTIONAL)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viaje_doc_carga_orden_oficial'), OrdenOficial, 
        FailureHandling.OPTIONAL)

    IngresaProducto()

    WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))

    WebUI.waitForPageLoad(0)

	WebUI.verifyTextPresent(NroOrdenGenerado, false, FailureHandling.OPTIONAL)
	
	ErrorNroOrdenDuplicado()

} 
else {
    WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_planta_flete'), PlantaFlete, 
        false)

    IngresaProducto()

    WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))

    WebUI.waitForPageLoad(0)
	
	WebUI.verifyTextPresent(NroOrdenGenerado, false, FailureHandling.OPTIONAL)
	
	ErrorNroOrdenDuplicado ()
	
	}


/**
 Método - IngresaDireccion_viaEntrega : Ingresa el ID de la 'Dirección' y ID de la 'Via de Entrega'
 1 - Hace clic en el campo 'Dirección'
 2 - Ingresa el ID de la 'Dirección'
 3 - Ingresa el ID de la 'Via de Entrega'
 4 - Hace un 'Tab' (para que se complete la Politica)
 */

	/**
 Método - IngresaProducto : Llena los campos que se refieren al 'Producto'
 1 - Limpia el campo Producto para que esté vacío
 2 - Ingresa el ID del Producto
 3 - Ingresa la Cantidad
 4 - Ingresa Unidad
 */

	/**
 Método - ErrorNroOrdenDuplicado : Trata el error por Nro de Orden duplicado
 1 - Verifica si el error 'Número de Orden Duplicado' está presente
 2 - Si está ingresa un nuevo Nro de Orden aleatorio
 3 - Sigue llenando los demás campos y presiona Aceptar
 */
def IngresaDireccion_viaEntrega() {
    //WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_direccion_entrega'))

    WebUI.click(findTestObject('Page_Detalle de rden de Carga/td_viaje_doc_carga_area_direccion_entrega'))

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_direccion'), IdDireccion)

    WebUI.click(findTestObject('Page_Detalle de rden de Carga/tr_viaje_doc_carga_area_cliente'))

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_via_entrega'), IdViaEntrega)

    WebUI.sendKeys(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_via_entrega'), Keys.chord(Keys.TAB))
}


def IngresaProducto() {
    WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_producto'))

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_producto'), IdProducto)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_prd_cantidad'), ProdCantidad)

    WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_unidad'), ProdUnidad)
}


def ErrorNroOrdenDuplicado() {
    while (WebUI.verifyTextPresent('Número de Orden Duplicado', false, FailureHandling.OPTIONAL) == true) {
        CustomKeywords.'custom.Number_generator.randomNumber'(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
            10, 999999)

        IngresaDireccion_viaEntrega()

		WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), FailureHandling.OPTIONAL)
		
		WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), IdPolitica, FailureHandling.OPTIONAL)

        WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_negocio'), Negocio, 
            true, FailureHandling.OPTIONAL)

        WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_planta_flete'), 
            PlantaFlete, false, FailureHandling.OPTIONAL)

        WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_solicitud_auto'), 
            SolicitudAuto, false, FailureHandling.OPTIONAL)

        WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viajes_doc_carga_solicitud_entrega'), SolicitudEntrega, 
            FailureHandling.OPTIONAL)

        WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viaje_doc_carga_orden_oficial'), OrdenOficial, 
            FailureHandling.OPTIONAL)

        IngresaProducto()

		def NuevoNroOrdenGenerado = WebUI.getAttribute(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 'value', FailureHandling.OPTIONAL)
		
		WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))
		
		WebUI.verifyTextPresent(NuevoNroOrdenGenerado, false)
		
        break
    }
}


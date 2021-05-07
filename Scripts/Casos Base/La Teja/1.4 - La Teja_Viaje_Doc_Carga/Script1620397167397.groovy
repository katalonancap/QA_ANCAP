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
 2   - Presiona el botóm 'Nuevo'
 **Usar 3.1 o 3.2 (comentar la opción que NO será usada)
 3.1 - Llama la KeyWord randomNumber: genera un nro aleatorio para el campo Nro de Orden'
 3.2 - Permite ingresar un Nro de Orden especifico (linea 40 comentada)
 4   - Sigue llenando los demás campos
 **Para Cliente Oficial
 5.1 - Verifica si el texto 'Solicitud automática ?' está presente
 5.2 - Si está presente seleccionar una opción (Sí/No) Para Cliente Oficial
 5.3 - Llena 'Solicitude de Entrega' y 'Orden Oficial' si estan presentes (opcional)
 ** Para EESS/Particular
 5.4 - Si el texto 'Solicitud automática ?' NO está presente llena los campos
 */

WebUI.verifyTextPresent('Detalle de Documentos de Carga del viaje', false)

WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_detalle_doc_carga_nuevo'))

WebUI.waitForPageLoad(0)

//---Genera un numero aleatorio para el campo 'Nro de Orden'
CustomKeywords.'custom_keywords.Number_generator.randomNumber'(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
    10, 999999)

//--- Ingresa un Nro de Orden fijo (solamente para el caso de hacer alguna prueba especifica)
//WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), '13700')

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_cliente'), IdCliente)

WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_direccion_entrega'))

WebUI.click(findTestObject('Page_Detalle de rden de Carga/td_viaje_doc_carga_area_direccion_entrega'))

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_direccion'), IdDireccion)

WebUI.click(findTestObject('Page_Detalle de rden de Carga/tr_viaje_doc_carga_area_cliente'))

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_via_entrega'), IdViaEntrega)

WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), IdPolitica, FailureHandling.OPTIONAL)

WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_negocio'), Negocio, true, 
    FailureHandling.OPTIONAL)

//--- Verifica si el campo 'Solicitud automática' está presente (para Clientes Oficiales)
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

    ErrorNroOrdenDuplicado()

    ErrorPoliticaVacia()

    ValidaNuevoDocCarga()
	
} else {
	//Llena los campos demás campos (para EESS/Cliente Particular)
    WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_planta_flete'), PlantaFlete, 
        false)

    IngresaProducto()

    WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))

    WebUI.waitForPageLoad(0)

    ErrorNroOrdenDuplicado()

    ErrorPoliticaVacia()

    ValidaNuevoDocCarga()
}

/**
 Método - IngresaProducto : Llena los campos que se refieren al 'Producto'
 1 - Limpia el campo Producto para que esté vacío
 2 - Ingresa el ID del Producto
 3 - Ingresa la Cantidad
 4 - Ingresa Unidad
 */

def IngresaProducto() {
	WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_producto'))

	WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_producto'), IdProducto)

	WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_prd_cantidad'), ProdCantidad)

	WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_unidad'), ProdUnidad)
}

/**
 Método - ValidaNuevoDocCarga : Valida que el Documento de Carga fue creado
 1 - Verifica si el ícono 'Lupa' está presente
***Este método todavía está en desarrollo***
 */

def ValidaNuevoDocCarga() {
	WebUI.verifyElementVisible(findTestObject('Page_Detalle de rden de Carga/input_viaje_doc_carga_lupa'))
}

/**
 Método - ErrorNroOrdenDuplicado : Trata el error por Nro de Orden duplicado
 1 - Verifica si el error 'Número de Orden Duplicado' está presente
 2 - Si está ingresa un nuevo Nro de Orden aleatorio
 3 - Sigue llenando los demás campos y presiona Aceptar
 */
def ErrorNroOrdenDuplicado() {
    while (WebUI.verifyTextPresent('Número de Orden Duplicado', false, FailureHandling.OPTIONAL) == true) {
        CustomKeywords.'custom_keywords.Number_generator.randomNumber'(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_nro_orden'), 
            10, 999999)

        WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_cliente'), IdCliente)

        WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_direccion'), IdDireccion)

        WebUI.sendKeys(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_direccion'), Keys.chord(Keys.TAB))

        WebUI.delay(1)

        WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_negocio'), Negocio, 
            true, FailureHandling.OPTIONAL)

        WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'))

        WebUI.sendKeys(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), IdPolitica, FailureHandling.OPTIONAL)

        WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_via_entrega'), IdViaEntrega)

        WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_planta_flete'), 
            PlantaFlete, false, FailureHandling.OPTIONAL)

        WebUI.selectOptionByLabel(findTestObject('Page_Detalle de rden de Carga/select_sbx_viaje_doc_carga_solicitud_auto'), 
            SolicitudAuto, false, FailureHandling.OPTIONAL)

        WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viajes_doc_carga_solicitud_entrega'), SolicitudEntrega, 
            FailureHandling.OPTIONAL)

        WebUI.setText(findTestObject('Page_Detalle de rden de Carga/input_cbox_viaje_doc_carga_orden_oficial'), OrdenOficial, 
            FailureHandling.OPTIONAL)

        IngresaProducto()

        WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))

        ErrorPoliticaVacia()

        break
    }
}

/**
 Método - ErrorPoliticaVacia : Trata el error por campo Politica Vacío 
 (este error pasa algunas veces cuando la Politica se campleta sola - solamente tiene 1 opción de Politica)
 1 - Verifica si el error 'La política no puede ser vacía.' está presente
 2 - Si está ingresa la Politica
 3 - Sigue llenando los demás campos y presiona Aceptar
 */

def ErrorPoliticaVacia() {
    if (WebUI.verifyTextPresent('La política no puede ser vacía.', false, FailureHandling.OPTIONAL)) {
        WebUI.clearText(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), FailureHandling.OPTIONAL)

        WebUI.sendKeys(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), IdPolitica, FailureHandling.OPTIONAL)

        WebUI.delay(1)

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
		
		WebUI.sendKeys(findTestObject('Page_Detalle de rden de Carga/input_cbx_viaje_doc_carga_politica'), Keys.chord(Keys.TAB))
        
		WebUI.delay(1)
		
		IngresaProducto()

        WebUI.click(findTestObject('Page_Detalle de rden de Carga/input_btn_viaje_doc_carga_aceptar'))
		
    } else {
    }
}


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



class execution_listeners {
	
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 Actualiza variable para escribir resultados del test en el EXCEL (i se refiere a cada fila)
	 */ 
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		//println testCaseContext.getTestCaseId()
		//println testCaseContext.getTestCaseVariables()
		
		GlobalVariable.FilasExcel = GlobalVariable.FilasExcel+1

	} 
	
	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	/**
	 Listener - 'tearDownTestCase':
	 1   - Verifica el estado en que finalizó el Test Case
	 2.1 - Si tiene estado 'FAILED' o 'ERROR'
	 2.2 - Imprime "Test case is FAILED"
	 2.3 - Captura un Screenshot (con fecha y hora de la captura)
	 2.4 - Cierra el navegador
	 3.1 - Si tiene estado PASSED
	 3.2 - Imprime "Test case SUCCESSFUL"
	 */
	@AfterTestCase
	
	/**
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
	
	}
	*/
	
	def tearDownTestCase(TestCaseContext testCaseContext) {		
		def testCaseStatus = testCaseContext.getTestCaseStatus();
		if(testCaseStatus.equals('FAILED')) {
		System.out.println("Test case is FAILED")
		
		try {
				Date data = new Date(System.currentTimeMillis())
				SimpleDateFormat formatarDate = new SimpleDateFormat('yyyyMMdd_HHmmss')
				WebUI.takeScreenshot(('C:\\Users\\Usuario\\git\\QA_ANCAP\\Screenshots\\Errores\\Screenshot_' + formatarDate.format(data)) + '.png')
				}catch (Exception e) {    e.printStackTrace()}
				
				//WebUI.closeBrowser()
				
		}else if (testCaseStatus.equals('ERROR')) {
			
			System.out.println("Test case is FAILED")
			
			try {
					Date data = new Date(System.currentTimeMillis())
					SimpleDateFormat formatarDate = new SimpleDateFormat('yyyyMMdd_HHmmss')
					WebUI.takeScreenshot(('C:\\Users\\Usuario\\git\\QA_ANCAP\\Screenshots\\Errores\\Screenshot_' + formatarDate.format(data)) + '.png')
					}catch (Exception e) {    e.printStackTrace()}
					
					//WebUI.closeBrowser()
					
			}else {
				
			System.out.println("Test case SUCCESSFUL")
		}
	//CustomKeywords.'custom.Write_Excel.writeExcel'(testCaseContext.getTestCaseId(), GlobalVariable.i)
	CustomKeywords.'custom.Write_Excel.writeExcel'(GlobalVariable.NroViajeAncap, GlobalVariable.FilasExcel)
	CustomKeywords.'custom.Write_Excel.writeExcel'(testCaseContext.getTestCaseStatus(), GlobalVariable.FilasExcel)
	}

	 
	
	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	} */

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}*/
}


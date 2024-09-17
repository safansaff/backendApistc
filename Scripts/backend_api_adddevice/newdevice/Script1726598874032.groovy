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

// Send the API Request
def response = WS.sendRequest(findTestObject('bakendapi/stc_addnewdevice'))

// Validate the status code is 200 (OK)
WS.verifyResponseStatusCode(response, 200)

WS.verifyElementPropertyValue(response, 'name', 'Apple 16 Pro 256 GB')

WS.verifyElementPropertyValue(response, 'data.year', 2024)

WS.verifyElementPropertyValue(response, 'data.price', 6999.99)

// For fields with spaces like 'CPU model' and 'Hard disk size', use square brackets
WS.verifyElementPropertyValue(response, 'data["CPU model"]', 'Apple ARM A1')

WS.verifyElementPropertyValue(response, 'data["Hard disk size"]', '256 GB')

// Get the actual values from the response
def actualId = WS.getElementPropertyValue(response, 'id')

// Log the ID value for debugging
println('Actual ID: ' + actualId)

// Validate that ID is not null or empty
assert actualId != null : 'Validation failed: ID is null'

assert !(actualId.isEmpty()) : 'Validation failed: ID is empty'

// (Optional) Validate the ID follows a specific pattern, e.g., a UUID pattern
def uuidPattern = '^[0-9a-fA-F]{32}$'

assert actualId.matches(uuidPattern) : 'Validation failed: ID does not match the expected pattern'

// Get the 'createdAt' property value from the response
def createdAt = WS.getElementPropertyValue(response, 'createdAt')

// Log the 'createdAt' value for debugging
println('createdAt: ' + createdAt)

// Validate that 'createdAt' is not null
assert createdAt != null : 'Validation failed: \'createdAt\' is null'

// (Optional) Validate that 'createdAt' matches a date-time format, for example, ISO 8601 format
def dateTimePattern = '\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}'

assert createdAt ==~ dateTimePattern : 'Validation failed: \'createdAt\' does not match the expected date-time format'


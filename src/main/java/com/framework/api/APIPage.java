/**
 * 
 */
package com.framework.api;

import static io.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.params.CoreConnectionPNames;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.framework.ui.pageobjects.BasePage;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.MultiPartConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author prabhudatta
 *
 */
@SuppressWarnings("rawtypes")
public class APIPage extends BasePage {

	public static int NUMBER_OF_THREADS = 0;
	public static String API_NAME = null;
	private static String clientID = "clientId";
	private static String deviceID = "deviceId";
	private static String pageNO = "pageNo";
	private static String pageSize = "pageSize";
	private static String appJson = "application/json";
	private static String appactet = "application/octet-stream";
	private static String tenantID = "tenantId";
	private static String dateType = "yyyy-MM-dd'T'HH:mm:ss 0530";
	@SuppressWarnings("unused")
	private static String dateType2 = "yyyy-MM-dd'T'HH:mm:ss";
	private static String queryStr = "queryString";
	private static long timeout = 180000;

	// public String accessToken = null;
	// public static long access_time= 0;

	@SuppressWarnings("deprecation")
	public static String getAccessToken(String key, String secret) {
		Response response = null;
		try {
			HashMap<String, String> credentials = new HashMap<>();
			credentials.put("grant_type", "client_credentials");
			credentials.put("client_secret", secret);//
			credentials.put("client_id", key);//
			RestAssured.useRelaxedHTTPSValidation();

			RestAssuredConfig config = RestAssured.config()
					.httpClient(HttpClientConfig.httpClientConfig()
							.setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
							.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

			config.encoderConfig(
					EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
			response = (Response) given().contentType("application/x-www-form-urlencoded").header("Accept", appJson)
					.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
					.formParams(credentials).when().post(APIUri.baseUri + APIUri.accessToken).then().extract();
		} catch (Exception e) {
			testCaseLogger.get().error("The access token not generated proeprly " + e.getMessage());

		}
		System.out.println("Access token :" + response.asString());
		String accessToken = response.path("access_token").toString();
		// access_time =
		// System.currentTimeMillis()+response.jsonPath().getLong("expires_in");
		return accessToken;
	}

	public static String getAccessToken(String key, String secret, String content, String header) {
		Response response = null;
		try {
			HashMap<String, String> credentials = new HashMap<>();
			credentials.put("grant_type", "client_credentials");
			credentials.put("client_secret", secret);//
			credentials.put("client_id", key);//
			RestAssured.useRelaxedHTTPSValidation();

			RestAssuredConfig config = RestAssured.config()
					.httpClient(HttpClientConfig.httpClientConfig()
							.setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
							.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));
			config.multiPartConfig(MultiPartConfig.multiPartConfig().defaultBoundary("PARTS_BOUNDARY"));
			response = (Response) given()
					.config(RestAssured.config().encoderConfig(
							RestAssured.config().getEncoderConfig().encodeContentTypeAs(content, ContentType.ANY)))
					.header("Accept", header).formParams(credentials).when().post(APIUri.baseUri + APIUri.accessToken)
					.then().extract();

		} catch (Exception e) {
			e.printStackTrace();
			testCaseLogger.get().info("The access token not generated proeprly " + e.getMessage());
		}
		System.out.println("Access token :" + response.asString());
		String accessToken = response.path("access_token").toString();
		// access_time =
		// System.currentTimeMillis()+response.jsonPath().getLong("expires_in");
		return accessToken;
	}

	/**
	 * setAccessToken() is to set the access token
	 */
	public static String setAccessToken() {
		Response response = null;

		try {
			/*
			 * HashMap<String, String> credentials = new HashMap<String, String>();
			 * credentials.put("grant_type", "client_credentials");
			 * credentials.put("client_secret", secret);// credentials.put("client_id",
			 * key);//
			 */ RestAssured.useRelaxedHTTPSValidation();
			RestAssuredConfig config = RestAssured.config()
					.httpClient(HttpClientConfig.httpClientConfig()
							.setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
							.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

			config.encoderConfig(
					EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
			response = (Response) given().contentType("application/x-www-form-urlencoded").header("Accept", appJson)
					.config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
					// .formParams(credentials)
					.when().post(APIUri.baseUri + APIUri.accessToken).then().extract();
		} catch (Exception e) {
			testCaseLogger.get().info("The access token not generated proeprly " + e.getMessage());
		}
		System.out.println(response.asString());
		String accessToken = response.path("access_token").toString();
		// access_time =
		// System.currentTimeMillis()+response.jsonPath().getLong("expires_in");
		return accessToken;

	}

	/**
	 * Returns common request specification for API Request
	 * 
	 * @param accessToken2
	 * @return Returns common request specification for API Request
	 */
	private static RequestSpecification specificationBuilder(String accessToken2) {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(APIUri.apibaseUri);
		builder.addHeader("Authorization", "Bearer " + accessToken2);
		builder.setContentType(ContentType.URLENC);
		builder.setAccept(appJson);
		RestAssuredConfig config = RestAssured.config().httpClient(
				HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
						.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

		config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	/**
	 * Returns common request specification for API Request
	 * 
	 * @param accessToken2
	 * @return Returns common request specification for API Request
	 */
	private static RequestSpecification specificationBuilderCookie(Set<Cookie> cookie) {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(APIUri.apibaseUri);
		builder.addCookie("JSESSIONID", cookie);
		builder.setContentType(ContentType.URLENC);
		builder.setAccept(appJson);
		RestAssuredConfig config = RestAssured.config().httpClient(
				HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
						.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

		config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	private static RequestSpecification specificationBuilderoctate(String accessToken2) {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(APIUri.apibaseUri);
		builder.addHeader("Authorization", "Bearer " + accessToken2);
		builder.setContentType(ContentType.BINARY);
		builder.setAccept(appactet);
		RestAssuredConfig config = RestAssured.config().httpClient(
				HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
						.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

		config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	/**
	 * Returns common request specification for API Request
	 * 
	 * @param accessToken2
	 * @return Returns common request specification for API Request
	 */
	private static RequestSpecification specificationBuilderAgentDownload(String accessToken2) {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(APIUri.apibaseUri);
		builder.addHeader("Authorization", "Bearer " + accessToken2);
		builder.setContentType(ContentType.BINARY);
		builder.setAccept(appJson);
		RestAssuredConfig config = RestAssured.config().httpClient(
				HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
						.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

		config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	/**
	 * Returns common request specification for API Request
	 * 
	 * @param accessToken2
	 * @return Returns common request specification for API Request
	 */
	private static RequestSpecification postspecificationBuilder(String accessToken2) {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(APIUri.apibaseUri);
		builder.addHeader("Authorization", "Bearer " + accessToken2);
		builder.setContentType(ContentType.JSON);
		builder.setAccept(appJson);
		RestAssuredConfig config = RestAssured.config().httpClient(
				HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
						.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

		config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	/**
	 * Returns common request specification for API Request
	 * 
	 * @param accessToken2
	 * @return Returns common request specification for API Request
	 */
	private static RequestSpecification postspecificationBuilderMultiPart(String accessToken2, String content) {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(APIUri.apibaseUri);
		builder.addHeader("Authorization", "Bearer " + accessToken2);
		builder.setContentType(content);
		builder.setAccept(content);

		RestAssuredConfig config = RestAssured.config().httpClient(
				HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
						.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));
		config.multiPartConfig(MultiPartConfig.multiPartConfig().defaultBoundary("PARTS_BOUNDARY"));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	public static Response post(String pathUri, Object obj, Map<String, String> map, String key, String secret) {
		Response response = null;

		String accessToken = getAccessToken(key, secret);
		if (obj == null) {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).when().post(APIUri.apibaseUri + pathUri)
						.then().extract().response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).when()
						.post(APIUri.apibaseUri + pathUri, map).then().extract().response();
			}

		} else {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when()
						.post(APIUri.apibaseUri + pathUri).then().extract().response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when()
						.post(APIUri.apibaseUri + pathUri, map).then().extract().response();
			}
		}
		testCaseLogger.get()
				.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	public static Response postWithContent(String pathUri, Object obj, Map<String, String> map, String key,
			String secret, String content, String header, String filename) {
		Response response = null;
		String accessToken = getAccessToken(key, secret, content, header);

		if (obj == null) {

			if (map == null) {
				response = given().spec(postspecificationBuilderMultiPart(accessToken, content))
						.multiPart(new MultiPartSpecBuilder(content).fileName(filename).controlName("file").build())
						.body(obj).post(APIUri.apibaseUri + pathUri);

			} else {
				response = given().spec(postspecificationBuilderMultiPart(accessToken, content))
						.multiPart(new MultiPartSpecBuilder(content).fileName(filename).controlName("file").build())
						.body(obj).post(APIUri.apibaseUri + pathUri, map);
			}

		} else {

			if (map == null) {
				response = given().spec(postspecificationBuilderMultiPart(accessToken, content))
						.multiPart(new MultiPartSpecBuilder(content).fileName(filename).controlName("file").build())
						.body(obj).post(APIUri.apibaseUri + pathUri);
			} else {
				response = given().spec(postspecificationBuilderMultiPart(accessToken, content))
						.multiPart(new MultiPartSpecBuilder(content).fileName(filename).controlName("file").build())
						.body(obj).post(APIUri.apibaseUri + pathUri, map);
			}
		}
		testCaseLogger.get()
				.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	/**
	 * Returns post response of an API
	 * 
	 * @param path
	 *            uri The unique path for particular API
	 * @param obj
	 *            The object is payload given to request for creation.
	 * @param map
	 *            The map object is to assign path variables
	 * @return Returns post response of an API
	 */
	public static Response post(String pathUri, Map<String, String> map) {

		Response response = null;
		// System.out.println(APIUri.apibaseUri + pathUri);
		// String accessToken = getAccessToken((String)
		// TestSetUp.context.getBean("key"),
		// (String) TestSetUp.context.getBean("secret"));

		try {
			if (map == null) {
				response = given().spec(postspecificationBuilder()).when().post(APIUri.apibaseUri + pathUri).then()
						.extract().response();
			} else {
				response = given().when().spec(postspecificationBuilder()).post(APIUri.apibaseUri + pathUri, map).then()
						.extract().response();
			}

			System.out.println(response.asString());
			testCaseLogger.get()
					.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static Response put(String pathUri, Object obj, Map<String, String> map) {
		Response response = null;

		String accessToken = setAccessToken();
		if (obj == null) {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).when().put(pathUri).then().extract()
						.response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).when().put(pathUri, map).then().extract()
						.response();
			}

		} else {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when().put(pathUri).then()
						.extract().response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when().put(pathUri, map).then()
						.extract().response();
			}
		}
		testCaseLogger.get().info(String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	/**
	 * Returns get response of an API
	 * 
	 * @param map
	 *            The map object is to assign path variables
	 * @return Returns get response of an API
	 */
	@SuppressWarnings("unchecked")
	public static Response getDownload(String pathUri, @SuppressWarnings("rawtypes") Map map) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + APIUri.apibaseUri + pathUri);

		String accessToken = setAccessToken();
		if (map == null) {
			response = given().spec((RequestSpecification) specificationBuilderAgentDownload(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri).then().extract().response();
		}

		else {
			response = given().spec((RequestSpecification) specificationBuilderAgentDownload(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri, map).then().extract().response();

		}
		System.out.println(
				APIUri.apibaseUri + pathUri + " >> " + response.getStatusCode() + " >>> " + response.asString());
		testCaseLogger.get()
				.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	/**
	 * Returns get response of an API
	 * 
	 * @param map
	 *            The map object is to assign path variables
	 * @return Returns get response of an API
	 */
	@SuppressWarnings("unchecked")
	public static Response get(String pathUri, @SuppressWarnings("rawtypes") Map map) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + APIUri.apibaseUri + pathUri);

		String accessToken = setAccessToken();
		if (map == null) {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri).then().extract().response();
		}

		else {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri, map).then().extract().response();

		}
		System.out.println(
				APIUri.apibaseUri + pathUri + " >> " + response.getStatusCode() + " >>> " + response.asString());
		testCaseLogger.get()
				.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	/**
	 * Returns get response of an API
	 * 
	 * @param map
	 *            The map object is to assign path variables
	 * @return Returns get response of an API
	 */
	@SuppressWarnings("unchecked")
	public static Response getWithCookie(String pathUri, @SuppressWarnings("rawtypes") Map map, Set<Cookie> cookie) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + APIUri.apibaseUri + pathUri);

		if (map == null) {
			response = given().spec((RequestSpecification) specificationBuilderCookie(cookie)).when()
					.get(APIUri.apibaseUri + pathUri).then().extract().response();
		}

		else {
			response = given().spec((RequestSpecification) specificationBuilderCookie(cookie)).when()
					.get(APIUri.apibaseUri + pathUri, map).then().extract().response();

		}
		System.out.println(
				APIUri.apibaseUri + pathUri + " >> " + response.getStatusCode() + " >>> " + response.asString());
		testCaseLogger.get()
				.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	@SuppressWarnings("unchecked")
	public static Response getWithKeySec(String pathUri, @SuppressWarnings("rawtypes") Map map, String clientKey,
			String clientSecret) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + APIUri.apibaseUri + pathUri);

		String accessToken = getAccessToken(clientKey, clientSecret);
		if (map == null) {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri).then().extract().response();
		}

		else {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri, map).then().extract().response();

		}
		testCaseLogger.get()
				.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	@SuppressWarnings("unchecked")
	public static Response getWithKeysSec(String pathUri, @SuppressWarnings("rawtypes") Map map, String clientKey,
			String clientSecret, String content, String header) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + APIUri.apibaseUri + pathUri);
		String accessToken = getAccessToken(clientKey, clientSecret, content, header);
		if (map == null) {
			response = given().spec((RequestSpecification) specificationBuilderoctate(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri).then().extract().response();
		}

		else {
			response = given().spec((RequestSpecification) specificationBuilderoctate(accessToken)).when()
					.get(APIUri.apibaseUri + pathUri, map).then().extract().response();

		}
		System.out.println(APIUri.apibaseUri + pathUri);
		System.out.println(response.asString());
		testCaseLogger.get()
				.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		return response;
	}

	public static Map<String, String> prepareDeviceAvailability(String clientId, String resourceType,
			String resourceUUID) {
		Map<String, String> map = new HashMap<>();
		map.put(clientID, clientId);
		map.put("resourceType", resourceType);
		map.put("resourceUUID", resourceUUID);
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map prepareMapSearch(String clientId, String device_id) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		Map map = new HashMap();
		map.put(clientID, clientId);
		map.put(deviceID, device_id);
		map.put(pageNO, 1);
		map.put(pageSize, 10);
		map.put("isDescendingOrder", true);
		map.put(queryStr, "recStatus:Completed+consoleType:RDP+startDate:" + sdf.format(new Date()) + "+endDate:"
				+ sdf.format(new Date()));

		return map;
	}

	public static int randomRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	public static String getRandomString() {
		String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String string = "AutoTest" + alphabets.charAt(randomRange(0, 51)) + alphabets.charAt(randomRange(0, 51))
				+ alphabets.charAt(randomRange(0, 51)) + alphabets.charAt(randomRange(0, 51))
				+ alphabets.charAt(randomRange(0, 51)) + alphabets.charAt(randomRange(0, 51))
				+ alphabets.charAt(randomRange(0, 51)) + alphabets.charAt(randomRange(0, 51))
				+ alphabets.charAt(randomRange(0, 51)) + alphabets.charAt(randomRange(0, 51))
				+ alphabets.charAt(randomRange(0, 51));
		return string;
	}

	/**
	 * Returns object of particular grt file
	 * 
	 * @param fileName
	 *            The JSON file name to create object
	 * @param resourceDirectory
	 *            The resource directory of file
	 * @return Returns object of particular JSON file
	 */
	/*
	 * public static Object getObject(String fileName, String resourceDirectory) {
	 * File file = new File(resourceDirectory + "/" + fileName); FileReader
	 * fileReader = null; try { fileReader = new FileReader(file); return (Object)
	 * new JSONParser().parse(fileReader); } catch (FileNotFoundException
	 * fileNotFoundException) { return fileNotFoundException; } catch (IOException
	 * ioException) { return ioException; } catch (ParseException parseException) {
	 * return parseException; } finally { try { if (fileReader != null) {
	 * fileReader.close(); }
	 * 
	 * } catch (Exception e) {
	 * testCaseLogger.get().error("inside get object exception " +
	 * e.getLocalizedMessage()); } file = null; } }
	 */

	public static boolean isResponseEmpty(String response) {
		boolean isEmpty = false;
		try {

			if (!StringUtils.isAlphanumeric(response)) {
				isEmpty = true;
				testCaseLogger.get().error("The response is coming as empty " + response);
			} else {
				isEmpty = false;
				testCaseLogger.get().pass("The response is coming as " + response);
			}
		} catch (Exception e) {
			testCaseLogger.get().warning("The response is coming as " + e.getMessage());
		}

		return isEmpty;
	}

	/*
	 * public static FileInputStream readPayLoadFile(String path) { String
	 * apiDataFolderPath = System.getProperty("user.dir") + "/" + (String)
	 * TestSetUp.context.getBean("api_folder"); FileInputStream fis = null; try { //
	 * fis = new FileInputStream(apiDataFolderPath + "/" + path +
	 * "_Payload.json.gz"); } catch (FileNotFoundException fe) {
	 * testCaseLogger.get() .error(path +
	 * " payload file couldnt able to locate in the folder " +
	 * fe.getLocalizedMessage()); } return fis; }
	 */

	public static List<Boolean> executeAPI(String api, Map<String, String> paramMap) {
		List<Boolean> result = new CopyOnWriteArrayList<>();
		Response response = null;
		response = APIPage.get(api, paramMap);
		System.out.println(response.getStatusCode());
		if (response.getStatusCode() == 200) {
			testCaseLogger.get().pass(api + "API could find response associated with the tenant id ");
			result.add(Boolean.TRUE);
		} else {
			result.add(Boolean.FALSE);
			testCaseLogger.get().warning(api + "API could not find any response associated with the tenant id ");
		}

		return result;
	}

	public static List<Boolean> executePostAPI(String api, Object jso, Map<String, String> paramMap) {
		List<Boolean> result = new CopyOnWriteArrayList<>();
		Response response = null;
		response = null;// PIPage.post(api, jso, paramMap);
		System.out.println(response.getStatusCode() + "POST API response >>> " + response.asString());
		if (response.getStatusCode() == 200) {
			testCaseLogger.get()
					.pass(api + "API could find response associated with the tenant id " + response.asString());
			result.add(Boolean.TRUE);
		} else {
			result.add(Boolean.FALSE);
			testCaseLogger.get().warning(
					api + "API could not find any reponse associated with the tenant id " + response.asString());
		}

		return result;
	}

	public static List<Boolean> executePost(String api, Object jso, Map<String, String> paramMap, String key,
			String secret) {
		List<Boolean> result = new CopyOnWriteArrayList<>();
		Response response = null;
		response = APIPage.post(api, jso, paramMap, key, secret);
		System.out.println(response.getStatusCode() + "POST API response >>> " + response.asString());
		if (response.getStatusCode() == 200) {
			testCaseLogger.get()
					.pass(api + "API could find response associated with the tenant id " + response.asString());
			result.add(Boolean.TRUE);
		} else {
			result.add(Boolean.FALSE);
			testCaseLogger.get().warning(
					api + "API could not find any reponse associated with the tenant id " + response.asString());
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> prepareGetResponseMap(String clientId, String param, String serviceRequestID) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		Map map = new HashMap<>();
		map.put(tenantID, clientId);
		map.put(param, serviceRequestID);
		map.put(pageNO, 1);
		map.put(pageSize, 10);
		map.put(queryStr, "startCreationDate:" + sdf.format(new Date()) + "+endCreationDate:" + sdf.format(new Date()));
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> prepareGetAssAttrMap(String attributeId) {
		Map map = new HashMap<>();
		map.put("attributeId", attributeId);
		map.put(pageNO, 1);
		map.put(pageSize, 10);
		map.put(queryStr, "");
		return map;
	}

	public static String getInternalAPIUri(String uri) {
		int lastocc = uri.lastIndexOf("v2");
		return uri.substring(0, lastocc);
	}

	public static Response postInternal(String accessToken, Object obj, String internalAPIUri,
			Map<String, String> map) {
		Response response = null;
		if (obj == null) {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).when().post(internalAPIUri).then()
						.extract().response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).when().post(internalAPIUri).then()
						.extract().response();
			}

		} else {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when().post(internalAPIUri)
						.then().extract().response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when().post(internalAPIUri)
						.then().extract().response();
			}
		}
		testCaseLogger.get().info("The POST API response code is coming as  " + String.valueOf(response.statusCode())
				+ response.asString());
		return response;
	}

	public static Response delete(String pathUri, Object obj, Map<String, String> map, String key, String secret) {
		Response response = null;

		String accessToken = getAccessToken(key, secret);
		if (obj == null) {
			response = given().spec(postspecificationBuilder(accessToken)).when()
					.delete(APIUri.apibaseUri + pathUri, map).then().extract().response();
		} else {
			response = given().spec(postspecificationBuilder(accessToken)).body(obj).when()
					.delete(APIUri.apibaseUri + pathUri, map).then().extract().response();
		}
		if (response.statusCode() == 407) {
			setAccessToken();
			post(pathUri, map);
		}
		testCaseLogger.get().info("The POST API response code is coming as  " + APIUri.apibaseUri + pathUri
				+ String.valueOf(response.statusCode()));
		return response;
	}

	private static RequestSpecification internalpostspecificationBuilder(String accessToken2) {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(getInternalAPIUri(APIUri.apibaseUri) + "basic");
		builder.addHeader("Authorization", "Basic " + accessToken2);
		builder.setContentType(ContentType.JSON);
		builder.setAccept(appJson);
		RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	public static String getBasicAccessToken(Map<String, String> data) {
		String str = data.get("validateSaveAuditActivity") + ":" + data.get("Password");
		byte[] bytesEncoded = Base64.getEncoder().encode(str.getBytes());
		String token = new String(bytesEncoded);
		System.out.println("encoded value is " + new String(bytesEncoded));
		return token;
	}

	@SuppressWarnings("unchecked")
	public static Response getInternal(String accessToken, String pathUri, @SuppressWarnings("rawtypes") Map map) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + getInternalAPIUri(APIUri.apibaseUri) + pathUri);
		if (map == null) {
			response = given().spec((RequestSpecification) internalpostspecificationBuilder(accessToken)).when()
					.get(getInternalAPIUri(APIUri.apibaseUri) + pathUri).then().extract().response();
		}

		else {
			response = given().spec((RequestSpecification) internalpostspecificationBuilder(accessToken)).when()
					.get(getInternalAPIUri(APIUri.apibaseUri) + pathUri, map).then().extract().response();

		}
		System.out.println(response.getStatusCode() + " >>> " + response.asString());
		testCaseLogger.get().info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()));
		return response;
	}

	@SuppressWarnings("unchecked")
	public static Response getpathuri(String pathUri, @SuppressWarnings("rawtypes") Map map) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + pathUri);

		String accessToken = setAccessToken();
		if (map == null) {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when().get(pathUri).then()
					.extract().response();
		}

		else {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when().get(pathUri, map)
					.then().extract().response();

		}
		System.out.println(response.getStatusCode() + " >>> " + response.asString());
		testCaseLogger.get().info(APIUri.apibaseUri + pathUri);
		return response;
	}

	@SuppressWarnings("unchecked")
	public static Response getInternal(String pathUri, @SuppressWarnings("rawtypes") Map map, String clientKey,
			String clientSecret) {
		Response response = null;
		testCaseLogger.get().info("The complete API Path is " + pathUri);

		String accessToken = getAccessToken(clientKey, clientSecret);
		if (map == null) {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when()
					.get("https://sjchc-trunk.vistara.it/" + pathUri).then().extract().response();
		}

		else {
			response = given().spec((RequestSpecification) specificationBuilder(accessToken)).when()
					.get("https://sjchc-trunk.vistara.it/" + pathUri, map).then().extract().response();

		}
		System.out.println(response.asString());
		testCaseLogger.get().info(APIUri.apibaseUri + pathUri);
		return response;
	}

	public static Response delete(String pathUri, Object obj, Map<String, String> map) {
		Response response = null;

		String accessToken = setAccessToken();
		if (obj == null) {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).when()
						.delete(APIUri.apibaseUri + pathUri).then().extract().response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).when()
						.delete(APIUri.apibaseUri + pathUri, map).then().extract().response();
			}

		} else {

			if (map == null) {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when()
						.delete(APIUri.apibaseUri + pathUri).then().extract().response();
			} else {
				response = given().spec(postspecificationBuilder(accessToken)).body(obj).when()
						.delete(APIUri.apibaseUri + pathUri, map).then().extract().response();
			}
		}
		if (response.statusCode() == 407) {
			setAccessToken();
			delete(pathUri, obj, map);
		}
		testCaseLogger.get().info(APIUri.apibaseUri + pathUri);
		return response;
	}

	private static RequestSpecification postspecificationBuilder() {

		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(APIUri.apibaseUri);
		// builder.setContentType(ContentType.JSON);
		builder.setAccept(appJson);
		builder.addHeader("Connection", "keep-alive");
		builder.setContentType("application/json;version=2.0");
		RestAssuredConfig config = RestAssured.config().httpClient(
				HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout)
						.setParam(CoreConnectionPNames.SO_TIMEOUT, timeout));

		config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
		RestAssured.useRelaxedHTTPSValidation();
		return builder.build();
	}

	public static Response postSEI(String pathUri, Map<String, String> map) {
		Response response = null;

		System.out.println(APIUri.apibaseUri + pathUri);
		try {
			if (map == null) {
				response = given().spec(postspecificationBuilder()).when().post(APIUri.apibaseUri + pathUri).then()
						.extract().response();
			} else {
				response = given().spec(postspecificationBuilder()).body(map).when().post(APIUri.apibaseUri + pathUri)
						.then().extract().response();
			}

			testCaseLogger.get()
					.info(APIUri.apibaseUri + pathUri + String.valueOf(response.statusCode()) + response.asString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.framework.ui.pageobjects.BasePage#getPageLoadCondition()
	 */
	@Override
	public ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}

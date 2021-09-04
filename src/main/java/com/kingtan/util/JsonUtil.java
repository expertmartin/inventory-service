package com.kingtan.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingtan.store.inventory.domain.Product;


public class JsonUtil {

	static public JsonNode getJsonNodeFromString(String jsonStr) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonStr);
	}

	static public JsonNode getJsonNodeFromByte(byte[] jsonByte) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonByte);
	}

	static public JsonNode getJsonNodeFromFile(File jsonFile) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonFile);
	}

	static public JsonNode getJsonNodeFromStream(InputStream jsonStream) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonStream);
	}

	static public JsonNode getJsonNodeFromReader(Reader jsonReader) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonReader);
	}

	static public JsonNode getJsonNodeFromURL(URL jsonURL) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonURL);
	}

	static public JsonNode getJsonNodeFromMap(Map<String, String> jmap) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(mapper.writeValueAsString(jmap));
	}

	static public Map<String, List<Map<String, String>>> getMapSLMSS(String str)
			throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(str);
		Map<String, List<Map<String, String>>> result = mapper.convertValue(jsonNode,
				new TypeReference<Map<String, List<Map<String, String>>>>() {
				});
		return result;
	}

	static public Map<String, List<Map<String, String>>> getMapSLMSS2(String str)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, List<Map<String, String>>> map = mapper.readValue(str,
				new TypeReference<Map<String, List<Map<String, String>>>>() {
				});
		return map;
	}

	static public Map<String, List<Product>> getMapSLProduct(String str)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, List<Product>> map = mapper.readValue(str,
				new TypeReference<Map<String, List<Product>>>() {
				});
		return map;
	}
}

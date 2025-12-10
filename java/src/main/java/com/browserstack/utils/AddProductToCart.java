package com.browserstack.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;

public class AddProductToCart {
    public static JSONObject jsonObject;

    public static Map<String, Object> createRequestBodyForInactiveApi() {
        Map<String, Object> customerDetails = new HashMap<>();
        customerDetails.put("userID", 179959539);
        customerDetails.put("isRegisteredUser", true);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("customerDetails", customerDetails);
        requestBody.put("status", "inactive");
        requestBody.put("cartSource", "nykaa");
        requestBody.put("userID", 179959539);

        return requestBody;
    }

    public static Map<String, Object> createRequestBody(String productId) {
        Map<String, Object> customerDetail = new HashMap<>();
        customerDetail.put("customerId", 179959539);
        customerDetail.put("bulkBuyerUser", false);
        customerDetail.put("groupId", 1);
        customerDetail.put("loyalUser", false);
        customerDetail.put("priveBand", "non_member");
        customerDetail.put("registeredUser", true);

        Map<String, Object> item = new HashMap<>();
        item.put("productId", productId);
        item.put("quantity", "1");

        List<Map<String, Object>> items = new ArrayList<>();
        items.add(item);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("customerDetail", customerDetail);
        requestBody.put("deviceType", "ANDROID");
        requestBody.put("domain", "nykaa");
        requestBody.put("items", items);
        requestBody.put("appVersion", "2.2.2");

        return requestBody;
    }

    public static void inactiveMethod() {
        ApiClient apiClient = new ApiClient();
        String url = "http://preprod-cartapi.nyk00-int.network/cartapi/int/status";
        Map<String, Object> requestBody = createRequestBodyForInactiveApi();
        try {

            var response = apiClient.patch(url, requestBody, java.util.Map.class);
            System.out.println("Cart deactivated : " + response);
            Thread.sleep(500);
        } catch (IOException | InterruptedException e) {
            throw new AssertionError("Failed to call inactiveMethod API: \n" + e.getMessage());
        }
    }

    public static void addToCart(String productId) throws IOException, InterruptedException {
        ApiClient apiClient = new ApiClient();
        String url = "http://preprod-cartapi.nyk00-int.network/cartapi/v1/item/add/refresh?lang=en";

        Map<String, Object> requestBody = createRequestBody(productId);
        try {
            var response = apiClient.post(url, requestBody, java.util.Map.class);
            jsonObject = new JSONObject(response);
            String success = jsonObject.optString("success", null);
            String message = jsonObject.optString("message", null);

            if (message.equals("Product added to bag") && success.equals("true")) {
                System.out.println("Product added to cart successfully");
            } else {
                throw new AssertionError("Failed to add product to cart. Response: " + response);

            }
            Thread.sleep(500);
        } catch (IOException | InterruptedException e) {
            throw new AssertionError("Failed to add product to cart: " + e.getMessage());
        }

    }

}

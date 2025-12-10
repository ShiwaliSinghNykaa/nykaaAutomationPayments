package com.browserstack.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;

public class UtilityAPI {
    public static JSONObject jsonObject;

    public static Map<String, String> generateDynamicSwitchingPayloadString(String productType, String deviceType,
            String domain, String paymentMethod, String paymentGateway) {
        Map<String, String> dynamicSwitchingPayload = new HashMap<>();
        dynamicSwitchingPayload.put("productType", productType);
        dynamicSwitchingPayload.put("deviceType", deviceType);
        dynamicSwitchingPayload.put("domain", domain);
        dynamicSwitchingPayload.put("paymentMethod", paymentMethod);
        dynamicSwitchingPayload.put("paymentGateway", paymentGateway);
        return dynamicSwitchingPayload;
    }

    public static String createPciUrl(String domain, String device, String switchValue) {
        String pciUrl = "http://172.26.16.112:8087/feature/pci/switch?domain=" + domain + "&device=" + device + "&switch=" + switchValue;
        return pciUrl;
    }

    public static void switchPaymentGateway(String productType, String deviceType, String domain, String paymentMethod,
            String paymentGateway) throws IOException, InterruptedException {
        ApiClient apiClient = new ApiClient();
        String url = "http://172.26.16.112:8087/dynamic/switching/";
        Map<String,String> requestBody = generateDynamicSwitchingPayloadString(productType, deviceType, domain,
                paymentMethod, paymentGateway);
        try {
            var response = apiClient.post(url, requestBody, java.util.Map.class);

            if (response.toString() == "dynamic pg set successfully") {
                System.out.println("Payment gateway switched successfully");
            } else {
                throw new AssertionError("Failed to switch payment gateway. Response: " + response);

            }
            Thread.sleep(500);
        } catch (IOException | InterruptedException e) {
            throw new AssertionError("Failed to switch payment gateway: " + e.getMessage());
        }

    }

     public static void switchPci(String switchValue, String deviceType, String domain) throws IOException, InterruptedException {
        ApiClient apiClient = new ApiClient();
        String url = createPciUrl(domain, deviceType, switchValue);

        try {
            var response = apiClient.get(url, java.util.Map.class);
            jsonObject = new JSONObject(response);
            System.out.println(response+"pci response");
            String message = jsonObject.getString("message");
            String status = jsonObject.getString("status");
            String pciMesssge = switchValue.equals("on") ? "PCI feature turned on" : "PCI feature turned off";
            if (message.contains(pciMesssge) && status.equals("success")) {
                System.out.println("PCI switched successfully");
            } else {
                throw new AssertionError("Failed to switch PCI. Response: " + response);

            }
            Thread.sleep(500);
        } catch (IOException | InterruptedException e) {
            throw new AssertionError("Failed to switch PCI: " + e.getMessage());
        }

    }

}

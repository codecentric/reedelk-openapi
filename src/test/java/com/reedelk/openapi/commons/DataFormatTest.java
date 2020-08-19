package com.reedelk.openapi.commons;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

class DataFormatTest {

    @Test
    void shouldCorrectlyDumpLinkedHashMapObject() {
        // Given
        Map<String, Object> shipment = new LinkedHashMap<>();
        shipment.put("id", "aabbcc");
        shipment.put("details", "FedEx");

        // When
        String jsonData = DataFormat.JSON.dump(shipment);

        // Then
        assertEquals("{\n" +
                "  \"details\": \"FedEx\",\n" +
                "  \"id\": \"aabbcc\"\n" +
                "}", jsonData, STRICT);
    }

    @Test
    void shouldCorrectlyDumpLinkedHashMapObjectWithNestedMap() {
        // Given
        Map<String, Object> shipmentDetails = new LinkedHashMap<>();
        shipmentDetails.put("carrier", "FedEx");
        shipmentDetails.put("time", "Morning");

        Map<String, Object> shipment = new LinkedHashMap<>();
        shipment.put("id", "aabbcc");
        shipment.put("details", shipmentDetails);

        // When
        String jsonData = DataFormat.JSON.dump(shipment);

        // Then
        assertEquals("{\n" +
                "  \"details\": {\n" +
                "    \"carrier\": \"FedEx\",\n" +
                "    \"time\": \"Morning\"\n" +
                "  },\n" +
                "  \"id\": \"aabbcc\"\n" +
                "}", jsonData, STRICT);
    }

    @Test
    void shouldCorrectlyDumpArrayAsJson() {
        // Given
        Map<String, Object> shipment1Details = new LinkedHashMap<>();
        shipment1Details.put("carrier", "FedEx");

        Map<String, Object> shipment1 = new LinkedHashMap<>();
        shipment1.put("id", "aabbcc");
        shipment1.put("details", shipment1Details);

        List<Map<String, Object>> shipments = new ArrayList<>();
        shipments.add(shipment1);

        // When
        String jsonData = DataFormat.JSON.dump(shipments);

        // Then
        assertEquals("[{\n" +
                "  \"details\": {\"carrier\": \"FedEx\"},\n" +
                "  \"id\": \"aabbcc\"\n" +
                "}]", jsonData, STRICT);
    }
}

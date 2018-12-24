import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class Utilities {

    public static JsonObject replaceProperty(JsonObject obj, String keyMain, String newValue) throws Exception {
        JsonObject lotJsonPayload = new JsonObject();
        JSONObject lotJsonPayload2 = new JSONObject(obj.toString());
        lotJsonPayload = replaceProperty_bfrconversion(lotJsonPayload2, keyMain, newValue);
        return lotJsonPayload;

    }

    public static JsonObject replaceProperty_bfrconversion(JSONObject obj, String keyMain, String newValue)
            throws Exception {
        // We need to know keys of Jsonobject

        JSONObject json = new JSONObject();
        Iterator iterator = obj.keys();
        String key = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            // if object is just string we change value in key
            if ((obj.optJSONArray(key) == null) && (obj.optJSONObject(key) == null)) {
                // if ((key.equals(keyMain)) && (obj.get(key).toString().equals(valueMain))) {
                if ((key.equals(keyMain))) {
                    // put new value
                    obj.put(key, newValue);
                    JsonElement nlotJsonPayload = new JsonObject();
                    JsonParser js = new JsonParser();
                    nlotJsonPayload = js.parse(obj.toString());

                    return nlotJsonPayload.getAsJsonObject();
                }
            }

            // if it's jsonobject
            if (obj.optJSONObject(key) != null) {
                replaceProperty_bfrconversion(obj.getJSONObject(key), keyMain, newValue);
            }

            // if it's jsonarray
            if (obj.optJSONArray(key) != null) {
                JSONArray jArray = obj.getJSONArray(key);
                for (int i = 0; i < jArray.length(); i++) {
                    replaceProperty_bfrconversion(jArray.getJSONObject(i), keyMain, newValue);
                }
            }
        }
        // return obj;

        JsonElement nlotJsonPayload = new JsonObject();
        JsonParser js = new JsonParser();
        nlotJsonPayload = js.parse(obj.toString());

        return nlotJsonPayload.getAsJsonObject();
    }

  /*  try {
        //responseEntity = requestProcessor.sendGetRequest(endpoint, path, queryParams, headers, true, TokenType.employee);
        ReportSteps.VERIFY("Status Code", "200", responseEntity.getStatusCode().toString());
        Utils.addResponseToReport(responseEntity.getBody(), FileType.json);
        String strResponse = responseEntity.getBody().toString();
        strResponse = "{\"docs\":" + strResponse + "}";
        JsonParser parser = new JsonParser();
        JsonObject responseObj1 = parser.parse(strResponse).getAsJsonObject();
        //System.out.println(strResponse);


        JsonArray docs = responseObj1.getAsJsonArray("docs");
        int arrCount = docs.size();
        for (int j = 0; j < arrCount; j++) {
            finObjType = docs.get(j).getAsJsonObject().get("finObjType").toString();
            finObjType = finObjType.replace("\"", "");
            documentId = docs.get(j).getAsJsonObject().get("documentId").toString();
            if (finObjType.equals("REMINDER")) {
                k = k + 1;
                url = docs.get(j).getAsJsonObject().get("url").toString();
                url = url.replace("\"", "");
            }
        }
        ReportSteps.INFO("Number of Reminders generated---" + k, true);
        if (event_type.equals("MBRLTSLD")) {
            if (k != 1) {
                flag = false;
            } else {
                flag = true;
            }
        } else {
            if (k != 3) {
                flag = false;
            } else {
                flag = true;
            }
        }

    } catch (HttpServerErrorException | HttpClientErrorException e) {
        ReportSteps.FAIL(e.getResponseBodyAsString());
    } */
}

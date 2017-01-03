package org.wikipedia.bridge;

import org.json.JSONException;
import org.json.JSONObject;

public class NightModeHandler {
    private final CommunicationBridge bridge;

    /**
     * @param bridge The bridge used to communicate with the WebView
     */
    public NightModeHandler(CommunicationBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * Returns the JSON used as payload for JS messages to turn night mode on/off
     *
     * @param hasPageLoaded Specify is the page has already been loaded or not.
     *                      If it has been loaded, inline style inversion is done by JS.
     *                      If not, it is handled by the transforms.
     * @return JSON used as payload for JS messages to turn night mode on or off
     */
    private JSONObject getPayload(boolean hasPageLoaded) {
        JSONObject payload = new JSONObject();
        try {
            payload.put("hasPageLoaded", hasPageLoaded);
            payload.put("nightStyleURL", "file:///android_asset/night.css");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }

    /**
     * Turn on Night Mode
     * @param hasPageLoaded Specify is the page has already been loaded or not.
     */
    public void turnOn(boolean hasPageLoaded) {
        bridge.sendMessage("toggleNightMode", getPayload(hasPageLoaded));
    }
}
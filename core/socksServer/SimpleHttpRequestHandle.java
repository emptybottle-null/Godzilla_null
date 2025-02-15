/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.socksServer;

import com.httpProxy.server.request.HttpRequest;
import com.httpProxy.server.response.HttpResponse;
import com.httpProxy.server.response.HttpResponseStatus;
import core.socksServer.HttpRequestHandle;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import util.functions;

public class SimpleHttpRequestHandle
implements HttpRequestHandle {
    @Override
    public HttpResponse sendHttpRequest(HttpRequest httpRequest) {
        HttpResponse ret = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(httpRequest.getUrl()).openConnection();
            httpURLConnection.setRequestMethod(httpRequest.getMethod());
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            List<String[]> headers = httpRequest.getHttpRequestHeader().getHeaders();
            for (int i = 0; i < headers.size(); ++i) {
                String[] hk = headers.get(i);
                httpURLConnection.setRequestProperty(hk[0], hk[1]);
            }
            httpURLConnection.getOutputStream().write(httpRequest.getRequestData());
            httpURLConnection.getOutputStream().flush();
            HttpResponse httpResponse = new HttpResponse(new HttpResponseStatus(httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage()));
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            for (String next : headerFields.keySet()) {
                if (next == null) continue;
                List<String> values = headerFields.get(next);
                for (int i = 0; i < values.size(); ++i) {
                    String v = values.get(i);
                    httpResponse.getHttpResponseHeader().addHeader(next, v);
                }
            }
            httpResponse.setResponseData(functions.readInputStream(httpURLConnection.getInputStream()));
            ret = httpResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}


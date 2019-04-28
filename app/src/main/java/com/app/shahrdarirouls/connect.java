package com.app.shahrdarirouls;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class connect {


    public static void downloadFile(String fileUrl, final File directory) {
//
//        try {
//            URL u = new URL(fileUrl);
//            URLConnection conn = u.openConnection();
//            int contentLength = conn.getContentLength();
//
//            DataInputStream stream = new DataInputStream(u.openStream());
//
//            byte[] buffer = new byte[contentLength];
//            stream.readFully(buffer);
//            stream.close();
//
//            DataOutputStream fos = new DataOutputStream(new FileOutputStream(directory));
//            fos.write(buffer);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            return; // swallow a 404
//        } catch (IOException e) {
//            return; // swallow a 404
//        }
//
//    }


        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[totalSize];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
//        extends Request<byte[]> {
//    private final Response.Listener<byte[]> mListener;
//    private Map<String, String> mParams;
//
//    //create a static map for directly accessing headers
//    public Map<String, String> responseHeaders ;
//
//    public connect(int method, String mUrl ,Response.Listener<byte[]> listener,
//                                    Response.ErrorListener errorListener, HashMap<String, String> params) {
//        // TODO Auto-generated constructor stub
//
//        super(method, mUrl, errorListener);
//        // this request would never use cache.
//        setShouldCache(false);
//        mListener = listener;
//        mParams=params;
//    }
//
//    @Override
//    protected Map<String, String> getParams()
//            throws com.android.volley.AuthFailureError {
//        return mParams;
//    };
//
//
//    @Override
//    protected void deliverResponse(byte[] response) {
//        mListener.onResponse(response);
//    }
//
//    @Override
//    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
//
//        //Initialise local responseHeaders map with response headers received
//        responseHeaders = response.headers;
//
//        //Pass the response data here
//        return Response.success( response.data, HttpHeaderParser.parseCacheHeaders(response));
//    }
//}






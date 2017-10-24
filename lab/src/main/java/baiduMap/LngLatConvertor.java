package baiduMap;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class LngLatConvertor {
	public static boolean sendReqMsg(){  
         boolean flag=false;
         String token = "aUoRtZCHgO6B1gqebx0XLGoG9hl2Ghce";
         try {  
             CloseableHttpClient httpclient = HttpClients.createDefault();  
             HttpPost httpPost= new HttpPost("http://api.map.baidu.com/geoconv/v1/?from=1&to=5&ak=" + token
             + "&coords=114.21892734521,29.575429778924;114.21892734521,29.575429778924");  
             // Create a custom response handler  
            ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {  
                //对访问结果进行处理  
                public JSONObject handleResponse(  
                        final HttpResponse response) throws ClientProtocolException, IOException {  
                    int status = response.getStatusLine().getStatusCode();  
                    if (status >= 200 && status < 300) {  
                        HttpEntity entity = response.getEntity();  
                        if(null!=entity){  
                            String result= EntityUtils.toString(entity);
                            System.out.println("fdskfha---" + result);
                            //根据字符串生成JSON对象  
                            JSONObject resultObj = new JSONObject(result);
                            JSONArray jsonArray = (JSONArray) resultObj.get("result");
                            
                            for(int i = 0; i < jsonArray.length(); i++){
                            	System.out.println(((JSONObject)jsonArray.get(i)).get("x"));
                            }
                            
//                            return resultObj; 
                            return null;
                        }else{  
                            return null;  
                        }  
                    } else {  
                        throw new ClientProtocolException("Unexpected response status: " + status);  
                    }  
                }  
  
            };  
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);  
            httpclient.close();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
         return flag;  
    }
	public static void main(String[] args){
		sendReqMsg();
	}
}

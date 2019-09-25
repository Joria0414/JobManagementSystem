package com.ics.employeemanager.face;

import java.util.ArrayList;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;

//人脸识别——单例模式（一种设计模式）
public class FaceClient {
	private static volatile FaceClient faceClient;
	 private AipFace client =null;
	 //私有构造方法，只能由本例调用
	 private FaceClient(String APP_ID, String API_KEY, String SECRET_KEY) {
		 client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
	 }
	 //公有静态方法返回对象
	 public static FaceClient getInstance() {
		 //我的账户信息
		 String APP_ID="16806132";
		 String API_KEY="hzTpOliD7iRLR6OGq5B9QN5A";
		 String SECRET_KEY="coGRojQ8WBXfGvl1cP0ejp527lMRBdH9";
      if (faceClient == null) {
          synchronized (FaceClient.class) {
              if (faceClient == null) {
           	   faceClient = new FaceClient(APP_ID, API_KEY, SECRET_KEY);
              }}}
      return faceClient;
	 }
	 
	 public boolean faceContrast(String image1,String image2) {
		 // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
		    MatchRequest req1 = new MatchRequest(image1, "BASE64");
		    MatchRequest req2 = new MatchRequest(image2, "BASE64");
		    ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
		    requests.add(req1);
		    requests.add(req2);
		    JSONObject res = client.match(requests);
		    System.out.println(res.toString());
		    Object object=res.get("result");
		    System.out.println("cccc=="+object);
		    if(object==null || object.toString().equals("null")){
		    	return false;
		    }else{
		    	res=(JSONObject) object;
			    double result=res.getDouble("score");
			    //相似度大于90则登录成功
			    if(result>90){
			    	return true;
			    }else{
			    	return false;
			    }
		    }}

}


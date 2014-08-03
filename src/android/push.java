package android;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class push {
	public void sendMessage(ArrayList<String> aList, String masseage) throws IOException {
		Sender sender = new Sender("AIzaSyBedluAUpTDzU3zPw9qjkp-otWKcBWHXVQ");
		String regId = "APA91bGiT2MCn8p1g1XmvDqROZltbsybEFTpYZTmdToJO_OUrIfNi96o8Mav_4QIfjwJAF1AIgl9lb5bnu8_GRspeaiy6WKO9siD8KVW_018xqQEUPYLc4Pw52YA0mAruVSJNA9narb0v4iFLoWmdGRL6JwfsfP-ZA";
		String msg = URLEncoder.encode(masseage, "euc-kr");
		System.out.println(msg);
		Message message = new Message.Builder().addData("msg", msg).build();
		List<String> list = aList;
		MulticastResult multiResult = sender.send(message, list, 5);
		if (multiResult != null) {
			List<Result> resultList = multiResult.getResults();
			for (Result result : resultList) {
				System.out.println(result.getMessageId());
			}
		}
	}
}
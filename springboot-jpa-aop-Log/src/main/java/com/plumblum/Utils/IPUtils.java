package com.plumblum.Utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther: cpb
 * @Date: 2018/8/30 09:28
 * @Description:
 */
public class IPUtils{
        public IPUtils() {
        }

        public static String getIp() {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException var1) {
                var1.printStackTrace();
                return null;
            }
        }

        public static String getIp(HttpServletRequest httpRequest) {
            String remoteAddress = httpRequest.getRemoteAddr();
            if (remoteAddress.equals("0:0:0:0:0:0:0:1") || remoteAddress.equals("127.0.0.1")) {
                try {
                    remoteAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

            return remoteAddress;
        }
}

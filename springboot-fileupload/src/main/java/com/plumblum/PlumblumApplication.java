package com.plumblum;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlumblumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlumblumApplication.class, args);
	}

	//Tomcat large file upload connection reset
//	上传文件大于10M出现连接重置的问题。此异常内容GlobalException也捕获不到。
//	@Bean
//	public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
//		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
//			if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
//				//-1 means unlimited
//				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
//			}
//		});
//		return tomcat;
//	}

}

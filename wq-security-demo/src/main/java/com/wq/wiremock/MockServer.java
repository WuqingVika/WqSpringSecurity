package com.wq.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wuqingvika
 *
 */
public class MockServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
        System.out.println("这里一直报 java.lang.ClassNotFoundException: org.apache.http.HttpEntity按理说该导的包都导了 真心不会玩 心里苦");
        configureFor(8062); //官网使用参考 http://wiremock.org/docs/running-standalone/
		//java -jar wiremock-standalone-2.19.0.jar --port 8062
		removeAllMappings();

		mock("/order/1", "01");
		mock("/order/2", "02");
	}

	private static void mock(String url, String file) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
	}

}

package com.ohmyclass.server.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix = "constants.api.urls")
@Configuration
@Getter
@Setter
public class ApiProperties {

	private String baseurl;

	private String loginurl;

	private String registerurl;

	private String refreshurl;

	private String forgottenurl;

	private String usergeturl;

	private String userupdateurl;

	private String userdeleteurl;

	private List<String> uriwhitelist;
}

package com.github.catapan.microprofile;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

@Path("/mp-config")
public class MPConfigResource {

	@Inject
	Config config;

	@GET
	@Path("config-sources")
	@Produces(MediaType.TEXT_PLAIN)
		public String getConfigSources() {
			config = ConfigProvider.getConfig();
			Iterable<ConfigSource> configSources = config.getConfigSources();
			StringBuilder sb = new StringBuilder();
			for (ConfigSource configSource : configSources) {
				sb.append("NOME: ").append(configSource.getName())
					.append("\nORDINAL: ").append(configSource.getOrdinal())
					.append("\nPROPERTYNAMES: ").append(configSource.getPropertyNames())
					.append("\n\n");
			}
			return sb.toString();
	}
}
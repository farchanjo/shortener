package br.eti.archanjo.velociraptor.services;


import br.eti.archanjo.velociraptor.configs.PropertiesConfig;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetAddress;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GeoIPService {
    private static Logger logger = LoggerFactory.getLogger(GeoIPService.class);
    private DatabaseReader reader;

    @Autowired
    public GeoIPService(PropertiesConfig config) {
        try {
            reader = new DatabaseReader.Builder(new java.io.File(config.getGeoip().getDbPath())).build();
        } catch (IOException e) {
            logger.error("GepIPService{constructo}", e);
        }
    }

    /**
     * @param ip {@link String}
     * @return {@link CountryResponse}
     */
    public CountryResponse parse(String ip) throws IOException, GeoIp2Exception {
        InetAddress address = InetAddress.getByName(ip);
        return reader.country(address);
    }

    @PreDestroy
    private void shutDown() throws IOException {
        reader.close();
    }

}

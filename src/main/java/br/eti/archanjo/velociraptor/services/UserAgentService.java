package br.eti.archanjo.velociraptor.services;


import br.eti.archanjo.velociraptor.configs.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.udger.parser.UdgerIpResult;
import org.udger.parser.UdgerParser;
import org.udger.parser.UdgerUaResult;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserAgentService {
    private UdgerParser udgerParser;

    private final PropertiesConfig config;

    @Autowired
    public UserAgentService(PropertiesConfig config) {
        this.config = config;
    }

    @PostConstruct
    private void createParser() {
        udgerParser = new UdgerParser(config.getUdger().getDbPath());
    }

    /**
     * @param ip {@link String}
     * @return {@link UdgerIpResult}
     * @throws SQLException
     * @throws UnknownHostException
     */
    public UdgerIpResult parseIp(String ip)
            throws SQLException, UnknownHostException {
        return udgerParser.parseIp(ip);
    }

    /**
     * @param ua {@link String}
     * @return {@link UdgerUaResult}
     * @throws SQLException
     * @throws UnknownHostException
     */
    public UdgerUaResult parseUa(String ua)
            throws SQLException, UnknownHostException {
        return udgerParser.parseUa(ua);
    }

    @PreDestroy
    private void shutDown() throws IOException {
        udgerParser.close();
    }
    
}

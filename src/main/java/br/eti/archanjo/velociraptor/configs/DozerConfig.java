package br.eti.archanjo.velociraptor.configs;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DozerConfig {
    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerMapper() {
        List<String> mappingFiles = Arrays.asList(
                "dozer-global-configuration.xml",
                "dozer-bean-mappings.xml"
        );

        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }
}

package br.eti.archanjo.velociraptor.pojo;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UdgerIp implements Serializable {
    private static final long serialVersionUID = -4499007724004168507L;
    private int ipVer = 0;
    private String ipClassification;
    private String ipClassificationCode;
    private String ipLastSeen;
    private String ipHostname;
    private String ipCountry;
    private String ipCountryCode;
    private String ipCity;
    private String crawlerName;
    private String crawlerVer;
    private String crawlerVerMajor;
    private String crawlerFamily;
    private String crawlerFamilyCode;
    private String crawlerFamilyHomepage;
    private String crawlerFamilyVendor;
    private String crawlerFamilyVendorCode;
    private String crawlerFamilyVendorHomepage;
    private String crawlerFamilyIcon;
    private String crawlerFamilyInfoUrl;
    private String crawlerLastSeen;
    private String crawlerCategory;
    private String crawlerCategoryCode;
    private String crawlerRespectRobotstxt;

    // DATACENTER
    private String dataCenterName;
    private String dataCenterNameCode;
    private String dataCenterHomePage;
}

package br.eti.archanjo.velociraptor.pojo;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UdgerUa implements Serializable {
    private static final long serialVersionUID = -4499007724004168507L;
    private String uaString;
    private Integer clientId;
    private Integer classId;
    private String uaClass;
    private String uaClassCode;
    private String ua;
    private String uaEngine;
    private String uaVersion;
    private String uaVersionMajor;
    private String crawlerLastSeen;
    private String crawlerRespectRobotstxt;
    private String crawlerCategory;
    private String crawlerCategoryCode;
    private String uaUptodateCurrentVersion;
    private String uaFamily;
    private String uaFamilyCode;
    private String uaFamilyHomepage;
    private String uaFamilyIcon;
    private String uaFamilyIconBig;
    private String uaFamilyVendor;
    private String uaFamilyVendorCode;
    private String uaFamilyVendorHomepage;
    private String uaFamilyInfoUrl;
    private String osFamily;
    private String osFamilyCode;
    private String os;
    private String osCode;
    private String osHomePage;
    private String osIcon;
    private String osIconBig;
    private String osFamilyVendor;
    private String osFamilyVendorCode;
    private String osFamilyVedorHomepage;
    private String osInfoUrl;
    private String deviceClass;
    private String deviceClassCode;
    private String deviceClassIcon;
    private String deviceClassIconBig;
    private String deviceClassInfoUrl;
    private String deviceMarketname;
    private String deviceBrand;
    private String deviceBrandCode;
    private String deviceBrandHomepage;
    private String deviceBrandIcon;
    private String deviceBrandIconBig;
    private String deviceBrandInfoUrl;
}

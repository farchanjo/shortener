package br.eti.archanjo.velociraptor.utils;

import br.eti.archanjo.velociraptor.entities.mongo.RequestEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RandomUtils {

    private static List<String> uas = Arrays.asList(
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; 2345Explorer 5.0.0.14136)",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/7.1.0.12633",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.103 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");

    private static List<String> ips = Arrays.asList(
            "34.230.0.90", "105.168.16.75", "201.7.34.197", "186.232.43.19",
            "191.180.137.3", "157.55.39.103", "89.154.227.217", "197.235.7.14",
            "89.154.227.217", "190.15.97.38", "157.55.39.103", "191.180.137.3",
            "190.15.97.38", "187.106.236.5", "89.154.227.217", "177.140.25.220",
            "191.180.137.3", "179.72.193.254", "196.210.39.85", "190.15.97.38",
            "191.180.137.3", "89.154.227.217", "173.252.115.10", "177.140.25.220",
            "138.97.226.90", "172.18.149.1", "191.180.137.3", "66.249.66.210",
            "177.179.118.71", "187.106.25.62", "177.140.25.220", "200.158.178.192",
            "157.55.39.103", "177.140.25.220", "187.106.25.62", "157.55.39.103",
            "187.106.236.5", "157.55.39.112", "187.106.236.5", "77.88.47.34",
            "177.140.25.220", "187.106.236.5", "157.55.39.103", "157.55.39.112",
            "200.158.178.192", "190.15.97.38", "105.168.16.75", "162.243.23.83",
            "190.15.97.38", "157.55.39.112", "190.15.97.38", "77.88.5.9",
            "190.15.97.38", "157.55.39.139", "51.255.66.127", "157.55.39.139",
            "162.243.23.83", "157.55.39.103", "191.180.137.3");

    private static List<String> referrers = Arrays.asList("facebook.com", "google.com", "github.com");
    private static List<Long> urlsIds = Arrays.asList(1L, 2L, 3L);
    private static List<Long> domainsId = Collections.singletonList(1L);
    private static List<String> domains = Collections.singletonList("sk.io");
    private static List<String> shorts = Arrays.asList("847d1l", "haha23", "hauah");

    public static String getUa() {
        int random = org.apache.commons.lang.math.RandomUtils.nextInt(uas.size());
        return uas.get(random);
    }

    public static String getIp() {
        int random = org.apache.commons.lang.math.RandomUtils.nextInt(ips.size());
        return ips.get(random);
    }

    public static String getReferrer() {
        int random = org.apache.commons.lang.math.RandomUtils.nextInt(referrers.size());
        return referrers.get(random);
    }

    public static Long getDomainId() {
        int random = org.apache.commons.lang.math.RandomUtils.nextInt(domainsId.size());
        return domainsId.get(random);
    }

    public static Long getUrlId() {
        int random = org.apache.commons.lang.math.RandomUtils.nextInt(urlsIds.size());
        return urlsIds.get(random);
    }

    public static String getDomains() {
        int random = org.apache.commons.lang.math.RandomUtils.nextInt(domains.size());
        return domains.get(random);
    }

    public static RequestEntity getRequest(Date date) {
        RequestEntity.RequestEntityBuilder builder = RequestEntity.builder();
        if (org.apache.commons.lang.math.RandomUtils.nextInt(300) < 100) {
            builder.referrer(getReferrer());
        }
        Long urlId = getUrlId();
        builder.userAgent(getUa());
        builder.ip(getIp());
        builder.urlId(urlId);
        builder.shortValue(shorts.get(urlId.intValue() - 1));
        builder.domainId(getDomainId());
        builder.domain(getDomains());
        builder.created(date);
        return builder.build();
    }

    public static RequestEntity getRequest() {
        return getRequest(new Date());
    }
}

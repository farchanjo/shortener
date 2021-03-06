package br.eti.archanjo.velociraptor.entities.mongo;

import br.eti.archanjo.velociraptor.pojo.UdgerIp;
import br.eti.archanjo.velociraptor.pojo.UdgerUa;
import lombok.*;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Builder
@Document(collection = "requests")
public class RequestEntity implements Serializable {
    private static final long serialVersionUID = 8693690212914406584L;

    @Field("useragent")
    private String userAgent;
    @Field("ip")
    private String ip;
    @Indexed
    @Field("urlId")
    private Long urlId;
    @Indexed
    @Field("domainId")
    private Long domainId;
    @Field("shortValue")
    private String shortValue;
    @Field("domain")
    private String domain;
    @Field("referrer")
    private String referrer;
    @Field("ua")
    private UdgerUa ua;
    @Field("uip")
    private UdgerIp uip;
    @Field("created")
    @Indexed(direction = IndexDirection.ASCENDING, expireAfterSeconds = 15228000)
    private Date created;

    @Override
    public String toString() {
        return "RequestEntity{" +
                "userAgent='" + userAgent + '\'' +
                ", ip='" + ip + '\'' +
                ", urlId=" + urlId +
                ", domainId=" + domainId +
                ", shortValue='" + shortValue + '\'' +
                ", domain='" + domain + '\'' +
                ", referrer='" + referrer + '\'' +
                ", created=" + created +
                '}';
    }
}

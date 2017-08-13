package br.eti.archanjo.velociraptor.pojo;

import lombok.*;

import java.io.Serializable;
import java.net.URI;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class Request implements Serializable {
    private static final long serialVersionUID = -5330620870398154170L;
    private String userAgent;
    private URI uri;
    private String ip;
}

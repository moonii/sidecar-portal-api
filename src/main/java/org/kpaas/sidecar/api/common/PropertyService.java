package org.kpaas.sidecar.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("sPropertyService")
@Data
@EqualsAndHashCode(callSuper=true)
public class PropertyService extends org.container.platform.api.common.PropertyService {

    @Value("${sidecarApi.url}")
    private String sidecarApiUrl;
}

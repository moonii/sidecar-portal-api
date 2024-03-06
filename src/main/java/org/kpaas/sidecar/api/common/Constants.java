package org.kpaas.sidecar.api.common;

public class Constants extends org.container.platform.api.common.Constants{

    public static final String TARGET_SIDECAR_API = "sidecarApi";
    public static final String URI_SIDECAR_API_WHOAMI = "/whoami";
    public static final String URI_SIDECAR_API_PREFIX = "sidecar";
    public static final String[] SIDECAR_PERMIT_PATH_LIST = new String[]{ URI_SIDECAR_API_WHOAMI, "/sidecar", "/logout"};

}

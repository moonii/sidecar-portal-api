package org.kpaas.sidecar.api.exception;

import org.container.platform.api.exception.BaseBizException;

/**
 * Container Platform Exception Model 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2020.08.24
 **/
public class SidecarException extends BaseBizException {
	private static final long serialVersionUID = -1288712633779609678L;

	public SidecarException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public SidecarException(String errorCode, String errorMessage, int statusCode, String detailMessage) {
		super(errorCode, errorMessage, statusCode, detailMessage);
	}

	public SidecarException(String errorMessage) {
		super(errorMessage);
	}
}

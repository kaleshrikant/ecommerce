package com.alibou.ecommece.exception.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}

package com.devteria.identity.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class AuthenticationRequestInterceptor implements RequestInterceptor {
    /**
     * - modifier request trước khi gửi đi
     * - authHeader có giá trị thì thêm vào header request trước khi gửi đi
     * - khi implement 1 RequestInterceptor.class thì FeignClient quét có bean này thì nó tự động sử dụng.
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        var authHeader = servletRequestAttributes.getRequest().getHeader("Authorization");
        log.info("Header: {}", authHeader);

        if (StringUtils.hasText(authHeader))
            requestTemplate.header("Authorization", authHeader);
    }
}

package com.imooc.youtube.api.support;

import com.imooc.youtube.domain.exception.ConditionException;
import com.imooc.youtube.service.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Component
public class UserSupport {
    public Long getCurrentUserId(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if(userId < 0){
            throw new ConditionException("illegal user.");
        }
        return userId;
    }
}

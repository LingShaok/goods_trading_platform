package com.lingshao.secondhand.shiro;

import com.lingshao.annotation.CurrentUser;
import com.lingshao.secondhand.Exception.UnauthorizedException;
import com.lingshao.secondhand.user.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @description 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 * @author LingShao
 * @date 2021/4/18
 * @param @param null:
 * @return
 */

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = (User) webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
        if (user == null) {
            throw new UnauthorizedException("当前用户无访问权限，未登录");
        }
        return user;
    }
}

package lt.tomasbarauskas.blog.utiles;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> customErrorAttributes = super.getErrorAttributes(webRequest, options);
        customErrorAttributes.put("customErrorAttribute", "Custom error text - page not found");

        return customErrorAttributes;
    }
}

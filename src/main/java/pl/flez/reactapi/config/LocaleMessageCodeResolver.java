package pl.flez.reactapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.validation.DefaultMessageCodesResolver;

import java.util.Locale;

@RequiredArgsConstructor
public class LocaleMessageCodeResolver extends DefaultMessageCodesResolver {

    private final MessageSource messageSource;

    @Override
    public String[] resolveMessageCodes(String errorCode, String objectName, String field, Class<?> fieldType) {
        final String[] errors = super.resolveMessageCodes(errorCode, objectName, field, fieldType);
        errors[0] = messageSource.getMessage(errors[0],new Object[]{}, Locale.forLanguageTag("pl"));
        return errors;
    }
}

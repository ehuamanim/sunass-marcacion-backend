package pe.gob.sunass.marcacion.httpconnection.facade;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pe.gob.sunass.marcacion.constant.SecretsEnum;
import pe.gob.sunass.marcacion.httpconnection.Secret;
import pe.gob.sunass.marcacion.httpconnection.SecretList;

public class SecretFacade {

    public static SecretList parseToList( String jsonArray ){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Secret>>() {}.getType();
        List<Secret> secrets = gson.fromJson(jsonArray, listType);
        return new SecretList(secrets);
    }

    public static Secret getSecretFromName( SecretList list, String secretName ){
        return list
            .getData()
            .stream()
            .filter( ic -> ic.getSecretName().equals(secretName) )
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No se encuentra el secretName: " + secretName));
    }

    public static String getValueFromName( SecretList list, SecretsEnum secretName ){
        return getSecretFromName(list, secretName.value())
            .getSecretValue();
    }

}
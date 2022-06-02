package StepDefinitions;
import ApiClient.ApiClient;
import Utils.Configuration;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

public class StepDefinition extends ApiClient {
    int id;
    JSONObject lastChar;
    String lastEp;
    String expectedSpecies;
    String actualSpecies;
    String expectedLocation;
    String actualLocation;
    JSONObject MS;
    JSONObject lastEpWithMorty;

    @Когда("^Находим Morty Smith по id$")
    public void НаходимMortySmithПоId() {
        MS = getCharByName(Configuration.getConfigurationValue("TestCharacter"));
        id = MS.getInt("id");
    }

    @И("^Выбираем последний эпизод где появился Морти$")
    public void ВыбираемПоследнийЭпизодГдеПоявилсяМорти() {
        expectedSpecies = MS.get("species").toString();
        expectedLocation = MS.getJSONObject("location").get("name").toString();
        JSONArray jsonArray = MS.getJSONArray("episode");
        lastEp = jsonArray.get(jsonArray.length() - 1).toString();
        lastEpWithMorty = new JSONObject(getObj(lastEp).getBody().asString());
    }

    @И("^Получаем последнего персонажа из последнего эпизода$")
    public void получаемПоследнегоПерсонажаИзПоследнегоЭпизода() {
        JSONArray jsonArrayEp = lastEpWithMorty.getJSONArray("characters");
        String lastCharLink = jsonArrayEp.get(jsonArrayEp.length() - 1).toString();
         lastChar = new JSONObject(getObj(lastCharLink).getBody().asString());

    }

    @И("^Сохраняем расу и местонахождения последнего персонажа из последнего эпизода$")
    public void сохраняемРасуИМестонахожденияПоследнегоПерсонажаИзПоследнегоЭпизода() {
        actualSpecies = lastChar.get("species").toString();
        actualLocation = lastChar.getJSONObject("location").get("name").toString();
    }

    @Тогда("Сравниваем расу Морти с расой последнего персонажа из последнего эпизода")
    public void сравниваемРасуМортиСРасойПоследнегоПерсонажаИзПоследнегоЭпизода() {
        Assertions.assertEquals(expectedSpecies,actualSpecies,"Не совпадает");
    }

    @Тогда("Сравниваем местоположение Морти с местоположением последнего персонажа из последнего эпизода")
    public void сравниваемМестоположениеМортиСМестоположениемПоследнегоПерсонажаИзПоследнегоЭпизода() {
        Assertions.assertEquals(expectedLocation,actualLocation, "Не совпадает");
    }

}


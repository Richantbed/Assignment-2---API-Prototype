import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CryptoController {

    @GetMapping("/crypto")
    public CryptoPrice getBitcoinPrice() {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // Parse JSON using org.json library
        JSONObject jsonObject = new JSONObject(response);
        double price = jsonObject.getJSONObject("bitcoin").getDouble("usd");

        // Populate CryptoPrice object
        CryptoPrice cryptoPrice = new CryptoPrice();
        cryptoPrice.setName("Bitcoin");
        cryptoPrice.setPrice(price);

        return cryptoPrice;
    }
}

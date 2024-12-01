package mg.itu.rh.service.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class JourFerieService {
    private static final Logger LOGGER           = LoggerFactory.getLogger(JourFerieService.class);
    private static final String API_KEY          = "8Aw6I2ChiUamhNbPqroZdKocuGBdGrgg";
    private static final String API_URL_TEMPLATE = "https://calendarific.com/api/v2/holidays?api_key=%s&country=MG&year=%s";

    private final ObjectMapper objectMapper;

    @Cacheable("jours_feries")
    public List<LocalDate> getAllForYear(int annee) {
        String url = String.format(API_URL_TEMPLATE, API_KEY, annee);
        LOGGER.info("Fetch des jours fériés à partir de l'url : {}", url);

        try {
            JsonNode holidaysJsonNode = objectMapper.readTree(HttpClient.newHttpClient().send(
                HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build(), HttpResponse.BodyHandlers.ofString())
                .body()).path("response").path("holidays");

            List<LocalDate> joursFeries = new ArrayList<>();
            if (holidaysJsonNode.isArray()) for (JsonNode holidayJsonNode : holidaysJsonNode)
                try {
                    joursFeries.add(LocalDate.parse(holidayJsonNode.path("date").path("iso").asText()));
                } catch (DateTimeParseException ignored) { }

            return joursFeries;
        } catch (IOException | InterruptedException e) {
            String message = String.format("Erreur lors de la récupération des jours fériés pour l'année : %d", annee);

            LOGGER.error(message);
            throw new RuntimeException(message, e);
        }
    }
}

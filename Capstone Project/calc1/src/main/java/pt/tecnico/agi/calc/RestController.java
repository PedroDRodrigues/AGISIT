package pt.tecnico.agi.calc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/calc1")
@Slf4j
public class RestController {

    @Autowired
    WebClient storage;

    @GetMapping(value = "/healthz")
    public ResponseEntity<HttpStatus> healthz(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/add")
    public float add(float a, float b) {
        float result = a + b;
        log.info("Sending add operation to storage...");
        String res = storage
                .post()
                .uri("/api/storage/create")
                .bodyValue(new Operation(fmt(a), fmt(b), "+", fmt(result)))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Received response from storage: {}", res);
        return result;
    }

    @CrossOrigin
    @GetMapping("/sub")
    public float sub(float a, float b) {
        float result = a - b;
        log.info("Sending subtract operation to storage...");
        String res = storage.post().uri("/api/storage/create")
                .bodyValue(new Operation(fmt(a), fmt(b), "-", fmt(result)))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Received response from storage: {}", res);
        return result;
    }

    public static String fmt(float d) {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}

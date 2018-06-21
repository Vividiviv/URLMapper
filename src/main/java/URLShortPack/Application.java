package URLShortPack;

import URLShortPack.Service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
    MapService mapService;
    public static void main(String args[]){
        SpringApplication.run(Application.class,args);
    }
}

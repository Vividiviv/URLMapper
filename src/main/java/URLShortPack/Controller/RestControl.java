package URLShortPack.Controller;

import URLShortPack.Model.URLMapping;
import URLShortPack.Repositories.KeyURLRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/go")
public class RestControl {
    private KeyURLRepos keyURLRepos;

    @Autowired
    public RestControl(KeyURLRepos keyURLRepos){
        this.keyURLRepos=keyURLRepos;
    }

    @RequestMapping(value = "/AddMapping/{Key}/{Protocol}/{URL}", method = RequestMethod.GET)
    public String addURLMapping(@PathVariable("Key") String key,@PathVariable("Protocol") String protocol,@PathVariable("URL") String url){
//        if(!isValid(""+protocol+"//"+url))
//            return "The given url is not valid";
        URLMapping urlMapping = new URLMapping();
        urlMapping.setKey(key.toLowerCase());
        urlMapping.setUrl(url);
        urlMapping.setProtocol(protocol);
        Boolean result_Key= keyURLRepos.exists(key);
        Boolean result_URL= keyURLRepos.exists(url);
        if(!(result_Key||result_URL)){
            keyURLRepos.save(urlMapping);
            String response ="The request for addition of URL Mapping :\n KEY :"+key+"\n URL :"+url+"\n is successfully processed.";
            return response;
        }
        if(result_Key)
        {
            String response ="The given key has been used for mapping.";
            return response;
        }
        if(result_URL)
        {
            String response ="The given URL has been used for mapping";
            return response;
        }
        return "Never_sent";
    }

    @RequestMapping(value = "/OverwriteMapping/{Key}/{Protocol}/{URL}", method = RequestMethod.GET)
    public String overwriteMap(@PathVariable("Key") String key,@PathVariable("Protocol") String protocol,@PathVariable("URL") String url){
        URLMapping urlMapping = new URLMapping();
        urlMapping.setKey(key);
        urlMapping.setUrl(url);
        urlMapping.setProtocol(protocol);
        Boolean result_Key= keyURLRepos.exists(key);
        Boolean result_URL= keyURLRepos.exists(url);
        keyURLRepos.save(urlMapping);
        String response ="The request for addition of URLShortener :\n KEY :"+key+"\n URL :"+url+"\n is successfully processed.";
        return response;
    }

    @RequestMapping(value = "/DeleteMapping/{Key}", method = RequestMethod.GET)
    public String deleteMap(@PathVariable("Key") String key){
        Boolean result = keyURLRepos.exists(key);
        if(!result)
            return "The entry with given key is not present in the database";
        keyURLRepos.delete(key);
        return "The URL Shortener with given Key"+key+" is deleted from the database";
    }

    @RequestMapping(value = "/ListMapping/{Key}", method = RequestMethod.GET)
    public URLMapping listMapping(@PathVariable("Key") String key){
        URLMapping response= (URLMapping)keyURLRepos.findOne(key);
        return response;
    }
    @RequestMapping(value = "/ListAllMapping",method = RequestMethod.GET)
    public List<String> getAllMappings()
    {

        List<String>  result= new ArrayList<String>();
        List<URLMapping> response = new ArrayList<URLMapping>();
        response = (List<URLMapping>)keyURLRepos.findAll();
        for( URLMapping u: response)
        {
            String part="KEY:"+u.getKey()+" URL: "+u.getProtocol()+"//"+u.getUrl();
            result.add(part);
        }
        return result;
    }

    @RequestMapping(value="/redirect/{key}", method=RequestMethod.GET)
    public String redirectToURL(HttpServletResponse response, @PathVariable String key)throws Exception{
        final URLMapping url=listMapping(key);
        if(url != null){
            response.sendRedirect(url.getProtocol()+"://"+url.getUrl());
        }
        return "Redirect Function not working as needed";


    }
    private boolean isValid(String url){
        boolean valid=true;
        try{
            new URL(url);
        }
        catch(MalformedURLException e){
            valid=false;
        }
        return valid;
    }
}

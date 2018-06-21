package URLShortPack.Service;

import URLShortPack.Model.URLMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MapService {
    public String addURLMapping(String key,String url,String protocol);
    public String overwriteMap(String key,String url,String protocol);
    public String deleteMap(String key);
    public URLMapping listMapping(String key);
    public List<String> getAllMappings();
    public String redirectToURL(HttpServletResponse response, String key)throws Exception;

}

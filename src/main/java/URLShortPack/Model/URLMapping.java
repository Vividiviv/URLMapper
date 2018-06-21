package URLShortPack.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class URLMapping {
    @Id
    @Column(name="keyid")
    private String key;

    @Column(name="url")
    private String url;

    @Column(name="protocol")
    private String protocol;
    public URLMapping() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        String message= "Mapping of url:"+protocol+"//"+url+" to key"+key;
        return message;
    }

}

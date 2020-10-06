package otus.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Sources({"system:properties", "classpath:config.properties"})
public interface ServerConfig extends Mutable {

    @Key("url")
    String url();

    @Key("browser")
    String browser();
}
package play.plugins;

import helpers.elasticsearch.ES;
import play.PlayPlugin;

/**
 * @author jtremeaux
 */
public class ElasticSearchPlugin extends PlayPlugin {
    @Override
    public void onConfigurationRead() {
        ES.reset();
    }
}

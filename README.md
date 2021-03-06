[![GitHub release](https://img.shields.io/github/release/sismics/play-elasticsearch.svg?style=flat-square)](https://github.com/sismics/play-elasticsearch/releases/latest)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# play-elasticsearch plugin

This plugin adds [Elasticsearch](https://en.wikipedia.org/wiki/Elasticsearch) support to Play! Framework 1 applications.

# Features

# How to use

####  Add the dependency to your `dependencies.yml` file

```
require:
    - elasticsearch -> elasticsearch 1.1.0

repositories:
    - sismics:
        type:       http
        artifact:   "http://release.sismics.com/repo/play/[module]-[revision].zip"
        contains:
            - elasticsearch -> *

```
####  Run the `play deps` command
####  Configure elasticsearch in your `application.conf`

```
# Elasticsearch
elasticsearch.host=myelasticsearch.com
```

- Full configuration:
```
# Elasticsearch
elasticsearch.host=node1.myelasticsearch.com:9300,node2.myelasticsearch.com:9300
elasticsearch.clusterName=mycluster
```

####  Start using the client
```
TransportClient tc = ES.get();
IndexResponse response = tc.prepareIndex("twitter", "tweet", "1")
        .setSource(jsonBuilder()
                    .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                    .endObject()
                  )
        .get();
```

# License

This software is released under the terms of the Apache License, Version 2.0. See `LICENSE` for more
information or see <https://opensource.org/licenses/Apache-2.0>.

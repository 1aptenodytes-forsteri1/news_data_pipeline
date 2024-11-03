package org.newspipeline;

import reactor.core.publisher.Flux;

public interface WebsitePlugin {
    public Flux<Article> getArticles(Location location);
}

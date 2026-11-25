package tn.spring.gestioncatalogues.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VectorStoreConfig {
    @Bean
    public SimpleVectorStore vectorStoreConfig(EmbeddingModel model){
        return SimpleVectorStore.builder(model).build();
    }
}

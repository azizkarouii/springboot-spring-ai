package tn.spring.gestioncatalogues.rag;

import lombok.AllArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceIngestion {

    private VectorStore vectorStore;

    @PostConstruct
    public void ingestionDocuments()
    {
        System.out.println("----debut d'ingestion----");
        List<Document> liste = new ArrayList<>();
        liste.addAll(new TextReader("classpath:docs/garantie.txt").read());
        liste.addAll(new TextReader("classpath:docs/livraison.txt").read());
        liste.addAll(new TextReader("classpath:docs/retour.txt").read());
        liste.addAll(new PagePdfDocumentReader("classpath:docs/cgv.pdf").read());
        System.out.println("----fin d'ingestion----");
    }
}

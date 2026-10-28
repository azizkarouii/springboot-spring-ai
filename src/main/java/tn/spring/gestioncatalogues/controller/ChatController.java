package tn.spring.gestioncatalogues.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.spring.gestioncatalogues.tools.ProductTools;

@RestController
public class ChatController {
    private ChatClient chatClient;
    public ChatController(ChatClient.Builder chatclient, ChatMemory chatMemory, ProductTools productTools){
        this.chatClient = chatclient
                .defaultAdvisors(
                        a->a.advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                                .param(ChatMemory.CONVERSATION_ID, "default")
                )
                .defaultTools(productTools)
                .defaultSystem(
                        """
                                Tu es un assistant e- commerce d’elite pour la boutique Polytechnique Sousse Regles strictes que tu dois imperativement respecter :
                                - Reponds UNIQUEMENT aux questions relatives aux produits du catalogue .
                                - Utilise SYSTEMATIQUEMENT tes outils pour verifier les prix et disponibilites .
                                - Si la question concerne un autre sujet , reponds exactement ’Je ne sais pas ’
                                - Ne divulgue jamais de fausses informations inventees .
                             """
                )
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

}

package com.AI.Controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {
    private final OllamaChatModel ollamaChatModel;
    private static final String PROMPT = "What is java language?";
    public AIController(OllamaChatModel ollamaChatModel){
        this.ollamaChatModel = ollamaChatModel;
    }
    @GetMapping("/prompt")
    public String  promptResponse(){
        if (ollamaChatModel == null) {
            return "AI model is not available";
        }
        String response  = ollamaChatModel.call(PROMPT);
        return response;
    }
}

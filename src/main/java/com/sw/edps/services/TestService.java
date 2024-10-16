package com.sw.edps.services;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String getProducts() {
        return """
            {
                "pcg": "Protective coatings group",
                "iom": "Integrated order management"
            }
        """;
    }
}

package com.app.simpleBackend.entities;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtResponse {
    private String token;
    private String username;

    private String jwtToken;
    // other fields

    private JwtResponse(Builder builder) {
        this.token = builder.token;
        this.username = builder.username;
        this.jwtToken = builder.jwtToken;
        // set other fields
    }

    // getters for fields

    public static class Builder {
        private String token;
        private String username;

        private String jwtToken;
        // other fields

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        // methods for setting other fields

        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }
}

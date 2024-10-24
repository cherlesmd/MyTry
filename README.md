# MyTry
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens) ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white) ![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white) ![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
<br/><br/>

 [Frontend](https://github.com/cherlesmd/MyTry-Frontend/) for this project

 ## Quick Facts
 * Uses Springboot for minimal setup
 * Uses Mongodb with two collections
 * Uses JWT for authetication
 * Custom CORS configuration

 ## Installation and Usage
 * Requires Java 17
 * Configure application.properties file with own secret keys
 * Configure CORS in SecurityConfig
```Java
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration ccfg = new CorsConfiguration();
        ccfg.setAllowedOrigins(Arrays.asList("https://your-frontend-here.com"));
        ccfg.setAllowedMethods(Arrays.asList("*"));
        ccfg.setAllowedHeaders(Arrays.asList("*"));
        ccfg.setExposedHeaders(Arrays.asList("*"));
        ccfg.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", ccfg);
        return source;
    }
```
Clone repo then run following commands
```bash
./mvnw clean install
```
```bash
./mvnw springboot:run
```

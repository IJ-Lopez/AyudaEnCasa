/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.controllers;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // add converter suport Content-Type: 'application/x-www-form-urlencoded'
        converters.stream()
                .filter(AllEncompassingFormHttpMessageConverter.class::isInstance)
                .map(AllEncompassingFormHttpMessageConverter.class::cast)
                .findFirst()
                .ifPresent(converter -> converter.addSupportedMediaTypes(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
    }

}
    


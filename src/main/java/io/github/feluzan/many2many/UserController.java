package io.github.feluzan.many2many;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public MappingJacksonValue list(){
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("following", "followedBy");
        SimpleBeanPropertyFilter simpleBeanPropertyFilter2 = SimpleBeanPropertyFilter.serializeAllExcept("following", "followedBy");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("followingCustomFilter", simpleBeanPropertyFilter)
                .addFilter("followedByCustomFilter", simpleBeanPropertyFilter2)
                .setFailOnUnknownId(false);

        List<User> response =  userService.listAll();

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(response);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

}

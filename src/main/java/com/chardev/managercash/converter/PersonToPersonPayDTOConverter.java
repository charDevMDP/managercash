package com.chardev.managercash.converter;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


@Component
public class PersonToPersonPayDTOConverter Converter<Person, PersonPayDTO>{

private final ModelMapper modelMapper;


public PersonToPersonPayDTOConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        }

@Override
public PersonaPayDto convert(Person source) {
        return modelMapper.map(source, PersonaPayDto.class);
        }
}

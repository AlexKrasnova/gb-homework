package ru.alexkrasnova.spring.lesson2.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.alexkrasnova.spring.lesson2.repository.CompanyRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CompanyValidator implements ConstraintValidator<Company, String> {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean isValid(String company, ConstraintValidatorContext context) {

        if(companyRepository.findAll().contains(company)) {
            return true;
        }

        return false;
    }
}

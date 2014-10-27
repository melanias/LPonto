package br.com.lponto.validation.impl;

import javax.inject.Inject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.lponto.bean.Setor;
import br.com.lponto.repository.SetorRepository;
import br.com.lponto.validation.SetorValidation;

/**
 *
 * @author Phelipe Melanias
 */
public class SetorValidator implements ConstraintValidator<SetorValidation, Setor> {

    @Inject
    private SetorRepository repository;

    @Override
    public void initialize(SetorValidation constraintAnnotation) {}

    @Override
    public boolean isValid(Setor setor, ConstraintValidatorContext context) {
        if (setor == null || repository == null) {
            return true;
        }

        //Desativar a mensagem default
        context.disableDefaultConstraintViolation();

        boolean nome  = ((setor.getNome() == null)  ? true : repository.isUniqueSector(setor));
        boolean sigla = ((setor.getSigla() == null) ? true : repository.isUniqueAbbreviation(setor));

        //Nome
        if (!nome) {
            context.buildConstraintViolationWithTemplate("{setor_nome_already_exists}").addPropertyNode("nome").addConstraintViolation();
        }

        //Sigla
        if (!sigla) {
            context.buildConstraintViolationWithTemplate("{setor_sigla_already_exists}").addPropertyNode("sigla").addConstraintViolation();
        }

        return (nome && sigla);
    }
}
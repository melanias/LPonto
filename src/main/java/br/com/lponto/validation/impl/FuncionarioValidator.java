package br.com.lponto.validation.impl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.lponto.bean.Funcionario;
import br.com.lponto.repository.FuncionarioRepository;
import br.com.lponto.validation.FuncionarioValidation;

/**
 *
 * @author Phelipe Melanias
 */
public class FuncionarioValidator implements ConstraintValidator<FuncionarioValidation, Funcionario> {

    @Inject
    private FuncionarioRepository repository;

    @Override
    public void initialize(FuncionarioValidation constraintAnnotation) {}

    @Override
    public boolean isValid(Funcionario funcionario, ConstraintValidatorContext context) {
        if (funcionario == null || repository == null) {
            return true;
        }

        //Desativar a mensagem default
        context.disableDefaultConstraintViolation();

        //CPF
        boolean cpf = ((funcionario.getCpf() == null) ? true : repository.isUniqueCPF(funcionario));

        if (!cpf) {
            context.buildConstraintViolationWithTemplate("{funcionario_cpf_already_exists}").addPropertyNode("cpf").addConstraintViolation();
        }
        //CPF

        //Nome
        boolean nome = ((funcionario.getNome() == null) ? true : repository.isUniqueName(funcionario));

        if (!nome) {
            context.buildConstraintViolationWithTemplate("{funcionario_nome_already_exists}").addPropertyNode("nome").addConstraintViolation();
        }
        //Nome

        //Setor
        boolean setor;

        if (funcionario.getSetor() == null) {
            setor = true;
        } else {
            setor = funcionario.getSetor().getId() != null;

            if (!setor) {
                context.buildConstraintViolationWithTemplate("{funcionario_setor_not_selected}").addPropertyNode("setor").addConstraintViolation();
            }
        }
        //Setor

        //Senha e CheckPassword
        boolean senhaAndCheckPassword = true;

        if (funcionario.getSenha() == null && funcionario.getCheckPasswd() != null) {
            senhaAndCheckPassword = false;
            context.buildConstraintViolationWithTemplate("{funcionario_senha_not_informed}").addPropertyNode("senha").addConstraintViolation();

            if (funcionario.getCheckPasswd().length() < 6) {
                context.buildConstraintViolationWithTemplate("{funcionario_checkPasswd_incorrect_size}").addPropertyNode("checkPasswd").addConstraintViolation();
            }
        } else if (funcionario.getSenha() != null && funcionario.getCheckPasswd() == null) {
            senhaAndCheckPassword = false;
            context.buildConstraintViolationWithTemplate("{funcionario_checkPasswd_not_informed}").addPropertyNode("checkPasswd").addConstraintViolation();

            if (funcionario.getSenha().length() < 6) {
                context.buildConstraintViolationWithTemplate("{funcionario_senha_incorrect_size}").addPropertyNode("senha").addConstraintViolation();
            }

        } else if (funcionario.getSenha() != null && funcionario.getCheckPasswd() != null) {
            if (funcionario.getSenha().length() < 6 & funcionario.getCheckPasswd().length() < 6) {
                senhaAndCheckPassword = false;
                context.buildConstraintViolationWithTemplate("{funcionario_senha_incorrect_size}").addPropertyNode("senha").addConstraintViolation();
                context.buildConstraintViolationWithTemplate("{funcionario_checkPasswd_incorrect_size}").addPropertyNode("checkPasswd").addConstraintViolation();
            } else if (funcionario.getSenha().length() < 6 & funcionario.getCheckPasswd().length() > 5) {
                senhaAndCheckPassword = false;
                context.buildConstraintViolationWithTemplate("{funcionario_senha_incorrect_size}").addPropertyNode("senha").addConstraintViolation();
            } else if (funcionario.getSenha().length() > 5 & funcionario.getCheckPasswd().length() < 6) {
                senhaAndCheckPassword = false;
                context.buildConstraintViolationWithTemplate("{funcionario_checkPasswd_incorrect_size}").addPropertyNode("checkPasswd").addConstraintViolation();
            } else if (!funcionario.getSenha().equals(funcionario.getCheckPasswd())) {
                senhaAndCheckPassword = false;
                context.buildConstraintViolationWithTemplate("").addPropertyNode("senha").addConstraintViolation();
                context.buildConstraintViolationWithTemplate("{funcionario_senha_and_checkPasswd_are_different}").addPropertyNode("checkPasswd").addConstraintViolation();
            }
        }
        //Senha e CheckPassword

        return (cpf && nome && setor && senhaAndCheckPassword);
    }
}
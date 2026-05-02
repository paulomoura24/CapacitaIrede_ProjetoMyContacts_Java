package com.mycompany.mycontacts.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidadorEmailTest {

    @Test
    void deveValidarEmailValido() {
        assertTrue(ValidadorEmail.validar("usuario@exemplo.com"));
        assertTrue(ValidadorEmail.validar("nome.sobrenome@dominio.org"));
        assertTrue(ValidadorEmail.validar("contato+teste@sub.dominio.com"));
    }

    @Test
    void deveInvalidarEmailInvalido() {
        assertFalse(ValidadorEmail.validar("usuario.com"));
        assertFalse(ValidadorEmail.validar("usuario@"));
        assertFalse(ValidadorEmail.validar("@dominio.com"));
        assertFalse(ValidadorEmail.validar(""));
        assertFalse(ValidadorEmail.validar(null));
    }
}


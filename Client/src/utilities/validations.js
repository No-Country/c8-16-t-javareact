export const validations = (values) => {

    const emailRegEx =  /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
    const passwordRegEx = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])([A-Za-z\d$@$!%*?&]|[^ ]){8,15}$/;

    let errors = {};

    if (!values.name) {
        errors.name = "Ingrese un nombre"
    }

    if (!values.lastName) {
        errors.lastname = "Ingrese un Apellido"
    }

    if (!emailRegEx.test(values.email)) {
        errors.email = "Ingrese un correo electrónico válido"
    }

    if(!values.email) {
        errors.email = "Ingrese un correo electrónico"
    }

    if (!passwordRegEx.test(values.password)) {
        errors.password = "La contraseña debe tener: Minimo 8 caracteres y máximo 15 - Al menos una letra mayúscula y una letra minucula - Al menos un dígito - No espacios en blanco - Al menos 1 caracter especial"
    }
    if(!values.password) {
        errors.password = "Ingrese una contraseña"
    }

    if(values.password != values.confirmPassword) {
        errors.confirmPassword = "Las contraseñas no coinciden"
    }

    return errors;
}